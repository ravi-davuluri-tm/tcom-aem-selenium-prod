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
import com.toy.pages.LocalSpecialPage;
import com.toy.pages.RequestAQuotePage;
import com.toy.selenium.core.BaseTest;
import com.toy.utilities.Read_XLS;
import com.toy.utilities.Utilities;
import com.toy.validation.ValidationData;

@Guice
public class RAQLocalSpecialMobile extends BaseTest {

	public RAQLocalSpecialMobile() {
		mobileTest = true;
	}

//	@Test(dataProvider = "LocalSpecial", dataProviderClass = DataProvidersUs.class)
	public void RAQLocalSpecialMobileTest(RequestQuoteModel requestQuoteModel) throws Exception {
		RequestAQuotePage requestAQuotePage = PageFactory.initElements(getWebDriver(), RequestAQuotePage.class);

		requestQuoteModel.setCommentText(super.getComment("TestUs Mobile RAQ Local Specials"));
		requestQuoteModel.setCampaignCode("TMTT12640000");
		requestQuoteModel.setSiteName("mobile");

		String newUrl = getWebDriver().getCurrentUrl() + "?zipcode="+requestQuoteModel.getZipCode();
		getWebDriver().navigate().to(newUrl);
		super.reportLog("Navigate Url with zip code "+requestQuoteModel.getZipCode());

		reportLog("click on shopping tool menu");
		HomePage homePage = PageFactory.initElements(getWebDriver(), HomePage.class);
		LocalSpecialPage localSpecialPage = homePage.gotoLocalSpecialMobile();
		reportLog("Go to local special page");

		localSpecialPage.clickViewDetails(requestQuoteModel);
		requestQuoteModel.setSeriesName(Utilities.splitStringWithNewLine(localSpecialPage.getSeriesName())[0]);
		
		requestQuoteModel.setOfferDescription(localSpecialPage.getDescription());
		
		String name = localSpecialPage.getSeriesName();
		System.out.println("series Name" + name);
		requestAQuotePage = localSpecialPage.clickRequestAQuoteButton();
		reportLog("Click on view details button and click on request a quote button");
		Thread.sleep(2000);

		
		requestAQuotePage.fillRQDetailForLocalSpecial(requestQuoteModel);
		requestQuoteModel.setVendorName(requestAQuotePage.getVendorNameLocalSpecial());
		requestQuoteModel.setDealerCode(requestAQuotePage.dealerCodeLocalSpecial());
		reportLog("fill request detail " + requestQuoteModel.toString());

		// GmailAPI.delete(requestQuoteModel.getEmail(), gmailPass);
		requestAQuotePage.clickRAQLocalSpecialSubmit();
		reportLog("click on submit button");

		String message = requestAQuotePage.getMessage();
		Assert.assertEquals(message.trim(), GlobalPagesConstant.RequestSent);
		reportLog("Verify message request send successfully");
		System.out.println(requestQuoteModel.toString());

		String raqConfirmationMessage = requestAQuotePage.getConfirmationThankYouMessage();
		System.out.println(raqConfirmationMessage + "test");
		Assert.assertTrue(raqConfirmationMessage.trim().toLowerCase()
				.contains(GlobalPagesConstant.ThankYouConfirmation.toLowerCase()));
		reportLog("Verify message Thank you! A dealer will contact you soon with a quote on your new Vehicle.");

		requestAQuotePage.verifyButtons();
		reportLog("Verify 'Search vender' and 'Build your own' buttons");

		Read_XLS.writeContactListExcel(requestQuoteModel,  GlobalConstant.US);
	}

}