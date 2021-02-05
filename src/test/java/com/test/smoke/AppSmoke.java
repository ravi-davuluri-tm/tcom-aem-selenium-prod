package com.test.smoke;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.ITestResult;
import org.testng.Reporter;
import com.toy.constant.ConstantFactory;
import com.toy.datamodel.AppDataModel;
import com.toy.dataproviders.DataProvidersUs;
import com.toy.pages.DisclaimerPage;
import com.toy.pages.HomePage;
import com.toy.selenium.core.BaseTest;

public class AppSmoke extends BaseTest {

	@BeforeClass
	@Parameters({"excelSheetName"})
	public void navigateZipUrl(@Optional("TCOM") String excelSheetName) {
		ConstantFactory.getInstance().setExcelSheetName(excelSheetName);
		getWebDriver().navigate().to(super.applicationUrl + "?zipcode=90010");
		super.reportLog("Navigated url with zip code 90010");
	}
	
	 
	  
	@Test(dataProvider = "AppSmoke", dataProviderClass = DataProvidersUs.class)
	public void AppSmokeTest(AppDataModel model) {
		ITestResult result = Reporter.getCurrentTestResult();
	    result.getMethod().setDescription(model.getAppName());
	    
		homePage = PageFactory.initElements(getWebDriver(), HomePage.class);
		DisclaimerPage disclaimerPage = PageFactory.initElements(getWebDriver(), DisclaimerPage.class);
		reportLog("Running test for application : " + model.getAppName());
		
		super.verifyJSFile("dg-loader-app.min.js");
		reportLog("Verified dg-loaser-app.min.js loaded");
		
		getWebDriver().get(this.applicationUrl + model.getUrl());
		reportLog("Navigated application url : " + model.getUrl());

		homePage.verifyTitle(model.getTitle());
		reportLog("Verified title : " + model.getTitle());		

		String disclaimerText1 = disclaimerPage.selectEnableDisclaimer();
		super.reportLog("Selected disclaimer " + disclaimerText1);

		if(! disclaimerText1.contentEquals("Not found")) {
			String selectedDisclaimer = disclaimerPage.getSelectedDisclaimer();
			super.reportLog("Get disclaimer text " + selectedDisclaimer);
	
			System.out.println(selectedDisclaimer);
			Assert.assertTrue(selectedDisclaimer.startsWith(disclaimerText1),
					"Selected Disclaimr " + selectedDisclaimer + " is no start with " + disclaimerText1);
			super.reportLog("Verified disclaimer text start with " + disclaimerText1);
		}
	}

}