package com.test.us.mobile.functional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.toy.constant.GlobalPagesConstant;
import com.toy.datamodel.RequestQuoteModel;
import com.toy.dataproviders.DataProviderFunctional;
import com.toy.gmail.GmailAPI;
import com.toy.pages.BuildAndPricePage;
import com.toy.pages.HomePage;
import com.toy.pages.RequestAQuotePage;
import com.toy.selenium.core.BaseTest;
import com.toy.selenium.core.Configuration;
import com.toy.utilities.ArrayUtils;
import com.toy.utilities.Utilities;

public class BuildAndPriceMobile extends BaseTest {

	private HomePage homePage;
	private  RequestAQuotePage requestAQuotePage;
	public BuildAndPriceMobile(){
		mobileTest = true;
	}
	
	@BeforeMethod
	public void initObj() {
		homePage = PageFactory.initElements(getWebDriver(), HomePage.class);
		requestAQuotePage = PageFactory.initElements(getWebDriver(), RequestAQuotePage.class);
	}
	
	@Test(dataProvider = "BuildAndPriceTab", dataProviderClass = DataProviderFunctional.class, priority = 1, enabled = true)
	public void BuildAndPriceCategoryNavigationTest(String[] data) throws InterruptedException {
		RequestQuoteModel requestQuoteModel = new RequestQuoteModel();
		requestQuoteModel.setZipCode(data[0]);
		requestQuoteModel.setSeriesName(data[1]);

		requestAQuotePage.submitZipCodeMobile(data[0]);
		reportLog("sumit zip code " + requestQuoteModel.getZipCode());

		BuildAndPricePage buildAndPricePage = homePage.gotoBuildAndPriceMobilePage();
		reportLog("Go to build and price page");

		List<String> subMenuTabs = new ArrayList<String>(Arrays.asList(GlobalPagesConstant.CarsMinivan, GlobalPagesConstant.Trucks, GlobalPagesConstant.CrossoversSUVs, GlobalPagesConstant.Hybrids));
		buildAndPricePage.verifyCategorySubMenus(subMenuTabs);
		reportLog("Verify sub menus of build and price page");

		buildAndPricePage.verifyCategoryTitle(subMenuTabs);
		reportLog("Verify categories titles");

		buildAndPricePage.verifyCategorySubMenu(GlobalPagesConstant.CarsMinivan);
		reportLog("Verify by default " + GlobalPagesConstant.CarsMinivan + " category menu selected");

		buildAndPricePage.selectCategorySubMenu(GlobalPagesConstant.Trucks);
		buildAndPricePage.verifyCategorySubMenu(GlobalPagesConstant.Trucks);
		buildAndPricePage.selectCategorySubMenu(GlobalPagesConstant.CrossoversSUVs);
		buildAndPricePage.verifyCategorySubMenu(GlobalPagesConstant.CrossoversSUVs);
		buildAndPricePage.selectCategorySubMenu(GlobalPagesConstant.Hybrids);
		buildAndPricePage.verifyCategorySubMenu(GlobalPagesConstant.Hybrids);
		buildAndPricePage.selectCategorySubMenu(GlobalPagesConstant.CarsMinivan);
		buildAndPricePage.verifyCategorySubMenu(GlobalPagesConstant.CarsMinivan);
		reportLog("Navigate category sub menu and verify it selected");
	}
	
