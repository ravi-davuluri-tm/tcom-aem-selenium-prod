package com.test.smoke;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.toy.pages.HomePage;
import com.toy.pages.VehiclePage;
import com.toy.selenium.core.BaseTest;

public class GeoLocationSmoke extends BaseTest {
	
	private String zipCodeZeoLoc;

	@BeforeMethod
	public void getZipCode() throws InterruptedException {
		homePage = PageFactory.initElements(getWebDriver(), HomePage.class);		
		//homePage.submitZipCodeMobile("90010");
		super.reportLog("Get default zip code from top your location field");
		zipCodeZeoLoc = homePage.getZipCode();
	}

	@Test(priority = 1)
	public void VerifyGeoLocationOfSetYourLocationPopUp() {
		getWebDriver().navigate().to(applicationUrl);
		super.reportLog("Click on pencil icon and verify zip code");
		
		homePage.clickPencilZipIcon();
		super.reportLog("Verify geolocated zip code match with Set your location popup on home page");
		String zipCode = homePage.getZipCodeFromPopUp();
		Assert.assertEquals(zipCodeZeoLoc.trim(), zipCode.trim());
	}

	@Test(priority = 1)
	public void verifyGeoLocationRAQFromBnP() {
		super.reportLog("Navigated build and price page");
		homePage.selectShoppingToolMenu();
		homePage.gotoBuildAndPricePage();
		
		super.reportLog("Verify geolocated zip code at global nav");
		String zipCode1 = homePage.getZipCode();
		Assert.assertEquals(zipCodeZeoLoc.trim(), zipCode1.trim());
		
		super.reportLog("Click on global nav setlocation and verify zip code on popup window from Build and Price");
		homePage.clickPencilZipIcon();
		String zipCode2= homePage.getZipCodeFromPopUp();
		Assert.assertEquals(zipCodeZeoLoc.trim(), zipCode2.trim());
		
	}

	@Test(priority = 1)
	public void verifyGeolocationRAQFromSIT() {
		super.reportLog("Navigated search inventory");
		homePage.selectShoppingToolMenu();
		homePage.gotoSearchInventory();

		super.reportLog("Verify geolocated zip code at global nav");
		String zipCode1 = homePage.getZipCode();
		Assert.assertEquals(zipCodeZeoLoc.trim(), zipCode1.trim());
		
		super.reportLog("Click on global nav setlocation and verify zip code on popup window from search inventory");
		homePage.clickPencilZipIcon();
		String zipCode2 = homePage.getZipCodeFromPopUp();
		Assert.assertEquals(zipCodeZeoLoc.trim(), zipCode2.trim());
	}
	
	@Test(priority = 1)
	public void verifyGeolocationRAQFromFindADelear() {
		super.reportLog("Navigated search inventory");
		homePage.selectShoppingToolMenu();
		homePage.gotofindDealers();

		super.reportLog("Verify geolocated zip code at global nav");
		String zipCode1 = homePage.getZipCode();
		Assert.assertEquals(zipCodeZeoLoc.trim(), zipCode1.trim());
		
		super.reportLog("Click on global nav setlocation and verify zip code on popup window from Find a Dealer");
		homePage.clickPencilZipIcon();
		String zipCode2 = homePage.getZipCodeFromPopUp();
		Assert.assertEquals(zipCodeZeoLoc.trim(), zipCode2.trim());
	}

	@Test(priority = 1)
	public void VerifyGeoLocationRAQStandalone() {
		super.reportLog("Navigate Request A Quote menu from shopping tool menu");
		homePage.selectShoppingToolMenu();
		homePage.gotoRequestQuote();

		super.reportLog("Verify geolocated zip code at global nav");
		String zipCode1 = homePage.getZipCode();
		Assert.assertEquals(zipCode1.trim(), zipCode1.trim());
		
		super.reportLog("Click on global nav setlocation and verify zip code on popup window from RAQ Standalone");
		homePage.clickPencilZipIcon();
		String zipCode2 = homePage.getZipCodeFromPopUp();
		Assert.assertEquals(zipCode2.trim(), zipCode2.trim());
	}
	
	@Test(priority = 1)
	public void VerifyGeoLocationMLPPage() {
		super.reportLog("Navigate Select vehicle page");		
		VehiclePage vehiclePage = homePage.goToSelectVehicle();
		
		super.reportLog("Select first vehicle");	
		vehiclePage.selectFirstVehicle();
		
		super.reportLog("Verify geolocated zip code at global nav");
		String zipCode1 = homePage.getZipCode();
		Assert.assertEquals(zipCodeZeoLoc.trim(), zipCode1.trim());
		
		super.reportLog("Click on global nav setlocation and verify zip code on popup window from MLP");
		homePage.clickPencilZipIcon();
		String zipCode2 = homePage.getZipCodeFromPopUp();
		Assert.assertEquals(zipCodeZeoLoc.trim(), zipCode2.trim());
	}

	@Test(priority = 2)
	public void VerifyGeoLocationValidation() throws InterruptedException {
		getWebDriver().navigate().to(applicationUrl);

		super.reportLog("Click on set location");
		homePage.clickPencilZipIcon();

		super.reportLog("Verify setlocation overlay display");
		homePage.verifyZipCodePopUpOptions();

		super.reportLog("Enter less than 5 digit number and verify submit button not enable");
		homePage.changeZipCodeFromPopUp("1234");
		homePage.verifyZipSubmitButtonDisabled();

		super.reportLog("Enter non numeric number and verify it should accept only numeric value");
		homePage.changeZipCodeFromPopUp("Test@");
		String value = homePage.getZipCodeFromPopUp();
		Assert.assertTrue(value.isEmpty(), "non numeric number input in inputbox");
		System.out.println("value " + value);

		super.reportLog("Enter in valid zip code like 00000 and verify your zip code is invalid");
		homePage.changeZipCodeFromPopUp("00000");
		homePage.clickOnSubmitButton();
		Assert.assertEquals(homePage.getZipErrorMessage().trim(), "Your ZIP is invalid.");

		super.reportLog("Enter valid zip code and click on submit button");
		homePage.changeZipCodeFromPopUp("90010");
		homePage.clickOnSubmitButton();

		super.reportLog("Verify zip code updated on utility nav");
		String zipCode = homePage.getZipCode();
		Assert.assertEquals(zipCode, "90010");

		super.reportLog("Click on your location overlay and verify your location overly should open");
		homePage.clickPencilZipIcon();

		super.reportLog("Verify set location overlay options");
		homePage.verifyZipCodePopUpOptions();
	}

}