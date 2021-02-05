package com.toy.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import com.toy.selenium.core.BasePage;

public class TrialSpecialEditionsPage extends BasePage  {

	public TrialSpecialEditionsPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(id="fullName")
	private WebElement fullName;
	
	@FindBy(id="firstName")
	private WebElement firstNameElement;
	
	@FindBy(id="lastName")
	private WebElement lastNameElement;
	
	@FindBy(id="email")
	private WebElement emailElement;
	
	@FindBy(id="zipcode")
	private WebElement zipcodeElement;
	
	@FindBy(xpath="//button[@class='tfresh-btn tfresh-btn-one' or @data-di-id='#tfresh-btn-tfresh-btn-one-2']") 
	private WebElement submitForm;
	
	@FindBy(css="[class='tcom-form-confirmation-header']")
	private WebElement requestSendMsgElement;
	
	@FindBy(css="[class='tcom-form-confirmation-message']")
	private WebElement thankMsgElement;
	
	public void fillForm(String firstName, String lastName, String email, String zipCode) {
		executeJsScroolToElement(fullName);
		clickOn(fullName);
		inputText(firstNameElement, firstName);
		inputText(lastNameElement, lastName);
		inputText(emailElement, email);
		inputText(zipcodeElement, zipCode);
	}
	
	public void clickSubmitButton() {
		clickOn(submitForm);
	}
	
	public void verifySubmitConfirmMsg(String msg1, String msg2) {
		Assert.assertEquals(getText(requestSendMsgElement).trim(), msg1);
		Assert.assertEquals(getText(thankMsgElement).trim(), msg2);
	}
	
}