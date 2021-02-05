package com.toy.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import com.toy.selenium.core.BasePage;

public class CommonPage extends BasePage {

	public CommonPage(WebDriver driver) {
		super(driver);
	}
	
	//*************************** compare/corolla-vs-civic/ ***********************
	@FindBy(css = "[class='cs-welcome-image-desktop']")
	private WebElement desktopImage1;
	
	public void expandAllReadMoreKeyBenefitsButton() {
		String locator = "[class='vis-accordion-expand']";
		List<WebElement> elements = driver.findElements(By.cssSelector(locator));
		for(WebElement element : elements) {
			clickOn(element);
		}
		String locator1 = "[class='vis-accordion-expand'][style=\"display: none;\"]";
		List<WebElement> elementAfterClick = driver.findElements(By.cssSelector(locator1));
		Assert.assertEquals(elements.size(), elementAfterClick.size() , "Some 'Read more key benefits' button not clicked");
	}
	
	public void verifyFirstMenuButtons(List<String> menus) {
		for(String menu : menus) {
			String locator = "//*[@id='next-steps']//a/span[text()='"+menu+"']";
			WebElement element  = driver.findElement(By.xpath(locator));
			Assert.assertTrue(isElementPresent(element), "Menu " +menu+ " not present at page");
		}
	}
	
	
	public void verifySecondMenusButtons(List<String> menus) {
		for(String menu : menus) {
			String locator = "//*[@id='next-steps_bottom']//span[text()='"+menu+"']";
			WebElement element  = driver.findElement(By.xpath(locator));
			Assert.assertTrue(isElementPresent(element), "Menu " +menu+ " not present at page");
		}
	}
	
	
	//*********************** connected-services  page *******************************
	
	@FindBy(css = "[class='cs-welcome-image-desktop']")
	private WebElement desktopImage;
	
	@FindBy(css = "[id='wrapper'] [aria-label='Toyota App']")
	private WebElement ToyotaAppIcon;
	
	@FindBy(css = "[id=\"wrapper\"] [aria-label=\"Safety Connect<sup>®</sup>\"]")
	private WebElement SafetyConnectIcon;
	
	@FindBy(css = "[id='wrapper'] [aria-label='Remote Connect']")
	private WebElement RemoteConnectIcon;
	
	@FindBy(css = "[id='wrapper'] [aria-label='Service Connect']")
	private WebElement ServiceConnectIcon;
	
	@FindBy(css = "[id='wrapper'] [aria-label='Destination Assist']")
	private WebElement DestinationAssistIcon;
	
	@FindBy(css = "[id='wrapper'] [aria-label='Wi-Fi Connect']")
	private WebElement WiFiConnectIcon;
	
	public void verifyServicesButtonOptions() {
		Assert.assertTrue(isElementPresent(ToyotaAppIcon),  "Verify toyota App icon not present");
		Assert.assertTrue(isElementPresent(SafetyConnectIcon),  "Safety Connect icon not present");
		Assert.assertTrue(isElementPresent(ServiceConnectIcon),  "Service Connect icon not present");
		Assert.assertTrue(isElementPresent(RemoteConnectIcon),  "Remote Connect Icon not present");
		Assert.assertTrue(isElementPresent(DestinationAssistIcon),  "Destination Assist icon not present");
		Assert.assertTrue(isElementPresent(WiFiConnectIcon),  "WiFiConnect Icon not present");
	}
	
	public void verifyMobileImage() {
		Assert.assertTrue(isElementPresent(desktopImage),  "mobile image icon not present");
	}
	
	public void verifyMidButton(List<String> subMenus) {
		for(String name : subMenus) {
			WebElement subMenuElement= driver.findElement(By.xpath("//*[@class='tcom-support-image-ctas']//p[contains(text(), '"+name+"')]"));
			Assert.assertTrue(isElementPresent(subMenuElement), "Sub menu not found "+name);
		}
		//String locator = "//*[@class=\"tcom-support-image-ctas\"]//p[contains(text(), 'Connected Services Support')]"
	}
	
	
	
	//*********************** connected-services toyotaapp page *******************************
	
	@FindBy(xpath = "//a[@href='https://play.google.com/store/apps/details?id=com.toyota.oneapp']")
	private WebElement googlePlayButton;
	
	
	@FindBy(xpath="//*[@href='https://apps.apple.com/us/app/toyota/id1455685357']")
	private WebElement appleStoreButton;
	
	
	public void verifyGoogleAppleButtons() {
		Assert.assertTrue(isElementPresent(googlePlayButton),  " Google play store button not present");
		Assert.assertTrue(isElementPresent(appleStoreButton),  " apple play store button  not present");
	}
	
	public void verifyFeatureButton(String buttonName) {
		String locator = "//*[@data-component='featurepanel']//h2[contains(text(), \"" +buttonName+ "\")]";
		Assert.assertTrue(isElementPresent(driver.findElement(By.xpath(locator))), buttonName + " not present");
	}
	
	
	
	
}