package com.test.us.functional;

import java.util.List;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.toy.datamodel.RequestQuoteModel;
import com.toy.pages.HomePage;
import com.toy.pages.SearchInventoryPage;
import com.toy.selenium.core.BaseTest;

public class SearchInventoryDriveTrain extends BaseTest {
	
	private SearchInventoryPage searchInventoryPage ;
	String vehicleName = "2020 Camry";
	String zipCode = "97001";
	
	@BeforeMethod
	public void setupSearhIventory() {
						
		HomePage homePage = PageFactory.initElements(getWebDriver(),
				HomePage.class);				
		String newUrl = getWebDriver().getCurrentUrl() + "?zipcode="+zipCode;
		getWebDriver().navigate().to(newUrl);
		super.reportLog("Navigate Url with zip code "+zipCode);		
		
		homePage.selectShoppingToolMenu();	
		searchInventoryPage = homePage.gotoSearchInventory();
		super.reportLog("click on shopping tool menu and navigate search inventory page");
		
		RequestQuoteModel raqModel = new RequestQuoteModel();
		raqModel.setSeriesName(vehicleName);
		
		searchInventoryPage.selectCarBySeries(raqModel);
		super.reportLog("Select first vehicle");
	}	
	

	@Test 
	public void testVerifyDriveTrain() {	
		String name = "FWD";
		String name1 = "AWD";
		searchInventoryPage.selectDriveTrain(name);
		super.reportLog("Selected Drive train filter from left panel");
		
		List<String> enginesName = searchInventoryPage.getListOfVehicleEngineName();
		super.reportLog("Get all engines name from vehicle list");
		
		for(String engineName : enginesName ) {
			Assert.assertTrue(engineName.contains(name), "Selected Vechile engine name "+engineName+ " not contans  "+name);
		}
		super.reportLog("Verify vehicle contains selected Drive Train");
		
		searchInventoryPage.clearFilter();
		super.reportLog("clear filter");
		
		searchInventoryPage.selectDriveTrain(name1);	
		searchInventoryPage.sleepInSecond(3);
		super.reportLog("Selected Drive train filter from left panel");
		
		enginesName = searchInventoryPage.getListOfVehicleEngineName();
		super.reportLog("Get all engines name from vehicle list");
		System.out.println(enginesName.toString());
		
		for(String engineName : enginesName ) {
			Assert.assertTrue(engineName.contains(name1), "Selected Vechile engine name "+engineName+ " not contans  "+name1);
		}
		super.reportLog("Verify vehicle contains selected Drive Train");
	}

}