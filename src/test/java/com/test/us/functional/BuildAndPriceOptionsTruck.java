package com.test.us.functional;

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

public class BuildAndPriceOptionsTruck extends BaseTest {

	private HomePage homePage;
	private RequestAQuotePage requestAQuotePage;
	private BuildAndPricePage buildAndPricePage;
	RequestQuoteModel requestQuoteModel;;

	@BeforeMethod
	public void initObj() {
		homePage = PageFactory.initElements(getWebDriver(), HomePage.class);
		requestAQuotePage = PageFactory.initElements(getWebDriver(), RequestAQuotePage.class);
	}

	@Test(dataProvider = "BuildAndPriceTrucks", dataProviderClass = DataProviderFunctional.class, priority = 1, enabled = true)
	public void BuildAndPriceGradeTest(String[] data) throws InterruptedException {
		requestQuoteModel = new RequestQuoteModel();
		requestQuoteModel.setZipCode(data[0]);
		requestQuoteModel.setSeriesName(data[1]);
		
		String newUrl = getWebDriver().getCurrentUrl() + "?zipcode="+requestQuoteModel.getZipCode();
		getWebDriver().navigate().to(newUrl);
		reportLog("Navigate Url with zip code "+requestQuoteModel.getZipCode());

		homePage.selectShoppingToolMenu();
		reportLog("click on shopping tool menu");
		buildAndPricePage = homePage.gotoBuildAndPricePage();
		reportLog("navigated build and price page");

		buildAndPricePage.selectCarBySeries(requestQuoteModel);
		reportLog("Select series from car list " + requestQuoteModel.getSeriesName());

		buildAndPricePage.selectTabByName(GlobalPagesConstant.Grade);
		reportLog("Select Grade tab");

		String modelName = buildAndPricePage.selectAndVerifyModelOrGrade();
		System.out.println(modelName);
		reportLog("Select and get geade name");

		buildAndPricePage.selectTabByName(GlobalPagesConstant.Summary);
		buildAndPricePage.verifyModelNameOnSummary(modelName);
		reportLog("Go to summay page and verify selected grade");

		buildAndPricePage.clickOnSummayModelEditIcon();
		buildAndPricePage.verifyTabChecked(GlobalPagesConstant.Grade);
		reportLog("Click on Edit button and verify Grade tab selected");

		modelName = buildAndPricePage.selectModel();
		reportLog("Select Grade name");

		buildAndPricePage.selectTabByName(GlobalPagesConstant.Summary);
		buildAndPricePage.verifyModelNameOnSummary(modelName);
		reportLog("Go to summay page and verify grade selected");
	}

	@Test(dataProvider = "BuildAndPriceTrucks", dataProviderClass = DataProviderFunctional.class, priority = 2, enabled = true)
	public void BuildAndPriceCabAndBedsTest(String[] data) throws InterruptedException {
		requestQuoteModel = new RequestQuoteModel();
		requestQuoteModel.setZipCode(data[0]);
		requestQuoteModel.setSeriesName(data[1]);
		
		String newUrl = getWebDriver().getCurrentUrl() + "?zipcode="+requestQuoteModel.getZipCode();
		getWebDriver().navigate().to(newUrl);
		reportLog("Navigate Url with zip code "+requestQuoteModel.getZipCode());

		homePage.selectShoppingToolMenu();
		reportLog("click on shopping tool menu");
		buildAndPricePage = homePage.gotoBuildAndPricePage();
		reportLog("navigated build and price page");

		buildAndPricePage.selectCarBySeries(requestQuoteModel);
		reportLog("Select series from car list " + requestQuoteModel.getSeriesName());

		buildAndPricePage.selectTabByName(GlobalPagesConstant.CabsAndBeds);
		reportLog("Select cabs and beds tab");

		String cabAndBedsName = buildAndPricePage.selectCabsAndBedsOrPowertrain();
		reportLog("Verify cab and beds select functionality");

		buildAndPricePage.selectTabByName(GlobalPagesConstant.Summary);
		String cabAndBedsNameSummary = buildAndPricePage.getCabsOnSummary();
		String updatedName = cabAndBedsName.replace(" with", ",");
		Assert.assertTrue(cabAndBedsNameSummary.contains(updatedName), updatedName + " not matched on summary page");

		buildAndPricePage.selectTabByName(GlobalPagesConstant.CabsAndBeds);
		reportLog("Again Select cabs and beds tab");

		String cabAndBedsName1 = buildAndPricePage.selectCabsAndBedsOrPowertrain();
		reportLog("Select again cabs and beds");

		buildAndPricePage.selectTabByName(GlobalPagesConstant.Summary);
		cabAndBedsNameSummary = buildAndPricePage.getCabsOnSummary();
		updatedName = cabAndBedsName1.replace(" with", ",");
		Assert.assertTrue(cabAndBedsNameSummary.contains(updatedName), updatedName + " not matched on summary page");
		reportLog("Go to summary page and verify selected beds and tab");
	}

