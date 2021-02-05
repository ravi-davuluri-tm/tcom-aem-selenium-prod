package com.test.smoke;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.toy.constant.GlobalPagesConstant;
import com.toy.pages.BuildAndPricePage;
import com.toy.pages.HomePage;
import com.toy.pages.RequestAQuotePage;
import com.toy.pages.VehiclePage;
import com.toy.selenium.core.BaseTest;

//GlobalNav

public class GlobalNav extends BaseTest {

	@BeforeMethod
	public void setupObject() throws InterruptedException {
		
		homePage = PageFactory.initElements(getWebDriver(), HomePage.class);
		getWebDriver().manage().deleteAllCookies();
		RequestAQuotePage raqPage = PageFactory.initElements(getWebDriver(), RequestAQuotePage.class);		
		raqPage.submitZipCode("90090");
	}
	
	@Test(priority=1)
	public void verifyShoppingToolsMenuNavigation() {
		super.reportLog("Navigate build and price menu from shopping tool menu");		
		homePage.gotoBuildAndProceShoppingToolSubMenu();
		
		super.reportLog("Verify build and price page opened");
		String headerText = homePage.getH1Value();
		Assert.assertEquals(headerText, "Build Your Toyota");
		
		super.reportLog("Navigate local special menu from shopping tool menu");
		homePage.selectShoppingToolMenu();
		homePage.gotoLocalSpecial();
		
		super.reportLog("Verify local special page opened");
		//homePage.verifyH1ValueContainsText("Local Specials");
		
		super.reportLog("Navigate search inventroy menu from shopping tool menu");
		homePage.selectShoppingToolMenu();
		homePage.gotoSearchInventory();
		
		super.reportLog("Verify search inventroy page opened");
		homePage.verifyH1ValueContainsText("Search Inventory");
		
		super.reportLog("Navigate Request A Quote menu from shopping tool menu");
		homePage.selectShoppingToolMenu();
		RequestAQuotePage rqaPage = homePage.gotoRequestQuote();
		
		super.reportLog("Verify Request A Quote page opened");
		headerText = rqaPage.getTitle();  // Request A Quote
		Assert.assertEquals(headerText, "Request A Quote");
		
		super.reportLog("Navigate accessories menu from shopping tool menu");
		homePage.selectShoppingToolMenu();
		homePage.goToAccessoriesPage();
		
		super.reportLog("Verify accessories page opened");		
		homePage.verifyH1ValueContainsText("Genuine Toyota Accessories");		
	
		super.reportLog("Navigate Find Your Match menu from shopping tool menu");
		homePage.gotoFindMatch();
		
		super.reportLog("Verify Find Your Match page opened");
		homePage.verifyH1ValueContainsText("Where are you headed");	
		
		super.reportLog("Navigate View Brochures menu from shopping tool menu");
		homePage.goToViewBrochuersPage();
		
		super.reportLog("Verify View Brochures page opened");
		Assert.assertEquals(homePage.getHeadingText(), "Toyota Brochures");
		
		super.reportLog("Navigate Contact a Dealer menu from shopping tool menu");
		homePage.goToContactDealerPage();
		
		super.reportLog("Verify Contact a Dealer page opened");
		Assert.assertEquals(homePage.getHeadingText(), "Contact A Dealer");		
		
	}
	
	@Test(priority=2)
	public void verifyBuildAndPriceMenu() {
		super.reportLog("Click on build and price menu");
		homePage.gotoBuildAndPricePage();
		
		super.reportLog("Verify build and price page opened");
		String headerText = homePage.getH1Value();
		Assert.assertEquals(headerText, "Build Your Toyota");
	}
	
	@Test(priority=3, enabled=false)
	public void verifySelectVehicle() throws InterruptedException {
		super.reportLog("Navigate Select vehicle page");		
		VehiclePage vehiclePage = homePage.goToSelectVehicle();
		
		super.reportLog("Select first vehicle");	
		vehiclePage.selectFirstVehicle();
		
		super.reportLog("Click on galary tab and verify galary selected");	
		Thread.sleep(5000);
		vehiclePage.clickOnGalaryTab();
		homePage.verifyH1ValueContainsText("Photos, Videos & 360 Views");		
		super.reportLog("Click on galary tab and verify galary selected");
		vehiclePage.clickOnFeatureTabs();
		homePage.verifyH1ValueContainsText("Features");	
		
		super.reportLog("Click on specs tab and verify selected");
		vehiclePage.clickOnSpecsTab();
		//String headerText = homePage.getHeadingText();
		//Assert.assertEquals(headerText, "Full Specs");
		
		super.reportLog("Click on build button");
		BuildAndPricePage buildAndPricePage = vehiclePage.clickOnBuildButton();
		
		super.reportLog("Click on summay tab and click on request a quote button");
		buildAndPricePage.selectTabByName(GlobalPagesConstant.Summary);		
		buildAndPricePage.clickRaqButton();
		
		super.reportLog("Verify request a quote form opened");
		Assert.assertEquals(buildAndPricePage.getModelTitle(), "Request A Quote");
	}
	
}