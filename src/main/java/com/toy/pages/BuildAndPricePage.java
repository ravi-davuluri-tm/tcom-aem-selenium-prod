package com.toy.pages;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.toy.constant.GlobalPagesConstant;
import com.toy.datamodel.RequestQuoteModel;
import com.toy.datamodel.StringModel;
import com.toy.selenium.core.BasePage;
import com.toy.utilities.Utilities;

public class BuildAndPricePage extends BasePage {

	public BuildAndPricePage(WebDriver driver) {
		super(driver);
	}

	@FindBy(css = "[class^='tcom-vehicle-lineup-vehicles'] div[class='tcom-vehicle-card-image']:nth-child(1)")
	private WebElement firstCarImage;

	@FindBy(css = "[class='steps-buttons clearfix'] button")
	private WebElement nextButton;

	// @FindBy(css = "[id='hero'] [data-flyout-form='REQUEST_QUOTE_EFC']")

	@FindBy(css = "[class='tcom-raq-cta tfresh-btn tfresh-btn-one raq-modal-cta']")
	private WebElement requestAQuoteButton;

	@FindBy(css = "[class^='nav model']")
	private WebElement modelTab;

	@FindBy(css = "[class^='nav configuration']")
	private WebElement engienTab;

	@FindBy(css = "[class^='nav color']")
	private WebElement colorTab;

	@FindBy(css = "[class^='nav package']")
	private WebElement packageTab;

	@FindBy(css = "[class^='nav accessory']")
	private WebElement accessoryTab;

	@FindBy(css = "[aria-controls='summary-tab']")
	private WebElement summaryTab;

	@FindBy(css = "[aria-label='go to more models']")
	private WebElement showmoreButton;

	@FindBy(css = "[class='tcom-card-group-nav-arrow tcom-left']")
	private WebElement previousButton;

	@FindBy(xpath = "(//*[text()='Total MSRP'])[1]/following-sibling::span")
	private WebElement priceElement;

	@FindBy(xpath = "(//*[text()='MSRP total'])[1]/following-sibling::span")
	private WebElement priceElementEsp;

	@FindBy(xpath = "//div[span[text()='Engine']]/*[@class='vehicle-aspect-value']")
	private WebElement engineNameElement;

	@FindBy(xpath = "//div[span[text()='Transmission']]/*[@class='vehicle-aspect-value']")
	private WebElement TransmissionElement;

	public void checkModelViewDetailsAndAdd() {
		String viewDetailsLocator = "(//*[@class='tcom-card-cta tcom-view-details'])[2]";
		waitForElement(viewDetailsLocator);
		clickOn(viewDetailsLocator);
		String locatorSelect = "(//*[@class='tcom-view-details-ctas'])[2]/button/span[1]";
		waitForElement(locatorSelect);
		String selectText = getText(driver.findElement(By.xpath(locatorSelect)));
		Assert.assertEquals(selectText.toLowerCase(), "Select".toLowerCase());
		clickOn(locatorSelect);
		sleepInSecond(2);
		locatorSelect = "(//*[@class='tcom-view-details-ctas'])[2]/button/span[2]/div[2]";
		String selectedText = getText(driver.findElement(By.xpath(locatorSelect)));
		Assert.assertEquals(selectedText.toLowerCase(), "SELECTED".toLowerCase());
		String locatorClose = "(//*[@class='modal-group-close btn-close circle'])[2]";
		clickOn(locatorClose);
		String selectLocator = "(//*[@class=\"tcom-title\"])[2]";
		sleepInSecond(2);
		selectedText = getText(driver.findElement(By.xpath(selectLocator)));
		Assert.assertEquals(selectedText.toLowerCase(), "SELECTED".toLowerCase());
	}

	public String checkAccessoriesViewDetailsAndAdd() {
		String viewDetailsLocator = "(//*[@class='tcom-card-cta tcom-view-details'])[1]";
		waitForElement(viewDetailsLocator);
		clickOn(viewDetailsLocator);
		String locatorSelect = "(//*[@class='tcom-view-details-ctas'])[1]//span[@class='tcom-add']";
		waitForElement(locatorSelect);
		String selectText = getText(driver.findElement(By.xpath(locatorSelect)));
		Assert.assertEquals(selectText.toLowerCase(), "Add".toLowerCase());
		clickOn(locatorSelect);
		sleepInSecond(2);
		locatorSelect = "(//*[@class=\"tcom-added\"])[1]/div[2]";
		String selectedText = getText(driver.findElement(By.xpath(locatorSelect)));
		Assert.assertEquals(selectedText.toLowerCase(), "added".toLowerCase());
		String locatorClose = "(//*[@class='modal-group-close btn-close circle'])[1]";
		clickOn(locatorClose);
		String selectLocator = "(//*[@class='tcom-title'])[1]";
		sleepInSecond(2);
		selectedText = getText(driver.findElement(By.xpath(selectLocator)));
		Assert.assertEquals(selectedText.toLowerCase(), "Added".toLowerCase());
		String firstVehicleLocator = "(//*[@class=\"tcom-accessory-card-title\"])[1]";
		return getText(driver.findElement(By.xpath(firstVehicleLocator)));
	}

