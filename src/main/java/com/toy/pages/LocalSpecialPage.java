package com.toy.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;

import com.toy.datamodel.RequestQuoteModel;
import com.toy.selenium.core.BasePage;

public class LocalSpecialPage extends BasePage {

	public LocalSpecialPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(css = "[class='view-details-link'] span")
	private WebElement viewDetail;

	// @FindBy(xpath="//*[contains(@class, 'slick-current
	// slick-center')]//a[text()='Request A Quote']")
	@FindBy(css = "[class*='offer-modal active'] [data-atid='raq_incentive.raqanchor']")
	private WebElement requestAQuoteButton;

	@FindBy(css = "[class='tcom-submit active']")
	private WebElement submitZipCodeButton;

	@FindBy(css = "[placeholder='Zip Code']")
	private WebElement zipInputBox;

	@FindBy(css = "[class='tcom-incentive-detail'] [class='standard-offer']")
	private WebElement seriesNameField;

	private final String str = "//*[contains(@class, 'offer-modal active')]//*[@class='standard-offer']";
	@FindBy(xpath = str)
	private WebElement seriesNameFieldEsp;

	@FindBy(css = "[data-type='APR'] [class='incentive-description']")
	private WebElement offerAPRDescription;

	@FindBy(css = "[data-type='Lease'] [class='incentive-description']")
	private WebElement offerLeaseDescription;

	@FindBy(css = "[data-type='Cash'] [class='incentive-description']")
	private WebElement offerCashDescription;

	@FindBy(xpath = "//*[contains(@class, 'offer-modal active')]//*[@class='incentive-description']")
	private WebElement descriptionElement;
	
	@FindBy(css="[class='details-link']")
	private WebElement vieweDetailElement;

	@FindBy(css = "[aria-label='close overlay']")
	private WebElement closeOverLay;

	@FindBy(css="[class='request-quote-title'] p")
	private WebElement raqTitleElement;

	@FindBy(css="[class='location-cta--desktop'] [class='tcom-zipcode-changer-input sub-tablet']")
	private WebElement locationZipInput;

	public String verifyHeaderText() {
		String locator = "//div[@id='root']/div/nav/div[2]/span/div";
		waitForElement(locator);
		return getText(driver.findElement(By.xpath(locator)));

	}
	
	public String getLocationZip() {
		return getAttribute(locationZipInput, "value");
	}
	
	public String getRaqTitle() {
		return getText(raqTitleElement);
	}

	public void clickOnCloseButton() {
		waitForElement(closeOverLay);
		clickOn(closeOverLay);
	}

	public void clickOnViewDetail() {
		waitForElement(vieweDetailElement);
		clickOn(vieweDetailElement);
	}
	
	public String getSeriesName(){
		String text = null;
		waitForElementVisible(seriesNameFieldEsp);
		try{
			text = getText(seriesNameFieldEsp);
		}
		catch(StaleElementReferenceException ex){
			text = getText(seriesNameFieldEsp);
		}
		return text;
	}

	public String getDescription(){
		waitForElementVisible(descriptionElement);
		return getText(descriptionElement).trim();
	}

	public String getSeriesNameEsp() {
		try{
			waitForElementVisible(seriesNameFieldEsp);
		}
		catch(StaleElementReferenceException ex){
			waitForElementVisible(seriesNameFieldEsp);
		}
		String seriesYear = str + "//*[@class='series-year']";
		String seriesId = str + "//*[@class='series-id']";
		//WebElement elementYear = driver.findElement(By.xpath(seriesYear));
		//WebElement elementId = driver.findElement(By.xpath(seriesId));
		try {
			return driver.findElement(By.xpath(seriesYear)).getText() + " " + driver.findElement(By.xpath(seriesId)).getText();
		}catch(StaleElementReferenceException ex ) {
			return driver.findElement(By.xpath(seriesYear)).getText() + " " + driver.findElement(By.xpath(seriesId)).getText();
		}
	}

	public String getOfferDescription(RequestQuoteModel data) {

		if (data.getOfferRibbon() == "FINANCE") {
			return offerAPRDescription.getText();
		} else if (data.getOfferRibbon() == "LEASE") {
			return offerLeaseDescription.getText();
		} else if (data.getOfferRibbon() == "CASH BACK") {
			return offerCashDescription.getText();
		}
		return "invalid offer type";

	}

	/**
	 * Click on View detail button of first product
	 */
	public LocalSpecialPage clickViewDetails(RequestQuoteModel data) {

		String locator = "//*[@class='offer-ribbon' and text()=' " + data.getOfferRibbon().toLowerCase()
				+ " ']/parent::div/button[@class='view-details-link']";
		waitForElement(locator);
		WebElement element = driver.findElement(ByLocator(locator));
		javaScriptClick(element);
		return this;
	}

	/**
	 * Click on View detail button of first product
	 */
	public RequestAQuotePage clickRequestAQuoteButton() {
		super.waitAndClick(requestAQuoteButton);
		return PageFactory.initElements(driver, RequestAQuotePage.class);
	}

}