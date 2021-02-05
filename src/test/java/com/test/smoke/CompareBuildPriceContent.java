package com.test.smoke;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.toy.constant.GlobalConstant;
import com.toy.constant.GlobalPagesConstant;
import com.toy.datamodel.RequestQuoteModel;
import com.toy.datamodel.StringModel;
import com.toy.dataproviders.DataProviderFunctional;
import com.toy.pages.BuildAndPricePage;
import com.toy.pages.HomePage;
import com.toy.selenium.core.BaseTest;
import com.toy.selenium.core.Configuration;
import com.toy.utilities.Read_XLS;


public class CompareBuildPriceContent extends BaseTest {

	BuildAndPricePage buildAndPricePage;
	
	@Test(dataProvider = "BuildAndPrice", dataProviderClass = DataProviderFunctional.class, priority = 2, enabled = true)
	public void CompareBuildPriceCarContentTest(String[] data) throws Exception {
		reportLog("click on shopping tool menu");
		RequestQuoteModel requestQuoteModel = new RequestQuoteModel();
		homePage = PageFactory.initElements(getWebDriver(), HomePage.class);
		requestQuoteModel.setZipCode(data[0]);
		requestQuoteModel.setSeriesName(data[1]);

		String newUrlProd = applicationUrl + "?zipcode="+requestQuoteModel.getZipCode();
		getWebDriver().navigate().to(newUrlProd);
		reportLog("Navigate Url with zip code "+requestQuoteModel.getZipCode());

		homePage.selectShoppingToolMenu();
		buildAndPricePage = homePage.gotoBuildAndPricePage();
		reportLog("navigated build and price page");

		buildAndPricePage.selectCarBySeries(requestQuoteModel);
		reportLog("Select series from car list " + requestQuoteModel.getSeriesName());
			
		String updatedurl = getWebDriver().getCurrentUrl() + "?removeEnsighten=true&removeDigitalGarage=true&removeAdrum=true";
		getWebDriver().navigate().to(updatedurl);
		
		List<StringModel> listGradeProd = buildAndPricePage.captureBuildAndPriceTabText();
		reportLog("Select engine tab and capture all model values");
		
		List<StringModel> listEngineProd = new ArrayList<StringModel>();
		if(data[2].equalsIgnoreCase("Minivan") ){
			listEngineProd =buildAndPricePage.captureBuildAndPriceTabText(GlobalPagesConstant.Models, 
					GlobalPagesConstant.Configuration);	
		}else {
			 listEngineProd =buildAndPricePage.captureBuildAndPriceTabText(GlobalPagesConstant.Models, 
						GlobalPagesConstant.Engines);	
		}
		reportLog("Select engine tab and capture all engine values");
		
		String newUrlTest = Configuration.readApplicationFile("TestUrl") + "?zipcode="+requestQuoteModel.getZipCode();		
		getWebDriver().navigate().to(newUrlTest);
		reportLog("Navigate Url with zip code "+requestQuoteModel.getZipCode());

		homePage.selectShoppingToolMenu();
		buildAndPricePage = homePage.gotoBuildAndPricePage();
		reportLog("navigated build and price page");

		buildAndPricePage.selectCarBySeries(requestQuoteModel);
		reportLog("Select series from car list " + requestQuoteModel.getSeriesName());
		
		List<StringModel> listGradeTest = buildAndPricePage.captureBuildAndPriceTabText();
		reportLog("Select engine tab and capture all model values");
		
		getWebDriver().navigate().refresh();
		
		List<StringModel> listEngineTest = new ArrayList<StringModel>();
		if(data[2].equalsIgnoreCase("Minivan") ){
			listEngineTest =buildAndPricePage.captureBuildAndPriceTabText(GlobalPagesConstant.Models, 
					GlobalPagesConstant.Configuration);	
		}else {
			 listEngineTest =buildAndPricePage.captureBuildAndPriceTabText(GlobalPagesConstant.Models, 
						GlobalPagesConstant.Engines);	
		}
		reportLog("Select engine tab and capture all engine values");
		
		Read_XLS.writeContentInExcel(requestQuoteModel.getSeriesName(), "Models Content", GlobalConstant.CarContentSheet);
		Read_XLS.writeContentInExcel(listGradeProd, listGradeTest, GlobalConstant.CarContentSheet);
		
		Read_XLS.writeContentInExcel(requestQuoteModel.getSeriesName(), "Engine content", GlobalConstant.CarContentSheet);
		Read_XLS.writeContentInExcel(listEngineProd, listEngineTest, GlobalConstant.CarContentSheet);		
		
		Assert.assertEquals(listGradeProd.toString(), listGradeTest.toString());
		Assert.assertEquals(listEngineProd.toString(), listEngineTest.toString());
		
	}
	
