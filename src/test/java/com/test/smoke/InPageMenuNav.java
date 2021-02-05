package com.test.smoke;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.toy.pages.HomePage;
import com.toy.selenium.core.BaseTest;

public class InPageMenuNav extends BaseTest {

	@BeforeMethod
	public void setupObject() throws InterruptedException {
		homePage = PageFactory.initElements(getWebDriver(), HomePage.class);
	//	homePage.submitZipCode("75010");
		getWebDriver().navigate().to(applicationUrl);
	}

	@Test
	public void InPageBuildAndPriceNavTest() {
		super.reportLog("Click in page Build And Price button");
		homePage.clickBuildAndPriceInPageButton();

		super.reportLog("Verify build and price page opened");
		String headerText = homePage.getH1Value();
		Assert.assertEquals(headerText, "Build Your Toyota");
	}

	@Test
	public void InPageSearchInventoryNavTest() {
		super.reportLog("Click in page Search inventory button");
		homePage.clickSearchInventoryInPageButton();

		super.reportLog("Verify search inventroy page opened");
		homePage.verifyH1ValueContainsText("Search Inventory");
	}
	

	@Test
	public void InPageFindADealerNavTest() {
		super.reportLog("Click in page Find A dealer button");
		homePage.clickFindADealerInPageButton();

		super.reportLog("Verify Find A Dealer page opened");
		String headerText = homePage.getH1Value();
		Assert.assertEquals(headerText, "Find a Dealer by ZIP Code, City & State or Dealer Name");
	}

}