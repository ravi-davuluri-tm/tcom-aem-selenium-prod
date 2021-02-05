package com.test.us.mobile.functional;

import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.toy.constant.GlobalPagesConstant;
import com.toy.datamodel.RequestQuoteModel;
import com.toy.dataproviders.DataProviderFunctional;
import com.toy.pages.BuildAndPricePage;
import com.toy.pages.HomePage;
import com.toy.pages.RequestAQuotePage;
import com.toy.selenium.core.BaseTest;
import com.toy.utilities.ArrayUtils;
import com.toy.utilities.Utilities;

public class BuildAndPriceOptionsCarMobile extends BaseTest {

	private HomePage homePage;
	private RequestAQuotePage requestAQuotePage;
	RequestQuoteModel requestQuoteModel;	
	
	public BuildAndPriceOptionsCarMobile(){
		mobileTest = true;
	}
		
	@BeforeMethod
	public void initObj() {
		homePage = PageFactory.initElements(getWebDriver(), HomePage.class);
		requestAQuotePage = PageFactory.initElements(getWebDriver(), RequestAQuotePage.class);
	}

	@Test(dataProvider = "BuildAndPrice", dataProviderClass = DataProviderFunctional.class, priority = 1, enabled = true)
	public void BuildAndPriceModelTest(String[] data) throws InterruptedException {
		requestQuoteModel = new RequestQuoteModel();
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
		buildAndPricePage.selectTabByName(GlobalPagesConstant.Summary);
		buildAndPricePage.verifyModelNameOnSummary(modelName);
		
		buildAndPricePage.clickOnSummayModelEditIcon();
		buildAndPricePage.verifyTabChecked(GlobalPagesConstant.Models);
		modelName = buildAndPricePage.selectModel();
		
		buildAndPricePage.selectTabByName(GlobalPagesConstant.Summary);
		buildAndPricePage.verifyModelNameOnSummary(modelName);
	}
	
	@Test(dataProvider = "BuildAndPrice", dataProviderClass = DataProviderFunctional.class, priority = 2, enabled = true)
	public void BuildAndPriceEnginesTest(String[] data) throws InterruptedException {
		requestQuoteModel = new RequestQuoteModel();
		requestQuoteModel.setZipCode(data[0]);
		requestQuoteModel.setSeriesName(data[1]);
		
		requestAQuotePage.submitZipCodeMobile(data[0]);
		reportLog("submit zip code " + requestQuoteModel.getZipCode());

		BuildAndPricePage buildAndPricePage = homePage.gotoBuildAndPriceMobilePage();
		reportLog("Go to build and price page");

		buildAndPricePage.selectCarBySeries(requestQuoteModel);
		reportLog("Select series from car list " + requestQuoteModel.getSeriesName());

		buildAndPricePage.selectTabByName(GlobalPagesConstant.Engines);
		reportLog("Select engine tab");
		HashMap<String, String> engineDetail = buildAndPricePage.selectEngine();
		reportLog("Verify engine select functionality");

		buildAndPricePage.selectTabByName(GlobalPagesConstant.Summary);
		String engine = buildAndPricePage.getEngineOnSummary();
		Assert.assertTrue(engine.contains(engineDetail.get("EnginePower")), "Engine power not matched on summary page");
		Assert.assertTrue(engine.contains(engineDetail.get("Gear")), "Engine Gear not matched on summary page");
	}

	@Test(dataProvider = "BuildAndPrice", dataProviderClass = DataProviderFunctional.class, priority = 3, enabled = true)
	public void BuildAndPriceColorsTest(String[] data) throws InterruptedException {
		requestQuoteModel = new RequestQuoteModel();
		requestQuoteModel.setZipCode(data[0]);
		requestQuoteModel.setSeriesName(data[1]);
		
		requestAQuotePage.submitZipCodeMobile(data[0]);
		reportLog("submit zip code " + requestQuoteModel.getZipCode());

		BuildAndPricePage buildAndPricePage = homePage.gotoBuildAndPriceMobilePage();
		reportLog("Go to build and price page");
		
		buildAndPricePage.selectCarBySeries(requestQuoteModel);
		reportLog("Select series from car list " + requestQuoteModel.getSeriesName());

		buildAndPricePage.selectTabByName(GlobalPagesConstant.Colors);
		reportLog("Select color tab");
		
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
		reportLog("Select interior color and verify color selected");		
		buildAndPricePage.selectTabByName(GlobalPagesConstant.Summary);
		reportLog("Select Summary tab");
		
		List<String> summaryData = buildAndPricePage.getListOfSummaryPageData();
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
	
	@Test(dataProvider = "BuildAndPrice", dataProviderClass = DataProviderFunctional.class, priority = 5, enabled = true)
	public void BuildAndPriceAccessoriesTest(String[] data) throws InterruptedException {
		requestQuoteModel = new RequestQuoteModel();
		requestQuoteModel.setZipCode(data[0]);
		requestQuoteModel.setSeriesName(data[1]);
		
		requestAQuotePage.submitZipCodeMobile(data[0]);
		reportLog("submit zip code " + requestQuoteModel.getZipCode());

		BuildAndPricePage buildAndPricePage = homePage.gotoBuildAndPriceMobilePage();
		reportLog("Go to build and price page");
		
		buildAndPricePage.selectCarBySeries(requestQuoteModel);
		reportLog("Select series from car list " + requestQuoteModel.getSeriesName());

		buildAndPricePage.selectTabByName(GlobalPagesConstant.Accessories);
		reportLog("Select Accessories tab");

		buildAndPricePage.verifyAccessoriesCount();
		reportLog("Verify accessories count all is equal to exterior + interior");

		String accessoryName = buildAndPricePage.selectAccessory();
		System.out.println(accessoryName);
		reportLog("Verify select functionality");

		buildAndPricePage.selectTabByName(GlobalPagesConstant.Summary);
		List<String> summaryData = buildAndPricePage.getListOfSummaryPageData();
		Assert.assertTrue(summaryData.contains(accessoryName), "Summary page data not contains " + accessoryName);
		reportLog("Verify select accessories at summary tab");
		
		buildAndPricePage.clickOnSummayAccessoryEditIcon();
		reportLog("Click on accessories Edit button at summary tab");
		
		buildAndPricePage.verifyTabChecked(GlobalPagesConstant.Accessories);
		reportLog("Verify selected Accessories tab");
		
		accessoryName = buildAndPricePage.selectAccessoryOnly();
		System.out.println(accessoryName);
		reportLog("Select again accessory from accessories tab");
		
		buildAndPricePage.selectTabByName(GlobalPagesConstant.Summary);
		summaryData = buildAndPricePage.getListOfSummaryPageData();
		Assert.assertTrue(summaryData.contains(accessoryName), "Summary page data not contains " + accessoryName);
		reportLog("Verify selected accessories at summary tab");
	}	
}
