package com.toy.pages;

import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.toy.selenium.core.BasePage;

public class DealerPage extends BasePage {

	public DealerPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(css = "[class='name']")
	private WebElement dealerNameElement;

	@FindBy(xpath = "//a[text()='Dealer Details']")
	private WebElement dealerDetailsElement;

	@FindBy(css = "[data-form-type=\"REQUEST_QUOTE\"]")
	private WebElement raqButton;

	@FindBy(css = "[aria-label='close overlay']")
	private WebElement closeOverLay;

	@FindBy(css = "[class='view-more-btn']")
	private WebElement viewMoreButton;

	@FindBy(css = "[class='distance']")
	private List<WebElement> distanceLocators;

	@FindBy(css = "[class='name']")
	private List<WebElement> dealerName;
	
	public void clickOnViewMoreIfAvailable() {
		while(isElementDisplay(viewMoreButton)) {
			clickOn(viewMoreButton);
			sleepInSecond(1);
		}
	}

	public void verifyViewMoreButton() {
		Assert.assertTrue(isElementDisplay(viewMoreButton), "Vew more button not displaying");
	}

	public List<String> getDealersName() {
		List<String> dealersName = new ArrayList<String>();
		for(WebElement element : dealerName) {
			String name = getText(element);		
			dealersName.add(name);
		}
		return dealersName;
	}
	
	public List<String> getDistanceOfDealers() {
		List<String> distances = new ArrayList<String>();
		for(WebElement element : distanceLocators) {
			String distance = getText(element);
			String distance1 = distance.split(" ")[0];
			distances.add(distance1.substring(1, distance1.length()));
		}
		return distances;
	}
	
	public void clickOnRaqButton() {
		waitForElement(raqButton);
		clickOn(raqButton);
	}

	public void clickOnCloseButton() {
		waitForElement(closeOverLay);
		clickOn(closeOverLay);
	}
	/**
	 * Click on RAQ button at dealer page
	 * 
	 * @param dealerCode
	 */
	public RequestAQuotePage clickRAQButton(String dealerCode) {
		String locator = "//button[@dealerid='" + dealerCode + "']";
		super.waitForElement(locator);
		super.clickOn(locator);
		return PageFactory.initElements(driver, RequestAQuotePage.class);
	}

	/***
	 * Get Dealer name
	 * 
	 * @return
	 */
	public String getDealerName() {
		waitForElement(dealerNameElement);
		return getText(dealerNameElement);
	}
	
	public void clickOnFirstDealer() {
		waitForElement(dealerNameElement);
		clickOn(dealerNameElement);
	}

	public void clickOnDealerDetails() {
		waitForElement(dealerDetailsElement);
		clickOn(dealerDetailsElement);
	}

	/***
	 * Get Dealer name from dealers page
	 * 
	 * @return
	 */
	public String getDealerNameFromDealersPage(String dealerCode) {
		String locator = "[href='/dealers/dealer/" + dealerCode + "'][class='name']";
		waitForElement(locator);
		return getText(driver.findElement(By.cssSelector(locator)));
	}

}