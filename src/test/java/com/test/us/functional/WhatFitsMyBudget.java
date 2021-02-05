package com.test.us.functional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import com.toy.pages.HomePage;
import com.toy.pages.WhatFitsMyBudgetPage;
import com.toy.selenium.core.BaseTest;

public class WhatFitsMyBudget extends BaseTest {
	
	String zipCode = "90010";
	
	@Test
	public void testWhatFitMyBudgetFilter() {

		HomePage homePage = PageFactory.initElements(getWebDriver(), HomePage.class);		
		String newUrl = getWebDriver().getCurrentUrl() + "?zipcode="+zipCode;
		getWebDriver().navigate().to(newUrl);
		reportLog("Navigate Url with zip code "+zipCode);

		homePage.selectShoppingToolMenu();
		reportLog("click on shopping tool menu");
		
		WhatFitsMyBudgetPage whatFitsBMyBudgetPage = homePage.goToWhatFitsMyBudgetPage();
		reportLog("click on what fits my budget menu");
		whatFitsBMyBudgetPage.selectWhatFitsMyBudgetFrame();
		whatFitsBMyBudgetPage.sleepInSecond(3);		
		whatFitsBMyBudgetPage.clickOnViewResult();
		reportLog("click on view result button");
	
		whatFitsBMyBudgetPage.clickOnAdditionalBudgetOptions();		
		reportLog("click and advance budget options");
		
		whatFitsBMyBudgetPage.unSelectAllAdditionalOption();
		reportLog("unselect all advance options");
		
		whatFitsBMyBudgetPage.selectAddionalBudgetOptions("lease");
		reportLog("select lease from advance options");
		
		whatFitsBMyBudgetPage.sleepInSecond(4);
		List<String> options1 = new ArrayList<String>( Arrays.asList("lease"));
		whatFitsBMyBudgetPage.verifyFilterOptions(options1);
		reportLog("Verify all selected vehicle are contains lease");
		
		whatFitsBMyBudgetPage.unSelectAddionalBudgetOptions("lease");
		reportLog("unselect lease from options");
		
		whatFitsBMyBudgetPage.selectAddionalBudgetOptions("finance");
		whatFitsBMyBudgetPage.sleepInSecond(4);
		reportLog("select fiance from filter options");
	
		List<String> options = new ArrayList<String>( Arrays.asList("finance"));
		whatFitsBMyBudgetPage.verifyFilterOptions(options);	
		reportLog("Verify all selected vehicle are contains finance");		
		
	}
}