	/**
	 * Select first car
	 */
	public void selectFirstCar() {
		waitForElement(firstCarImage);
		clickOn(firstCarImage);
	}

	public String getTrim() {
		String engine = getText(engineNameElement);
		String transmission = getText(TransmissionElement);
		return engine + " " + transmission;
	}

	public BuildAndPricePage selectCarBySeries(RequestQuoteModel data) {
		String year = Utilities.getSeriesYear(data);
		String seriesName = Utilities.getSeriesName(data);
		String locator = "//*[@class='grade']/span[text()='" + year + " ']/parent::div/span[text()='" + seriesName
				+ "']";
		waitForElement(locator);
		WebElement element = driver.findElement(ByLocator(locator));
		javaScriptClick(element);
		return PageFactory.initElements(driver, BuildAndPricePage.class);
	}

	public BuildAndPricePage selectCarBySeriesEsp(RequestQuoteModel data) {
		String year = Utilities.getSeriesYearEsp(data);
		String seriesName = Utilities.getSeriesNameEsp(data);
		String locator = "//*[@class='grade']/span[text()='" + year + "']/parent::div/span[text()='" + seriesName
				+ " ']";
		waitForElement(locator);
		WebElement element = driver.findElement(ByLocator(locator));
		javaScriptClick(element);
		return PageFactory.initElements(driver, BuildAndPricePage.class);
	}

	public BuildAndPricePage clickNextButton() throws InterruptedException {
		Thread.sleep(1000);
		waitForElement(nextButton);
		javaScriptClick(nextButton);
		return PageFactory.initElements(driver, BuildAndPricePage.class);
	}

	public RequestAQuotePage clickRaqButton() {
		waitAndClick(requestAQuoteButton);
		return PageFactory.initElements(driver, RequestAQuotePage.class);
	}

	public RequestQuoteModel getPrice(RequestQuoteModel data) {
		waitForElement(priceElement);
		String str = priceElement.getText();
		String priceVehicle = str.replaceAll("[^0-9]", "");
		data.setPrice(priceVehicle);
		return data;
	}

	public RequestQuoteModel getPriceEsp(RequestQuoteModel data) {
		waitForElement(priceElementEsp);
		String str = priceElementEsp.getText();
		String priceVehicle = str.replaceAll("[^0-9]", "");
		data.setPrice(priceVehicle);
		return data;
	}

	public void selectTabByName(String tabName) {
		String locator = "//*[@role='tablist']//*[@class='nav-title' and text()='" + tabName + "']";
		waitForElement(locator);
		clickOn(locator);
	}

	@FindBy(css = "[data-step='model:grade']")
	private WebElement gradeTab;

	@FindBy(css = "[class*='nav configuration']")
	private WebElement powertrainTab;

	@FindBy(css = "[class*='nav cab-bed']")
	private WebElement cabBedTab;

	public String getAttributeTabValue(String tabName, String attributeName) {
		String attribute = "Active";
		switch (tabName) {
		case GlobalPagesConstant.Models:
			attribute = super.getAttribute(modelTab, attributeName);
			break;
		case GlobalPagesConstant.Engines:
			attribute = super.getAttribute(engienTab, attributeName);
			break;
		case GlobalPagesConstant.Colors:
			attribute = super.getAttribute(colorTab, attributeName);
			break;
		case GlobalPagesConstant.Packages:
			attribute = super.getAttribute(packageTab, attributeName);
			break;
		case GlobalPagesConstant.Accessories:
			attribute = super.getAttribute(accessoryTab, attributeName);
			break;
		case GlobalPagesConstant.Summary:
			attribute = super.getAttribute(summaryTab, attributeName);
			break;
		case GlobalPagesConstant.Powertrain:
			attribute = super.getAttribute(powertrainTab, attributeName);
			break;
		case GlobalPagesConstant.Grade:
			attribute = super.getAttribute(gradeTab, attributeName);
			break;
		case GlobalPagesConstant.CabsAndBeds:
			attribute = super.getAttribute(cabBedTab, attributeName);
			break;
		}
		return attribute;
	}

	public void verifySelectedTab(String tabName) {
		String attribute = this.getAttributeTabValue(tabName, "class");
		Assert.assertTrue(attribute.contains("is-active"));
	}

	public void verifyTabChecked(String tabName) {
		String attribute = this.getAttributeTabValue(tabName, "class");
		Assert.assertTrue(attribute.contains("is-checked"));
	}

	@FindBy(css = "[class='tcom-card-title']")
	private List<WebElement> modelTitleObj;

	@FindBy(css = "[class='series-title'] span")
	private WebElement seriesTitleobj;

	@FindBy(xpath = "(//span[contains(@class, 'price-normal')])[1]")
	private WebElement totalPriceObj;

	private String addButtonLocator = "//button[contains(@class, 'pick tcom-pick-button')]";

	public String getSeriesTitleName() {
		return getText(seriesTitleobj);
	}

