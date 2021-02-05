package com.test.smoke;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.toy.datamodel.AppDataModel;
import com.toy.dataproviders.DataProvidersUs;
import com.toy.pages.HomePage;
import com.toy.selenium.core.BaseTest;


public class AppDefaultGeoLocation extends BaseTest {

	@Test(dataProvider = "AppSmoke", dataProviderClass = DataProvidersUs.class)
	public void AppSmokeTest(AppDataModel model) {
		homePage = PageFactory.initElements(getWebDriver(), HomePage.class);	
		reportLog("Running test for application : " + model.getAppName());

		super.reportLog("Get default zip code from top your location field");
		String defaultZipCode = homePage.getZipCode();
		System.out.println(defaultZipCode);

		getWebDriver().get(this.applicationUrl + model.getUrl());
		reportLog("Navigated application url : " + model.getUrl());

		homePage.verifyTitle(model.getTitle());
		reportLog("Verify title : " + model.getTitle());	

		super.reportLog("Get zip code from top your location field after navigating app");
		String appZipCode = homePage.getZipCode();
		Assert.assertEquals(appZipCode, defaultZipCode);
	}
}
 