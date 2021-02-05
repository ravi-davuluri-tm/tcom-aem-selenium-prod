package com.toy.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import com.toy.selenium.core.BasePage;

public class GRSupraPage extends BasePage {

	public GRSupraPage(WebDriver driver) {
		super(driver);	
	}
	
	@FindBy(name="firstName")
	private WebElement firstNameInput;
	
	@FindBy(name="lastName")
	private WebElement lastNameInput;
	
	@FindBy(name="email")
	private WebElement emailInput;
	
	@FindBy(name="zipcode")
	private WebElement zipcodeInput;
	
	@FindBy(css="[type='submit']")
	private WebElement submitButton;
	
	@FindBy(css="[class='tcom-form-confirmation-message']")
	private WebElement thankYouMsg;
	
	@FindBy(css="[class='tcom-form-confirmation-header']")
	private WebElement requestSentMsg;
	
	
	public void submitForm(String fistName, String lastName, 
			String zipCode, String email) {
		inputText(firstNameInput, fistName);
		inputText(lastNameInput, lastName);	
		inputText(emailInput, email);	
		inputText(zipcodeInput, zipCode);
		clickOn(submitButton);
	}
	
	public void verifySubmitted(String msg, String mgs2) {	
		Assert.assertEquals(getText(thankYouMsg).trim(), msg);
		Assert.assertEquals(getText(requestSentMsg).trim(), mgs2);
	}
	
	
}