	public String selectAndVerifyModelOrGrade() throws InterruptedException {
		List<WebElement> element = driver.findElements(By.xpath(addButtonLocator));
		super.moveToElement(element.get(0));
		javaScriptClick(element.get(0));
		Thread.sleep(4000);
		String priceLocator = "//*[@class='tcom-card-copy-item tcom-msrp']/*[@class='tcom-card-copy-title']";
		String modelTitleLocator = "//*[@class='tcom-card-title']";

		// Verify title and price
		List<WebElement> priceLocatorElement = driver.findElements(By.xpath(priceLocator));
		List<WebElement> modelLocatorElement = driver.findElements(By.xpath(modelTitleLocator));
		String modelPriceValue = getText(priceLocatorElement.get(0));
		String modelTitle = getText(modelLocatorElement.get(0));
		String seriesTitle = getText(seriesTitleobj);
		String totalPriceValue = getText(totalPriceObj);
		Assert.assertEquals(totalPriceValue.replaceAll("[^0-9]", ""), modelPriceValue.replaceAll("[^0-9]", ""));
		Assert.assertTrue(seriesTitle.contains(modelTitle), "Series: " + seriesTitle + " not contains " + modelTitle);

		// Verify selected
		List<WebElement> buttonElement = driver.findElements(By.xpath(addButtonLocator));
		String classValue = super.getAttribute(buttonElement.get(0), "class");
		System.out.println(classValue);
		// Assert.assertTrue(classValue.contains("is-selected"), "Model/Grade
		// Button not selected");
		return modelTitle;
	}

	public String selectModel() throws InterruptedException {
		String prefix = "//div[contains(@class, 'tcom-card pick') and not(contains(@class, 'is-selected'))]";
		// waitForElementVisible(driver.findElement(By.xpath(prefix +
		// "//*[@class='tcom-pick-button-checkmark']")));
		List<WebElement> element = driver.findElements(By.xpath(prefix + "//*[@class='tcom-pick-button-checkmark']"));
		String modelTitleLocator = prefix + "//*[@class='tcom-card-title']";
		List<WebElement> modelLocatorElement = driver.findElements(By.xpath(modelTitleLocator));
		String modelTitle = getText(modelLocatorElement.get(0));
		// super.moveToElement(element.get(0));
		javaScriptClick(element.get(0));
		sleepInSecond(3);
		javaScriptClick(element.get(0));
		sleepInSecond(15);
		return modelTitle;
	}

	public HashMap<String, String> selectEngine() throws InterruptedException {
		String selectButton = "//button[span[@class='select caption']]//*[@class='selected caption']";
		waitForElement(selectButton);
		List<WebElement> element = driver.findElements(By.xpath(selectButton));
		int modelIndex = getRandomIndex(element);
		super.moveToElement(element.get(modelIndex));
		javaScriptClick(element.get(modelIndex));
		Thread.sleep(4000);
		String engineModel = "//*[@class='tcom-card-title']";
		String engineDetails = "//*[@class='tcom-card-sub-title']";
		List<WebElement> engineModelElement = driver.findElements(By.xpath(engineModel));
		List<WebElement> engineDetailsElement = driver.findElements(By.xpath(engineDetails));

		String enginePower = getText(engineModelElement.get(modelIndex));
		System.out.println(enginePower);
		String gear = getText(engineDetailsElement.get(modelIndex));
		System.out.println(gear);
		HashMap<String, String> enginedetails = new HashMap<String, String>();
		enginedetails.put("EnginePower", enginePower);
		enginedetails.put("Gear", gear);
		return enginedetails;
	}

	public String selectCabsAndBedsOrPowertrain() {
		String selectButton = "//button[span[@class='select caption']]//*[@class='selected caption']";
		waitForElement(selectButton);
		String title = null;
		List<WebElement> element = driver.findElements(By.xpath(selectButton));
		int modelIndex = this.getRandomIndex(element);
		if (element.size() == 1) {
			String titleLocator = "//*[@class='tcom-card-title']";
			List<WebElement> titleElements = driver.findElements(By.xpath(titleLocator));
			javaScriptClick(titleElements.get(modelIndex));
			sleepInSecond(2);
			super.moveToElement(element.get(modelIndex));
			sleepInSecond(4);
			javaScriptClick(element.get(modelIndex));
			title = getText(titleElements.get(modelIndex));
		} else {
			selectButton = "//*[@class='tcom-cards-container tcom-card-group-wrapper']//button[not(contains(@class, 'is-selected'))]";
			String titleLocator = "//*[@class='tcom-cards-container tcom-card-group-wrapper']/div[not(contains(@class, 'is-selected'))]//*[@class='tcom-card-title']";
			List<WebElement> elementButton = driver.findElements(By.xpath(selectButton));
			List<WebElement> titleElements = driver.findElements(By.xpath(titleLocator));
			modelIndex = this.getRandomIndex(elementButton);
			// added logics to click multiple time so it will reach on correct
			// option
			for (int i = 0; i <= modelIndex; i++) {
				javaScriptClick(elementButton.get(modelIndex));
				System.out.println("click " + i);
				sleepInSecond(2);
			}
			super.moveToElement(elementButton.get(modelIndex));
			javaScriptClick(elementButton.get(modelIndex));
			sleepInSecond(4);
			title = titleElements.get(modelIndex).getText();
		}
		return title;
	}

