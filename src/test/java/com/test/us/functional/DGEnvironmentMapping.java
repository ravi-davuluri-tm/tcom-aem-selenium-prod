package com.test.us.functional;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.toy.dataproviders.DataProviderFunctional;
import com.toy.pages.HomePage;
import com.toy.selenium.core.BaseTest;

public class DGEnvironmentMapping extends BaseTest {

	String zipcodeSet = "63141";

	/*
	 * 1. verify env and js file loading on homepage 2. RTI page loads js script 3.
	 * Saves page load appropriate script 4. "window.viewConfig.env" and
	 * "DGDataHub.DEPLOY_ENV".
	 * cdn.dgint.deops.toyota.com/deploy/dg-loader/latest/dg-loader-app.min.js
	 * cdn.dgtest.deops.toyota.com/deploy/dg-loader/latest/dg-loader-app.min.js
	 * cdn.dgstage.deops.toyota.com/deploy/dg-loader/latest/dg-loader-app.min.js
	 * 
	 */
	
	@Test(priority = 1)
	public void verifyEnvAndJsLoadingOnHomePage() throws InterruptedException {
		HomePage homePage = PageFactory.initElements(getWebDriver(), HomePage.class);

		getWebDriver().manage().deleteAllCookies();
		String newUrl = getWebDriver().getCurrentUrl() + "?zipcode=" + zipcodeSet;
	
		getWebDriver().navigate().to(newUrl);
		super.reportLog("Navigated url " + newUrl);
		Thread.sleep(4000);
		
		homePage.selectDealerByNameFromModelPopup("Seeger Toyota");
		super.reportLog("Select first dealer from popup");
		
		homePage._waitForJStoLoad();
		homePage.sleepInSecond(2);
		
		Object DGhubDeployEnvdata = ((JavascriptExecutor) getWebDriver())
				.executeScript("return window.DGDataHub.DEPLOY_ENV");
		System.out.println("DGhubDeployEnvdata: " + DGhubDeployEnvdata.toString());
		
		this.verifyJSFile("ws-app.min.js");
		this.reportLog("verify ws-app.min.js");
		
		if (newUrl.contains("test6")) {
			Assert.assertEquals(DGhubDeployEnvdata.toString(), "test");
			this.verifyJSFile("https://cdn.test.dg.toyota.com");
			this.reportLog("Verify DGhub=test and DG=https://cdn.test.dg.toyota.com in test6 env");
		} else if (newUrl.contains("test4")) {
			Assert.assertEquals(DGhubDeployEnvdata.toString(), "demo");
			this.verifyJSFile("https://cdn.demo.dg.toyota.com");
			this.reportLog("Verify DGhub=demo and DG=https://cdn.demo.dg.toyota.com");
		} else if (newUrl.contains("staging")) {
			Assert.assertEquals(DGhubDeployEnvdata.toString(), "stage");
			this.verifyJSFile("https://cdn.stage.dg.toyota.com");
			this.reportLog("Verify DGhub=stage and DG=https://cdn.stage.dg.toyota.com");
		} else if (newUrl.contains("test1") || newUrl.contains("test2")) {
			Assert.assertEquals(DGhubDeployEnvdata.toString(), "stage");
			this.verifyJSFile("https://cdn.stage.dg.toyota.com");
			this.reportLog("Verify DGhub=test and DG=https://cdn.stage.dg.toyota.com in test1 and test2");
		} else {
			Assert.assertEquals(DGhubDeployEnvdata.toString(), "prod");
			this.verifyJSFile("cdn.dg.toyota.com/deploy/dg-loader/latest/dg-loader-app.min.js");
			this.reportLog("Verify dg-loader-app.min.js java script file with complete path in prod");

		}
	}