	@Test(dataProvider = "BuildAndPriceSUV", dataProviderClass = DataProviderFunctional.class, priority = 2, enabled = true)
	public void CompareBuildPriceSUVContentTest(String[] data) throws Exception {
		reportLog("click on shopping tool menu");
		RequestQuoteModel requestQuoteModel = new RequestQuoteModel();
		homePage = PageFactory.initElements(getWebDriver(), HomePage.class);
		requestQuoteModel.setZipCode(data[0]);
		requestQuoteModel.setSeriesName(data[1]);

		String newUrlProd = applicationUrl + "?zipcode="+requestQuoteModel.getZipCode();
		getWebDriver().navigate().to(newUrlProd);
		reportLog("Navigate Url with zip code "+requestQuoteModel.getZipCode());

		homePage.selectShoppingToolMenu();
		buildAndPricePage = homePage.gotoBuildAndPricePage();
		reportLog("navigated build and price page");

		buildAndPricePage.selectCarBySeries(requestQuoteModel);
		reportLog("Select series from car list " + requestQuoteModel.getSeriesName());
		
		String updatedurl = getWebDriver().getCurrentUrl() + "?removeEnsighten=true&removeDigitalGarage=true&removeAdrum=true";
		getWebDriver().navigate().to(updatedurl);
		
		List<StringModel> listGradeProd = buildAndPricePage.captureBuildAndPriceTabText();
		reportLog("Select engine tab and capture all model values");
				
		getWebDriver().navigate().refresh();
		List<StringModel> listPowertrainDev = buildAndPricePage.captureBuildAndPriceTabText(GlobalPagesConstant.Models, 
				GlobalPagesConstant.Powertrain);	
		reportLog("Select powertrain tab and capture all engine values");
		
		String newUrlTest = Configuration.readApplicationFile("TestUrl") + "?zipcode="+requestQuoteModel.getZipCode();		
		getWebDriver().navigate().to(newUrlTest);
		reportLog("Navigate Url with zip code "+requestQuoteModel.getZipCode());

		homePage.selectShoppingToolMenu();
		buildAndPricePage = homePage.gotoBuildAndPricePage();
		reportLog("navigated build and price page");

		buildAndPricePage.selectCarBySeries(requestQuoteModel);
		reportLog("Select series from car list " + requestQuoteModel.getSeriesName());
		
		List<StringModel> listGradeTest = buildAndPricePage.captureBuildAndPriceTabText();
		reportLog("Select engine tab and capture all model values");
		
		getWebDriver().navigate().refresh();
		List<StringModel> listPowertrainTest =  buildAndPricePage.captureBuildAndPriceTabText(GlobalPagesConstant.Models, 
				GlobalPagesConstant.Powertrain);	
		reportLog("Select powertrain tab and capture all engine values");
		
		Read_XLS.writeContentInExcel(requestQuoteModel.getSeriesName(), "Models Content", GlobalConstant.SUVContentSheet);
		Read_XLS.writeContentInExcel(listGradeProd, listGradeTest, GlobalConstant.SUVContentSheet);
		
		Read_XLS.writeContentInExcel(requestQuoteModel.getSeriesName(), "Powertrain content", GlobalConstant.SUVContentSheet);
		Read_XLS.writeContentInExcel(listPowertrainDev, listPowertrainTest, GlobalConstant.SUVContentSheet);		
		
		Assert.assertEquals(listGradeProd.toString(), listGradeTest.toString());
		Assert.assertEquals(listPowertrainDev.toString(), listPowertrainTest.toString());
		
	}
	