	private int getRandomIndex(List<WebElement> element) {
		int totalModel = element.size();
		if (totalModel == 1)
			return 0;
		if (totalModel >= 3)
			totalModel = 3;
		int index = Utilities.getRandomInteger(0, totalModel - 1);
		return index;
	}

	private int getRandomIndex(List<WebElement> element, int maxSize) {
		int totalModel = element.size();
		if (totalModel >= maxSize)
			totalModel = maxSize;
		int index = Utilities.getRandomInteger(0, totalModel - 1);
		return index;
	}

	@FindBy(css = "a[class*='tcom-accessory-group']")
	private List<WebElement> accessoriesEle;

	public void verifyAccessoriesCount() {
		int allAccessory = 0;
		int otherAccessories = 0;
		if (accessoriesEle.size() > 0) {
			allAccessory = Utilities.getNumberFromString(accessoriesEle.get(0).getText());
			int i = 0;
			for (WebElement element : accessoriesEle) {
				i++;
				if (i == 1)
					continue;
				otherAccessories = otherAccessories + Utilities.getNumberFromString(element.getText());
			}
			Assert.assertEquals(allAccessory, otherAccessories, "Accessories not matched with total count");
		}
		System.out.println(allAccessory + "  " + otherAccessories);
	}

	@FindBy(xpath = "(//span[contains(@class, 'price-normal')])[2]")
	private WebElement totalPriceObj1;

	public String selectAccessory() throws InterruptedException {
		Thread.sleep(5000);
		String accessoryNameLocator = "[class='tcom-accessory-card-title']";
		_waitForJStoLoad();
		// waitForElement(accessoryNameLocator);
		String totalPriceValueBeforeAdd = getText(totalPriceObj1);
		String selectButton = "[class*='tcom-accessory-items tcom-cards-container'] [class='select caption']";
		List<WebElement> element = driver.findElements(By.cssSelector(selectButton));
		int modelIndex = this.getRandomIndex(element);
		super.moveToElement(element.get(modelIndex));
		javaScriptClick(element.get(modelIndex));
		// sleepInSecond(2);
		// javaScriptClick(element.get(modelIndex));
		sleepInSecond(4);
		List<WebElement> accessoryNameElement = driver.findElements(By.cssSelector(accessoryNameLocator));
		String accessoryName = accessoryNameElement.get(modelIndex).getText();
		sleepInSecond(2);
		String priceLocator = "[class='tcom-accessory-msrp-amount']";
		_waitForJStoLoad();
		waitForElement(priceLocator);
		List<WebElement> priceLocatorElement = driver.findElements(By.cssSelector(priceLocator));
		String price = getText(priceLocatorElement.get(modelIndex));
		String totalPriceValueAfterAdd = getText(totalPriceObj1);
		Assert.assertEquals(Utilities.getNumberFromString(totalPriceValueAfterAdd),
				Utilities.getNumberFromString(price) + Utilities.getNumberFromString(totalPriceValueBeforeAdd));
		return accessoryName;
	}

	public String selectAccessoryOnly() throws InterruptedException {
		Thread.sleep(5000);
		String prefix = "//div[contains(@class, 'tcom-card pick') and not(contains(@class, 'is-selected'))]";
		String accessoryNameLocator = prefix + "//div[@class='tcom-accessory-card-title']";
		_waitForJStoLoad();
		String selectButton = prefix + "//*[@class='select caption']";
		List<WebElement> element = driver.findElements(By.xpath(selectButton));
		int modelIndex = this.getRandomIndex(element);
		List<WebElement> accessoryNameElement = driver.findElements(By.xpath(accessoryNameLocator));
		String accessoryName = accessoryNameElement.get(modelIndex).getText();
		super.moveToElement(element.get(modelIndex));
		javaScriptClick(element.get(modelIndex));
		Thread.sleep(4000);
		// return accessoryName.substring(0, accessoryName.length() - 3);
		return accessoryName;
	}

	public String getTotalPrice() {
		waitForElementVisible(totalPriceObj1);
		return getText(totalPriceObj1);
	}

	public void verifyCategorySubMenus(List<String> listTabs) {
		for (String tab : listTabs) {
			By by = By.xpath("//*[@class='tcom-tab-subnav-nav']//a/span[text()='" + tab + "']");
			Assert.assertTrue(isElementPresent(driver.findElement(by)), "Tab " + tab + " not found");
		}
	}

	// click on sub menu tabs
	public void selectCategorySubMenu(String tab) throws InterruptedException {
		By by = By.xpath("//*[@class='tcom-tab-subnav-nav']//a/span[text()='" + tab + "']");
		clickOn(driver.findElement(by));
		Thread.sleep(3000);
	}

	public void verifyCategorySubMenu(String tab) {
		By by = By.xpath("//*[@class='tcom-tab-subnav-nav']//a[span[text()='" + tab + "']]");
		String attributes = driver.findElement(by).getAttribute("class");
		Assert.assertTrue(attributes.contains("is-on"), "Tab :" + tab + " is not selected");
	}

