/*
 * 
 */
package com.test.raqemail.espanol.desktop;

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

/**
 * @author apadani Test steps: MyDataProvider.class is using DataBuilder class
 *         to retrieve data from TestData.xlsx and set/get data to/from
 *         RequestQuoteModel 1. Select shopping tool and click on request a
 *         quote sub menu. 2. Enter zipcode in opened zipcode and input box and
 *         click on submit button 3. Fill request a quote form 4. Capture vendor
 *         name and id 5. Click on submit button 6. verify request submitted and
 *         verify "Search vender" and "Build your own" displayed 7. read request
 *         a quote email details and verify with expected content
 */

@Guice
public class RAQStandaloneEsp extends BaseTest {

	/**
	 * Test request quote.
	 *
	 * @param requestQuoteModel
	 *            the request quote model
	 * @throws Exception 
	 */

	@Test(dataProvider = "Standalone", dataProviderClass = DataProvidersEsp.class)
	public void RAQStandaloneEspTest(RequestQuoteModel requestQuoteModel) throws Exception {
		
		HomePage homePage = PageFactory.initElements(getWebDriver(), HomePage.class);
		RequestAQuotePage requestAQuotePage = PageFactory.initElements(getWebDriver(), RequestAQuotePage.class);
			
		homePage.selectEspanolLanguage();
		reportLog("select espanol language");
		
		String newUrl = getWebDriver().getCurrentUrl() + "?zipcode="+requestQuoteModel.getZipCode();
		getWebDriver().navigate().to(newUrl);
		super.reportLog("Navigate Url with zip code "+requestQuoteModel.getZipCode());
		
		homePage.selectShoppingToolMenu();
		reportLog("click on shopping tool menu");
		requestQuoteModel.setCommentText(super.getComment("Test ESP Desktop RAQ Standalone"));
		requestQuoteModel.setCampaignCode("TWTT12110000");
		requestQuoteModel.setSiteName("website");
		
		requestAQuotePage = homePage.gotoRequestQuoteESP();
		reportLog("click on request a quote menu");
	
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
		raqConfirmationMessage.contains(GlobalPagesConstant.ThankYouConfirmationEsp);
		System.out.println("raq : " + raqConfirmationMessage.toLowerCase());
		System.out.println(GlobalPagesConstant.ThankYouConfirmationEsp.toLowerCase());
		System.out.println(requestQuoteModel.getSeriesNameEsp().toLowerCase());
		
		Assert.assertTrue(raqConfirmationMessage.trim().toLowerCase().contains(GlobalPagesConstant.ThankYouConfirmationEsp.toLowerCase())); 
				//+ " " + requestQuoteModel.getSeriesName().toLowerCase()));
		
		reportLog("Verify message Thank you! A dealer will contact you soon with a quote on your new Vehicle.");

		reportLog("Verify RAQ confirmation grade message with Vehicle info");

		Read_XLS.writeContactListExcel(requestQuoteModel, GlobalConstant.ESP);	

		requestAQuotePage.verifyButtons();
		reportLog("Verify 'Search vender' and 'Build your own' buttons");

		/*
		String from = "tmsusaincsaleslead@tmsusaconnect.com";
		String mailContent = GmailAPI.check(requestQuoteModel.getEmail(), gmailPass, from,
				requestQuoteModel.getCommentText());
		reportLog("Read email content");
		reportLog(mailContent);
		//GmailAPI.delete(requestQuoteModel.getEmail(), gmailPass);
		//reportLog("Delete emails");

		Assert.assertNotEquals("Not found", mailContent, "Email not recieved in 2 minutes");
		ValidationData.validateRAQEmailData(requestQuoteModel, mailContent, "US");
		reportLog("Verify email content successfully"); 
		*/
	}

}
