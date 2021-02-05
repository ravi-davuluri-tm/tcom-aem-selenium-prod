package com.test.us.mobile.functional;

import java.util.List;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.toy.datamodel.RequestQuoteModel;
import com.toy.dataproviders.DataProviderFunctional;
import com.toy.pages.BuildAndPricePage;
import com.toy.pages.HomePage;
import com.toy.pages.RequestAQuotePage;
import com.toy.selenium.core.BaseTest;
import com.toy.utilities.ArrayUtils;
import com.toy.utilities.Utilities;

public class BuildAndPriceOptionsSUV extends BaseTest {

	private HomePage homePage;
	private RequestAQuotePage requestAQuotePage;
	private BuildAndPricePage buildAndPricePage;
	RequestQuoteModel requestQuoteModel;		

	public BuildAndPriceOptionsSUV(){
		mobileTest = true;		
		requestQuoteModel = new RequestQuoteModel();
	}	
		
	@BeforeMethod
	public void initObj() {
		homePage = PageFactory.initElements(getWebDriver(), HomePage.class);
		requestAQuotePage = PageFactory.initElements(getWebDriver(), RequestAQuotePage.class);
	}
	
	@Test(dataProvider = "BuildAndPriceSUV", dataProviderClass = DataProviderFunctional.class, enabled = true)
	public void BuildAndPriceFunctionalitySUV(String[] data) throws InterruptedException {		
		
		RequestQuoteModel requestQuoteModel = new RequestQuoteModel();
		requestQuoteModel.setZipCode(data[0]);
		requestQuoteModel.setSeriesName(data[1]);

		requestAQuotePage.submitZipCodeMobile(data[0]);
		reportLog("submit zip code " + requestQuoteModel.getZipCode());

		buildAndPricePage = homePage.gotoBuildAndPriceMobilePage();
		reportLog("Go to build and price page");

		buildAndPricePage.selectCarBySeries(requestQuoteModel);
		reportLog("Select series from car list " + requestQuoteModel.getSeriesName());
		
		String modelName = buildAndPricePage.selectAndVerifyModelOrGrade();
		System.out.println(modelName);
		reportLog("Verify model select functionality");

		buildAndPricePage.clickNextButton();
		reportLog("Click on next button");
		String powertrain = buildAndPricePage.selectCabsAndBedsOrPowertrain();
		reportLog("Verify powertrin select functionality");

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
		boolean status = buildAndPricePage.isPackage();
		String packageName  = "";
		// package
		if (status) {
			packageName = buildAndPricePage.selectVerifyPackage();
			reportLog("Select package and verify " + packageName);
		}
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
		
		powertrain = buildAndPricePage.getEngineOnSummary();
		String title = powertrain.split(" with ")[0];
		Assert.assertTrue(powertrain.contains(title), title + " not matched on summary page");
		
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
		if (status) {
			Assert.assertTrue(summaryData.contains(packageName), "Summary page data not contains package " + packageName);
		}
	}

}
