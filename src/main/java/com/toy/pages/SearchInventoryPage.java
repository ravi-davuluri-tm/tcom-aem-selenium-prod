package com.toy.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.toy.datamodel.RequestQuoteModel;
import com.toy.selenium.core.BasePage;
import com.toy.utilities.Utilities;

public class SearchInventoryPage extends BasePage {

	public SearchInventoryPage(WebDriver driver) {
		super(driver);	
	}
	
	
	@FindBy(css = "[class^='tcom-vehicle-lineup-vehicles'] div[class='tcom-vehicle-card-image']:nth-child(1)")
	private WebElement firstCarImage;
	
	@FindBy(xpath = "//button[contains(text(), 'View Details')]")
	private WebElement viewDetailsButton;
	
	@FindBy(xpath = "//button[contains(text(), 'Ver detalles')]")
	private WebElement viewDetailsButtonEsp;

	@FindBy(xpath = "(//div[contains(text(), 'Total MSRP (As Built)')]/following-sibling::div)[2]")
	private WebElement priceElement;
	
	@FindBy(xpath = "//div[div[contains(text(), 'Total MSRP (As Built)')]]/div[2]")
	private WebElement priceElementMobile;
	
	//@FindBy(xpath = "(//div[text()='Total MSRP (tal cual)']/following-sibling::td)[2]")
	@FindBy(xpath="//*[@id='price-and-summary-desktop-container']//div[div[text()='Total MSRP (tal cual)']]/div[2]")
	private WebElement priceElementEsp;
	
	@FindBy(xpath = "(//div[text()='Total MSRP (tal cual)']/following-sibling::div)[1]")
	private WebElement priceElementEspMobile;
	
	@FindBy(xpath = "//a[contains(text(), 'View Local Offers')]")
	private WebElement viewLocalOffers;
	
	@FindBy(xpath = "(//a[contains(text(), 'Apply for Financing')])[2]")
	private WebElement applyForFinancing;
	
	@FindBy(xpath = "//a[contains(text(), 'Ver Ofertas Locales')]")
	private WebElement viewLocalOffersEsp;
	
	@FindBy(xpath = "(//a[contains(text(), 'Financiamiento')])[2]")
	private WebElement applyForFinancingEsp;
	
	@FindBy(css = "[class='tcom-inventory-inline-raq-title']")
	private WebElement raqFormTitle;
	
	@FindBy(css = "[data-target='#tcom-nav-zip-modal'] [class='zip']")
	private WebElement locationZipCode;
		
	@FindBy(css="[class='inline-saves-inventory-item-heart'] [class='dg-inline-save-heart']")
	private WebElement saveHeartButton;
	
	@FindBy(xpath="//h3[contains(@class, 'dealer-name-heading-search')]")
	private WebElement dealerName;
	
	@FindBy(css="[class='toggle-button']")
	private WebElement specialOfferElement;
	
	@FindBy(css="[class='view-incentive-details']")
	private WebElement viewOfferButton;
	
	@FindBy(css="[class*='modal theme-inline modal-carousel-item slide offer-modal active']  h2[class='modal-title']")
	private WebElement modelDialogTitle;
	
	public String getDealerName() {
		return getText(dealerName);
	}
	public String getLocationZip() {
		return getText(locationZipCode);
	}
	
	public String getRAQFormTitle() {
		return getText(raqFormTitle);
	}
	
	public void selectFirstCar() {
		waitForElement(firstCarImage);
		clickOn(firstCarImage);
	}
		
	public SearchInventoryPage selectCarBySeries(RequestQuoteModel data) {
		
		String year = Utilities.getSeriesYear(data);
		String seriesName = Utilities.getSeriesName(data);
		String locator = "//*[@class='grade']/span[text()='"+year+" ']/parent::div/span[text()='"+seriesName+"']";
		waitForElement(locator);
		WebElement element = driver.findElement(ByLocator(locator));
		javaScriptClick(element);
		return PageFactory.initElements(driver, SearchInventoryPage.class);
	}
	
	public SearchInventoryPage selectCarBySeriesEsp(RequestQuoteModel data) {
		String year = Utilities.getSeriesYearEsp(data);
		String seriesName = Utilities.getSeriesNameEsp(data);
		String locator = "//*[@class='grade']/span[text()='"+year+"']/parent::div/span[text()='"+seriesName+" ']";
		waitForElement(locator);
		WebElement element = driver.findElement(ByLocator(locator));
		javaScriptClick(element);
		return PageFactory.initElements(driver, SearchInventoryPage.class);
	}
	
