package com.toy.pages;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import com.toy.datamodel.RequestQuoteModel;
import com.toy.selenium.core.BasePage;
import com.toy.selenium.core.Configuration;
import com.toy.utilities.Utilities;

public class RequestAQuotePage extends BasePage {

	public RequestAQuotePage(WebDriver driver) {
		super(driver);
	}

	final String prefix = "offer-modal active";

	@FindBy(css = "[id='seriesName-trigger']")
	private WebElement seriesTrigger;

	@FindBy(css = "[id='modelName-trigger']")
	private WebElement modelTrigger;
	
	@FindBy(css = "[data-atid='raq_vehicle_select.series_select']")
	private WebElement seriesName;

	@FindBy(css = "[data-atid='raq_vehicle_select.trim_select']")
	private WebElement modelName;

	@FindBy(css = "[class='tcom-custom-checkbox-label']")
	private WebElement vechicleCheck;

	@FindBy(css = "[class*='" + prefix + "'] [data-atid='raq_form.fullname']")
	private WebElement fullNameInputLocalSpecial;

	@FindBy(css = "[class*='" + prefix + "'] [data-atid='raq_form.firstname']")
	private WebElement firstNameLocalSpecial;

	@FindBy(css = "[class*='" + prefix + "'] [data-atid='raq_form.lastname']")
	private WebElement lastNameInputLocalSpecial;

	@FindBy(css = "[class*='" + prefix + "'] [data-atid='raq_form.email']")
	private WebElement emailAddressInputLocalSpecial;

	@FindBy(css = "[class*='" + prefix + "'] [data-atid='raq_form.phone']")
	private WebElement phoneInputLocalSpecial;

	@FindBy(css = "[class*='" + prefix + "'] [data-atid='raq_form.address']")
	private WebElement address1InputLocalSpecial;

	@FindBy(css = "[class*='" + prefix + "'] [data-atid='raq_form.city']")
	private WebElement cityInputLocalSpecial;

	@FindBy(css = "[class*='" + prefix + "'] [data-atid='raq_form.addcomments']")
	private WebElement addCommentButtonLocalSpecial;

	@FindBy(css = "[class*='" + prefix + "'] [data-atid='raq_form.comments']")
	private WebElement commentTextAreaLocalSpecial;

	@FindBy(css = "[class*='" + prefix + "'] [data-atid='raq_form.submit']")
	private WebElement submitButtonLocalSpecial;

	@FindBy(css = "[class*='" + prefix + "'] [class='tcom-dealer-select-list'] label span")
	private WebElement firstVendorNameLocalSpecial;

	@FindBy(css = "[class*='" + prefix + "'] [class='tcom-dealer-select-list'] input")
	private WebElement firstVendorNameCheckBoxLocalSpecial;

	@FindBy(css = "[class*='" + prefix + "'] [name='dealerCode']")
	private WebElement dealerCodeLocalSpecial;

	@FindBy(css = "[data-atid='raq_form.fullname']")
	private WebElement fullNameInput;

	@FindBy(css = "[data-atid='raq_form.email']")
	private WebElement emailAddressInput;

	@FindBy(css = "[data-atid='raq_form.phone']")
	private WebElement phoneInput;

	@FindBy(css = "[data-atid='raq_form.address']")
	private WebElement address1Input;

	@FindBy(css = "[data-atid='raq_form.city']")
	private WebElement cityInput;

	@FindBy(css = "[data-atid='raq_form.firstname']")
	private WebElement firstName;

	@FindBy(css = "[data-atid='raq_form.lastname']")
	private WebElement lastNameInput;

	@FindBy(css = "[class='raq-dealers-container'] [placeholder='Zip Code']")
	private WebElement zipCodeInput;

		
	//@FindBy(css= "[value='SUBMIT REQUEST'],[data-atid='raq_form.submit']")
	@FindBy(xpath="//*[@value='SUBMIT' or @data-atid='raq_form.submit']")
	private WebElement submitButton;
	
