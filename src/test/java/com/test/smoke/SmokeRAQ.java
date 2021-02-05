package com.test.smoke;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.toy.constant.GlobalPagesConstant;
import com.toy.pages.BuildAndPricePage;
import com.toy.pages.DealerPage;
import com.toy.pages.HomePage;
import com.toy.pages.LocalSpecialPage;
import com.toy.pages.RequestAQuotePage;
import com.toy.pages.SearchInventoryPage;
import com.toy.selenium.core.BaseTest;

public class SmokeRAQ extends BaseTest {

	@BeforeMethod
	public void setupObject() throws InterruptedException {
		homePage = PageFactory.initElements(getWebDriver(), HomePage.class);
		RequestAQuotePage raqPage = PageFactory.initElements(getWebDriver(), RequestAQuotePage.class);		
		raqPage.submitZipCode("75010");
	}
	
	@Test
	public void buildAndPriceRAQ() {
		super.reportLog("Navigated build and price page");
		homePage.selectShoppingToolMenu();
		BuildAndPricePage buildAndPricePage = homePage.gotoBuildAndPricePage();
		
		super.reportLog("Select first car");
		buildAndPricePage.selectFirstCar();
		
		super.reportLog("Click on summay tab and click on request a quote button");
		buildAndPricePage.selectTabByName(GlobalPagesConstant.Summary);		
		buildAndPricePage.clickRaqButton();
		
		super.reportLog("Verify request a quote form opened");
		Assert.assertEquals(buildAndPricePage.getModelTitle(), "Request A Quote");	
	}
	
	
	
	@Test
	public void searchInventoryRAQ() {
		super.reportLog("Navigated search inventory");
		homePage.selectShoppingToolMenu();
		SearchInventoryPage searchInventoryPage = homePage.gotoSearchInventory();
		
		super.reportLog("Verify local special page opened");
		searchInventoryPage.verifyH1ValueContainsText("Search Inventory");
		
		super.reportLog("Click on view detail button");
		searchInventoryPage.selectFirstCar();
		
		super.reportLog("Click on view detail button");
		searchInventoryPage.clickViewDetails();
		
		super.reportLog("Click on Request A quote button and verify form");
		Assert.assertEquals(searchInventoryPage.getRAQFormTitle(), "Contact A Dealer");
	}
	
	@Test
	public void findADealerRAQ() {
		DealerPage dealerPage = homePage.gotofindDealers();
		super.reportLog("Navigated find a dealer page");
	
		super.reportLog("Click on dealer name and click on verify dealer name");
		String name = dealerPage.getDealerName();
		dealerPage.clickOnFirstDealer();		
		dealerPage.verifyH1ValueContainsText(name);
		
		super.reportLog("Navigate back");
		getWebDriver().navigate().back();
		
		super.reportLog("Click on dealer details link and name");
		dealerPage.clickOnDealerDetails();
		dealerPage.verifyH1ValueContainsText(name);
		
		super.reportLog("Click on raq button and verify form opened");
		dealerPage.clickOnRaqButton();		
		Assert.assertEquals(dealerPage.getModelTitle(), "Request A Quote");		
		dealerPage.clickOnCloseButton();
		
		super.reportLog("Navigate back");
		getWebDriver().navigate().back();
		
		super.reportLog("Click on raq button and verify form opened");
		dealerPage.clickOnRaqButton();	
		Assert.assertEquals(dealerPage.getModelTitle(), "Request A Quote");		
		dealerPage.clickOnCloseButton();
	}	
	
	
}