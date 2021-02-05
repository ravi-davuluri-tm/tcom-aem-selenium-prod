package com.test.espanol.functional;

import java.util.List;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

import com.toy.dataproviders.DataProvidersEsp;
import com.toy.dataproviders.DataProvidersUs;
import com.toy.pages.DisclaimerPage;
import com.toy.selenium.core.BaseTest;

@Guice
public class AdDisclaimerEsp extends BaseTest {
/*
	@Test(dataProvider = "Disclaimer", dataProviderClass = DataProvidersEsp.class)
	public void testAdDisclaimer(String name, String uri, String disclaimerText) throws Exception {
		getWebDriver().navigate().to(super.applicationUrl + "/" + uri);
		DisclaimerPage disclaimerPage = PageFactory.initElements(getWebDriver(), DisclaimerPage.class);
		List<String> disclaimerList = disclaimerPage.getDisclaimerList();
		
		System.out.println("A " + disclaimerList.toString().replaceAll("[^a-zA-Z0-9\\s+]", ""));
		System.out.println("E "+ disclaimerText.toString().replaceAll("[^a-zA-Z0-9\\s+]", ""));
		
		String[] str = disclaimerText.replaceAll("[^a-zA-Z0-9\\s+]", "").split("\n");
		for (String str1 : str) {
			
			Assert.assertTrue(disclaimerList.contains(str1), "Uri " + uri + "  not contains disclaimer " + str1);
		

	}
}
*/
	@Test(dataProvider = "DisclaimerEsp", dataProviderClass = DataProvidersEsp.class)
	public void testAdDisclaimerEsp(String name, String uri) throws Exception {
		getWebDriver().navigate().to(super.applicationUrl + "/" + uri);
		DisclaimerPage disclaimerPage = PageFactory.initElements(getWebDriver(), DisclaimerPage.class);
		List<String> disclaimerList = disclaimerPage.getDisclaimerList();
		System.out.println(disclaimerList);

		for (String str1 : disclaimerList) {
			System.out.println("length: " + str1.length());
			if (str1.length() < 6)
				Assert.assertTrue(false, "Uri " + uri + "  Disclaimer window is blank " + str1.length());

		}

	}
}