	@Test(dataProvider = "BuildAndPriceTab", dataProviderClass = DataProviderFunctional.class, priority = 2, enabled = true)
	public void BuildAndPriceTabNavigationTest(String[] data) throws InterruptedException {
		reportLog("click on shopping tool menu");
		RequestQuoteModel requestQuoteModel = new RequestQuoteModel();
		requestQuoteModel.setZipCode(data[0]);
		requestQuoteModel.setSeriesName(data[1]);

		requestAQuotePage.submitZipCodeMobile(data[0]);
		reportLog("sumit zip code " + requestQuoteModel.getZipCode());

		BuildAndPricePage buildAndPricePage = homePage.gotoBuildAndPriceMobilePage();
		reportLog("Go to build and price page");
		
		buildAndPricePage.selectCarBySeries(requestQuoteModel);
		reportLog("Select series from car list " + requestQuoteModel.getSeriesName());
		String[] tabs = data[2].split(",");
		reportLog("Verify checked mark of selected sub menus");
		for (String tab : tabs) {
			buildAndPricePage.clickNextButton();
			buildAndPricePage.verifyTabChecked(tab);
			reportLog("click on next button and verify " + tab + " is checked");
		}
	}
	
	@Test(dataProvider = "BuildAndPrice", dataProviderClass = DataProviderFunctional.class, priority = 1, enabled = true)
	public void BuildAndPriceFunctionalityTest(String[] data) throws InterruptedException {

		RequestQuoteModel requestQuoteModel = new RequestQuoteModel();
		requestQuoteModel.setZipCode(data[0]);
		requestQuoteModel.setSeriesName(data[1]);

		requestAQuotePage.submitZipCodeMobile(data[0]);
		reportLog("sumit zip code " + requestQuoteModel.getZipCode());

		BuildAndPricePage buildAndPricePage = homePage.gotoBuildAndPriceMobilePage();
		reportLog("Go to build and price page");
		
		buildAndPricePage.selectCarBySeries(requestQuoteModel);
		reportLog("Select series from car list " + requestQuoteModel.getSeriesName());
		String modelName = buildAndPricePage.selectAndVerifyModelOrGrade();
		System.out.println(modelName);
		reportLog("Verify model select functionality");

		buildAndPricePage.clickNextButton();
		reportLog("Click on next button");
		HashMap<String, String> engineDetail = buildAndPricePage.selectEngine();
		reportLog("Verify engine select functionality");

		buildAndPricePage.clickNextButton();
		reportLog("Click on next button");

		String extColorName = buildAndPricePage.selectExteriorColor();
		String extSelectedColor = buildAndPricePage.getSelectedExteriorColor();
		Assert.assertEquals(Utilities.getCharactersFromString(extColorName).toLowerCase(), 
				Utilities.getCharactersFromString(extSelectedColor).toLowerCase());
		reportLog("Select exterior color and verify exterior color selected");
		
		String intColorName = buildAndPricePage.selectInteriorColor();
		if(!intColorName.equals("No color")) {
			String intSelectedColor = buildAndPricePage.getSelectedInteriorColor();
			Assert.assertEquals(Utilities.getCharactersFromString(intColorName).toLowerCase(), 
					Utilities.getCharactersFromString(intSelectedColor).toLowerCase());
		}
		
		buildAndPricePage.clickNextButton();
		reportLog("Click on next button");

		buildAndPricePage.clickNextButton();
		reportLog("Click on next button");

		buildAndPricePage.verifyAccessoriesCount();
		reportLog("Verify accessories count all is equal to exterior + interior");

		String accessoryName = buildAndPricePage.selectAccessory();
		System.out.println(accessoryName);
		reportLog("Verify select functionality");

		buildAndPricePage.clickNextButton();
		reportLog("Click on next button");
		buildAndPricePage.verifyModelNameOnSummary(modelName);
		String engine = buildAndPricePage.getEngineOnSummary();
		Assert.assertTrue(engine.contains(engineDetail.get("EnginePower")), "Engine power not matched on summary page");
		Assert.assertTrue(engine.contains(engineDetail.get("Gear")), "Engine Gear not matched on summary page");
		List<String> summaryData = buildAndPricePage.getListOfSummaryPageData();
		Assert.assertTrue(summaryData.contains(accessoryName), "Summary page data not contains " + accessoryName);
		
		extColorName = Utilities.removeNumberFromString(extColorName);		
		Assert.assertTrue(ArrayUtils.isArrayListDataContains(summaryData, extColorName), 
				"Summary page data not contains " + extColorName + " exterior color");
		if(!intColorName.equals("No color")) {	
			intColorName = Utilities.removeNumberFromString(intColorName);			
			Assert.assertTrue(ArrayUtils.isArrayListDataContains(summaryData, intColorName), 
					"Summary page data not contains " + intColorName + " interior color");			
		}
		reportLog("Verify selected color at summay tab");
	}
	