	@Test(priority = 2)
	public void verifyEnvAndJsLoadsOnRtiPage() {
		HomePage homePage = PageFactory.initElements(getWebDriver(), HomePage.class);

		getWebDriver().manage().deleteAllCookies();
		String newUrl = getWebDriver().getCurrentUrl() + "?zipcode=" + zipcodeSet;

		getWebDriver().navigate().to(newUrl);
		System.out.println("env:" + newUrl);
		super.reportLog("navigate url with zipcode " + zipcodeSet);

		homePage.selectDealerFromModelPopUp();
		super.reportLog("Select first dealer from popup");

		homePage.selectShoppingToolMenu();
		super.reportLog("click on shopping tool menu");

		homePage.gotoSearchInventory();
		super.reportLog("click on search inventory menu");

		homePage._waitForJStoLoad();
		homePage.sleepInSecond(2);


		Object DGhubDeployEnvdata = ((JavascriptExecutor) getWebDriver())
				.executeScript("return window.DGDataHub.DEPLOY_ENV");
		System.out.println("DGhubDeployEnvdata: " + DGhubDeployEnvdata.toString());

		
		if (newUrl.contains("test6")) {
			Assert.assertEquals(DGhubDeployEnvdata.toString(), "test");
			this.verifyJSFile("https://cdn.test.rtic.toyota.com");
			this.reportLog("Verify DGhub=test and RTIC: https://cdn.test.rtic.toyota.com");
		} else if (newUrl.contains("test4")) {
			Assert.assertEquals(DGhubDeployEnvdata.toString(), "demo");
			this.verifyJSFile("https://cdn.demo.rtic.toyota.com");
			this.reportLog("Verify DGhub=demo and RTIC: https://cdn.demo.rtic.toyota.com");
		} else if (newUrl.contains("staging")) {
			Assert.assertEquals(DGhubDeployEnvdata.toString(), "stage");
			this.verifyJSFile("https://cdn.stage.rtic.toyota.com");
			this.reportLog("Verify DGhub=stage and RTIC:https://cdn.stage.rtic.toyota.com in stage");
		} else if (newUrl.contains("test1") || newUrl.contains("test2")) {
			Assert.assertEquals(DGhubDeployEnvdata.toString(), "stage");
			this.verifyJSFile("https://cdn.stage.rtic.toyota.com");
			this.reportLog("Verify DGhub=stage and RTIC:https://cdn.stage.rtic.toyota.com in test1 or test2");
		} else {
			Assert.assertEquals(DGhubDeployEnvdata.toString(), "prod");
			this.verifyJSFile("cdn.dg.toyota.com/deploy/dg-loader/latest/dg-loader-app.min.js");
			this.reportLog("Verify dg-loader-app.min.js java script file with complete path");

		}
	
		super.verifyJSFile("dg-loader-app.min.js");
		super.verifyJSFile("dg-component-main-toyota.min.js");
		super.verifyJSFile("dg-nav-menu-toyota.min.js");
		super.verifyJSFile("dg-progress-tracker-toyota.min.js");
		super.verifyJSFile("dg-toast-notification-toyota.min.js");
		super.reportLog("Verify dg-java script file");

	}

	@Test(priority = 3)
	public void verifyEnvAndJsLoadsOnRtiSavesPage() {
		HomePage homePage = PageFactory.initElements(getWebDriver(), HomePage.class);
		getWebDriver().manage().deleteAllCookies();

		String newUrl = getWebDriver().getCurrentUrl() + "?zipcode=" + zipcodeSet;
		getWebDriver().navigate().to(newUrl);
		super.reportLog("Navigated url " + newUrl);

		homePage.selectDealerFromModelPopUp();
		super.reportLog("Select first dealer from popup");

		homePage.selectShoppingToolMenu();
		super.reportLog("click on shopping tool menu");

		homePage.gotoSearchInventory();
		super.reportLog("click on search inventory menu");

		homePage._waitForJStoLoad();
		homePage.sleepInSecond(2);

		Object DGhubDeployEnvdata = ((JavascriptExecutor) getWebDriver())
				.executeScript("return window.DGDataHub.DEPLOY_ENV");
		System.out.println("DGhubDeployEnvdata: " + DGhubDeployEnvdata.toString());

		if (newUrl.contains("test6")) {
			Assert.assertEquals(DGhubDeployEnvdata.toString(), "test");
			this.verifyJSFile("https://cdn.test.rtic.toyota.com");
			this.reportLog("Verify DGhub=test and RTIC: https://cdn.test.rtic.toyota.com");
		} else if (newUrl.contains("test4")) {
			Assert.assertEquals(DGhubDeployEnvdata.toString(), "demo");
			this.verifyJSFile("https://cdn.demo.rtic.toyota.com");
			this.reportLog("Verify DGhub=demo and RTIC: https://cdn.demo.rtic.toyota.com");
		} else if (newUrl.contains("staging")) {
			Assert.assertEquals(DGhubDeployEnvdata.toString(), "stage");
			this.verifyJSFile("https://cdn.stage.rtic.toyota.com");
			this.reportLog("Verify DGhub=stage and RTIC:https://cdn.stage.rtic.toyota.com in stage");
		} else if (newUrl.contains("test1") || newUrl.contains("test2")) {
			Assert.assertEquals(DGhubDeployEnvdata.toString(), "stage");
			this.verifyJSFile("https://cdn.stage.rtic.toyota.com");
			this.reportLog("Verify DGhub=stage and RTIC:https://cdn.stage.rtic.toyota.com in test1 or test2");
		} else {
			Assert.assertEquals(DGhubDeployEnvdata.toString(), "prod");
			this.verifyJSFile("cdn.dg.toyota.com/deploy/dg-loader/latest/dg-loader-app.min.js");
			this.reportLog("Verify dg-loader-app.min.js java script file with complete path");

		}
		
	
	}

