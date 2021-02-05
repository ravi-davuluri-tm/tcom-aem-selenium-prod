package com.test.us.functional;

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

public class BuildAndPrice extends BaseTest {

	private HomePage homePage;
	private  RequestAQuotePage requestAQuotePage;
	private BuildAndPricePage buildAndPricePage;

	@BeforeMethod
	public void initObj() {
		homePage = PageFactory.initElements(getWebDriver(), HomePage.class);
		requestAQuotePage = PageFactory.initElements(getWebDriver(), RequestAQuotePage.class);
	}
	
	@Test(dataProvider = "BuildAndPriceTab", dataProviderClass = DataProviderFunctional.class, priority = 1, enabled = true)
	public void BuildAndPriceCategoryNavigationTest(String[] data) throws InterruptedException {

		HomePage homePage = PageFactory.initElements(getWebDriver(), HomePage.class);
				
		RequestQuoteModel requestQuoteModel = new RequestQuoteModel();
		requestQuoteModel.setZipCode(data[0]);
		requestQuoteModel.setSeriesName(data[1]);

		String newUrl = getWebDriver().getCurrentUrl() + "?zipcode="+requestQuoteModel.getZipCode();
		getWebDriver().navigate().to(newUrl);
		reportLog("Navigate Url with zip code "+requestQuoteModel.getZipCode());

		homePage.selectShoppingToolMenu();
		buildAndPricePage = homePage.gotoBuildAndPricePage();
		reportLog("navigated build and price page");

		List<String> subMenuTabs = new ArrayList<String>(Arrays.asList(GlobalPagesConstant.CarsMinivan, GlobalPagesConstant.Trucks, GlobalPagesConstant.CrossoversSUVs, GlobalPagesConstant.Hybrids));
		buildAndPricePage.verifyCategorySubMenus(subMenuTabs);
		reportLog("Verify sub menus of build and price page");

		buildAndPricePage.verifyCategoryTitle(subMenuTabs);
		reportLog("Verify categories titles");

		buildAndPricePage.verifyCategorySubMenu(GlobalPagesConstant.CarsMinivan);
		reportLog("Verify by default " + GlobalPagesConstant.CarsMinivan + " category menu selected");

		buildAndPricePage.selectCategorySubMenu(GlobalPagesConstant.Trucks);
		buildAndPricePage.verifyCategorySubMenu(GlobalPagesConstant.Trucks);
		reportLog("Verify by default " + GlobalPagesConstant.Trucks + " category menu selected");
		
		buildAndPricePage.selectCategorySubMenu(GlobalPagesConstant.CrossoversSUVs);
		buildAndPricePage.verifyCategorySubMenu(GlobalPagesConstant.CrossoversSUVs);
		reportLog("Verify by default " + GlobalPagesConstant.CrossoversSUVs + " category menu selected");
		
		buildAndPricePage.selectCategorySubMenu(GlobalPagesConstant.Hybrids);
		buildAndPricePage.verifyCategorySubMenu(GlobalPagesConstant.Hybrids);
		reportLog("Verify by default " + GlobalPagesConstant.Hybrids + " category menu selected");
		
		buildAndPricePage.selectCategorySubMenu(GlobalPagesConstant.CarsMinivan);
		buildAndPricePage.verifyCategorySubMenu(GlobalPagesConstant.CarsMinivan);
		reportLog("Verify by default " + GlobalPagesConstant.CarsMinivan + " category menu selected");
	}
	