	@Test(dataProvider = "BuildAndPriceEmail", dataProviderClass = DataProviderFunctional.class, priority = 4, enabled = true)
	public void BuildAndPriceEmailYourToyotaTest(String[] data) throws Exception {
	RequestQuoteModel requestQuoteModel = new RequestQuoteModel();		
		requestQuoteModel.setZipCode(data[0]);
		requestQuoteModel.setSeriesName(data[1]);
	
		requestAQuotePage.submitZipCodeMobile(data[0]);
		reportLog("sumit zip code " + requestQuoteModel.getZipCode());

		BuildAndPricePage buildAndPricePage = homePage.gotoBuildAndPriceMobilePage();
		reportLog("Go to build and price page");

		buildAndPricePage.selectCarBySeries(requestQuoteModel);
		reportLog("Select series from car list " + requestQuoteModel.getSeriesName());
		String modelName = buildAndPricePage.selectModel();
		reportLog("Verify model select functionality");
		System.out.println("Model Name"+modelName);

		buildAndPricePage.clickNextButton();
		reportLog("Click on next button");
		HashMap<String, String> engineDetail = buildAndPricePage.selectEngine();
		reportLog("Verify engine select functionality");

		buildAndPricePage.clickNextButton();
		reportLog("Click on next button");

		buildAndPricePage.clickNextButton();
		reportLog("Click on next button");

		buildAndPricePage.clickNextButton();
		reportLog("Click on next button");

		String accessoryName = buildAndPricePage.selectAccessoryOnly();
		reportLog("Verify select functionality");

		String mailTo = Configuration.readApplicationFile("MailTo");;
		String mailFrom = Configuration.readApplicationFile("Mailfrom");
		String comment = "This is test mail " + Utilities.getTimeStamp();

		buildAndPricePage.clickNextButton();
		String msrpPrice = buildAndPricePage.getTotalPriceSummaryTab();
		buildAndPricePage.sendEmailYourToyota("test account", mailFrom, mailTo, comment);

		String mailContent = GmailAPI.check(mailTo, gmailPass, "noreplies@tcom.aws.toyota.com", comment);
		System.out.println(mailContent);

		Assert.assertTrue(mailContent.contains("this email has been sent to you by <a href=\"mailto:" + mailFrom), mailFrom + " sender mail not found in received mail");
		Assert.assertTrue(mailContent.contains(requestQuoteModel.getZipCode()), requestQuoteModel.getZipCode() + " zipcode name not fonund in mail");
		Assert.assertTrue(mailContent.toLowerCase().contains(requestQuoteModel.getSeriesName().toLowerCase()), requestQuoteModel.getSeriesName() + " Series name not fonund in mail");
		System.out.println(modelName.toLowerCase());
		Assert.assertTrue(mailContent.toLowerCase().contains(modelName.toLowerCase()), "Model name not fonund in mail");
		Assert.assertTrue(mailContent.toLowerCase().contains(engineDetail.get("EnginePower").toLowerCase()), engineDetail.get("EnginePower") + " EnginePower not fonund in mail");
		Assert.assertTrue(mailContent.toLowerCase().contains(accessoryName.toLowerCase().substring(0, accessoryName.length() - 3).trim()), accessoryName + " accesories not fonund in mail");
		System.out.println(modelName.toLowerCase());
		Assert.assertTrue(mailContent.toLowerCase().contains(msrpPrice), " MSRP price (" + msrpPrice + ") not fonund in mail");
	}
}
