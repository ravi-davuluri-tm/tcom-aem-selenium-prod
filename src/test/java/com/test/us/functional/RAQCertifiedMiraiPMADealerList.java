package com.test.us.functional;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import com.toy.pages.HomePage;
import com.toy.pages.RequestAQuotePage;
import com.toy.report.TestListener;
import com.toy.selenium.core.BaseTest;
import com.toy.utilities.Verifier;

@Listeners(TestListener.class)
public class RAQCertifiedMiraiPMADealerList extends BaseTest {

	protected Verifier verify;

	@BeforeMethod
	public void initVerifyObject(Method method) {
		verify = new Verifier();
	}

	@Test(dataProvider = "Mirai-zipcode-provider1")
	public void RAQStandAloneCertifiedMiraiPMADealerListTest(String mzipcode) throws Exception {

		List<String> vendorsName = new ArrayList<String>(Arrays.asList("Hamer Toyota", "Toyota Santa Monica",
				"Longo Toyota", "Toyota of Orange", "Tustin Toyota", "Stevens Creek Toyota", "Toyota Sunnyvale",
				"Dublin Toyota", "San Francisco Toyota", "Roseville Toyota"));
		Collections.sort(vendorsName);
		HomePage homePage = PageFactory.initElements(getWebDriver(), HomePage.class);
		RequestAQuotePage requestAQuotePage = PageFactory.initElements(getWebDriver(), RequestAQuotePage.class);

		String newUrl = getWebDriver().getCurrentUrl() + "?zipcode=" + mzipcode;
		getWebDriver().navigate().to(newUrl);
		reportLog("Navigate Url with zip code " + mzipcode);

		homePage.selectShoppingToolMenu();
		reportLog("click on shopping tool menu");

		requestAQuotePage = homePage.gotoRequestQuote();
		reportLog("click on request a quote menu");

		String series = "2020 MIRAI";
		String trim = "MIRAI — FUEL CELL";
		homePage.sleepInSecond(3);
		requestAQuotePage.selectSeries(series);
		requestAQuotePage.selectTrim(trim);

		try {
			requestAQuotePage.changeZipCode(mzipcode);
			List<String> vendorsNameActual = requestAQuotePage.captureAllVendors();
			System.out.println(vendorsNameActual.toString());
			Collections.sort(vendorsNameActual);
			verify.assertEquals(vendorsNameActual, vendorsName, "Mirai PMA Dealer Failed for zipcode : " + mzipcode);
		} catch (Exception ex) {
			System.out.println(ex);
		}
	}

	@DataProvider(name = "Mirai-zipcode-provider1")
	public Object[][] getDataFromDataprovider1() {
		return new Object[][] { { "90210" }, { "94115" }, { "97204" }, { "80203" }, { "10010" }, { "02108" },
				{ "60608" }, { "45212" }, { "64106" }, { "30303" }, { "78726" }, { "23220" } };
	}

	@Test(dataProvider = "Mirai-zipcode-provider2")
	public void RAQContactaDealerCertifiedMiraiPMADealerListTest(String mzipcode) throws Exception {

		List<String> vendorsName = new ArrayList<String>(Arrays.asList("Hamer Toyota", "Toyota Santa Monica",
				"Longo Toyota", "Toyota of Orange", "Tustin Toyota", "Stevens Creek Toyota", "Toyota Sunnyvale",
				"Dublin Toyota", "San Francisco Toyota", "Roseville Toyota"));
		Collections.sort(vendorsName);
		HomePage homePage = PageFactory.initElements(getWebDriver(), HomePage.class);
		RequestAQuotePage requestAQuotePage = PageFactory.initElements(getWebDriver(), RequestAQuotePage.class);

		String newUrl = getWebDriver().getCurrentUrl() + "/contact-a-dealer/?zipcode=" + mzipcode;
		getWebDriver().navigate().to(newUrl);
		reportLog("Navigate Url with zip code " + mzipcode);

		String series = "2020 MIRAI";
		String trim = "MIRAI — FUEL CELL";
		homePage.sleepInSecond(3);
		requestAQuotePage.selectSeries(series);
		requestAQuotePage.selectTrim(trim);

		try {
			requestAQuotePage.changeZipCode(mzipcode);
			List<String> vendorsNameActual = requestAQuotePage.captureAllVendors();
			System.out.println(vendorsNameActual.toString());
			Collections.sort(vendorsNameActual);
			verify.assertEquals(vendorsNameActual, vendorsName, "Mirai PMA Dealer Failed for zipcode : " + mzipcode);
		} catch (Exception ex) {
			System.out.println(ex);
		}
	}

	@DataProvider(name = "Mirai-zipcode-provider2")
	public Object[][] getDataFromDataprovider2() {
		return new Object[][] { { "90210" }, { "94115" }, { "97204" }, { "80203" }, { "10010" }, { "02108" },
				{ "60608" }, { "45212" }, { "64106" }, { "30303" }, { "78726" }, { "23220" } };
	}

	@AfterMethod
	public void afterMethod() throws Exception {
		verify.assertAll();
	}

}