	public void verifyCategoryTitle(List<String> listCategories) {
		for (String category : listCategories) {
			By by = By.xpath("//h2[@class='tcom-vehicle-lineup-category-title'][text()='" + category + "']");
			Assert.assertTrue(isElementPresent(driver.findElement(by)), "Category " + category + " not found");
		}
	}

	@FindBy(css = "[class='no-packages']")
	private List<WebElement> noPackageElement;

	public boolean isPackage() {
		if (noPackageElement.size() > 0)
			return false;
		else {
			return true;
		}
	}

	public String selectVerifyPackage() {
		String prefix = "//div[contains(@class, 'tcom-card tcom-package-item') and not(contains(@class, 'is-selected'))]";
		String locatorAddbutton = prefix + "//*[@class='select caption' and text()='ADD']";
		String packageAmount = prefix + "//*[@class='tcom-package-msrp-amount']";
		String packageNameLocator = prefix + "//*[@class='tcom-card-title']";
		waitForElement(locatorAddbutton);
		String totalPriceBefore = getText(totalPriceObj1);
		List<WebElement> addbuttonElements = driver.findElements(By.xpath(locatorAddbutton));
		List<WebElement> amountElements = driver.findElements(By.xpath(packageAmount));
		List<WebElement> packageNameElements = driver.findElements(By.xpath(packageNameLocator));
		super.moveToElement(addbuttonElements.get(0));
		clickOn(packageNameElements.get(0));
		javaScriptClick(addbuttonElements.get(0));
		String packagePrice = getText(amountElements.get(0));
		String packageName = getText(packageNameElements.get(0));

		// Verify price add or minus from main price
		String totalPriceValue = getText(totalPriceObj1);
		System.out.println(packagePrice + " " + totalPriceValue + " " + totalPriceBefore);
		if (packagePrice.contains("-")) {
			Assert.assertEquals(Utilities.getNumberFromString(totalPriceValue),
					Utilities.getNumberFromString(totalPriceBefore) - Utilities.getNumberFromString(packagePrice));
		} else {
			Assert.assertEquals(Utilities.getNumberFromString(totalPriceValue),
					Utilities.getNumberFromString(totalPriceBefore) + Utilities.getNumberFromString(packagePrice));
		}
		// verify add button selected selected
		List<WebElement> addbuttonElements1 = driver.findElements(By.xpath(locatorAddbutton));
		Assert.assertEquals(addbuttonElements1.size(), addbuttonElements.size() - 1, "Add button not selected");

		return packageName;
	}

	public String selectRemovePackage() {
		String prefix = "//div[contains(@class, 'is-selected')]";
		String locatorRemoveButton = "[class*='is-selected'] [class='selected caption'] [class='tcom-remove']";
		String locatorAddedButton = prefix + "//div[@class=\"tcom-title\" and text()='ADDED']";
		String packageAmount = prefix + "//*[@class='tcom-package-msrp-amount']";
		String packageNameLocator = prefix + "//*[@class='tcom-card-title']";
		waitForElement(locatorAddedButton);
		String totalPriceBefore = getText(totalPriceObj1);

		List<WebElement> amountElements = driver.findElements(By.xpath(packageAmount));
		List<WebElement> packageNameElements = driver.findElements(By.xpath(packageNameLocator));
		List<WebElement> addedbuttonElements = driver.findElements(By.xpath(locatorAddedButton));

		// int randomIndex = this.getRandomIndex(removebuttonElements);
		super.executeJsScroolToElement(addedbuttonElements.get(0));
		super.moveToElement(addedbuttonElements.get(0));
		// javaScriptClick(packageNameElements.get(0));
		WebElement removebuttonElements = driver.findElement(By.cssSelector(locatorRemoveButton));
		// super.executeJsScroolToElement(removebuttonElements);
		sleepInSecond(2);
		clickOn(removebuttonElements);
		String packagePrice = getText(amountElements.get(0));
		String packageName = getText(packageNameElements.get(0));

		String totalPriceValue = getText(totalPriceObj1);
		System.out.println(packagePrice + " " + totalPriceValue + " " + totalPriceBefore);
		if (packagePrice.contains("-")) {
			Assert.assertEquals(Utilities.getNumberFromString(totalPriceValue),
					Utilities.getNumberFromString(totalPriceBefore) + Utilities.getNumberFromString(packagePrice));
		} else {
			Assert.assertEquals(Utilities.getNumberFromString(totalPriceValue),
					Utilities.getNumberFromString(totalPriceBefore) - Utilities.getNumberFromString(packagePrice));
		}
		System.out.println(packagePrice + " " + totalPriceValue + " " + totalPriceBefore);
		// verify add button selected selected
		List<WebElement> addbuttonElements1 = driver.findElements(By.xpath(locatorAddedButton));
		Assert.assertEquals(addbuttonElements1.size(), addedbuttonElements.size() - 1, "Added accessories not removed");

		return packageName;
	}

	@FindBy(css = "[class='tcom-summary-title']")
	private WebElement carNameEle;

