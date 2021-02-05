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
import com.toy.pages.RequestAQuotePage;
import com.toy.pages.SearchInventoryPage;
import com.toy.selenium.core.BaseTest;
import com.toy.utilities.Read_XLS;
import com.toy.utilities.Utilities;
import com.toy.validation.ValidationData;

@Guice
public class RAQSearchInventory extends BaseTest {

	@Test(dataProvider = "SearchInventory", dataProviderClass=DataProvidersUs.class)
	public void RAQSearchInventoryTest(RequestQuoteModel requestQuoteModel) throws Exception{
		
		RequestAQuotePage requestAQuotePage = PageFactory.initElements(getWebDriver(),
				RequestAQuotePage.class);		
		
		requestQuoteModel.setCommentText(super.getComment("TestUs desktop Search Inventory"));
		requestQuoteModel.setCampaignCode("TWTT10810000");
		requestQuoteModel.setSiteName("website");
		
		String newUrl = getWebDriver().getCurrentUrl() + "?zipcode="+requestQuoteModel.getZipCode();
		getWebDriver().navigate().to(newUrl);
		super.reportLog("Navigate Url with zip code "+requestQuoteModel.getZipCode());

		
		reportLog("click on shopping tool menu");
		HomePage homePage = PageFactory.initElements(getWebDriver(), HomePage.class);
		homePage.selectShoppingToolMenu();
		
		
		SearchInventoryPage searchInventoryPage = homePage.gotoSearchInventory();
		reportLog("Go to search inventory page");
		
		searchInventoryPage.selectCarBySeries(requestQuoteModel);
		reportLog("Select car from search inventory page");

		searchInventoryPage.clickViewDetails();
		reportLog("click on view details page");		
		
		requestAQuotePage.fillRQDetail(requestQuoteModel);
		requestQuoteModel.setVendorName(requestAQuotePage.getVendorName());
		requestQuoteModel.setDealerCode(requestAQuotePage.dealerCode());
		//requestQuoteModel.setTrim(requestAQuotePage.getTrim());
		requestQuoteModel = searchInventoryPage.getPrice(requestQuoteModel);
		String currentUrl = getWebDriver().getCurrentUrl();
		requestQuoteModel = Utilities.setColor(requestQuoteModel, currentUrl);
		reportLog("fill request detail " + requestQuoteModel.toString());
		
		//GmailAPI.delete(requestQuoteModel.getEmail(), gmailPass);
		requestAQuotePage.clickSubmitButton();
		reportLog("click on submit button");
		
		
		String message = requestAQuotePage.getMessage();
		Assert.assertEquals(message.trim(), GlobalPagesConstant.RequestSent);
		reportLog("Verify message request send successfully");
			
		String raqConfirmationMessage = requestAQuotePage.getConfirmationThankYouMessage();
		System.out.println(raqConfirmationMessage);
		System.out.println(GlobalPagesConstant.ThankYouConfirmation.toLowerCase() + " "
						+ requestQuoteModel.getSeriesName().toLowerCase());
		
		Assert.assertTrue(raqConfirmationMessage.trim().toLowerCase().contains(
				GlobalPagesConstant.ThankYouConfirmation.toLowerCase() + " "
						+ requestQuoteModel.getSeriesName().toLowerCase()));	
		System.out.println(requestQuoteModel.getSeriesName());
		reportLog("Verify message Thank you! A dealer will contact you soon with a quote on your new Vehicle.");

		Read_XLS.writeContactListExcel(requestQuoteModel,  GlobalConstant.US);
		
		searchInventoryPage.verifySearchInventoryButtonsAfterSubmit();
		reportLog("Verify 'View Local Offers' and 'Apply for Financing your Build' buttons");
		
		
		/*
		String from = "tmsusaincsaleslead@tmsusaconnect.com";
		String mailContent = GmailAPI.check(requestQuoteModel.getEmail(), gmailPass, from,
				requestQuoteModel.getCommentText());
		reportLog("Read email content");
		reportLog(mailContent);
		//GmailAPI.delete(requestQuoteModel.getEmail(), gmailPass);
		//reportLog("Delete emails");
		System.out.println(requestQuoteModel.getTrim());
		Assert.assertNotEquals("Not found", mailContent, "Email not recieved in 2 minutes");
		ValidationData.validateOtherRAQTextEmailData(requestQuoteModel, mailContent, "US");
		reportLog("Verify email content successfully"); 
		
		*/
	}
}