	@Test(dataProvider = "BuildAndPriceTab", dataProviderClass = DataProviderFunctional.class, priority = 2, enabled = true)
	public void BuildAndPriceTabNavigationTest(String[] data) throws InterruptedException {
		reportLog("click on shopping tool menu");
		RequestQuoteModel requestQuoteModel = new RequestQuoteModel();
		requestQuoteModel.setZipCode(data[0]);
		requestQuoteModel.setSeriesName(data[1]);

		String newUrl = getWebDriver().getCurrentUrl() + "?zipcode="+requestQuoteModel.getZipCode();
		getWebDriver().navigate().to(newUrl);
		reportLog("Navigate Url with zip code "+requestQuoteModel.getZipCode());

		homePage.selectShoppingToolMenu();
		buildAndPricePage = homePage.gotoBuildAndPricePage();
		reportLog("navigated build and price page");

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
		homePage = PageFactory.initElements(getWebDriver(), HomePage.class);
		requestAQuotePage = PageFactory.initElements(getWebDriver(), RequestAQuotePage.class);
		reportLog("click on shopping tool menu");
		RequestQuoteModel requestQuoteModel = new RequestQuoteModel();
		requestQuoteModel.setZipCode(data[0]);
		requestQuoteModel.setSeriesName(data[1]);

		String newUrl = getWebDriver().getCurrentUrl() + "?zipcode="+requestQuoteModel.getZipCode();
		getWebDriver().navigate().to(newUrl);
		reportLog("Navigate Url with zip code "+requestQuoteModel.getZipCode());
		
		homePage.selectShoppingToolMenu();
		buildAndPricePage = homePage.gotoBuildAndPricePage();
		reportLog("navigated build and price page");

		buildAndPricePage.selectCarBySeries(requestQuoteModel);
		reportLog("Select series from car list " + requestQuoteModel.getSeriesName());
		String modelName = buildAndPricePage.selectAndVerifyModelOrGrade();
		System.out.println(modelName);
		reportLog("Verify model select functionality");

		buildAndPricePage.selectTabByName(GlobalPagesConstant.Engines);
		reportLog("Select Engines tab");
		
		HashMap<String, String> engineDetail = buildAndPricePage.selectEngine();
		reportLog("Verify engine select functionality");

		buildAndPricePage.selectTabByName(GlobalPagesConstant.Colors);
		reportLog("Select Colors tab");

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
		reportLog("Select Interior color and verify exterior color selected");

		buildAndPricePage.selectTabByName(GlobalPagesConstant.Accessories);
		reportLog("Select Accessories tab");
		

		buildAndPricePage.verifyAccessoriesCount();
		reportLog("Verify accessories count all is equal to exterior + interior");

		String accessoryName = buildAndPricePage.selectAccessory();
		System.out.println(accessoryName);
		reportLog("Verify select functionality");

		buildAndPricePage.selectTabByName(GlobalPagesConstant.Summary);
		reportLog("Select Summary tab");
		
		buildAndPricePage.verifyModelNameOnSummary(modelName);
		String engine = buildAndPricePage.getEngineOnSummary();
		Assert.assertTrue(engine.contains(engineDetail.get("EnginePower")), "Engine power not matched on summary page");
		Assert.assertTrue(engine.contains(engineDetail.get("Gear")), "Engine Gear not matched on summary page");
		List<String> summaryData = buildAndPricePage.getListOfSummaryPageData();
		Assert.assertTrue(summaryData.contains(accessoryName), "Summary page data not contains " + accessoryName);
		reportLog("verify engine details and summay page EnginePower: "+engineDetail.get("EnginePower") + " Gear: "+engineDetail.get("Gear"));

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

		HomePage homePage = PageFactory.initElements(getWebDriver(), HomePage.class);
		reportLog("click on shopping tool menu");
		RequestQuoteModel requestQuoteModel = new RequestQuoteModel();

		requestQuoteModel.setZipCode(data[0]);
		requestQuoteModel.setSeriesName(data[1]);
		
		String newUrl = getWebDriver().getCurrentUrl() + "?zipcode="+requestQuoteModel.getZipCode();
		getWebDriver().navigate().to(newUrl);
		reportLog("Navigate Url with zip code "+requestQuoteModel.getZipCode());

		homePage.selectShoppingToolMenu();
		buildAndPricePage = homePage.gotoBuildAndPricePage();
		reportLog("navigated build and price page");

		buildAndPricePage.selectCarBySeries(requestQuoteModel);
		reportLog("Select series from car list " + requestQuoteModel.getSeriesName());
		String modelName = buildAndPricePage.selectModel();
		reportLog("Verify model select functionality");

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

		String accessoryName = buildAndPricePage.selectAccessory();
		reportLog("Verify select functionality");

		String mailTo = Configuration.readApplicationFile("MailTo");;
		String mailFrom = Configuration.readApplicationFile("MailFrom");
		String comment = "This is test mail " + Utilities.getTimeStamp();

		buildAndPricePage.clickNextButton();
		String msrpPrice = buildAndPricePage.getTotalPriceSummaryTab();
		buildAndPricePage.sendEmailYourToyota("test account", mailFrom, mailTo, comment);
		reportLog("send and verify email send message");
		
		String mailContent = GmailAPI.check(mailTo, gmailPass, "noreplies@tcom.aws.toyota.com", comment);
		System.out.println(mailContent);

		Assert.assertTrue(mailContent.contains("this email has been sent to you by <a href=\"mailto:" + mailFrom), mailFrom + " sender mail not found in received mail");
		Assert.assertTrue(mailContent.contains(requestQuoteModel.getZipCode()), requestQuoteModel.getZipCode() + " zipcode name not fonund in mail");
		Assert.assertTrue(mailContent.toLowerCase().contains(requestQuoteModel.getSeriesName().toLowerCase()), requestQuoteModel.getSeriesName() + " Series name not fonund in mail");
		Assert.assertTrue(mailContent.toLowerCase().contains(modelName.toLowerCase()), "Model name not fonund in mail");
		Assert.assertTrue(mailContent.toLowerCase().contains(modelName.toLowerCase()), "Model name not fonund in mail");
		Assert.assertTrue(mailContent.toLowerCase().contains(engineDetail.get("EnginePower").toLowerCase()), engineDetail.get("EnginePower") + " EnginePower not fonund in mail");
		Assert.assertTrue(mailContent.toLowerCase().contains(accessoryName.toLowerCase().substring(0, accessoryName.length() - 3).trim()), accessoryName + " accesories not fonund in mail");
		Assert.assertTrue(mailContent.toLowerCase().contains(msrpPrice), " MSRP price (" + msrpPrice + ") not fonund in mail");
		reportLog("Verify detais in email");
		
	}
	
