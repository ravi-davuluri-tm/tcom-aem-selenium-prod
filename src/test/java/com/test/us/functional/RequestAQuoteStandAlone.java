package com.test.us.functional;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.toy.pages.HomePage;
import com.toy.pages.RequestAQuotePage;
import com.toy.selenium.core.BaseTest;

public class RequestAQuoteStandAlone extends BaseTest {
	
	String zipCode = "90010";
	String seriesName = "2021 AVALON";
	String modelName = "LIMITED HYBRID — 2.5-LITER 4-CYLINDER ECVT HYBRID";
	
	@Test
	public void verifySelectedSeriesTrimColorTest() {
		
		HomePage homePage = PageFactory.initElements(getWebDriver(), HomePage.class);
		RequestAQuotePage requestAQuotePage = PageFactory.initElements(getWebDriver(), RequestAQuotePage.class);

		String newUrl = getWebDriver().getCurrentUrl() + "?zipcode="+zipCode;
		getWebDriver().navigate().to(newUrl);
		reportLog("Navigate Url with zip code "+zipCode);

		homePage.selectShoppingToolMenu();
		reportLog("click on shopping tool menu");
		
		requestAQuotePage = homePage.gotoRequestQuote();
		reportLog("click on request a quote menu");
		requestAQuotePage.sleepInSecond(2);
		
		requestAQuotePage.selectSeries(seriesName);
		requestAQuotePage.sleepInSecond(2);
		reportLog("select series from drop down "+seriesName);
		
		String seriesColor = requestAQuotePage.getSeriesOptionColor(seriesName);
		Assert.assertTrue(seriesColor.contains("rgb(204, 0, 0)"), "Selected series color is not red rgb(204, 0, 0)  actual "+seriesColor);
		System.out.println(seriesColor);
		reportLog("Verified selected series color is red ");
		
		requestAQuotePage.selectTrim(modelName);
		requestAQuotePage.sleepInSecond(2);
		reportLog("select model from drop down "+modelName);
				
		String modelColor = requestAQuotePage.getSelectedModelColor(modelName);
		System.out.println(modelColor);	
		Assert.assertTrue(modelColor.contains("rgb(204, 0, 0)"), "Selected Model color is not red rgb(204, 0, 0)  actual "+modelColor);
		reportLog("Verified selected model color is red ");
	}
	
}