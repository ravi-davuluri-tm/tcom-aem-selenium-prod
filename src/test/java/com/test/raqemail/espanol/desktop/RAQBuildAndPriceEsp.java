package com.test.raqemail.espanol.desktop;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

import com.toy.constant.GlobalConstant;
import com.toy.constant.GlobalPagesConstant;
import com.toy.datamodel.RequestQuoteModel;
import com.toy.dataproviders.DataProvidersEsp;
import com.toy.pages.BuildAndPricePage;
import com.toy.pages.HomePage;
import com.toy.pages.RequestAQuotePage;
import com.toy.selenium.core.BaseTest;
import com.toy.utilities.Read_XLS;
import com.toy.utilities.Utilities;

@Guice
public class RAQBuildAndPriceEsp extends BaseTest{
	
	@Test(dataProvider = "BuildAndPrice", dataProviderClass=DataProvidersEsp.class)
	public void RAQBuildAndPriceEspTest(RequestQuoteModel requestQuoteModel) throws Exception{
		
		RequestAQuotePage requestAQuotePage = PageFactory.initElements(getWebDriver(),
				RequestAQuotePage.class);		
		HomePage homePage = PageFactory.initElements(getWebDriver(), HomePage.class);
		
		requestQuoteModel.setCommentText(super.getComment("Test ESP Desktop RAQ Build & Price"));
		requestQuoteModel.setCampaignCode("twtt29500000");
		requestQuoteModel.setSiteName("website");
		
		homePage.selectEspanolLanguage();
		reportLog("select espanol language");
	
		String newUrl = getWebDriver().getCurrentUrl() + "?zipcode="+requestQuoteModel.getZipCode();
		getWebDriver().navigate().to(newUrl);
		super.reportLog("Navigate Url with zip code "+requestQuoteModel.getZipCode());
	
		homePage.selectShoppingToolMenu();
		reportLog("click on shopping tool menu");
		BuildAndPricePage buildAndPricePage = homePage.gotoBuildAndPriceEspPage();
		reportLog("Go to build and price page");
		
		buildAndPricePage.selectCarBySeriesEsp(requestQuoteModel);
		reportLog("Select series from car list "+requestQuoteModel.getSeriesName());
		
		buildAndPricePage.verifySelectedTab(GlobalPagesConstant.Models);
		buildAndPricePage.clickNextButton();
		reportLog("Select model tab");

		buildAndPricePage.verifySelectedTab(GlobalPagesConstant.Engines);
		buildAndPricePage.clickNextButton();
		reportLog("Select engien tab");

		buildAndPricePage.verifySelectedTab(GlobalPagesConstant.Colors);
		buildAndPricePage.clickNextButton();
		reportLog("Select color tab");

		buildAndPricePage.verifySelectedTab(GlobalPagesConstant.Packages);
		buildAndPricePage.clickNextButton();
		reportLog("Select package tab");

		buildAndPricePage.verifySelectedTab(GlobalPagesConstant.Accessories);
		buildAndPricePage.clickNextButton();
		reportLog("Select accessory tab");
			
		buildAndPricePage.verifySelectedTab(GlobalPagesConstant.Summary);
		requestAQuotePage = buildAndPricePage.clickRaqButton();
		reportLog("Select summary tab");
		
		requestQuoteModel = buildAndPricePage.getPriceEsp(requestQuoteModel);
		requestAQuotePage.fillRQDetail(requestQuoteModel);
		requestQuoteModel.setVendorName(requestAQuotePage.getVendorName());
		requestQuoteModel.setDealerCode(requestAQuotePage.dealerCode());
		reportLog("fill request detail " + requestQuoteModel.toString());

		String currentUrl = getWebDriver().getCurrentUrl();
		requestQuoteModel = Utilities.setColor(requestQuoteModel, currentUrl);
				
		//GmailAPI.delete(requestQuoteModel.getEmail(), gmailPass);
		requestAQuotePage.clickSubmitButton();
		reportLog("click on submit button");
		
		String message = requestAQuotePage.getMessage();
		Assert.assertEquals(message.trim(), GlobalPagesConstant.RequestSentEsp);
		reportLog("Verify message request send successfully");
		System.out.println(requestQuoteModel.toString());
		
		String raqConfirmationMessage = requestAQuotePage.getConfirmationThankYouMessage();
		String year = Utilities.getSeriesYearEsp(requestQuoteModel);
		String seriesName = Utilities.getSeriesNameEsp(requestQuoteModel);
		Assert.assertTrue(raqConfirmationMessage.trim().toLowerCase().contains(
				GlobalPagesConstant.ThankYouConfirmationEsp.toLowerCase() + " "
						+ year.toLowerCase() + " "+ seriesName.toLowerCase()));			
		reportLog("Verify message Thank you! A dealer will contact you soon with a quote on your new Vehicle.");
		
		Read_XLS.writeContactListExcel(requestQuoteModel, GlobalConstant.ESP);	
		
		requestAQuotePage.verifyBAndPButtonsAfterSubmitEsp();
		reportLog("Verify 'Search vender' and 'Continue your Build' buttons");
			
	}
}