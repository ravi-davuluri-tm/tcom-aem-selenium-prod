package com.test.us.functional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.toy.pages.BrochuresPage;
import com.toy.pages.CommonPage;
import com.toy.pages.MaintenancePage;
import com.toy.selenium.core.BaseTest;

public class VerifyPagesDetail extends BaseTest{
	
	@BeforeClass
	public void navigateUrl() {		
		String newUrl = getWebDriver().getCurrentUrl() + "?zipcode=90010";
		getWebDriver().navigate().to(newUrl);
		reportLog("Navigate Url with zip code ?zipcode=90010");		
	}
	
	
	@Test(enabled = false)
	public void testMaintenancePage() {
		MaintenancePage maintenancePage =  PageFactory.initElements(getWebDriver(), MaintenancePage.class);
		
		getWebDriver().navigate().to(applicationUrl + "/maintenance/index.html");
		reportLog("Navigate Url with /maintenance/index.html");
				
		maintenancePage.verifyPContainsText("Thank you for your patience.");
		reportLog("Verify Thank you for your patience.");
		
		maintenancePage.verifyLogoImage();
		reportLog("Verfy image logo ");
	}
	
	
	@Test(enabled = true)
	public void testBrochuresPage() {
		BrochuresPage brochuresPage = PageFactory.initElements(getWebDriver(), BrochuresPage.class);
	
		getWebDriver().navigate().to(applicationUrl + "/brochures/cars-minivan");
		reportLog("Navigate Url with /brochures/cars-minivan");
		
		brochuresPage.verifyHeaderText("Toyota Brochures");	
		reportLog("Verify header text Toyota Brochures");
		
		List<String> subMenuTabs = new ArrayList<String>(Arrays.asList("Cars & Minivan", 
				"Trucks", "Crossovers & SUVs", "Hybrids & FUEL CELL", "Other Brochures"));
		
		brochuresPage.verifySubnav(subMenuTabs);
		reportLog("Verify all sub menus");
		
		brochuresPage.verifySubnavNavigation(subMenuTabs);
		reportLog("Verify all sub menus navigation");		
	}
	
	@Test(enabled = true)
	public void testConnectedServicesToyotaAppPage() {
		CommonPage brochuresPage = PageFactory.initElements(getWebDriver(), CommonPage.class);
			
		getWebDriver().navigate().to(applicationUrl + "/connected-services/toyotaapp/");
		reportLog("Navigate Url with /connected-services/toyotaapp/");
		
		brochuresPage.verifyGoogleAppleButtons();
		reportLog("Verify google and apple play store button");
		
		brochuresPage.verifyFeatureButton("Check up on your Toyota's health.");
		reportLog("Verify Toyota's health button");
		
		brochuresPage.verifyFeatureButton("Control remotely.");
		reportLog("Verify 'Control remotely.\"' button");
		
		brochuresPage.verifyFeatureButton("Works with your smartwatch.");
		reportLog("Verify 'Works with your smartwatch.' button");
		
		brochuresPage.verifyFeatureButton("Make a Payment");
		reportLog("Verify 'Make a Payment' button");
	}
	
	
	@Test(enabled = true)
	public void testConnectedServicesToyotaPage() {
		CommonPage commonPage = PageFactory.initElements(getWebDriver(), CommonPage.class);
	
		getWebDriver().navigate().to(applicationUrl + "/connected-services/");
		reportLog("Navigate Url with /connected-services/");
		
		commonPage.verifyMobileImage();
		reportLog("Verify mobile image icon");
		
		commonPage.verifyServicesButtonOptions();
		reportLog("Verify all services button");
		
		List<String> subMenuTabs = new ArrayList<String>(Arrays.asList(" Connected Services Support", 
				"Connected Services by Vehicle", "Frequently Asked Questions", "Privacy & Protection Notice", "Audio Multimedia"));
		
		commonPage.verifyMidButton(subMenuTabs);
		reportLog("Verify servces mid menus buttons " + subMenuTabs.toString());
		
	}
	
	
	@Test(enabled = true)
	public void testVerifyCompareVehiclePage() {
		CommonPage commonPage = PageFactory.initElements(getWebDriver(), CommonPage.class);
	
		getWebDriver().navigate().to(applicationUrl + "/corolla/compare/corolla-vs-civic/");
		reportLog("Navigate Url with /corolla/compare/corolla-vs-civic/");
		
		commonPage.expandAllReadMoreKeyBenefitsButton();
		reportLog("expand all 'Read more key benefits' buttons");
		
		List<String> subMenu = new ArrayList<String>(Arrays.asList("Build & Price", 
				"Search Inventory", "Local Specials", "Find A Dealer"));
		
		commonPage.verifyFirstMenuButtons(subMenu);
		reportLog("Verify first set mid menus buttons");
		
		commonPage.verifySecondMenusButtons(subMenu);
		reportLog("Verify button menus buttons");		
	}	
	
}