	@Test(dataProvider = "BuildAndPriceTrucks", dataProviderClass = DataProviderFunctional.class, priority = 2, enabled = true)
	public void CompareBuildPriceTruckContentTest(String[] data) throws Exception {
		reportLog("click on shopping tool menu");
		RequestQuoteModel requestQuoteModel = new RequestQuoteModel();
		homePage = PageFactory.initElements(getWebDriver(), HomePage.class);
		requestQuoteModel.setZipCode(data[0]);
		requestQuoteModel.setSeriesName(data[1]);

		String newUrlProd = applicationUrl + "?zipcode="+requestQuoteModel.getZipCode();
		getWebDriver().navigate().to(newUrlProd);
		reportLog("Navigate Url with zip code "+requestQuoteModel.getZipCode());

		homePage.selectShoppingToolMenu();
		buildAndPricePage = homePage.gotoBuildAndPricePage();
		reportLog("navigated build and price page");

		buildAndPricePage.selectCarBySeries(requestQuoteModel);
		reportLog("Select series from car list " + requestQuoteModel.getSeriesName());
				
		String updatedurl = getWebDriver().getCurrentUrl() + "?removeEnsighten=true&removeDigitalGarage=true&removeAdrum=true";
		getWebDriver().navigate().to(updatedurl);
		
		List<StringModel> listGradeProd = buildAndPricePage.captureBuildAndPriceTabText();
		reportLog("Select Grade tab and capture all model values");
		
		getWebDriver().navigate().refresh();
		
		List<StringModel> listCabsAndBedsProd = buildAndPricePage.captureBuildAndPriceTabText(GlobalPagesConstant.Grade, 
				GlobalPagesConstant.CabsAndBeds, GlobalPagesConstant.Powertrain);	
		reportLog("Select cabs and beds tab and powertrain, capture all values");
		
		//getWebDriver().navigate().refresh();
		//List<StringModel> listPowertrainDev = buildAndPricePage.captureBuildAndPriceTabText(GlobalPagesConstant.Grade, 
				//GlobalPagesConstant.Powertrain);	
		//reportLog("Select powertrain tab and capture all engine values");
		
		String newUrlTest = Configuration.readApplicationFile("TestUrl") + "?zipcode="+requestQuoteModel.getZipCode();		
		getWebDriver().navigate().to(newUrlTest);
		reportLog("Navigate Url with zip code "+requestQuoteModel.getZipCode());

		homePage.selectShoppingToolMenu();
		buildAndPricePage = homePage.gotoBuildAndPricePage();
		reportLog("navigated build and price page");

		buildAndPricePage.selectCarBySeries(requestQuoteModel);
		reportLog("Select series from car list " + requestQuoteModel.getSeriesName());
		
		List<StringModel> listGradeTest = buildAndPricePage.captureBuildAndPriceTabText();
		reportLog("Select engine tab and capture all model values");
		
		getWebDriver().navigate().refresh();
		List<StringModel> listCabsAndBedsTest = buildAndPricePage.captureBuildAndPriceTabText(GlobalPagesConstant.Grade, 
				GlobalPagesConstant.CabsAndBeds, GlobalPagesConstant.Powertrain);
		reportLog("Select cabs and beds tab and capture all engine values");
		
		//getWebDriver().navigate().refresh();
		//List<StringModel> listPowertrainTest =  buildAndPricePage.captureBuildAndPriceTabText(GlobalPagesConstant.Grade, 
			//	GlobalPagesConstant.Powertrain);	
		//reportLog("Select powertrain tab and capture all engine values");
		Read_XLS.writeContentInExcel(requestQuoteModel.getSeriesName(), "Grade Content", GlobalConstant.TrucksContentSheet);
		Read_XLS.writeContentInExcel(listGradeProd, listGradeTest, GlobalConstant.TrucksContentSheet);
		
		Read_XLS.writeContentInExcel(requestQuoteModel.getSeriesName(), "CabsAndBeds and PowerTrain content", GlobalConstant.TrucksContentSheet);
		Read_XLS.writeContentInExcel(listCabsAndBedsProd, listCabsAndBedsTest, GlobalConstant.TrucksContentSheet);		
		
		Assert.assertEquals(listGradeProd.toString(), listGradeTest.toString());
		Assert.assertEquals(listCabsAndBedsProd.toString(), listCabsAndBedsTest.toString());
			
	}
	
	
}