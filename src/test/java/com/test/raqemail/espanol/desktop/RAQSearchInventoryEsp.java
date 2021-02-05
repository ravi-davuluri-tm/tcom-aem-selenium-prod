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
import com.toy.pages.SearchInventoryPage;
import com.toy.selenium.core.BaseTest;
import com.toy.utilities.Read_XLS;
import com.toy.utilities.Utilities;
import com.toy.validation.ValidationData;

@Guice
public class RAQSearchInventoryEsp extends BaseTest {

	@Test(dataProvider = "SearchInventory", dataProviderClass=DataProvidersEsp.class)
	public void RAQSearchInventoryEspTest(RequestQuoteModel requestQuoteModel) throws Exception{
		
		RequestAQuotePage requestAQuotePage = PageFactory.initElements(getWebDriver(),
				RequestAQuotePage.class);		
		HomePage homePage = PageFactory.initElements(getWebDriver(), HomePage.class);
		
		requestQuoteModel.setCommentText(super.getComment("Test ESP Desktop RAQ Search And Inventory"));
		requestQuoteModel.setCampaignCode("twtt12130000");
		requestQuoteModel.setSiteName("website");
		
		homePage.selectEspanolLanguage();
		reportLog("select espanol language");
		
		String newUrl = getWebDriver().getCurrentUrl() + "?zipcode="+requestQuoteModel.getZipCode();
		getWebDriver().navigate().to(newUrl);
		super.reportLog("Navigate Url with zip code "+requestQuoteModel.getZipCode());
		
		reportLog("click on shopping tool menu");		
		homePage.selectShoppingToolMenu();
		SearchInventoryPage searchInventoryPage = homePage.gotoSearchInventoryEsp();
		reportLog("Go to search inventory page");
		
		searchInventoryPage.selectCarBySeriesEsp(requestQuoteModel);
		reportLog("Select car from search inventory page");
			
		searchInventoryPage.clickViewDetailsEsp();
		reportLog("click on view details page");
		
		requestAQuotePage.fillRQDetail(requestQuoteModel);
		requestQuoteModel.setVendorName(requestAQuotePage.getVendorName());
		requestQuoteModel.setDealerCode(requestAQuotePage.dealerCode());
		requestQuoteModel = searchInventoryPage.getPriceEsp(requestQuoteModel);
		String currentUrl = getWebDriver().getCurrentUrl();
		requestQuoteModel = Utilities.setColor(requestQuoteModel, currentUrl);
		reportLog("fill request detail " + requestQuoteModel.toString());
		
		//GmailAPI.delete(requestQuoteModel.getEmail(), gmailPass);
		requestAQuotePage.clickSubmitButton();
		reportLog("click on submit button");
				
		String message = requestAQuotePage.getMessage();
		Assert.assertEquals(message.trim(), GlobalPagesConstant.RequestSentEsp);
		reportLog("Verify message request send successfully");
			
		String raqConfirmationMessage = requestAQuotePage.getConfirmationThankYouMessage();
		String year = Utilities.getSeriesYearEsp(requestQuoteModel);
		String seriesName = Utilities.getSeriesNameEsp(requestQuoteModel);
		Assert.assertTrue(raqConfirmationMessage.trim().toLowerCase().contains(
				GlobalPagesConstant.ThankYouConfirmationEsp.toLowerCase() + " "
						+ year.toLowerCase() + " "+ seriesName.toLowerCase()));		
		reportLog("Verify message Thank you! A dealer will contact you soon with a quote on your new Vehicle.");

		Read_XLS.writeContactListExcel(requestQuoteModel, GlobalConstant.ESP);	
		
		searchInventoryPage.verifySearchInventoryButtonsAfterSubmitEsp();
		reportLog("Verify 'View Local Offers' and 'Apply for Financing your Build' buttons");
	
	
	}
}