	@Test(dataProvider = "BuildAndPriceTrucks", dataProviderClass = DataProviderFunctional.class, priority = 3, enabled = true)
	public void BuildAndPricePowerTrainTest(String[] data) throws InterruptedException {
		requestQuoteModel = new RequestQuoteModel();
		requestQuoteModel.setZipCode(data[0]);
		requestQuoteModel.setSeriesName(data[1]);
		
		String newUrl = getWebDriver().getCurrentUrl() + "?zipcode="+requestQuoteModel.getZipCode();
		getWebDriver().navigate().to(newUrl);
		reportLog("Navigate Url with zip code "+requestQuoteModel.getZipCode());
		
		homePage.selectShoppingToolMenu();
		reportLog("click on shopping tool menu");
		buildAndPricePage = homePage.gotoBuildAndPricePage();
		reportLog("navigated build and price page");

		buildAndPricePage.selectCarBySeries(requestQuoteModel);
		reportLog("Select series from car list " + requestQuoteModel.getSeriesName());

		buildAndPricePage.selectTabByName(GlobalPagesConstant.Powertrain);
		reportLog("Select powertrain tab");

		String powertrainTitle = buildAndPricePage.selectCabsAndBedsOrPowertrain();
		reportLog("Verify powertrain select functionality");

		buildAndPricePage.selectTabByName(GlobalPagesConstant.Summary);
		String powertrain = buildAndPricePage.getEngineOnSummary();
		String title = powertrainTitle.split(" with ")[0];
		Assert.assertTrue(powertrain.contains(title), title + " not matched on summary page");
		reportLog("Verify powertrain in summay page");

		buildAndPricePage.selectTabByName(GlobalPagesConstant.Powertrain);
		reportLog("Go to powertrain tab againg");

		powertrainTitle = buildAndPricePage.selectCabsAndBedsOrPowertrain();
		reportLog("slect again powertrain at powertrain tab");

		buildAndPricePage.selectTabByName(GlobalPagesConstant.Summary);
		powertrain = buildAndPricePage.getEngineOnSummary();
		title = powertrainTitle.split(" with ")[0];
		Assert.assertTrue(powertrain.contains(title), title + " not matched on summary page");
		reportLog("Verify powertrain at summay page");
	}

	@Test(dataProvider = "BuildAndPriceTrucks", dataProviderClass = DataProviderFunctional.class, priority = 4, enabled = true)
	public void BuildAndPricePackageTest(String[] data) throws InterruptedException {
		requestQuoteModel = new RequestQuoteModel();
		requestQuoteModel.setZipCode(data[0]);
		requestQuoteModel.setSeriesName(data[1]);

		String newUrl = getWebDriver().getCurrentUrl() + "?zipcode="+requestQuoteModel.getZipCode();
		getWebDriver().navigate().to(newUrl);
		reportLog("Navigate Url with zip code "+requestQuoteModel.getZipCode());

		homePage.selectShoppingToolMenu();
		reportLog("click on shopping tool menu");
		buildAndPricePage = homePage.gotoBuildAndPricePage();
		reportLog("navigated build and price page");

		buildAndPricePage.selectCarBySeries(requestQuoteModel);
		reportLog("Select series from car list " + requestQuoteModel.getSeriesName());

		buildAndPricePage.selectTabByName(GlobalPagesConstant.Packages);
		reportLog("Select package tab");

		if (buildAndPricePage.isPackage()) {
			String packageName = buildAndPricePage.selectVerifyPackage();
			reportLog("Select package and verify " + packageName);

			buildAndPricePage.selectTabByName(GlobalPagesConstant.Summary);
			reportLog("Select summay tab");

			List<String> summaryData = buildAndPricePage.getListOfSummaryPageData();
			Assert.assertTrue(summaryData.contains(packageName),
					"Summary page data not contains package " + packageName);

			buildAndPricePage.selectTabByName(GlobalPagesConstant.Packages);
			reportLog("Select package tab");

			packageName = buildAndPricePage.selectRemovePackage();
			reportLog("remove selected package");

			buildAndPricePage.selectTabByName(GlobalPagesConstant.Summary);
			reportLog("Select summay tab again");

			summaryData = buildAndPricePage.getListOfSummaryPageData();
			Assert.assertFalse(summaryData.contains(packageName), "Summary page data contains package " + packageName);
			reportLog("Verify removed package is not displaying at summary page");

			buildAndPricePage.clickOnPackageEditButton();
			reportLog("click on package edit button");

			packageName = buildAndPricePage.selectVerifyPackage();
			reportLog("Select package and verify " + packageName);

			buildAndPricePage.selectTabByName(GlobalPagesConstant.Summary);
			reportLog("Select summay tab");

			summaryData = buildAndPricePage.getListOfSummaryPageData();
			Assert.assertTrue(summaryData.contains(packageName),
					"Summary page data not contains package " + packageName);

		} else {
			reportLog("Package is not avaiable");
		}

	}

	@Test(dataProvider = "BuildAndPriceTrucks", dataProviderClass = DataProviderFunctional.class, priority = 5, enabled = true)
	public void BuildAndPriceFunctionalityTruck(String[] data) throws InterruptedException {	
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
		
		String gradeName = buildAndPricePage.selectAndVerifyModelOrGrade();
		System.out.println(gradeName);
		reportLog("Verify model select functionality");

		buildAndPricePage.clickNextButton();
		reportLog("Click on next button");
		String beds = buildAndPricePage.selectCabsAndBedsOrPowertrain();
		reportLog("Verify beds select functionality");
		
		buildAndPricePage.clickNextButton();
		reportLog("Click on next button");
		String powertrain = buildAndPricePage.selectCabsAndBedsOrPowertrain();
		reportLog("Verify powertrain select functionality");

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
		buildAndPricePage.verifyModelNameOnSummary(gradeName);
		
		powertrain = buildAndPricePage.getEngineOnSummary();
		String title = powertrain.split(" with ")[0];
		Assert.assertTrue(powertrain.contains(title), title + " not matched on summary page");

		String cabAndBedsNameSummary = buildAndPricePage.getCabsOnSummary();
		String updatedName = beds.replace(" with", ",");
		Assert.assertTrue(cabAndBedsNameSummary.contains(updatedName), updatedName + " not matched on summary page");
				
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
