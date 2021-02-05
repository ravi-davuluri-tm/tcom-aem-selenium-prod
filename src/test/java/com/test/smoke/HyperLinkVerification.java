/*
 * package com.test.smoke;

import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import com.toy.datamodel.AppDataModel;
import com.toy.dataproviders.DataProvidersUs;
import com.toy.pages.HomePage;
import com.toy.selenium.core.BaseTest;

public class HyperLinkVerification extends BaseTest {
	


	@Test(dataProvider = "AppSmoke", dataProviderClass = DataProvidersUs.class)
	public void HyperLinkVerificatonTest(AppDataModel model) {
		homePage = PageFactory.initElements(getWebDriver(), HomePage.class);

		getWebDriver().get(this.applicationUrl + model.getUrl());
		reportLog("Navigated application url : " + model.getUrl());

		homePage.verifyTitle(model.getTitle());
		reportLog("Verify title : " + model.getTitle());	
		homePage.sleepInSecond(2);
		List<WebElement> links = getWebDriver().findElements(By.tagName("a"));
		Set<String> urls = super.getLinkList(links);
		super.validateLinks(urls);
		reportLog("Verify broken links");	
	}
}
*/
package com.test.smoke;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.toy.datamodel.AppDataModel;
import com.toy.dataproviders.DataProvidersUs;
import com.toy.pages.HomePage;
import com.toy.selenium.core.BaseTest;

public class HyperLinkVerification extends BaseTest {
	

	@BeforeClass
	public void navigateZipUrl() throws InterruptedException {
		getWebDriver().navigate().to(super.applicationUrl + "?zipcode=90091");
		super.reportLog("Navigated to url");
	
	}

	@Test(dataProvider = "AppSmoke", dataProviderClass = DataProvidersUs.class)
	public void HyperLinkVerificationTest(AppDataModel model) {
		homePage = PageFactory.initElements(getWebDriver(), HomePage.class);

		getWebDriver().get(this.applicationUrl + model.getUrl());
		reportLog("Navigated application url : " + model.getUrl());

		homePage.verifyTitle(model.getTitle());
		reportLog("Verify title : " + model.getTitle());	
		
		List<WebElement> links = getWebDriver().findElements(By.tagName("a"));
		Set<String> urls = super.getLinkList(links);
		reportLog("Verify broken links");	
		
		int broken_links = super.validateLinks(urls);
		if(broken_links > 0)
			this.reportFailLog(this.applicationUrl + model.getUrl() + "	Detection of broken links completed with " + broken_links + " broken links");
		else			
			this.reportLog(this.applicationUrl + model.getUrl() + "	No broken links detected");
	}	
	
}

