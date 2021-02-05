package com.test.us.functional;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.toy.constant.GlobalPagesConstant;
import com.toy.datamodel.RequestQuoteModel;
import com.toy.dataproviders.DataProviderFunctional;
import com.toy.pages.BuildAndPricePage;
import com.toy.pages.HomePage;
import com.toy.pages.SavesPage;
import com.toy.pages.SearchInventoryPage;
import com.toy.selenium.core.BaseTest;
import com.toy.utilities.Utilities;

public class GXPSmartPath extends BaseTest {

	//https://www.toyota.com/ToyotaSite/rest/dealerLocator/locateDealers?brandId=1&setRegion=false&zipCode=30303
	
	String zipCode = "63141";
	String zipCode2 = "08648";
	String smartPathZipCode2 = "98026";
	
	@BeforeMethod
	public void setup() {
		homePage = PageFactory.initElements(getWebDriver(), HomePage.class);
		getWebDriver().manage().deleteAllCookies();
	}
	
	@Test(dataProvider = "SmartPathModal", dataProviderClass = DataProviderFunctional.class, priority = 1, enabled = true)
	public void testSmartPathModal(String[] data) {
		String newUrl = getWebDriver().getCurrentUrl() + "?zipcode="+data[0];
		getWebDriver().navigate().to(newUrl);
		super.reportLog("Navigated url "+newUrl);
		  
		if(data[1].equalsIgnoreCase("true")) {
			String modelTitle = homePage.getModelTitleText();
			Assert.assertEquals(modelTitle.trim(), "Select Your Dealer");
			super.reportLog("Verify MultTDA modal popup title Select Your Dealer");
			 
			homePage.selectDealerByNameFromModelPopup(data[2]);
			super.reportLog("Select first dealer from popup");
		}
		
		if(data[3].equalsIgnoreCase("true"))
		{		 
			homePage.verifyAndCloseSmartPopUp();
			super.reportLog("Verify and close smartpath popup window");
			
			 Assert.assertTrue(homePage.isProfileImage(), "Profile image not present");
			super.reportLog("Verify profile image contains person icon "+data[0]);
			
			homePage.clickOnProfileIcon();
			int saveCount = homePage.getSavesCount();
			Assert.assertEquals(saveCount, 0);
			super.reportLog("Verify saved count to zero");
		}
		else {
			Assert.assertFalse(homePage.isProfileImage(), "Profile image present");	
			super.reportLog("Verify profile image not displayed for zip "+data[0]);
		}
	}
		
	@Test(priority=2)
	public void testGXPBuildAndPrice() {
		HomePage homePage = PageFactory.initElements(getWebDriver(), HomePage.class);
		getWebDriver().manage().deleteAllCookies();
		
		String newUrl = getWebDriver().getCurrentUrl() + "?zipcode="+zipCode;
		getWebDriver().navigate().to(newUrl);
		super.reportLog("Navigated url "+newUrl);
		
		homePage.selectDealerFromModelPopUp();
		super.reportLog("Select first dealer from popup");

		//homePage.verifyAndCloseSmartPopUp();
		super.reportLog("Verify and close smartpath popup window");
		
		homePage.selectShoppingToolMenu();
		BuildAndPricePage buildAndPricePage = homePage.gotoBuildAndPricePage();
		super.reportLog("Go to build and price page");	
		
		buildAndPricePage.selectFirstCar();
		super.reportLog("Select first car");
		
		buildAndPricePage.selectTabByName(GlobalPagesConstant.Summary);	
		super.reportLog("Select summay tab");
		
		String seriesName = buildAndPricePage.getSeriesTitle();
		buildAndPricePage.clickOnHeartButton();
		super.reportLog("Click on heart button");
		System.out.println(homePage.getProfileImage());	
		
		homePage.clickOnProfileIcon();
		int saveCount = homePage.getSavesCount();
		Assert.assertEquals(saveCount, 1);
		super.reportLog("Verify build saved");
		
		SavesPage savesPage = homePage.openSaveList();
		savesPage.verifyHeader("Saved Builds");
		super.reportLog("Verify saved build header text");
	
		savesPage.verifySavedVehicle(seriesName);
		super.reportLog("Verify vehicle trim for saved build");
		
		savesPage.removeSaveBuild();
		super.reportLog("Remove Saved build");
		
		homePage.clickOnProfileIcon();
		saveCount = homePage.getSavesCount();
		Assert.assertEquals(saveCount, 0);
		super.reportLog("Verify saved count after removing saved build");
	}
	
