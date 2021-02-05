package com.test.us.functional;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.toy.dataproviders.DataProvidersUs;
import com.toy.pages.HomePage;
import com.toy.selenium.core.BaseTest;

public class FooterLink extends BaseTest {
	
	@BeforeClass
	public void navidateUrlWithZip() {
		getWebDriver().navigate().to(applicationUrl + "?zipcode=75010");
	}
	
	@Test(dataProvider = "FooterLinks", dataProviderClass = DataProvidersUs.class)
	public void VerifyLinkTest(String link, String title, String newWin) {
		reportLog("Test Running for link "+link);
		HomePage homePage = PageFactory.initElements(getWebDriver(), HomePage.class);
		
		homePage.clickOnFooterLink(link);
		super.reportLog("Click on link "+link);
		
		if(newWin.equalsIgnoreCase("yes")) {
			String winId = switchWinow();
			super.reportLog("switched new window");
			
			homePage.waitAndVerifyTitle(title);
			super.reportLog("Verify title "+title);
			
			getWebDriver().close();			
			getWebDriver().switchTo().window(winId);
			super.reportLog("Close new window and switched on parent window");
			
		}else {
			homePage.waitAndVerifyTitle(title);
			super.reportLog("Verify title "+title);
		}
	}
}