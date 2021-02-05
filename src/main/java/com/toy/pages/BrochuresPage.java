package com.toy.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import com.toy.selenium.core.BasePage;

public class BrochuresPage extends BasePage {

	public BrochuresPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//a[@class='tcom-icon-cta receive-by-mail' or @data-di-id='#tcom-icon-cta-receive-by-mail']")
	private WebElement receiveByMailButton;

	@FindBy(xpath = "//div[select[@aria-label=\"SECOND BROCHURE\"]]/div")
	private WebElement selectBrochureField;
	
	@FindBy(css = "[id='firstName']")
	private WebElement firstNameInput;

	@FindBy(css = "[id='lastName']")
	private WebElement lastNameInput;

	@FindBy(css = "[id='emailAddress']")
	private WebElement emailAddressInput;

	@FindBy(css = "[id='phone']")
	private WebElement phoneInput;

	@FindBy(css = "[id='address1']")
	private WebElement address1Input;

	@FindBy(css = "[id='city']")
	private WebElement cityInput;

	@FindBy(xpath = "//div[select[@name='state']]/div[@class='tcom-select-trigger']")
	private WebElement stateDropDown;
	
	@FindBy(css = "[id='zipcode']")
	private WebElement zipcodeInput;

	@FindBy(css = "[value='submit']")
	private WebElement submitButton;

	@FindBy(css = "[id='error in filed firstName']")
	private WebElement firstNameErrorMsg;

	@FindBy(css = "[id='error in filed lastName']")
	private WebElement lastNameErrorMsg;

	@FindBy(css = "[id='error in filed emailAddress']")
	private WebElement emailAddressErrorMsg;

	@FindBy(css = "[id='error in filed address1']")
	private WebElement address1Error;

	@FindBy(css = "[id='error in filed city']")
	private WebElement cityError;

	@FindBy(css = "[id='error in field']")
	private WebElement fieldError;

	@FindBy(css = "[id='error in filed zipcode']")
	private WebElement zipcodeError;

	@FindBy(css = "[class='tcom-request-brochure-form-headline']")
	private WebElement thanksMsg;
	
	@FindBy(css = "[class='tcom-request-brochures-list-title']")
	private WebElement brochuresMsg;
	
	@FindBy(css = "[class='tcom-request-brochure-form-header']")
	private WebElement brochuresMsg1;
			
	@FindBy(css = "[class='tcom-masthead-heading']")
	public WebElement headerText;

	public void verifySubnav(List<String> menus) {
		for(String name : menus) {
			WebElement subMenuElement= driver.findElement(By.xpath("//*[@class='tcom-tab-subnav-nav']//a[contains(text(), '"+name+"')]"));
			Assert.assertTrue(isElementPresent(subMenuElement), "Sub menu not found "+name);
		}
	}

	public void verifySubnavNavigation(List<String> menus) {
		for(String name : menus) {
			WebElement element = driver.findElement(By.xpath("//*[@class='tcom-tab-subnav-nav']//a[contains(text(), '"+name+"')]")); 					
			clickOn(element);
			sleepInSecond(2);
			String attribute = getAttribute(element, "class");	
			System.out.println(attribute);
			Assert.assertTrue(attribute.contains("is-on"));
		}
	}

	public void verifyHeaderText(String text) {
		Assert.assertEquals(getText(headerText), text);
	}
	
	public void clickReceiveByMail() {
		clickOn(receiveByMailButton);
	}

	public void clickOnSubmitButton() {
		waitAndClick(submitButton);
	}
	
	public void selectBrochureField(String brochure) {
		clickOn(selectBrochureField);
		clickOn(driver.findElement(By.xpath("//div[select[@aria-label='SECOND BROCHURE']]/ul/li[text()='"+brochure+"']")));
	}
	
	public void validateFormMandatoryField() {
		this.clickOnSubmitButton();
		Assert.assertEquals(getText(firstNameErrorMsg).trim(), "This field is required.");
		Assert.assertEquals(getText(lastNameErrorMsg).trim(), "This field is required.");
		Assert.assertEquals(getText(emailAddressErrorMsg).trim(), "This field is required.");
		Assert.assertEquals(getText(address1Error).trim(), "This field is required.");
		Assert.assertEquals(getText(cityError).trim(), "This field is required.");
		Assert.assertEquals(getText(fieldError).trim(), "This field is required.");
		Assert.assertEquals(getText(zipcodeError).trim(), "This field is required.");
	}
	
	public void fillFormAndSubmit(String firstName, String lastName, String email, String phone , 
			String address, String city, String state, String zipcode) {
		inputText(firstNameInput, firstName);
		inputText(lastNameInput, lastName);
		inputText(emailAddressInput, email);
		inputText(phoneInput, phone);
		inputText(address1Input, address);
		inputText(cityInput, city);
		clickOn(stateDropDown);
		clickOn(driver.findElement(By.xpath("//div[select[@name=\"state\"]]/ul/li[text()='"+state+"']")));
		inputText(zipcodeInput, zipcode);
		clickOnSubmitButton();
	}
	
	public void verifySuccessMessage() {
		Assert.assertEquals(getText(thanksMsg), "Thank You for Your Request");
		Assert.assertEquals(getText(brochuresMsg), "BROCHURES ORDERED:");
		Assert.assertTrue(getText(brochuresMsg1).contains("We appreciate the opportunity to serve you."));
	}

}