	public void clickViewDetails(){		
		javaScriptClick(viewDetailsButton);
	}
	
	public void clickViewDetailsEsp(){
		clickOn(viewDetailsButtonEsp);
	}
	
	public RequestQuoteModel getPrice(RequestQuoteModel data){
		waitForElement(priceElement);
		String str = priceElement.getText();
		String priceVehicle= str.replaceAll("[^0-9]", "");
		data.setPrice(priceVehicle);
		return data;
	}
	
	public RequestQuoteModel getPriceMobile(RequestQuoteModel data){
		waitForElement(priceElementMobile);
		String str = priceElementMobile.getText();
		String priceVehicle= str.replaceAll("[^0-9]", "");
		data.setPrice(priceVehicle);
		return data;
	}
	
	public RequestQuoteModel getPriceEsp(RequestQuoteModel data){
		waitForElement(priceElementEsp);
		String str = priceElementEsp.getText();
		String priceVehicle= str.replaceAll("[^0-9]", "");
		data.setPrice(priceVehicle);
		return data;
	}
	
	public RequestQuoteModel getPriceEspMobile(RequestQuoteModel data){
		waitForElement(priceElementEspMobile);
		String str = priceElementEspMobile.getText();
		String priceVehicle= str.replaceAll("[^0-9]", "");
		data.setPrice(priceVehicle);
		return data;
	}
	
	/**
	 * Verify request sent page "Search vendor" and "Build your own" buttons
	 */
	public void verifySearchInventoryButtonsAfterSubmit() {
		Assert.assertTrue(isElementPresent(viewLocalOffers), "View Local Offers button not found");
		Assert.assertTrue(isElementPresent(applyForFinancing), "Apply for Financing button not found");
	}
	
	/**
	 * Verify request sent page "Search vendor" and "Build your own" buttons
	 */
	public void verifySearchInventoryButtonsAfterSubmitEsp() {
		Assert.assertTrue(isElementPresent(viewLocalOffersEsp), "View Local Offers button not found");
		Assert.assertTrue(isElementPresent(applyForFinancingEsp), "Apply for Financing button not found");
	}
	
	public void clickOnSaveHeartButton() {
		clickOn(saveHeartButton);
	}
	
	public void viewOffer() {
		waitAndClick(specialOfferElement);
		waitAndClick(viewOfferButton);
	}
	
	public void verifyModelDialogTitle(String text) {
		String title = getText(modelDialogTitle);
		Assert.assertEquals(title.trim(), text);
	}
	
	// Filter
	@FindBy(css="[class*='tcom-select-trigger']")
	private WebElement priceFilter;
	
	@FindBy(css="[class='tcom-inventory-result-card-msrp-amount']")
	private List<WebElement> vehiclePriceElement;
	
	@FindBy(xpath="//*[@data-name='grade']//*[@class='tcom-custom-checkbox-label']")
	private WebElement modelFilter;
	
	@FindBy(xpath="//*[@data-name='grade']//*[@class='tcom-custom-check-title']")
	private WebElement modelFilterText;
	
	@FindBy(css="[class='tcom-tag-title']")
	private WebElement tagTitleObj;
	
	@FindBy(css="[class='tcom-inventory-result-card-grade']")
	private List<WebElement> inventoryResultCardTitle;
	
	@FindBy(css="[data-name='enginetransmission'] [class='tcom-custom-checkbox-label']")
	private WebElement engineFilter;
	
	@FindBy(css="[class='tcom-filter-group-header'] a")
	private WebElement clearFilter;
		
	@FindBy(css="[data-name='enginetransmission'] [class='tcom-custom-check-title']")
	private WebElement engineFilterText;
	
	@FindBy(css="[class='tcom-inventory-result-card-engine-transmission']")
	private List<WebElement> inventoryResultCardEngineTransmission;
	
	@FindBy(css="[data-name='exteriorcolor'] [class='tcom-custom-checkbox-label']")
	private WebElement extColorFilter;
	
	@FindBy(css="[data-name='exteriorcolor'] [class='tcom-custom-check-title']")
	private WebElement extColorFilterText;
	
	@FindBy(css="[class='tcom-inventory-result-card-exterior-color'] [class='tcom-inventory-result-card-color-title']")
	private List<WebElement> inventoryResultExtColor;
	
