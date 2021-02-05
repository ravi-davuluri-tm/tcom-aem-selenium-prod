package com.test.raqemail.us.desktop;

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
public class RAQZEV extends BaseTest {

	/**
	 * Test request quote.
	 *
	 * @param requestQuoteModel the request quote model
	 * @throws Exception
	 */

	@Test(dataProvider = "RAQZEV", dataProviderClass = DataProvidersUs.class)
	public void RAQZEVTest(RequestQuoteModel requestQuoteModel) throws Exception {

		HomePage homePage = PageFactory.initElements(getWebDriver(), HomePage.class);
		RequestAQuotePage requestAQuotePage = PageFactory.initElements(getWebDriver(), RequestAQuotePage.class);

		String newUrl = getWebDriver().getCurrentUrl() + "?zipcode=" + requestQuoteModel.getZipCode();
		getWebDriver().navigate().to(newUrl);
		reportLog("Navigate Url with zip code " + requestQuoteModel.getZipCode());

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

		/*
		 * String message = requestAQuotePage.getMessage();
		 * Assert.assertEquals(message.trim(), GlobalPagesConstant.RequestSent);
		 * reportLog("Verify message request send successfully");
		 */
		String raqConfirmationMessage = requestAQuotePage.getRAQZEVConfirmationMessage();
		System.out.println(raqConfirmationMessage);
		// Assert.assertTrue(raqConfirmationMessage.trim()
		// .contains("Thank you!"), "Thank you message Verification failed");
		Assert.assertTrue(raqConfirmationMessage.trim().contains(
				"Please note that inventory for RAV4 Prime is extremely limited in your area, but a dealer will contact you soon."),
				"Thank you message Verification failed");
		reportLog("Verify message Thank you! A dealer will contact you soon with a quote on your new Vehicle.");

		Read_XLS.writeContactListExcel(requestQuoteModel, GlobalConstant.US);
		/*
		 * requestAQuotePage.verifyButtons();
		 * reportLog("Verify 'Search vender' and 'Build your own' buttons");
		 */
	}

}