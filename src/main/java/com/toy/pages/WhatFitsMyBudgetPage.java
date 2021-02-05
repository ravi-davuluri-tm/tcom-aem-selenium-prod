package com.toy.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import com.toy.selenium.core.BasePage;

public class WhatFitsMyBudgetPage extends BasePage {
	
	public WhatFitsMyBudgetPage(WebDriver driver) {
		super(driver);	
	}
	
	@FindBy(css= "button[class*='BudgetDesktop_viewOffersCta']")
	private WebElement viewResultButton;
	
	@FindBy(css= "button[class*='BudgetDesktop_additionalOptionsBtn']")
	private WebElement additionalBudgetOptions;
	
	@FindBy(css= "[id='wfmb-iframe']")
	private WebElement wfmbIframe;
		
	@FindBy(xpath= "//*[contains(@class, 'VehicleCard_header')]/div[1]/div[1]")
	private List<WebElement> vehicleOptions;
	
	public void selectWhatFitsMyBudgetFrame() {
		waitForElement(wfmbIframe);
		selectFrameById(wfmbIframe);
	}
	
	public void clickOnViewResult() {
		waitAndClick(viewResultButton);
	}
	
	public void clickOnAdditionalBudgetOptions() {
		waitAndClick(additionalBudgetOptions);
	}
	
	public void unSelectAddionalBudgetOptions(String option) {
		String locator = "[aria-controls='"+option+"'] input";
		WebElement element = driver.findElement(By.cssSelector(locator));
		if(isCheckBoxSelected(element))
			clickOn(element);		
	}
	
	public void selectAddionalBudgetOptions(String option) {
		String locator = "[aria-controls='"+option+"'] input";
		WebElement element = driver.findElement(By.cssSelector(locator));
		if(!isCheckBoxSelected(element))
			clickOn(element);		
	}
	
	public void unSelectAllAdditionalOption() {
		unSelectAddionalBudgetOptions("Cars & Minivan");
		unSelectAddionalBudgetOptions("Trucks");
		unSelectAddionalBudgetOptions("Crossovers & SUVs");
		unSelectAddionalBudgetOptions("Hybrids & EVs");
		unSelectAddionalBudgetOptions("finance");
		unSelectAddionalBudgetOptions("lease");
	}
	
	public void verifyFilterOptions(List<String> options) {
		for(WebElement element : vehicleOptions ) {
				String text = getText(element);
				Assert.assertTrue(options.contains(text.toLowerCase()),text + " not filtered");
		}
	}
	
}