	@FindBy(xpath = "(//*[@class='tcom-summary-subtitle'])[1]")
	private WebElement engienNameOnSummaryEle;

	@FindBy(xpath = "(//*[@class='tcom-summary-subtitle'])[2]")
	private WebElement cabsOnSummaryEle;

	@FindBy(css = "[class='tcom-summary-line-item-white-highlight']")
	private List<WebElement> summaryDataObject;

	@FindBy(css = "[class='tcom-summary-pencil-button'][data-step='package']")
	private WebElement packageEditButton;

	public void clickOnPackageEditButton() {
		clickOn(packageEditButton);
	}

	public void verifyModelNameOnSummary(String carName) {
		String name = getText(carNameEle);
		System.out.println("car name" + name);
		Assert.assertTrue(name.toLowerCase().contains(carName.toLowerCase()),
				"Car name not matched on summary page " + carName);
	}

	public String getCabsOnSummary() {
		return getText(cabsOnSummaryEle);
	}

	public String getEngineOnSummary() {
		return getText(engienNameOnSummaryEle);
	}

	public List<String> getListOfSummaryPageData() {
		List<String> summaryData = new ArrayList<String>();
		for (WebElement element : summaryDataObject) {
			summaryData.add(element.getText());
			System.out.println(element.getText());
		}
		return summaryData;
	}

	@FindBy(css = "[class='tcom-summary-cta tcom-email']")
	private WebElement emailYourToyotaElemeny;

	@FindBy(css = "[id='firstName']")
	private WebElement firstNameEle;

	@FindBy(css = "[name='senderEmail']")
	private WebElement senderEmailEle;

	@FindBy(css = "[name='recipientEmail']")
	private WebElement recipientEmailEle;

	@FindBy(css = "[name='senderComments']")
	private WebElement senderCommentsEle;

	@FindBy(css = "[data-action='submit']")
	private WebElement submitEle;

	@FindBy(css = "[class='tcom-request-brochure-form-headline']")
	private WebElement confirmationEle;

	@FindBy(css = "[class='tcom-request-brochure-form-header']")
	private WebElement mailSentMessageEle;

	@FindBy(css = "[class=\"tcom-summary-msrp\"] [class=\"tcom-summary-line-item-value\"] > span")
	private WebElement totalPriceSummaryTab;

	public String getTotalPriceSummaryTab() {
		return getText(totalPriceSummaryTab);
	}

	public void sendEmailYourToyota(String firstName, String senderEmail, String recipientEmail, String senderComment) {
		clickOn(emailYourToyotaElemeny);
		inputText(firstNameEle, firstName);
		inputText(senderEmailEle, senderEmail);
		inputText(recipientEmailEle, recipientEmail);
		inputText(senderCommentsEle, senderComment);
		clickOn(submitEle);
		Assert.assertTrue(getText(confirmationEle).contains("Confirmation"), "Confirmation message not found");
		Assert.assertTrue(getText(mailSentMessageEle)
				.contains("An email with the details of this offer has been sent to: " + recipientEmail));
	}

	@FindBy(css = "[class='tcom-summary-title'] [class='tcom-summary-pencil-button']")
	private WebElement summaryModelEditIcon;

	@FindBy(css = "[class='tcom-summary-pencil-button'][data-step='accessory']")
	private WebElement summaryAccessoryEditIcon;

	@FindBy(css = "[class='tcom-summary-title']")
	private WebElement summaryTitleEle;

	@FindBy(css = "[class='tcom-summary-subtitle']")
	private WebElement engineSummaryEle;

	@FindBy(css = "[class='tcom-summary-line-item-title tcom-no-edit'] >[class='tcom-summary-line-item-white-highlight']")
	private List<WebElement> elementSummary;

	public void clickOnSummayAccessoryEditIcon() {
		clickOn(summaryAccessoryEditIcon);
	}

	public void clickOnSummayModelEditIcon() {
		clickOn(summaryModelEditIcon);
	}

	public String getSeriesTitle() {
		return getText(summaryTitleEle);
	}

	public String getEngineTitle() {
		return getText(engineSummaryEle);
	}

	public List<String> getSummaryList() {
		List<String> summaryDetails = new ArrayList<String>();
		for (WebElement element : elementSummary) {
			summaryDetails.add(getText(element));
		}
		return summaryDetails;
	}

	// color tab
	@FindBy(css = "[class='color-set exteriorcolor clearfix'] [class='color-item-wrapper '] a")
	private List<WebElement> exteriorColorColorsElement;

	@FindBy(css = "[class='color-set exteriorcolor clearfix'] [class='selected-title']")
	private WebElement selectedExteriorColorElement;

	@FindBy(css = "[class='color-set interiorcolor clearfix'] [class='color-item-wrapper '] a")
	private List<WebElement> interiorColorsElement;

	@FindBy(css = "[class='color-set interiorcolor clearfix'] [class='selected-title']")
	private WebElement selectedInteriorcolorColorElement;

