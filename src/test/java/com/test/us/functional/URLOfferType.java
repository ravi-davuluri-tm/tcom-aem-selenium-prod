package com.test.us.functional;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import com.toy.dataproviders.DataProviderFunctional;
import com.toy.pages.HomePage;
import com.toy.pages.RequestAQuotePage;
import com.toy.selenium.core.BaseTest;

public class URLOfferType extends BaseTest {
	private RequestAQuotePage requestAQuotePage;
	
	@Test(dataProvider = "OfferType", dataProviderClass = DataProviderFunctional.class)
	public void HomePageURLOfferTest(String[] data) throws Exception
	{
		homePage = PageFactory.initElements(getWebDriver(), HomePage.class);
		requestAQuotePage = PageFactory.initElements(getWebDriver(), RequestAQuotePage.class);
		requestAQuotePage.submitZipCode(data[0]);
		String url = applicationUrl + "?forceOfferType="+data[2];
		getWebDriver().navigate().to(url);
		reportLog("Verified MPL "+data[2] + " for series "+data[1]);
		
		homePage.verifyOfferHomePage(data[2]);
		reportLog("Verified MPL "+data[2] + " for series "+data[1]);
	}
	
	@Test(dataProvider = "OfferType", dataProviderClass = DataProviderFunctional.class)
	public void MLPURLOfferTest(String[] data) throws Exception{
		homePage = PageFactory.initElements(getWebDriver(), HomePage.class);
		requestAQuotePage = PageFactory.initElements(getWebDriver(), RequestAQuotePage.class);
		requestAQuotePage.submitZipCode(data[0]);
		String url = applicationUrl +"/"+ data[1]+"/?forceOfferType="+data[2];
		getWebDriver().navigate().to(url);
		reportLog("Navigated url for MPL "+data[2] + " and series "+data[1]);
		
		homePage.verifyOfferType(data[2]);
		reportLog("Verified MPL "+data[2] + " for series "+data[1]);
	}
}
