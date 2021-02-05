package com.test.us.functional;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.toy.pages.HomePage;
import com.toy.pages.RequestAQuotePage;
import com.toy.selenium.core.BaseTest;

public class RAQSETRules extends BaseTest {
	String zipcodeSet = "33033";
	String zipCodeNormal = "75001";

	@Test
	public void RAQSETRulesTest() {
		HomePage homePage = PageFactory.initElements(getWebDriver(), HomePage.class);

		String newUrl = getWebDriver().getCurrentUrl() + "?zipcode="+zipcodeSet;
		getWebDriver().navigate().to(newUrl);
		super.reportLog("navigate url with zipcode "+zipcodeSet);

		homePage.selectShoppingToolMenu();
		super.reportLog("click on shopping tool menu");

		RequestAQuotePage requestAQuotePage = homePage.gotoRequestQuote();
		super.reportLog("click on request a quote menu");

		boolean status = requestAQuotePage.isVendorSelected();
		Assert.assertFalse(status, "Vendor is selected");
		super.reportLog("verify vendor is not selected");

		newUrl = getWebDriver().getCurrentUrl() + "?zipcode="+zipCodeNormal;
		getWebDriver().navigate().to(newUrl);
		super.reportLog("navigate url with zipcode "+zipCodeNormal);

		homePage.selectShoppingToolMenu();
		super.reportLog("click on shopping tool menu");

		requestAQuotePage = homePage.gotoRequestQuote();
		super.reportLog("click on request a quote menu");

		status = requestAQuotePage.isVendorSelected();
		Assert.assertTrue(status, "Vendor is not selected");
		super.reportLog("verify vendor is selected");
	}

}