	@FindBy(css="[data-name='interiorcolor'] [class='tcom-custom-checkbox-label']")
	private WebElement intColorFilter;
	
	@FindBy(css="[data-name='interiorcolor'] [class='tcom-custom-check-title']")
	private WebElement intColorFilterText;
	
	@FindBy(css="[class='tcom-inventory-result-card-interior-color'] [class='tcom-inventory-result-card-color-title']")
	private List<WebElement> inventoryResultIntColor;
	
	@FindBy(css="[data-name='accessory'] button")
	private WebElement accessoryFilterButton;
	
	@FindBy(css="[data-name='accessory'] [class='tcom-custom-checkbox-label']")
	private WebElement accessoryFilterLabel;
	
	@FindBy(css="[data-name='accessory'] [class='tcom-custom-check-title']")
	private WebElement accessoryFilterText;
	
	@FindBy(css="[class='tcom-list-accordion-mask']")
	private List<WebElement> accessoryPackage;
	
	@FindBy(css="[class='tcom-inventory-result-card-engine-transmission']")
	private List<WebElement> listCarEngineName;
	
	public void filterPriceByValue(String filterText) {
		waitForElement(priceFilter).click();		
		String locator = "//*[@class='tcom-select-options open']/li[text()='"+filterText+"']";
		clickOn(driver.findElement(By.xpath(locator)));
		sleepInSecond(3);
	}
	
	public void clearFilter() {
		clickOn(clearFilter);
	}
	
	public List<Integer>  getAllVehiclePrice() {
		List<Integer> prices = new ArrayList<Integer>();
		for(WebElement element : vehiclePriceElement) {
			System.out.println("Price "+element.getText());
			prices.add(Utilities.getNumberFromString(element.getText()));
		}
		return prices;
	}
	
	public String selectFirstModelFilter() {
		clickOn(modelFilter);
		waitForElementPresent(tagTitleObj);
		return getText(modelFilterText);
	}
	
	public List<String>  getListModel() {
		List<String> models = new ArrayList<String>();
		for(WebElement element : inventoryResultCardTitle) {			
			models.add(Utilities.removeLine(element.getText()));
		}
		return models;
	}
	
	public String selectFirstEngineFilter() {
		clickOn(engineFilter);
		waitForElementPresent(tagTitleObj);
		return getText(engineFilterText);
	}
	
	public List<String>  getListEngineTransmission() {
		List<String> models = new ArrayList<String>();
		for(WebElement element : inventoryResultCardEngineTransmission) {			
			models.add(element.getText());
		}
		return models;
	}
	
	public String selectExtColorFilter() {
		clickOn(extColorFilter);
		waitForElementPresent(tagTitleObj);
		return getText(extColorFilterText);
	}
	
	public List<String>  getListofExtColor() {
		List<String> color = new ArrayList<String>();
		for(WebElement element : inventoryResultExtColor) {			
			color.add(element.getText());
		}
		return color;
	}
	
	public String selectIntColorFilter() {
		clickOn(intColorFilter);
		waitForElementPresent(tagTitleObj);
		return getText(intColorFilterText);
	}
	
	public List<String>  getListofIntColor() {
		List<String> color = new ArrayList<String>();
		for(WebElement element : inventoryResultIntColor) {			
			color.add(element.getText());
		}
		return color;
	}
	
	public void clickAccessoryFilterPlusButton() {
		clickOn(accessoryFilterButton);
	}
	
	public String selectAccessoryFilter() {
		clickOn(accessoryFilterLabel);
		return Utilities.removeNumberFromString(getText(accessoryFilterText));
	}
	
	public List<String> getListOfVehicleAccessories() {		
		List<String> accessories = new ArrayList<String>();
		for(WebElement element : accessoryPackage) {			
			accessories.add(Utilities.removeLine(element.getText()));
		}
		return accessories;
	}
	
	public void selectDriveTrain(String name) {
		String locator = "//*[@value='"+name+"']/preceding-sibling::label";
		waitForElement(locator);
		executeJsScroolToElement(driver.findElement(By.xpath(locator)));
		clickOn(locator);
		sleepInSecond(3);
	}
	
	public List<String> getListOfVehicleEngineName() {		
		List<String> engineName = new ArrayList<String>();
		for(WebElement element : listCarEngineName) {			
			engineName.add(Utilities.removeLine(element.getText()));
		}
		return engineName;
	}
	
}