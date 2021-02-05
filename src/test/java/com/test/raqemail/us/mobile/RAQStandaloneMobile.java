/*
 * 
 */
package com.test.raqemail.us.mobile;

import org.testng.annotations.Test;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Guice;

import com.toy.constant.GlobalConstant;
import com.toy.constant.GlobalPagesConstant;
import com.toy.datamodel.RequestQuoteModel;
import com.toy.dataproviders.DataProvidersUs;
import com.toy.pages.HomePage;
import com.toy.pages.RequestAQuotePage;
import com.toy.selenium.core.BaseTest;
import com.toy.utilities.Read_XLS;

@Guice
public class RAQStandaloneMobile extends BaseTest {

	public RAQStandaloneMobile(){
		mobileTest = true;
	}
	
	@Test(dataProvider = "Standalone", dataProviderClass = DataProvidersUs.class)
	public void RAQStandAloneMobileTest(RequestQuoteModel requestQuoteModel) throws Exception {

		HomePage homePage = PageFactory.initElements(getWebDriver(), HomePage.class);
		RequestAQuotePage requestAQuotePage = PageFactory.initElements(getWebDriver(), RequestAQuotePage.class);
		
		String newUrl = getWebDriver().getCurrentUrl() + "?zipcode="+requestQuoteModel.getZipCode();
		getWebDriver().navigate().to(newUrl);
		homePage.waitZipUpdatedMobile(requestQuoteModel.getZipCode());
		super.reportLog("Navigate Url with zip code "+requestQuoteModel.getZipCode());
		
		requestQuoteModel.setCommentText(super.getComment("TestUs Mobile RAQ Standalone"));
		requestQuoteModel.setCampaignCode("tmtt00310000");
		requestQuoteModel.setSiteName("mobile");

		requestAQuotePage = homePage.gotoRequestQuoteMobile();
		reportLog("click on request a quote menu");
		
		requestAQuotePage.fillRequestQuoteDetail(requestQuoteModel);
		requestQuoteModel.setVendorName(requestAQuotePage.getVendorName());

		requestQuoteModel.setDealerCode(requestAQuotePage.dealerCode());
		reportLog("fill request detail " + requestQuoteModel.toString());

		//GmailAPI.delete(requestQuoteModel.getEmail(), gmailPass);
		requestAQuotePage.clickSubmitButton();
		reportLog("click on submit button");

		String message = requestAQuotePage.getMessage();
		Assert.assertEquals(message.trim(), GlobalPagesConstant.RequestSent);
		reportLog("Verify message request send successfully");

		String raqConfirmationMessage = requestAQuotePage.getConfirmationThankYouMessage();

		String expectedThanksMsg = "";
		if(requestQuoteModel.getSeriesName() != null && requestQuoteModel.getSeriesName() != "")
			expectedThanksMsg = GlobalPagesConstant.ThankYouConfirmation.toLowerCase() + " "
					+ requestQuoteModel.getSeriesName().toLowerCase();
		else
			expectedThanksMsg = GlobalPagesConstant.ThankYouConfirShortMsg.toLowerCase();
		Assert.assertTrue(
				raqConfirmationMessage.trim().toLowerCase().contains(expectedThanksMsg), "Thank you message Verification failed");
		reportLog("Verify message Thank you! A dealer will contact you soon with a quote on your new Vehicle.");

		Read_XLS.writeContactListExcel(requestQuoteModel,  GlobalConstant.US);	
		
		requestAQuotePage.verifyButtons();
		reportLog("Verify 'Search vender' and 'Build your own' buttons");		
	}

}