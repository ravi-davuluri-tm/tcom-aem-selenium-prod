package com.test.us.functional;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.toy.dataproviders.DataProviderFunctional;
import com.toy.pages.HomePage;
import com.toy.pages.RequestAQuotePage;
import com.toy.selenium.core.BaseTest;

public class MultiTDA extends BaseTest {

	@BeforeMethod
	public void setup() {
		getWebDriver().manage().deleteAllCookies();
	}
	
	@Test(dataProvider = "MultiTDA", dataProviderClass = DataProviderFunctional.class, priority = 1, enabled = true)
	public void multiTDAModalDisplayTest(String[] data) throws InterruptedException {
		HomePage homePage = PageFactory.initElements(getWebDriver(), HomePage.class);

		String newUrl = getWebDriver().getCurrentUrl()+  "?zipcode=" + data[0];
		getWebDriver().navigate().to(newUrl);

		if(data[1].equalsIgnoreCase("true")) {
			
			String zipCodeActual = homePage.getZipCodeFromPopUp();
			Assert.assertEquals(zipCodeActual, data[0]);
			super.reportLog("verify multi-TDA zip code in popup model");

		
			String headerTitle = homePage.getModelTitleText();
			Assert.assertEquals("Select Your Dealer", headerTitle);
			super.reportLog("Verify MultiTDA model popup header text");

			
			String zipCode = homePage.getDealerZipCode();
			String dealerNameExpected = homePage.getDealerName();
			
			homePage.selectDealerFromModelPopUp();
			String updatedZip = homePage.getZipCode();
			Assert.assertEquals(updatedZip, zipCode);
			super.reportLog("Get zipcode from address in modal and verify zipcode ");

			
			homePage.selectShoppingToolMenu();
			RequestAQuotePage raqPage = homePage.gotoRequestQuote();
			super.reportLog("Navigate Request A Quote menu from shopping tool menu");
			
			String dealerNameActual = raqPage.getVendorName();
			Assert.assertEquals(dealerNameExpected, dealerNameActual);
			super.reportLog("Verify first dealer name match is a multi TDA dealer");
		}
		else {
			Assert.assertFalse(homePage.isModelPopUp(), "Model popup is opened for zip code "+data[0]);
		}
	}	

	@Test(priority = 2, enabled = true)
	public void multiTDAChangeDealerTest() {
		HomePage homePage = PageFactory.initElements(getWebDriver(), HomePage.class);
		String zipCode1 = "92088";
		String zipCode2 = "08541";

		String newUrl = getWebDriver().getCurrentUrl() + "?zipcode="+zipCode1;
		getWebDriver().navigate().to(newUrl);

		String zipCodeActual = homePage.getZipCodeFromPopUp();
		String dealerName1 = homePage.getDealerName();
		Assert.assertEquals(zipCodeActual, zipCode1);
		super.reportLog("verify zip code in popup model");

		homePage.changeZipCodeFromPopUp(zipCode2);
		homePage.sleepInSecond(2);
		super.reportLog("Change zip code");	

		String changedZipCode = homePage.getZipCodeFromPopUp();
		String dealerName2 = homePage.getDealerName();

		Assert.assertEquals(changedZipCode, zipCode2);
		super.reportLog("verify zip code in popup model after change");

		Assert.assertNotEquals(dealerName2, dealerName1);
		super.reportLog("verify dealer name changed");

	}

}