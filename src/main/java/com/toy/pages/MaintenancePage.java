package com.toy.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import com.toy.selenium.core.BasePage;

public class MaintenancePage extends BasePage {

	public MaintenancePage(WebDriver driver) {
		super(driver);		
	}
	
	@FindBy(xpath = "//h1/span")
	private WebElement thankMsg;
	
	@FindBy(xpath = "//*[@id='footerWrapper']/span[text()='Toyota']")
	private WebElement toyotaLogo;
	
	public String getThankMessage() {
		return getText(thankMsg);
	}
	
	public void verifyLogoImage() {
		Assert.assertTrue(isElementDisplay(toyotaLogo), "Toyota Logo is  not appear");
	}
	

}