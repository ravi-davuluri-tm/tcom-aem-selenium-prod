package com.test.raqemail.espanol.desktop;

import org.testng.annotations.Test;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Guice;

import com.toy.constant.GlobalConstant;
import com.toy.constant.GlobalPagesConstant;
import com.toy.datamodel.RequestQuoteModel;
import com.toy.dataproviders.DataProvidersEsp;
import com.toy.dataproviders.DataProvidersUs;
import com.toy.pages.HomePage;
import com.toy.pages.RequestAQuotePage;
import com.toy.selenium.core.BaseTest;
import com.toy.utilities.Read_XLS;

@Guice
public class RAQZEVEsp extends BaseTest {

	/**
	 * Test request quote.
	 *
	 * @param requestQuoteModel the request quote model
	 * @throws Exception
	 */

	@Test(dataProvider = "RAQZEVEsp", dataProviderClass = DataProvidersEsp.class)
	public void RAQZEVESPTest(RequestQuoteModel requestQuoteModel) throws Exception {

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
				"Ojo, ten en cuenta que hay muy pocas unidades de la RAV4 Prime en tu área, un concesionario se contactará contigo pronto."),
				"Thank you message Verification failed");
		reportLog("Verify message Thank you! A dealer will contact you soon with a quote on your new Vehicle.");

		Read_XLS.writeContactListExcel(requestQuoteModel, GlobalConstant.ESP);	
		/*
		 * requestAQuotePage.verifyButtons();
		 * reportLog("Verify 'Search vender' and 'Build your own' buttons");
		 */
	}

}