	public String selectExteriorColor() {
		int randomIndex = this.getRandomIndex(exteriorColorColorsElement, 5);
		moveToElement(exteriorColorColorsElement.get(randomIndex));
		String color = getAttribute(exteriorColorColorsElement.get(randomIndex), "title");
		javaScriptClick(exteriorColorColorsElement.get(randomIndex));
		return color;
	}

	public String getSelectedExteriorColor() {
		waitForPageLoaded();
		_waitForJStoLoad();
		try {
			return getText(selectedExteriorColorElement);
		} catch (StaleElementReferenceException ex) {
			return getText(selectedExteriorColorElement);
		}
	}

	public String selectInteriorColor() {
		if (interiorColorsElement.size() == 0)
			return "No color";
		int randomIndex = this.getRandomIndex(interiorColorsElement, 5);
		moveToElement(interiorColorsElement.get(randomIndex));
		String color = getAttribute(interiorColorsElement.get(randomIndex), "title");
		javaScriptClick(interiorColorsElement.get(randomIndex));
		return color;
	}

	public String getSelectedInteriorColor() {
		try {
			return getText(selectedInteriorcolorColorElement);
		} catch (StaleElementReferenceException ex) {
			return getText(selectedInteriorcolorColorElement);
		}
	}

	@FindBy(css = "[class='dg-inline-save-heart']")
	private WebElement saveHeartButton;

	public void clickOnHeartButton() {
		clickOn(saveHeartButton);
	}

	public List<StringModel> captureBuildAndPriceTabText(String firstTab, String secondTab) {

		if (isElementDisplay(previousButton)) {
			for (int i = 0; i <= 5; i++) {
				String status = getAttribute(previousButton, "aria-disabled");
				if (status.equals("true"))
					break;
				else
					clickOn(previousButton);
			}
		}

		String locator = "[class='tcom-cards-container tcom-card-group-wrapper'] > div";
		waitForElement("css=" + locator);
		List<WebElement> elementModels = driver.findElements(By.cssSelector(locator));
		List<StringModel> modelText = new ArrayList<StringModel>();
		int i = 1;
		sleepInSecond(2);

		sleepInSecond(2);
		for (WebElement elementModel : elementModels) {
			String locatorCard = "(//div[@class='tcom-cards-container tcom-card-group-wrapper']//*[@class='tcom-card-title'])["
					+ i + "]";
			executeJsScroolToElement(driver.findElement(By.xpath(locatorCard)));
			clickOn(locatorCard);
			clickOn(locatorCard);
			// javaScriptClick(elementModel);

			if (secondTab.equals(GlobalPagesConstant.CabsAndBeds)) {
				this.selectTabByName(GlobalPagesConstant.CabsAndBeds);
			} else if (secondTab.equals(GlobalPagesConstant.Powertrain)) {
				this.selectTabByName(GlobalPagesConstant.Powertrain);
			}

			else if (secondTab.equals(GlobalPagesConstant.Configuration)) {
				this.selectTabByName(GlobalPagesConstant.Configuration);
			} else {
				this.selectTabByName(GlobalPagesConstant.Engines);
			}

			waitForElement("css=" + locator);
			List<WebElement> elements = driver.findElements(By.cssSelector(locator));
			for (WebElement elementEngine : elements) {
				this.removeDisclaimer();
				StringModel model = new StringModel();
				String text = elementEngine.getText().replace("SELECTED", "").replace("SELECT", "");
				model.setText(text);
				model.setTextUrl(driver.getCurrentUrl());
				System.out.println(text);
				System.out.println("==================");
				modelText.add(model);
			}
			System.out.println("Testing " + firstTab);
			if (firstTab.equals(GlobalPagesConstant.Grade)) {
				this.selectTabByName(GlobalPagesConstant.Grade);
			} else {
				this.selectTabByName(GlobalPagesConstant.Models);
			}

			sleepInSecond(4);
			i++;
		}
		return modelText;
	}

	public List<StringModel> captureBuildAndPriceTabText() {
		String locator = "[class='tcom-cards-container tcom-card-group-wrapper'] > div";
		waitForElement("css=" + locator);
		List<WebElement> elements = driver.findElements(By.cssSelector(locator));
		List<StringModel> modelText = new ArrayList<StringModel>();
		int i = 1;
		for (WebElement element : elements) {
			System.out.println(i);

			String locatorCard = "(//div[@class='tcom-cards-container tcom-card-group-wrapper']//*[@class='tcom-card-title'])["
					+ i + "]";
			clickOn(locatorCard);
			this.removeDisclaimer();
			StringModel model = new StringModel();

			String text = element.getText().replace("SELECTED", "").replace("SELECT", "");
			model.setText(text);
			model.setTextUrl(driver.getCurrentUrl());
			System.out.println(element.getText());
			System.out.println("==================");
			modelText.add(model);
			i++;
		}
		return modelText;
	}