	@Test(priority=3)
	public void testGXPChangeZipLocationModal() throws InterruptedException  {
		getWebDriver().manage().deleteAllCookies();	
		String newUrl = getWebDriver().getCurrentUrl() + "?zipcode="+zipCode;
		getWebDriver().navigate().to(newUrl);
		super.reportLog("Navigated url "+newUrl);
		
		homePage.selectDealerFromModelPopUp();
		super.reportLog("Select first dealer from multiTDA model");
		
		if(homePage.isSmartPath() ) {
			//homePage.verifyAndCloseSmartPopUp();
			super.reportLog("Verify and close smartpath popup window");
		}
		
		homePage.selectShoppingToolMenu();
		BuildAndPricePage buildAndPricePage = homePage.gotoBuildAndPricePage();
		super.reportLog("Go to build and price page");	
		
		buildAndPricePage.selectFirstCar();
		super.reportLog("Select first car");
		
		buildAndPricePage.selectTabByName(GlobalPagesConstant.Summary);	
		super.reportLog("Select summary tab");
		
		buildAndPricePage.clickOnHeartButton();
		super.reportLog("Click on heart button");
		
		homePage.submitZipCodeMobile(zipCode2);
		homePage.sleepInSecond(2);
		super.reportLog("Change zip code");	
		
		homePage.verifyChangeLocationPopup();
		homePage.clickOnUpdateLocation();
		super.reportLog("Verify and click on update location");	
				
		String defaultZipCode = homePage.getZipCode();
		Assert.assertEquals(defaultZipCode.trim(), zipCode2);
		super.reportLog("Verify zip code changed");
	
		homePage.submitZipCodeMobile(zipCode);		
		super.reportLog("User reverts to their previous zip code");	
		
		homePage.selectDealerFromModelPopUp();
		super.reportLog("Select first dealer from multiTDA model");
				
		homePage.clickOnProfileIcon();
		int saveCount = homePage.getSavesCount();
		Assert.assertEquals(saveCount, 1);
		super.reportLog("Verify previously saved item(s) will reappear in their saves");		
	}
	
	@Test(priority=4)
	public void testGXPOtherUrlSearchInventory() throws InterruptedException  {
		RequestQuoteModel raqModel = new RequestQuoteModel();
		raqModel.setSeriesName("2020 Camry");
		getWebDriver().manage().deleteAllCookies();
		String newUrl = getWebDriver().getCurrentUrl() + "?zipcode="+zipCode;
		getWebDriver().navigate().to(newUrl);
		super.reportLog("Navigated url "+newUrl);
		
		String dealerName = homePage.getDealerName();
		homePage.selectDealerFromModelPopUp();
		super.reportLog("Select first dealer from popup");
/*
		if(homePage.isSmartPath() ) {
			homePage.verifyAndCloseSmartPopUp();
			super.reportLog("Verify and close smartpath popup window");
		}
		*/
		homePage.selectShoppingToolMenu();
		SearchInventoryPage searchInventoryPage = homePage.gotoSearchInventory();
		//searchInventoryPage.selectCarBySeries(raqModel);
		//super.reportLog("Go to search inventory page and select  "+raqModel.getSeriesName());	
		
		//String dealerNameOfSearchInventory = searchInventoryPage.getDealerName();
		String currentUrl = getWebDriver().getCurrentUrl();
		Assert.assertTrue(currentUrl.contains("dealerinventory"), "Url not contains dealerinventory");
		String seriesName = Utilities.getSeriesName(raqModel);	
	//	Assert.assertTrue(currentUrl.toLowerCase().contains(seriesName.toLowerCase()), "Series name not contanin in URL");
	//	Assert.assertTrue(currentUrl.toLowerCase().contains("zipcode="+zipCode), "SmartPath zip code "+zipCode+" not contains in url");		
		super.reportLog("Verified url contains dealerinventory, series name, zip code");	
		
		//Assert.assertEquals(dealerNameOfSearchInventory.trim(), dealerName.trim());
		//super.reportLog("Verified delaer name on search inventory page");
		
		String defaultZipCode = homePage.getZipCode();
		Assert.assertEquals(defaultZipCode.trim(), zipCode);
		super.reportLog("Verify smart path zip code updated");
		
		Cookie dealerCodeCookie = getWebDriver().manage().getCookieNamed("tcom-dealer-code");
		Cookie ugzipcodeCookie = getWebDriver().manage().getCookieNamed("ugzipcode");
		Assert.assertTrue(currentUrl.toLowerCase().contains(dealerCodeCookie.getValue().toLowerCase()), "Delaer code of cookie not contains in URL");
		Assert.assertTrue(ugzipcodeCookie.getValue().toLowerCase().contains(zipCode), "Zip code not contains in cookie ugzipcode");
	
		homePage.submitZipCodeMobile(zipCode2);
		super.reportLog("Change zip code with non smart path zipcode ");	
	
		homePage.selectShoppingToolMenu();
		searchInventoryPage = homePage.gotoSearchInventory();

		homePage.sleepInSecond(3);
		currentUrl = getWebDriver().getCurrentUrl();
		String year = Utilities.getSeriesYear(raqModel);	
		Assert.assertTrue(currentUrl.contains("search-inventory"), "Url not contains search-inventory");	
	//	Assert.assertTrue(currentUrl.toLowerCase().contains(year.toLowerCase()), "Series Year not contanin in URL");
	//	Assert.assertTrue(currentUrl.toLowerCase().contains(seriesName.toLowerCase()), "Series name not contanin in URL");
		super.reportLog("Verified url contains dealerinventory, series name, zip code");
		
		defaultZipCode = homePage.getZipCode();
		Assert.assertEquals(defaultZipCode.trim(), zipCode2);
		super.reportLog("Verify zip code changed");
			
		homePage.submitZipCodeMobile(smartPathZipCode2);
		super.reportLog("Change zip code with non smart path zipcode ");
		
		dealerName = homePage.getDealerName();
		homePage.selectDealerFromModelPopUp();
		homePage.sleepInSecond(4);
		super.reportLog("Select first dealer from popup");
		
		
		//https://test1.tcom.aws.toyota.com/dealerinventory?dealerCd=24022&zipcode=63141
			
		currentUrl = getWebDriver().getCurrentUrl();
		System.out.println(currentUrl);
		//System.out.println(seriesName);
		//dealerNameOfSearchInventory = searchInventoryPage.getDealerName();
		Assert.assertTrue(currentUrl.contains("dealerinventory"), "Url not contains dealerinventory");		
		//Assert.assertTrue(currentUrl.toLowerCase().contains(seriesName.toLowerCase()), "Series name not contanin in URL");
		Assert.assertTrue(currentUrl.toLowerCase().contains("zipcode="+smartPathZipCode2), "SmartPath zip code "+smartPathZipCode2+" not contains in url");
		super.reportLog("Verified url contains dealerinventory, series name, zip code");
		
		//Assert.assertEquals(dealerNameOfSearchInventory.trim(), dealerName.trim());
		//super.reportLog("Verified delaer name on search inventory page");
		
		dealerCodeCookie = getWebDriver().manage().getCookieNamed("tcom-dealer-code");
		ugzipcodeCookie = getWebDriver().manage().getCookieNamed("ugzipcode");
		Assert.assertTrue(currentUrl.toLowerCase().contains(dealerCodeCookie.getValue().toLowerCase()), "Delaer code of cookie not contains in URL");
		Assert.assertTrue(ugzipcodeCookie.getValue().toLowerCase().contains(smartPathZipCode2), "Zip code not contains in cookie ugzipcode");
			
		defaultZipCode = homePage.getZipCode();
		Assert.assertEquals(defaultZipCode.trim(), smartPathZipCode2);
		super.reportLog("Verify smart path zip code updated");	
	}