	@FindBy(css = "[class='raq-submit-buttons'] button:nth-child(1)")
	private WebElement submitButtonSearch;

	@FindBy(css = "[class='form-header']")
	private WebElement messageField;

	@FindBy(css = "[class='full-text']")
	private WebElement RAQZEVConfirmationMessage;
	
	@FindBy(css = "[class='raq-confirmation-message']")
	private WebElement raqConfirmationMessage;

	@FindBy(css = "[class='raq-confirmation-grade']")
	private WebElement raqConfirmationGradeMessage;

	@FindBy(name = "zipcode")
	private WebElement zipInputBox;

	@FindBy(css = "[class='zipcode-form-wrapper'] [name='button']")
	private WebElement zipSubmitButton;

	@FindBy(css = "[class='tcom-dealer-select-list'] label span")
	private WebElement firstVendorName;

	@FindBy(xpath = "(//*[@class='tcom-dealer-select-list']//label/input)[1]")
	private WebElement firstVendorCheckBox;

	@FindBy(css = "[name='dealerCode']")
	private WebElement dealerCode;

	@FindBy(css = "[data-atid='raq_form.search']")
	private WebElement searchInventoryButton;

	@FindBy(xpath = "//a[@rel='external'][text()='Busca en el inventario ']")
	private WebElement searchInventoryButtonEsp;

	@FindBy(css = "[data-atid='raq_form.build']")
	private WebElement buildYourOwnButton;

	@FindBy(xpath = "//a[@rel='external'][text()='DISEÑA Y COTIZA']")
	private WebElement buildYourOwnButtonEsp;

	@FindBy(xpath = "//a[@rel='external'][text()='Continúa con tu diseño']")
	private WebElement continueYourBuildButtonEsp;

	@FindBy(css = "[data-atid='raq_form.view_more']")
	private WebElement viewMore;

	@FindBy(css = "[data-atid='raq_form.addcomments']")
	private WebElement addCommentButton;

	@FindBy(css = "[data-atid='raq_form.comments']")
	private WebElement commentTextArea;

	@FindBy(css = "[class=\"zipcode has-icon-pencil zipcode-cta\"]")
	private WebElement topZipcodePencilIcon;

	@FindBy(css = "[class='zipcode-btn']")
	private WebElement topZipcodeIconMobile;

	@FindBy(xpath = "//a[@rel='external'][text()='Continue Your Build']")
	private WebElement continueYourBuildButton;

	@FindBy(css = "[data-di-id='#name']")
	private WebElement DealerName;

	@FindBy(css = "[data-di-id='#tfresh-btn-tfresh-btn-one']")
	private WebElement RequestAQuoteQDealersButton;

	@FindBy(css = "[class='tcom-custom-checkbox-label']")
	private WebElement undecidedCheckbox;

	@FindBy(css = "[ng-bind-html='$ctrl.estimatorStore.selectedYearSeries.tabManager[$ctrl.estimatorStore.tab].selectedModel.trimmedTitle']")
	private WebElement trimElement;

	@FindBy(css="[class=\"tcome-masthead-heading\"]")
	private WebElement headerTextObj;
	
	@FindBy(css="[class='zipcode-label']")
	private WebElement zipCodeLabel;

	@FindBy(css="[name='zipcode']")
	private WebElement zipCodeInputBox;

	@FindBy(css="[name='button']")
	private WebElement findButton;

	@FindBy(css="[class='tcom-pagination-message-item total-pages']")
	private WebElement totalPageElement;

	@FindBy(css="[data-atid='raq_dealer_select.next']")
	private WebElement nextButton;

	@FindBy(css="[data-atid='raq-dealer-select.view-more']")
	private WebElement viewMoreButton;

	public void clickOnViewMoreButton() {
		clickOn(viewMoreButton);
	}
	public List<String>  captureAllVendors() {
		int totalPage = Integer.parseInt(getText(totalPageElement));
		List<String> vendorsName = new ArrayList<String>();
		do {
			List<WebElement> vendorList = driver.findElements(
					By.xpath("//*[@class='tcom-dealer-select-list']//label[not(contains(@class, 'is-hidden'))]/span"));
			vendorList.forEach( element ->  vendorsName.add(element.getText()));	
			clickOn(nextButton);
			sleepInSecond(2);
		}while(--totalPage>=1);		
		return vendorsName;
	}

