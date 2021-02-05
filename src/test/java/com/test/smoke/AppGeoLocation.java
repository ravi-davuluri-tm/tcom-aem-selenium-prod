package com.test.smoke;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.toy.datamodel.AppDataModel;
import com.toy.dataproviders.DataProvidersUs;
import com.toy.pages.HomePage;
import com.toy.selenium.core.BaseTest;


public class AppGeoLocation extends BaseTest {

	String zipCode = "90010";

	@BeforeClass
	public void navigateZipUrl() {
		getWebDriver().navigate().to(super.applicationUrl + "?zipcode="+zipCode);
		super.reportLog("Navigated url with zip code "+zipCode);
	}

	@Test(dataProvider = "AppSmoke", dataProviderClass = DataProvidersUs.class)
	public void AppSmokeTest(AppDataModel model) {
		homePage = PageFactory.initElements(getWebDriver(), HomePage.class);	
		reportLog("Running test for application : " + model.getAppName());

		getWebDriver().get(this.applicationUrl + model.getUrl());
		reportLog("Navigated application url : " + model.getUrl());

		homePage.verifyTitle(model.getTitle());
		reportLog("Verify title : " + model.getTitle());	

		super.reportLog("Get default zip code from top your location field");
		String defaultZipCode = homePage.getZipCode();
		Assert.assertEquals(zipCode, defaultZipCode);
	}
}