	//@Test(priority=5)
	public void testGXPSmartUrlSearchInventory() {	
		RequestQuoteModel raqModel = new RequestQuoteModel();
		raqModel.setSeriesName("2020 Camry");
		getWebDriver().manage().deleteAllCookies();
		String newUrl = getWebDriver().getCurrentUrl() + "?zipcode="+zipCode;
		getWebDriver().navigate().to(newUrl);
		super.reportLog("Navigated url "+newUrl);
		
		String dealerName = homePage.getDealerName();
		homePage.selectDealerFromModelPopUp();
		super.reportLog("Select first dealer from popup");
/*
		if(homePage.isSmartPath() ) {
			homePage.verifyAndCloseSmartPopUp();
			super.reportLog("Verify and close smartpath popup window");
		}
	*/	
		homePage.selectShoppingToolMenu();
		SearchInventoryPage searchInventoryPage = homePage.gotoSearchInventory();
		super.reportLog("Go to search inventory page");	
		
		super.reportLog("Select first vehicle");
		searchInventoryPage.selectCarBySeries(raqModel);
					
		searchInventoryPage.clickOnSaveHeartButton();
		super.reportLog("Click on heart button");
				
		homePage.clickOnProfileIcon();
		int saveCount = homePage.getSavesCount();
		Assert.assertEquals(saveCount, 1);
		super.reportLog("Verify build saved");
		
		SavesPage savesPage = homePage.openSaveList();
		savesPage.verifyHeader("Saved Inventory");
		super.reportLog("Verify saved build header text");
	
		//savesPage.verifySavedVehicle(seriesName);
		//super.reportLog("Verify vehicle trim for saved Inventory");
		
		savesPage.removeSaveBuild();
		super.reportLog("Remove Saved build");
		
		homePage.clickOnProfileIcon();
		saveCount = homePage.getSavesCount();
		Assert.assertEquals(saveCount, 0);
		super.reportLog("Verify saved count after removing saved build");
	}

}