	public void changeZipCode(String zipCode) {
		clickOn(zipCodeLabel);
		inputText(zipCodeInputBox, zipCode);
		clickOn(findButton);
	}

	
	public String getZipCode() {
		waitForElement(zipCodeLabel);
		return getText(zipCodeLabel).trim();
	}
	
	public String getHeaderText() {
		waitForElement(headerTextObj);
		return getText(headerTextObj);
	}
	
	public void vehicleCheckBox() {
		javaScriptClick(vechicleCheck);
	}

	public String getTrim() {
		driver.switchTo().frame("payment-estimator-iframe");
		String name[] = getText(trimElement).split(",");
		String trim = "";
		for (int i = 1; i < name.length; i++)
			trim = trim + name[i];
		driver.switchTo().defaultContent();
		return getText(trimElement);
	}

	public String getRAQZEVConfirmationMessage() {
		return getText(RAQZEVConfirmationMessage);
	}
	
	/**
	 * Submit enter zipcode
	 * 
	 * @param model
	 */
	public void submitZipCode(RequestQuoteModel model) throws InterruptedException {
		submitZipCode(model.getZipCode());
	}

	/**
	 * Submit enter zipcode
	 * 
	 * @param model
	 */
	public void submitZipCode(String zipCode) throws InterruptedException {
		Thread.sleep(1000);
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		boolean status = isElementDisplay(zipInputBox);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		if (status) {
			inputTextWitClear(zipInputBox, zipCode);
			System.out.println(zipCode);
			javaScriptClick(zipSubmitButton);
		} else {
			waitAndClick(topZipcodePencilIcon);
			Thread.sleep(2000);
			inputTextWitClear(zipInputBox, zipCode);
			System.out.println("Pencil: " + zipCode);
			Thread.sleep(1000);
			clickOn(zipSubmitButton);
			Thread.sleep(2000);
		}
	}


	/**
	 * Submit enter zipcode
	 * 
	 * @param model
	 */
	public void submitZipCodeMobile(RequestQuoteModel model) throws InterruptedException {
		submitZipCodeMobile(model.getZipCode());
	}
	
	public boolean isVendorSelected() {
		sleepInSecond(2);
		waitForElementPresent(firstVendorCheckBox);
		boolean status = firstVendorCheckBox.isSelected();
		return status;
	}
	
	public void submitZipCodeMobile(String zipCode) throws InterruptedException {
		Thread.sleep(1000);
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		boolean status = isElementDisplay(zipInputBox);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		if (status) {
			inputTextWitClear(zipInputBox, zipCode);
			System.out.println(zipCode);
			javaScriptClick(zipSubmitButton);
		} else {
			javaScriptClick(topZipcodeIconMobile);
			inputTextWitClear(zipInputBox, zipCode);
			System.out.println("Pencil: " + zipCode);
			Thread.sleep(1000);
			clickOn(zipSubmitButton);
			Thread.sleep(2000);
		}
	}

	public String getMessage() {
		return getText(messageField);
	}

	public String getConfirmationThankYouMessage() {
		return getText(raqConfirmationMessage);
	}

	public String getConfirmationGradeMessage() {
		return getText(raqConfirmationGradeMessage);
	}