	public List<StringModel> captureBuildAndPriceTabText(String firstTab, String secondTab, String thirdTab) {
		if (isElementDisplay(previousButton)) {
			for (int i = 0; i <= 5; i++) {
				String status = getAttribute(previousButton, "aria-disabled");
				if (status.equals("true"))
					break;
				else
					clickOn(previousButton);
			}
		}
		String locator = "[class='tcom-cards-container tcom-card-group-wrapper'] > div";
		waitForElement("css=" + locator);
		List<WebElement> elementModels = driver.findElements(By.cssSelector(locator));
		List<StringModel> modelText = new ArrayList<StringModel>();
		int i = 1;
		sleepInSecond(2);
		for (WebElement elementModel : elementModels) {
			String locatorCard = "(//div[@class='tcom-cards-container tcom-card-group-wrapper']//*[@class='tcom-card-title'])["
					+ i + "]";
			clickOn(locatorCard);
			sleepInSecond(1);
			clickOn(locatorCard);
			// javaScriptClick(elementModel);
			sleepInSecond(2);
			if (secondTab.equals(GlobalPagesConstant.CabsAndBeds)) {
				this.selectTabByName(GlobalPagesConstant.CabsAndBeds);
			} else if (secondTab.equals(GlobalPagesConstant.Powertrain)) {
				this.selectTabByName(GlobalPagesConstant.Powertrain);
			} else if (secondTab.equals(GlobalPagesConstant.Configuration)) {
				this.selectTabByName(GlobalPagesConstant.Configuration);
			} else {
				this.selectTabByName(GlobalPagesConstant.Engines);
			}

			waitForElement("css=" + locator);
			List<WebElement> elements = driver.findElements(By.cssSelector(locator));
			for (WebElement elementEngine : elements) {
				StringModel model = new StringModel();
				this.removeDisclaimer();
				String text = elementEngine.getText().replace("SELECTED", "").replace("SELECT", "");
				model.setText(text);
				model.setTextUrl(driver.getCurrentUrl());
				System.out.println(text);
				System.out.println("==================");
				modelText.add(model);
			}
			if (thirdTab.equals(GlobalPagesConstant.CabsAndBeds)) {
				this.selectTabByName(GlobalPagesConstant.CabsAndBeds);
			} else if (thirdTab.equals(GlobalPagesConstant.Powertrain)) {
				this.selectTabByName(GlobalPagesConstant.Powertrain);
			} else {
				this.selectTabByName(GlobalPagesConstant.Engines);
			}
			waitForElement("css=" + locator);
			List<WebElement> elementsSecond = driver.findElements(By.cssSelector(locator));
			for (WebElement powerTrain : elementsSecond) {
				this.removeDisclaimer();
				StringModel model = new StringModel();
				String text = powerTrain.getText().replace("SELECT", "").replace("SELECTED", "");
				model.setText(text);
				model.setTextUrl(driver.getCurrentUrl());
				System.out.println(text);
				modelText.add(model);
			}
			if (firstTab.equals(GlobalPagesConstant.Grade)) {
				this.selectTabByName(GlobalPagesConstant.Grade);
			} else {
				this.selectTabByName(GlobalPagesConstant.Models);
			}
			i++;
		}
		return modelText;
	}
	
	@FindBy(xpath = "//div[@class='tcom-summary-cta-title' and text()='Email Your Toyota']")
	private WebElement emailYourToyotaButton;

	@FindBy(css = "[id='firstName']")
	private WebElement firstNameInput;

	@FindBy(css = "[name='senderEmail']")
	private WebElement senderEmailInput;

	@FindBy(css = "[name='recipientEmail']")
	private WebElement recipientEmailInput;

	@FindBy(css = "[name='senderComments']")
	private WebElement senderCommentsInput;

	@FindBy(css = "[value='SUBMIT']")
	private WebElement submitButton;

	@FindBy(css = "[id='error in filed firstName']")
	private WebElement errorNameField;

	@FindBy(xpath = "//div[input[@name='senderEmail']]/div[@id='error in field']")
	private WebElement emailErrorInput;	

	@FindBy(xpath = "//div[input[@name='recipientEmail']]/div[@id='error in field']")
	private WebElement recipientEmailError;

	@FindBy(css = "[class='tcom-request-brochure-form-headline']")
	private WebElement confirmationField;


	public void clickEmailYourToyotaButton() {
		clickOn(emailYourToyotaButton);
	}

	public void verifyErrorNameField(String expectedMsg) {
		String errorMsg = getText(errorNameField);
		Assert.assertEquals(errorMsg, expectedMsg);
	}

	public void enterFirstName(String fname) {
		inputTextWitClear(firstNameInput, fname);
	}


	public void enterEmail(String email) {
		inputTextWitClear(senderEmailInput, email);
	}

	public void verifyErrorEmailField(String expectedMsg) {
		String errorMsg = getText(emailErrorInput);
		Assert.assertEquals(errorMsg, expectedMsg);
	}

	public void enterRecipientEmail(String email) {
		inputTextWitClear(recipientEmailInput, email);
	}

	public void verifyErrorRecipientEmailField(String expectedMsg) {
		String errorMsg = getText(recipientEmailError);
		Assert.assertEquals(errorMsg, expectedMsg);
	}

	public void clickSubmitButton() {
		clickOn(submitButton);
	}

	public void verifyConfirmationMsg(String msg) {
		String text = getText(confirmationField);
		Assert.assertEquals(msg, text);
	}

}