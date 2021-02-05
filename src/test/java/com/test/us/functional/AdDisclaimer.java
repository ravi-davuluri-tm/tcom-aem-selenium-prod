package com.test.us.functional;

import java.util.List;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

import com.toy.dataproviders.DataProvidersUs;
import com.toy.pages.DisclaimerPage;
import com.toy.selenium.core.BaseTest;

@Guice
public class AdDisclaimer extends BaseTest {

	

	@Test(dataProvider = "Disclaimer", dataProviderClass = DataProvidersUs.class)
	public void VerifyDisclaimerSlide(String name, String uri) throws Exception {
		getWebDriver().navigate().to(super.applicationUrl + "/" + uri);
		DisclaimerPage disclaimerPage = PageFactory.initElements(getWebDriver(), DisclaimerPage.class);
		List<String> disclaimerList = disclaimerPage.getDisclaimerList();
		System.out.println(disclaimerList);

		for (String str1 : disclaimerList) {
			System.out.println("length: " + str1.length());
			if (str1.length() < 6)
				Assert.assertTrue(false, "Uri " + uri + "  Disclaimer window is blank " + str1.length());
			// getWebDriver().switchTo().activeElement();
			// disclaimerPage.closeAdDisclaimerDialog();
		}
		Thread.sleep(5000);
	}

	/*
	@Test(dataProvider = "Disclaimer", dataProviderClass = DataProvidersUs.class, enabled=false)
	public void testDisclaimer(String name, String uri, String disclaimerText) throws Exception {
		getWebDriver().navigate().to(super.applicationUrl + "/" + uri);
		DisclaimerPage disclaimerPage = PageFactory.initElements(getWebDriver(), DisclaimerPage.class);
		List<String> disclaimerList = disclaimerPage.getDisclaimerList();
		String[] str = disclaimerText.replaceAll("[^a-zA-Z0-9]", "").split("\n");
		System.out.println("str: " + str);
		for (String str1 : str) {
			Assert.assertTrue(disclaimerList.contains(str1), "Uri " + uri + "  not contains disclaimer " + str1);

		}

	}
	*/
}