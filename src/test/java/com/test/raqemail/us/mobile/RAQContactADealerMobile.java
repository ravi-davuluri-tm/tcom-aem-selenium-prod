package com.test.raqemail.us.mobile;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

import com.toy.constant.GlobalConstant;
import com.toy.constant.GlobalPagesConstant;
import com.toy.datamodel.RequestQuoteModel;
import com.toy.dataproviders.DataProvidersUs;
import com.toy.gmail.GmailAPI;
import com.toy.pages.HomePage;
import com.toy.pages.RequestAQuotePage;
import com.toy.pages.VehiclePage;
import com.toy.selenium.core.BaseTest;
import com.toy.utilities.Read_XLS;
import com.toy.validation.ValidationData;

@Guice
public class RAQContactADealerMobile extends BaseTest {

	public RAQContactADealerMobile(){
		mobileTest = true;
	}
	
	@Test(dataProvider = "Vehicle", dataProviderClass = DataProvidersUs.class)
	public void RAQContactADealerMobileTest(RequestQuoteModel requestQuoteModel) throws Exception {
		RequestAQuotePage requestAQuotePage = PageFactory.initElements(getWebDriver(), RequestAQuotePage.class);

		requestQuoteModel.setCommentText(super.getComment("TestUs Mobile RAQ Vehicles"));
		requestQuoteModel.setCampaignCode("TMTT00310000");
		requestQuoteModel.setSiteName("mobile");
		
		String newUrl = getWebDriver().getCurrentUrl() + "?zipcode="+requestQuoteModel.getZipCode();
		getWebDriver().navigate().to(newUrl);
		super.reportLog("Navigate Url with zip code "+requestQuoteModel.getZipCode());

		HomePage homePage = PageFactory.initElements(getWebDriver(), HomePage.class);	
		homePage.waitZipUpdatedMobile(requestQuoteModel.getZipCode());
		reportLog("click on shopping tool menu");
		
		VehiclePage vehiclePage = homePage.gotoSelectVehicleMobile("Crossovers & SUVs");
		reportLog("Go to vehicle and select menu");

		vehiclePage.selectModelVehicle(requestQuoteModel);
		reportLog("Select vehicle " + requestQuoteModel.getSeriesName());
		
		 vehiclePage.selectGalaryInteriorView();
		requestAQuotePage = vehiclePage.clickRQAButton();
		reportLog("Click on view details button and click on request a quote button");
		
		//requestQuoteModel.setSeriesName(requestQuoteModel.getSeriesName().toUpperCase());
		requestAQuotePage.fillRequestQuoteDetail(requestQuoteModel);
		requestQuoteModel.setVendorName(requestAQuotePage.getVendorName());
		requestQuoteModel.setDealerCode(requestAQuotePage.dealerCode());
		reportLog("fill request detail " + requestQuoteModel.toString());

		requestAQuotePage.clickSubmitButton();
		reportLog("click on submit button");

		String message = requestAQuotePage.getMessage();
		Assert.assertEquals(message.trim(), GlobalPagesConstant.RequestSent);
		reportLog("Verify message request send successfully");
		System.out.println(requestQuoteModel.toString());

		String raqConfirmationMessage = requestAQuotePage.getConfirmationThankYouMessage();
		String expectedThanksMsg = "";
		if(requestQuoteModel.getSeriesName() != null && requestQuoteModel.getSeriesName() != "")
			expectedThanksMsg = GlobalPagesConstant.ThankYouConfirmation.toLowerCase() + " "
					+ requestQuoteModel.getSeriesName().toLowerCase();
		else
			expectedThanksMsg = GlobalPagesConstant.ThankYouConfirShortMsg.toLowerCase();
		Assert.assertTrue(
				raqConfirmationMessage.trim().toLowerCase()
						.contains(expectedThanksMsg), "Thank you message Verification failed");
		reportLog("Verify message Thank you! A dealer will contact you soon with a quote on your new Vehicle.");

		requestAQuotePage.verifyButtons();
		reportLog("Verify 'Search vender' and 'Build your own' buttons");

		Read_XLS.writeContactListExcel(requestQuoteModel,  GlobalConstant.US);
	}

}