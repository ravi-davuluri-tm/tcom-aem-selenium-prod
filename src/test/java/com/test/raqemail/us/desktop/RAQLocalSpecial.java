package com.test.raqemail.us.desktop;

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
public class RAQLocalSpecial extends BaseTest {

//	@Test(dataProvider = "LocalSpecial", dataProviderClass = DataProvidersUs.class)
	public void RAQLocalSpecialTest(RequestQuoteModel requestQuoteModel) throws Exception {
		RequestAQuotePage requestAQuotePage = PageFactory.initElements(getWebDriver(), RequestAQuotePage.class);

		requestQuoteModel.setCommentText(super.getComment("TestUS desktop RAQ Local Specials"));
		requestQuoteModel.setCampaignCode("twtt12870000");
		requestQuoteModel.setSiteName("website");

		String newUrl = getWebDriver().getCurrentUrl() + "?zipcode=" + requestQuoteModel.getZipCode();
		getWebDriver().navigate().to(newUrl);
		super.reportLog("Navigate Url with zip code " + requestQuoteModel.getZipCode());

		reportLog("click on shopping tool menu");
		HomePage homePage = PageFactory.initElements(getWebDriver(), HomePage.class);
		homePage.selectShoppingToolMenu();
		LocalSpecialPage localSpecialPage = homePage.gotoLocalSpecial();
		reportLog("Go to local special page");

		Thread.sleep(2000);
		localSpecialPage.clickViewDetails(requestQuoteModel);
		Thread.sleep(2000);
		requestQuoteModel.setSeriesName(Utilities.splitStringWithNewLine(localSpecialPage.getSeriesName())[0]);
		requestQuoteModel.setOfferDescription(localSpecialPage.getDescription());

		String name = localSpecialPage.getSeriesName();
		System.out.println("series Name" + name);
		requestAQuotePage = localSpecialPage.clickRequestAQuoteButton();
		reportLog("Click on view details button and click on request a quote button");
		Thread.sleep(2000);

		// System.out.println("offer --- " +
		// localSpecialPage.getOfferDescription(requestQuoteModel));
		// requestQuoteModel.setOfferDescription(localSpecialPage.getOfferDescription(requestQuoteModel));

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

		Read_XLS.writeContactListExcel(requestQuoteModel, GlobalConstant.US);

		requestAQuotePage.verifyButtons();
		reportLog("Verify 'Search vender' and 'Build your own' buttons");

	}

}