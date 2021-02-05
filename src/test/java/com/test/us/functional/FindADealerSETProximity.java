package com.test.us.functional;

import java.util.Collections;
import java.util.List;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.toy.api.functions.APIFunctions;
import com.toy.dataproviders.DataProviderFunctional;
import com.toy.pages.DealerPage;
import com.toy.pages.HomePage;
import com.toy.selenium.core.BaseTest;

public class FindADealerSETProximity extends BaseTest {
	private HomePage homePage;
	private DealerPage dealerPage;

	@BeforeMethod
	public void beforeMethod() {
		homePage = PageFactory.initElements(getWebDriver(), HomePage.class);
		dealerPage = PageFactory.initElements(super.getWebDriver(), DealerPage.class);
	}

	@Test(dataProvider = "FindADealerSETProximity", dataProviderClass = DataProviderFunctional.class, priority = 1, enabled = true)
	public void verifyAdealerDistanceForAllZipcodeTest(String[] data) {
		int start = Integer.parseInt(data[1]);
		int end = Integer.parseInt(data[2]);

		String newUrl = getWebDriver().getCurrentUrl() + "?zipcode=" + data[0];
		getWebDriver().navigate().to(newUrl);
		super.reportLog("Navigated url using zip code");

		homePage.gotofindDealers();
		super.reportLog("Navigated find a dealer page");

		dealerPage.clickOnViewMoreIfAvailable();
		super.reportLog("Click on view more button");

		List<String> distances = dealerPage.getDistanceOfDealers();
		boolean status = false;
		for (String distance : distances) {
			double distance1 = Double.parseDouble(distance);
			if (distance1 < start)
				status = true;
		}
		if (status) {
			for (String distance : distances) {
				double distance1 = Double.parseDouble(distance);
				Assert.assertTrue(distance1 < start, "Distance is greater than " + start
						+ " miles when some distance less than " + start + " miles");
			}
		} else {
			for (String distance : distances) {
				double distance1 = Double.parseDouble(distance);
				Assert.assertTrue(distance1 > start, "Distance is less than " + start + " miles");
				Assert.assertTrue(distance1 < end, "Distance is greater than " + end + " miles");
			}
		}
	}

	@Test(dataProvider = "PMADealerProximity", dataProviderClass = DataProviderFunctional.class, priority = 1, enabled = true)
	public void DealerLocatorPMATest(String[] data) throws Exception {
		List<String> dealers = APIFunctions.getPMADealers(data[0]);
		super.reportLog("Get all dealer name from API which PMA is true");

		String newUrl = getWebDriver().getCurrentUrl() + "?zipcode=" + data[0];
		getWebDriver().navigate().to(newUrl);
		super.reportLog("Navigated url using zip code");

		homePage.gotofindDealers();
		super.reportLog("Navigated find a dealer page");

		List<String> dealersList = dealerPage.getDealersName();
		super.reportLog("Get all dealer name displaying default");

		Collections.sort(dealers);
		Collections.sort(dealersList);
		Assert.assertEquals(dealers, dealersList);
		super.reportLog("Verify dealer list for PMA true");

		dealerPage.verifyViewMoreButton();
		super.reportLog("Verify View more button");
	}
}