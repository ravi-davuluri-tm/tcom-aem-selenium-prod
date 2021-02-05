package com.toy.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import com.toy.datamodel.RequestQuoteModel;
import com.toy.selenium.core.BasePage;
import com.toy.utilities.Utilities;

public class AccessoriesPage extends BasePage {

	public AccessoriesPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(css = "[data-di-id='#tcom-toggle-link-how-to-buy']")
	private WebElement linkHowToBuy;

	@FindBy(css = "[data-di-id='#tfresh-btn-tfresh-btn-one-build-button']")
	private WebElement buildButton;

	@FindBy(css = "[data-di-id='#tfresh-btn-tfresh-btn-two-build-button']")
	private WebElement findADealerButton;
	
	@FindBy(css = "[data-di-id='#tfresh-btn-tfresh-btn-two-build-button-2']")
	private WebElement buyAccessoriesButton;

	public void selectAccessoriesOfVehicle(RequestQuoteModel raqModel) {
		String year = Utilities.getSeriesYear(raqModel);
		String name = Utilities.getSeriesName(raqModel);
		String locator = "//*[@class='modal-info'][span[text()='" + year + "']][span[text()='" + name
				+ "']]/following-sibling::span[text()='View Accessories']";
		waitForElement(locator);
		clickOn(locator);
	}

	public void clickOnLinkHowToBuy() {
		waitAndClick(linkHowToBuy);
	}

	public void clickOnBuildButton() {
		waitAndClick(buildButton);
	}

	public void clickOnBuyAccessories() {
		waitAndClick(buyAccessoriesButton);
	}
	
	public void clickOnFindADealerButton() {
		waitAndClick(findADealerButton);
	}
	
	public void verfiyButtons() {
		Assert.assertTrue(isElementDisplay(buildButton), "Build button not found");
		Assert.assertTrue(isElementDisplay(findADealerButton), "Find A dealer button not found");
		Assert.assertTrue(isElementDisplay(buyAccessoriesButton), "Buy Accessries Button not found");
	}
}