	@Test(dataProvider = "BuildAndPriceTab", dataProviderClass = DataProviderFunctional.class, priority = 2, enabled = true)
	public void BuildAndPriceVerifySeriesYearInUrl(String[] data) throws InterruptedException {
		reportLog("click on shopping tool menu");
		RequestQuoteModel requestQuoteModel = new RequestQuoteModel();
		requestQuoteModel.setZipCode(data[0]);
		requestQuoteModel.setSeriesName(data[1]);

		String newUrl = getWebDriver().getCurrentUrl() + "?zipcode="+requestQuoteModel.getZipCode();
		getWebDriver().navigate().to(newUrl);
		reportLog("Navigate Url with zip code "+requestQuoteModel.getZipCode());

		homePage.selectShoppingToolMenu();
		buildAndPricePage = homePage.gotoBuildAndPricePage();
		reportLog("navigated build and price page");

		buildAndPricePage.selectCarBySeries(requestQuoteModel);
		reportLog("Select series from car list " + requestQuoteModel.getSeriesName());

		buildAndPricePage.sleepInSecond(2);
		String currentUrl = getWebDriver().getCurrentUrl();
		String year = Utilities.getSeriesYear(requestQuoteModel);
		String name = Utilities.getSeriesYear(requestQuoteModel);
		Assert.assertTrue(currentUrl.contains(year), "url not contains year "+ year);
		Assert.assertTrue(currentUrl.contains(name), "url not contain model name "+name);
		reportLog("Verifying model "+name+" and year "+year+" in url : "+currentUrl);
	}
}
