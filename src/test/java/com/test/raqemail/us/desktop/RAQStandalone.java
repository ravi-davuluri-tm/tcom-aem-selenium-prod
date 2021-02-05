/*
 * 
 */
package com.test.raqemail.us.desktop;

import org.testng.annotations.Test;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Guice;

import com.toy.constant.GlobalConstant;
import com.toy.constant.GlobalPagesConstant;
import com.toy.datamodel.RequestQuoteModel;
import com.toy.dataproviders.DataProvidersUs;
import com.toy.gmail.GmailAPI;
import com.toy.pages.HomePage;
import com.toy.pages.RequestAQuotePage;
import com.toy.selenium.core.BaseTest;
import com.toy.utilities.Read_XLS;
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
public class RAQStandalone extends BaseTest {

	/**
	 * Test request quote.
	 *
	 * @param requestQuoteModel the request quote model
	 * @throws Exception
	 */

	@Test(dataProvider = "Standalone", dataProviderClass = DataProvidersUs.class)
	public void RAQStandAloneTest(RequestQuoteModel requestQuoteModel) throws Exception {

		HomePage homePage = PageFactory.initElements(getWebDriver(), HomePage.class);
		RequestAQuotePage requestAQuotePage = PageFactory.initElements(getWebDriver(), RequestAQuotePage.class);

		String newUrl = getWebDriver().getCurrentUrl() + "?zipcode=" + requestQuoteModel.getZipCode();
		getWebDriver().navigate().to(newUrl);
		super.reportLog("Navigate Url with zip code " + requestQuoteModel.getZipCode());

		homePage.selectShoppingToolMenu();
		reportLog("click on shopping tool menu");
		requestQuoteModel.setCommentText(super.getComment("TestUs Desktop RAQ Standalone"));
		requestQuoteModel.setCampaignCode("twtt10570000");
		requestQuoteModel.setSiteName("website");

		requestAQuotePage = homePage.gotoRequestQuote();
		reportLog("click on request a quote menu");

		requestAQuotePage.fillRequestQuoteDetail(requestQuoteModel);
		requestQuoteModel.setVendorName(requestAQuotePage.getVendorName());

		requestQuoteModel.setDealerCode(requestAQuotePage.dealerCode());
		reportLog("fill request detail " + requestQuoteModel.toString());

		requestAQuotePage.clickSubmitButton();
		reportLog("click on submit button");

		String message = requestAQuotePage.getMessage();
		Assert.assertEquals(message.trim(), GlobalPagesConstant.RequestSent);
		reportLog("Verify message request send successfully");

		String raqConfirmationMessage = requestAQuotePage.getConfirmationThankYouMessage();
		String expectedThanksMsg = "";
		if (requestQuoteModel.getSeriesName() != null && requestQuoteModel.getSeriesName() != "")
			expectedThanksMsg = GlobalPagesConstant.ThankYouConfirmation.toLowerCase() + " "
					+ requestQuoteModel.getSeriesName().toLowerCase();
		else
			expectedThanksMsg = GlobalPagesConstant.ThankYouConfirShortMsg.toLowerCase();
		Assert.assertTrue(raqConfirmationMessage.trim().toLowerCase().contains(expectedThanksMsg),
				"Thank you message Verification failed");

		reportLog("Verify message Thank you! A dealer will contact you soon with a quote on your new Vehicle.");

		Read_XLS.writeContactListExcel(requestQuoteModel, GlobalConstant.US);

		requestAQuotePage.verifyButtons();
		reportLog("Verify 'Search Inventory' and 'Build your own' buttons");

		/*
		 * String from = "tmsusaincsaleslead@tmsusaconnect.com"; String mailContent =
		 * GmailAPI.check(requestQuoteModel.getEmail(), gmailPass, from,
		 * requestQuoteModel.getCommentText()); reportLog("Read email content");
		 * reportLog(mailContent); // GmailAPI.delete(requestQuoteModel.getEmail(),
		 * gmailPass); reportLog("Delete emails");
		 * 
		 * Assert.assertNotEquals("Not found", mailContent,
		 * "Email not recieved in 2 minutes");
		 * ValidationData.validateRAQEmailData(requestQuoteModel, mailContent,
		 * "Default");
		 * 
		 * reportLog("Verify email content successfully");
		 */
	}

	@Test(dataProvider = "Standalone", dataProviderClass = DataProvidersUs.class)
	public void RAQStandAloneTest1(RequestQuoteModel requestQuoteModel) throws Exception {

		HomePage homePage = PageFactory.initElements(getWebDriver(), HomePage.class);
		RequestAQuotePage requestAQuotePage = PageFactory.initElements(getWebDriver(), RequestAQuotePage.class);

		String newUrl = getWebDriver().getCurrentUrl();// + "?zipcode="+requestQuoteModel.getZipCode();
		getWebDriver().navigate().to(newUrl);
		super.reportLog("Navigate Url with zip code " + requestQuoteModel.getZipCode());

		homePage.selectShoppingToolMenu();
		reportLog("click on shopping tool menu");
		requestQuoteModel.setCommentText(super.getComment("TestUs Desktop RAQ Standalone"));
		requestQuoteModel.setCampaignCode("twtt10570000");
		requestQuoteModel.setSiteName("website");

		requestAQuotePage = homePage.gotoRequestQuote();
		reportLog("click on request a quote menu");

		requestAQuotePage.fillRequestQuoteDetail(requestQuoteModel);
		requestQuoteModel.setVendorName(requestAQuotePage.getVendorName());

		requestQuoteModel.setDealerCode(requestAQuotePage.dealerCode());
		reportLog("fill request detail " + requestQuoteModel.toString());

		requestAQuotePage.clickSubmitButton();
		reportLog("click on submit button");

		String message = requestAQuotePage.getMessage();
		Assert.assertEquals(message.trim(), GlobalPagesConstant.RequestSent);
		reportLog("Verify message request send successfully");

		String raqConfirmationMessage = requestAQuotePage.getConfirmationThankYouMessage();
		String expectedThanksMsg = "";
		if (requestQuoteModel.getSeriesName() != null && requestQuoteModel.getSeriesName() != "")
			expectedThanksMsg = GlobalPagesConstant.ThankYouConfirmation.toLowerCase() + " "
					+ requestQuoteModel.getSeriesName().toLowerCase();
		else
			expectedThanksMsg = GlobalPagesConstant.ThankYouConfirShortMsg.toLowerCase();
		Assert.assertTrue(raqConfirmationMessage.trim().toLowerCase().contains(expectedThanksMsg),
				"Thank you message Verification failed");

		reportLog("Verify message Thank you! A dealer will contact you soon with a quote on your new Vehicle.");

		Read_XLS.writeContactListExcel(requestQuoteModel, GlobalConstant.US);

		requestAQuotePage.verifyButtons();
		reportLog("Verify 'Search Inventory' and 'Build your own' buttons");

	}

}