package com.test.us.functional;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.toy.constant.GlobalPagesConstant;
import com.toy.pages.BuildAndPricePage;
import com.toy.pages.FindYourMatchPage;
import com.toy.pages.HomePage;
import com.toy.pages.LocalSpecialPage;
import com.toy.pages.RequestAQuotePage;
import com.toy.pages.SearchInventoryPage;
import com.toy.selenium.core.BaseTest;

public class GeoLocationRAQ extends BaseTest {
	private String defaultZipCode;
	private RequestAQuotePage rqaPage;
	
	@BeforeMethod
	public void getZipCode() {
		homePage = PageFactory.initElements(getWebDriver(), HomePage.class);
		rqaPage = PageFactory.initElements(getWebDriver(), RequestAQuotePage.class);
		
		super.reportLog("Get default zip code from top your location field");
		defaultZipCode = homePage.getZipCode();
	}

	
	@Test
	public void VerifyGeoLocationOfSetYourLocationPopUp() {		
		getWebDriver().navigate().to(applicationUrl);
		super.reportLog("Click on pencil icon and verify zip code");
		homePage.clickPencilZipIcon();
		String zipCode = homePage.getZipCodeFromPopUp();
		Assert.assertEquals(defaultZipCode.trim(), zipCode.trim());		
	}

	@Test
	public void verifyGeoLocationRAQFromBnP() {
		super.reportLog("Navigated build and price page");
		homePage.selectShoppingToolMenu();
		BuildAndPricePage buildAndPricePage = homePage.gotoBuildAndPricePage();
		
		super.reportLog("Select first car");
		buildAndPricePage.selectFirstCar();
		
		super.reportLog("Click on summay tab and click on request a quote button");
		buildAndPricePage.selectTabByName(GlobalPagesConstant.Summary);		
		buildAndPricePage.clickRaqButton();
		
		super.reportLog("Verify RAQ page location zip code");
		String zipCode = rqaPage.getZipCode();
		Assert.assertEquals(defaultZipCode.trim(), zipCode.trim(), "Location zip code not matched at RAQ page");
	}

	@Test(enabled =  false , description = "This is need to update with lates changes")
	public void VerifyGeoLocationRAQFromLocalSpecialPage() {
		super.reportLog("Navigated local special page");
		homePage.selectShoppingToolMenu();
		LocalSpecialPage localSpecialPage = homePage.gotoLocalSpecial();
		
		super.reportLog("Verify local special page opened");
		localSpecialPage.verifyH1ValueContainsText("Local Specials");
				
		String localSpecialPageZipCode = localSpecialPage.getLocationZip();
		Assert.assertEquals(defaultZipCode.trim(), localSpecialPageZipCode.trim());		
		
		super.reportLog("Click on view detail button");
		localSpecialPage.clickOnViewDetail();
		
		super.reportLog("Click on Request A quote button and verify form");
		localSpecialPage.clickRequestAQuoteButton();	
		
		super.reportLog("Verify RAQ page location zip code");
		String zipCode = rqaPage.getZipCode();
		Assert.assertEquals(defaultZipCode.trim(), zipCode.trim(), "Location zip code not matched at RAQ page");
	}

	@Test
	public void verifyGeolocationRAQFromSIT() {
		super.reportLog("Navigated search inventory");
		homePage.selectShoppingToolMenu();
		SearchInventoryPage searchInventoryPage = homePage.gotoSearchInventory();
				
		super.reportLog("Select first vehicle");
		searchInventoryPage.selectFirstCar();
		
		super.reportLog("Get location zip code from search inventory page and verify");
		String zipCode1 = searchInventoryPage.getLocationZip();
		Assert.assertEquals(defaultZipCode.trim(), zipCode1.trim() , "Location zip code not matched at search inventory page");	
		
		super.reportLog("Click on view detail button");
		searchInventoryPage.clickViewDetails();
		
		super.reportLog("Verify RAQ page location zip code");
		String zipCode = rqaPage.getZipCode();
		Assert.assertEquals(defaultZipCode.trim(), zipCode.trim(), "Location zip code not matched at RAQ page");	
	}

	@Test
	public void VerifyGeoLocationRAQStandalone() {
		super.reportLog("Navigate Request A Quote menu from shopping tool menu");
		homePage.selectShoppingToolMenu();
		homePage.gotoRequestQuote();
		
		super.reportLog("Verify RAQ page location zip code");
		String zipCode = rqaPage.getZipCode();
		Assert.assertEquals(defaultZipCode.trim(), zipCode.trim(), "Location zip code not matched at RAQ page");
	}

	@Test
	public void verifyGeolocationRAQFindYourMatch() {
		super.reportLog("Navigated find a match page");
		FindYourMatchPage findMatchPage = homePage.gotoFindMatch();
		
		super.reportLog("Navigated to find a match options");
		String matchOptions = "Off to Work,Commuting to Work,Going to the Office,Being Efficient";
		findMatchPage.gotFindAMatchs(matchOptions);
		
		super.reportLog("Click on RAQ button");
		findMatchPage.clickOnRAQbutton();
		
		super.reportLog("Verify RAQ page location zip code");
		String zipCode = rqaPage.getZipCode();
		Assert.assertEquals(defaultZipCode.trim(), zipCode.trim(), "Location zip code not matched at RAQ page");
	}

}