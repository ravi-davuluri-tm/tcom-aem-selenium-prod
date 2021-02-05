package com.test.us.functional;

import java.util.Set;

import org.openqa.selenium.Cookie;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.toy.dataproviders.DataProviderFunctional;
import com.toy.selenium.core.BaseTest;

public class VerifyTDACookieGXP extends BaseTest {

	String zipCode = "75010";

	@Test(dataProvider = "TDACookie", dataProviderClass = DataProviderFunctional.class)
	public void VerifyTDACookieGXPTest(String[] data) throws Exception {
		getWebDriver().manage().deleteAllCookies();
		String newUrl = getWebDriver().getCurrentUrl() + "?zipcode=" + data[0];
		getWebDriver().navigate().to(newUrl);
		super.reportLog("Navigate Url with zip code " + zipCode);
		Thread.sleep(5000);
		String tdaValue = "";
		Set<Cookie> cookie = getWebDriver().manage().getCookies();
		for (Cookie ck : cookie) {
			if (ck.getName().equals("tda")) {
				tdaValue = ck.getValue();
			}
		}
		System.out.println(tdaValue + " "+data[1]);
		Assert.assertEquals(tdaValue, data[1]);
	}
}