	/**
	 * Fill Request quote form
	 * 
	 * @param model
	 * @throws Exception
	 */
	public void fillRequestQuoteDetail(RequestQuoteModel model) throws Exception {
		Thread.sleep(2000);
		String raqExperience = Configuration.readApplicationFile("raqExperienceValue");
		if (raqExperience.equalsIgnoreCase("control")) {
			selectDropDownByText(seriesName, model.getSeriesName().toUpperCase());
			Thread.sleep(5000);
			selectDropDownByText(modelName, model.getModelName());
		} else {
			
			int randnum = Utilities.getRandomInteger(1, 2);
			if (randnum == 1) {	
				selectDropDownByText(seriesName, model.getSeriesName().toUpperCase());
			} else {
				model.setSeriesName("");
				clickOn(undecidedCheckbox);
			}
		}

		Thread.sleep(2000);
		clickOn(fullNameInput);
		inputText(firstName, model.getFirstName());
		inputText(lastNameInput, model.getLastName());
		inputText(emailAddressInput, model.getEmail());
		inputText(phoneInput, model.getPhone());
		if (raqExperience.equalsIgnoreCase("control")) {
			inputText(address1Input, model.getAddress());
			inputText(cityInput, model.getCity());
		}

		javaScriptClick(addCommentButton);
		inputText(commentTextArea, model.getCommentText());
		System.out.println(model.isSet());
		if (model.isSet()) {
			boolean status = firstVendorCheckBox.isSelected();
			clickOn(firstVendorName);
			Assert.assertEquals(status, false, "Vendor check box is selected for SET");
		}
		boolean status = firstVendorCheckBox.isSelected();
		Assert.assertEquals(status, true, "Vendor check box is selected");
		clickOn(emailAddressInput);

	}

	/**
	 * Click on submit button
	 */
	public void clickSubmitButton() {
		executeJsScroolToElement(submitButton);
		waitAndClick(submitButton);
	}

	/**
	 * Click on submit button
	 */
	public void clickRAQLocalSpecialSubmit() {
		waitAndClick(submitButtonLocalSpecial);
	}

	public void clickRequestAQuoteDealerButton() {
		waitForElement(RequestAQuoteQDealersButton);
		javaScriptClick(RequestAQuoteQDealersButton);
		// waitAndClick(RequestAQuoteQDealersButton);
	}

	/**
	 * Click on view more link
	 */
	public void clickViewMoreLink() {
		waitAndClick(viewMore);
	}

	/**
	 * Get first vendor name from vendor list
	 * 
	 * @return
	 */
	public String getVendorName() {
		// waitForElement(firstVendorName);
		waitForElementVisible(firstVendorName);
		return firstVendorName.getText();
	}

	/**
	 * Get first dealer code from vendor list
	 * 
	 * @return
	 */
	public String dealerCode() {
		return dealerCode.getAttribute("value");
	}

	
	public String getDealerName() {
		return DealerName.getText();
	}

	/**
	 * Get first vendor name from vendor list
	 * 
	 * @return
	 */
	public String getVendorNameLocalSpecial() {
		waitForElement(firstVendorNameLocalSpecial);
		return firstVendorNameLocalSpecial.getText();
	}

	/**
	 * Get first dealer code from vendor list
	 * 
	 * @return
	 */
	public String dealerCodeLocalSpecial() {
		return dealerCodeLocalSpecial.getAttribute("value");
	}

	@FindBy(xpath = "//*[@data-atid='raq_form.search' or @data-atid='raq_form.localspecials']")
	private WebElement localSpecialOrSearchInventoryButton;
	
	/**
	 * Verify request sent page "Search vendor" and "Build your own" buttons
	 */
	public void verifyButtons() {
		Assert.assertTrue(isElementPresent(localSpecialOrSearchInventoryButton), "Search Inventory button not found");
		Assert.assertTrue(isElementPresent(buildYourOwnButton), "Build your own button not found");
	}

	/**
	 * Verify request sent page "Search vendor" and "Build your own" buttons
	 */
	public void verifyButtonsEsp() {
		Assert.assertTrue(isElementPresent(searchInventoryButtonEsp), "Search vender button not found");
		Assert.assertTrue(isElementPresent(buildYourOwnButtonEsp), "Build your own button not found");
	}

	/**
	 * Verify request sent page "Search vendor" and "Build your own" buttons
	 */
	public void verifyBAndPButtonsAfterSubmit() {
		Assert.assertTrue(isElementPresent(searchInventoryButton), "Search Inventory button not found");
		Assert.assertTrue(isElementPresent(continueYourBuildButton), "Continue your build button not found");
	}

