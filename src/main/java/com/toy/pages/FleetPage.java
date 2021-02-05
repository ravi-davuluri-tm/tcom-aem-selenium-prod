package com.toy.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import com.toy.selenium.core.BasePage;

public class FleetPage extends BasePage {

	public FleetPage(WebDriver driver) {
		super(driver);	
	}
	
	@FindBy(name="firstName")
	private WebElement firstNameInput;
	
	@FindBy(name="lastName")
	private WebElement lastNameInput;
	
	@FindBy(name="company")
	private WebElement companyInput;
	
	@FindBy(name="zipcode")
	private WebElement zipcodeInput;
	
	@FindBy(name="email")
	private WebElement emailInput;
	
	@FindBy(name="phone")
	private WebElement phoneInput;
	
	@FindBy(name="phoneExtension")
	private WebElement phoneExtensionInput;
	
	@FindBy(css="[class='tcom-form-comments'] > span")
	private WebElement addCommentbutton;
	
	@FindBy(name="comments")
	private WebElement commentsInput;
	
	@FindBy(css="button[type='submit']")
	private WebElement submitButton;
	
	@FindBy(css="[data-aa-content-section='handraiser-complete'] h2")
	private WebElement competeFormMessage;
		
	@FindBy(css="[class='tcom-form-confirmation-header']")
	private WebElement requestSentMessage;
	
	@FindBy(css="[class='tcom-form-confirmation-message']")
	private WebElement thanksMessage;
	
	public void submitForm(String fistName, String lastName, String zipCode , 
			String email, String phoneNumber, String companyName) {
		inputText(firstNameInput, fistName);
		inputText(lastNameInput, lastName);
		inputText(companyInput, companyName);
		inputText(zipcodeInput, zipCode);
		inputText(emailInput, email);
		inputText(phoneInput, phoneNumber);
		clickOn(addCommentbutton);
		inputText(commentsInput, "Fleet Test comments");
		clickOn(submitButton);
	}
	
	public void verifySubmitMessage(String msg1, String msg2, String msg3) {
	//	String message1 = getText(competeFormMessage);
		String message2 = getText(requestSentMessage);
		String message3 = getText(thanksMessage);		
	//Assert.assertEquals(message1.trim(), msg1);
		Assert.assertEquals(message2.trim(), msg2);
		Assert.assertEquals(message3.trim(), msg3);
	}
}