package com.test.us.functional;

import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.toy.selenium.core.BaseTest;

public class ABVerify extends BaseTest {

	@Test
	public void abTest() {
		int countDelayed = 0;
		int countDefault = 0;
		for (int i = 0; i < 100; i++) {
			getWebDriver().manage().deleteAllCookies();
			getWebDriver().navigate().to(applicationUrl);
			JavascriptExecutor js = (JavascriptExecutor) getWebDriver();
			Object abMboxRecipe = js.executeScript("return window.mboxRecipe");
			if (abMboxRecipe != null) {
				System.out.println(abMboxRecipe.toString().contains("ensighten-delay-default"));
				if (abMboxRecipe.toString().contains("ensighten-delay-delayed")) {
					countDelayed = countDelayed + 1;
				}
				if (abMboxRecipe.toString().contains("ensighten-delay-default")) {
					countDefault = countDefault + 1;
				}
				System.out.println(i + " " + abMboxRecipe.toString());
				System.out.println("delayed count " + countDelayed + " default count " + countDefault);
			}
		}
		int totalCount = countDefault + countDelayed;
		Assert.assertTrue(totalCount >= 5, "delayed and default count is less than 5%");
		Assert.assertTrue(countDelayed >= 1, "delayed not found");
		Assert.assertTrue(countDefault >= 1, "default not found");
	}

}