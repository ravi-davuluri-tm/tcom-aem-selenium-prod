/*
 * 
 */
package com.test.raqemail.espanol.mobile;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

import com.toy.constant.GlobalConstant;
import com.toy.constant.GlobalPagesConstant;
import com.toy.datamodel.RequestQuoteModel;
import com.toy.dataproviders.DataProvidersEsp;
import com.toy.gmail.GmailAPI;
import com.toy.pages.HomePage;
import com.toy.pages.RequestAQuotePage;
import com.toy.selenium.core.BaseTest;
import com.toy.utilities.Read_XLS;
import com.toy.utilities.Utilities;
import com.toy.validation.ValidationData;

@Guice
public class RAQStandaloneEspMobile extends BaseTest {
	
	public RAQStandaloneEspMobile(){
		mobileTest = true;
	}
	
	@Test(dataProvider = "Standalone", dataProviderClass = DataProvidersEsp.class)
	public void RAQStandaloneEspMobileTest(RequestQuoteModel requestQuoteModel) throws Exception {
		
		HomePage homePage = PageFactory.initElements(getWebDriver(), HomePage.class);
		RequestAQuotePage requestAQuotePage = PageFactory.initElements(getWebDriver(), RequestAQuotePage.class);
		requestQuoteModel.setCommentText(super.getComment("Test ESP Mobile RAQ Standalone"));
		requestQuoteModel.setCampaignCode("tmtt00340000");
		requestQuoteModel.setSiteName("mobile");
		
		String newUrl = getWebDriver().getCurrentUrl() + "?zipcode="+requestQuoteModel.getZipCode();
		getWebDriver().navigate().to(newUrl);
		super.reportLog("Navigate Url with zip code "+requestQuoteModel.getZipCode());
		
		homePage.waitZipUpdatedMobile(requestQuoteModel.getZipCode());
		homePage.selectEspanolLanguageMobile();
		reportLog("select espanol language");
		
		homePage.gotoRequestQuoteMobileEsp();
		reportLog("click on request a quote menu from shopping tool menu");
	
		requestAQuotePage.fillRequestQuoteDetail(requestQuoteModel);
		requestQuoteModel.setVendorName(requestAQuotePage.getVendorName());

		requestQuoteModel.setDealerCode(requestAQuotePage.dealerCode());
		reportLog("fill request detail " + requestQuoteModel.toString());

		//GmailAPI.delete(requestQuoteModel.getEmail(), gmailPass);
		requestAQuotePage.clickSubmitButton();
		reportLog("click on submit button");

		String message = requestAQuotePage.getMessage();
		Assert.assertEquals(message.trim(), GlobalPagesConstant.RequestSentEsp);
		reportLog("Verify message request send successfully");

		String raqConfirmationMessage = requestAQuotePage.getConfirmationThankYouMessage();
		
		String expectedThanksMsg = "";
		if(requestQuoteModel.getSeriesName() != null && requestQuoteModel.getSeriesName() != "")
			expectedThanksMsg = GlobalPagesConstant.ThankYouConfirmationEsp.toLowerCase() + " "
					+ requestQuoteModel.getSeriesName().toLowerCase();
		else
			expectedThanksMsg = GlobalPagesConstant.ThankYouConfirShortMsgEsp.toLowerCase();
		System.out.println("actual-- " + raqConfirmationMessage.trim().toLowerCase());
		System.out.println("expected " + expectedThanksMsg);
		raqConfirmationMessage.trim().contains(expectedThanksMsg);
		Assert.assertTrue(raqConfirmationMessage.trim().toLowerCase().contains(expectedThanksMsg), "Thank you message Verification failed");	
		reportLog("Verify message Thank you! A dealer will contact you soon with a quote on your new Vehicle.");

		//Assert.assertEquals(raqConfirmationGrade.trim(),
				//requestQuoteModel.getSeriesName().trim() + " " + requestQuoteModel.getModelName().trim());

		requestAQuotePage.verifyButtons();
		reportLog("Verify 'Search vender' and 'Build your own' buttons");

		Read_XLS.writeContactListExcel(requestQuoteModel, GlobalConstant.ESP);	
		/*
		String from = "tmsusaincsaleslead@tmsusaconnect.com";
		String mailContent = GmailAPI.check(requestQuoteModel.getEmail(), gmailPass, from,
				requestQuoteModel.getCommentText());
		reportLog("Read email content");
		reportLog(mailContent);
		//GmailAPI.delete(requestQuoteModel.getEmail(), gmailPass);
		//reportLog("Delete emails");

		Assert.assertNotEquals("Not found", mailContent, "Email not recieved in 2 minutes");
		ValidationData.validateRAQEmailData(requestQuoteModel, mailContent, "Dafault");
		reportLog("Verify email content successfully"); 
		*/
	}

}
