package com.test.raqemail.us.mobile;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Guice;

import com.toy.constant.GlobalConstant;
import com.toy.constant.GlobalPagesConstant;
import com.toy.datamodel.RequestQuoteModel;
import com.toy.dataproviders.DataProvidersUs;
import com.toy.pages.BuildAndPricePage;
import com.toy.pages.HomePage;
import com.toy.pages.RequestAQuotePage;
import com.toy.selenium.core.BaseTest;
import com.toy.utilities.Read_XLS;
import com.toy.utilities.Utilities;

@Guice
public class RAQBuildAndPriceMobile extends BaseTest {

	public RAQBuildAndPriceMobile(){
		mobileTest = true;
	}
	
	@Test(dataProvider = "BuildAndPrice", dataProviderClass = DataProvidersUs.class)
	public void RAQBuildAndPriceMobileTest(RequestQuoteModel requestQuoteModel) throws Exception {

		RequestAQuotePage requestAQuotePage = PageFactory.initElements(getWebDriver(), RequestAQuotePage.class);
		HomePage homePage = PageFactory.initElements(getWebDriver(), HomePage.class);

		requestQuoteModel.setCommentText(super.getComment("TestUs Mobile RAQ Build & Price"));
		requestQuoteModel.setCampaignCode("tmtt03700000");
		requestQuoteModel.setSiteName("mobile");
				
		String newUrl = applicationUrl + "?zipcode="+requestQuoteModel.getZipCode();
		getWebDriver().navigate().to(newUrl);
		homePage.waitZipUpdatedMobile(requestQuoteModel.getZipCode());
		reportLog("Navigate Url with zip code "+requestQuoteModel.getZipCode());
		
		BuildAndPricePage buildAndPricePage = homePage.gotoBuildAndPriceMobilePage();
		reportLog("Go to build and price page");

		buildAndPricePage.selectCarBySeries(requestQuoteModel);
		reportLog("Select series from car list " + requestQuoteModel.getSeriesName());

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

		requestQuoteModel = buildAndPricePage.getPrice(requestQuoteModel);
		requestAQuotePage.fillRQDetail(requestQuoteModel);
		requestQuoteModel.setVendorName(requestAQuotePage.getVendorName());
		requestQuoteModel.setDealerCode(requestAQuotePage.dealerCode());
		reportLog("fill request detail " + requestQuoteModel.toString());

		String currentUrl = getWebDriver().getCurrentUrl();
		requestQuoteModel = Utilities.setColor(requestQuoteModel, currentUrl);

		requestAQuotePage.clickSubmitButton();
		reportLog("click on submit button");

		String message = requestAQuotePage.getMessage();
		AssertJUnit.assertEquals(message.trim(), GlobalPagesConstant.RequestSent);
		reportLog("Verify message request send successfully");
		System.out.println(requestQuoteModel.toString());

		String raqConfirmationMessage = requestAQuotePage.getConfirmationThankYouMessage();
		System.out.println(raqConfirmationMessage);
		System.out.println(requestQuoteModel.getSeriesName());

		Assert.assertTrue(raqConfirmationMessage.trim().toLowerCase().contains(GlobalPagesConstant.ThankYouConfirmation.toLowerCase() 
				+ " " + requestQuoteModel.getSeriesName().toLowerCase()),
				"Thank you message Verification failed");
		reportLog("Verify message Thank you! A dealer will contact you soon with a quote on your new Vehicle.");
		System.out.println(requestQuoteModel.toString());

		Read_XLS.writeContactListExcel(requestQuoteModel,  GlobalConstant.US);	
		
		requestAQuotePage.verifyBAndPButtonsAfterSubmit();
		reportLog("Verify 'Search vender' and 'Continue your Build' buttons");
	}
}