	@Test(priority = 4, dataProvider = "data-provider-url", dataProviderClass = DataProviderFunctional.class, enabled = false)
	public void verifyEnvAndJsLoadsOnRtiPageInTest4AndTest6(String url) {
		HomePage homePage = PageFactory.initElements(getWebDriver(), HomePage.class);

		String newUrl = url + "?zipcode=" + zipcodeSet;
		getWebDriver().navigate().to(newUrl);
		System.out.println("env:" + newUrl);
		super.reportLog("navigate url with zipcode " + zipcodeSet);

		homePage.selectDealerFromModelPopUp();
		super.reportLog("Select first dealer from popup");

		homePage.selectShoppingToolMenu();
		super.reportLog("click on shopping tool menu");

		homePage.gotoSearchInventory();
		super.reportLog("click on search inventory menu");

		homePage._waitForJStoLoad();
		homePage.sleepInSecond(2);

		Object DGhubDeployEnvdata = ((JavascriptExecutor) getWebDriver())
				.executeScript("return window.DGDataHub.DEPLOY_ENV");
		System.out.println("DGhubDeployEnvdata: " + DGhubDeployEnvdata.toString());

		if (newUrl.contains("test6")) {
			Assert.assertEquals(DGhubDeployEnvdata.toString(), "test");
			super.verifyJSFile("cdn.dgtest.deops.toyota.com/deploy/dg-loader/latest/dg-loader-app.min.js");
			super.reportLog("Verify .dgtest java script file with complete path");
		} else if (newUrl.contains("test4")) {
			Assert.assertEquals(DGhubDeployEnvdata.toString(), "int");
			super.verifyJSFile("cdn.dgint.deops.toyota.com/deploy/dg-loader/latest/dg-loader-app.min.js");
			super.reportLog("Verify .dgint java script file with complete path");
		} else if (newUrl.contains("staging")) {
			Assert.assertEquals(DGhubDeployEnvdata.toString(), "stage");
			super.verifyJSFile("cdn.dgstage.deops.toyota.com/deploy/dg-loader/latest/dg-loader-app.min.js");
			super.reportLog("Verify .dgstage java script file with complete path");
		} else {
			Assert.assertEquals(DGhubDeployEnvdata.toString(), "prod");
			super.verifyJSFile("cdn.dg.toyota.com/deploy/dg-loader/latest/dg-loader-app.min.js");
			super.reportLog("Verify dg-loader-app.min.js java script file with complete path");

		}

		super.verifyJSFile("dg-loader-app.min.js");
		super.verifyJSFile("dg-component-main-toyota.min.js");
		super.verifyJSFile("dg-nav-menu-toyota.min.js");
		super.verifyJSFile("dg-progress-tracker-toyota.min.js");
		super.verifyJSFile("dg-toast-notification-toyota.min.js");
		super.reportLog("Verify dg-java script file");
		super.reportLog("Verify dg-loader-app.min.js java script file");
	}

}