	public void verifyBAndPButtonsAfterSubmitEsp() {
		Assert.assertTrue(isElementPresent(searchInventoryButtonEsp), "Search vender button not found");
		Assert.assertTrue(isElementPresent(continueYourBuildButtonEsp), "Continue your build button not found");
	}

	public void selectSeries(String series) {	
		selectDropDownByText(seriesName, series);		
	}


	public String getSeriesOptionColor(String series) {	
		String locator = "//*[@id='seriesName-list']/li[contains(text(), '"+series+"')]";
		moveAndClickOnElement(seriesTrigger);
		waitForElement(locator);
		return getCssValue(driver.findElement(By.xpath(locator)), "background");	
	}

	public String getSelectedModelColor(String model) {
		moveAndClickOnElement(modelTrigger);
		String locator = "//*[@id=\"modelName-list\"]/li[contains(text(), '"+model+"')]";
		waitForElement(locator);
		return getCssValue(driver.findElement(By.xpath(locator)), "background");
	}
	
	public void fillRQDetailForLocalSpecial(RequestQuoteModel model) throws Exception {
		executeJsScroolToElement(fullNameInputLocalSpecial);
		waitAndClick(fullNameInputLocalSpecial);
		inputText(firstNameLocalSpecial, model.getFirstName());
		inputText(lastNameInputLocalSpecial, model.getLastName());
		inputText(emailAddressInputLocalSpecial, model.getEmail());
		inputText(phoneInputLocalSpecial, model.getPhone());

		String raqExperience = Configuration.readApplicationFile("raqExperienceValue");
		if (raqExperience.equalsIgnoreCase("control")) {
			inputText(address1InputLocalSpecial, model.getAddress());
			inputText(cityInputLocalSpecial, model.getCity());
		}

		clickOn(addCommentButtonLocalSpecial);
		inputText(commentTextAreaLocalSpecial, model.getCommentText());
		System.out.println("Model value " + model.isSet());
		if (model.isSet()) {
			boolean status = firstVendorNameCheckBoxLocalSpecial.isSelected();
			System.out.println("status1" + status);
			clickOn(firstVendorNameLocalSpecial);
			Assert.assertEquals(status, false, "Vendor check box is selected for SET");
		}
		boolean status = firstVendorNameCheckBoxLocalSpecial.isSelected();
		System.out.println("status 2" + status);
		Assert.assertEquals(status, true, "Vendor check box is not selected");
		clickOn(emailAddressInputLocalSpecial);
	}

	/**
	 * Fill Request quote form for local special
	 * 
	 * @param model
	 * @throws InterruptedException
	 */
	public void fillRQDetail(RequestQuoteModel model) throws Exception {
		Thread.sleep(2000);
		waitAndClick(fullNameInput);
		inputText(firstName, model.getFirstName());
		inputText(lastNameInput, model.getLastName());
		// inputText(zipCodeInput, model.getZipCode());
		inputText(emailAddressInput, model.getEmail());
		inputText(phoneInput, model.getPhone());

		String raqExperience = Configuration.readApplicationFile("raqExperienceValue");
		if (raqExperience.equalsIgnoreCase("control")) {
			inputText(address1Input, model.getAddress());
			inputText(cityInput, model.getCity());
		}

		clickOn(addCommentButton);
		inputText(commentTextArea, model.getCommentText());
		System.out.println(model.isSet());
		if (model.isSet()) {
			boolean status = firstVendorCheckBox.isSelected();
			clickOn(firstVendorName);
			Assert.assertEquals(status, false, "Vendor check box is selected for SET");
		}
		boolean status = firstVendorCheckBox.isSelected();
		Assert.assertEquals(status, true, "Vendor check box is not selected");
		clickOn(emailAddressInput);
	}

	/**
	 * Select trim from drop down
	 * 
	 * @param trim
	 */
	public void selectTrim(String trim) {
		selectDropDownByText(modelName, trim);
	}

}