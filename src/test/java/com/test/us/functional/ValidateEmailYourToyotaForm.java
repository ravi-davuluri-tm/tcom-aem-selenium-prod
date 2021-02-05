package com.test.us.functional;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.toy.constant.GlobalPagesConstant;
import com.toy.datamodel.RequestQuoteModel;
import com.toy.pages.BuildAndPricePage;
import com.toy.pages.HomePage;
import com.toy.selenium.core.BaseTest;

public class ValidateEmailYourToyotaForm extends BaseTest {
	
	private HomePage homePage;
	private BuildAndPricePage buildAndPricePage;

	@BeforeMethod
	public void initObj() {
		homePage = PageFactory.initElements(getWebDriver(), HomePage.class);		
	}
	
	@Test(priority = 1, enabled = true)
	public void ValidateEmailYourToyotaFormTest() throws InterruptedException {
		reportLog("click on shopping tool menu");
		RequestQuoteModel requestQuoteModel = new RequestQuoteModel();
		requestQuoteModel.setZipCode("75010");
		requestQuoteModel.setSeriesName("2021 GR Supra");

		String newUrl = getWebDriver().getCurrentUrl() + "?zipcode="+requestQuoteModel.getZipCode();
		getWebDriver().navigate().to(newUrl);
		reportLog("Navigate Url with zip code "+requestQuoteModel.getZipCode());

		homePage.selectShoppingToolMenu();
		buildAndPricePage = homePage.gotoBuildAndPricePage();
		reportLog("navigated build and price page");

		buildAndPricePage.selectCarBySeries(requestQuoteModel);
		reportLog("Select series from car list " + requestQuoteModel.getSeriesName());	
				
		buildAndPricePage.selectTabByName(GlobalPagesConstant.Summary);
		reportLog("Select summary tab");
		
		buildAndPricePage.clickEmailYourToyotaButton();
		reportLog("Click on email your toyota button");
		
		buildAndPricePage.enterFirstName("523745");
		reportLog("Enter first name with numeric value");
		
		buildAndPricePage.verifyErrorNameField("Names cannot contain numbers.");
		reportLog("Verify name field error message 'Names cannot contain numbers.'");
		
		buildAndPricePage.enterFirstName("#$$%^");
		reportLog("Enter first name with special character value");
		
		buildAndPricePage.verifyErrorNameField("Names cannot contain special characters.");
		reportLog("Verify name field error message 'Names cannot contain special characters.'");
		
		buildAndPricePage.enterFirstName(" ");
		reportLog("Enter blank value in first name input field");
		
		buildAndPricePage.verifyErrorNameField("This field is required.");
		reportLog("Verify name field error message 'This field is required.'");
		
		buildAndPricePage.enterEmail("Test123");
		reportLog("Enter wrong email in email input fields");
		
		buildAndPricePage.verifyErrorEmailField("Please enter a valid email.");
		reportLog("Verify email field error message 'Please enter a valid email.'");
		
		buildAndPricePage.enterEmail(" ");
		reportLog("Enter blank value in email input fields");
		
		buildAndPricePage.verifyErrorEmailField("Please enter a valid email.");
		reportLog("Verify email field error message 'Please enter a valid email.'");
		
		buildAndPricePage.enterRecipientEmail("Test123");
		reportLog("Enter wrong email in recipient email input fields");
		
		buildAndPricePage.verifyErrorRecipientEmailField("Please enter a valid email.");
		reportLog("Verify Recipient email field error message 'Please enter a valid email.'");
				
		buildAndPricePage.enterRecipientEmail(" ");
		reportLog("Enter blank value in recipient email input fields");
		
		buildAndPricePage.verifyErrorRecipientEmailField("Please enter a valid email.");
		reportLog("Verify Recipient email field error message 'Please enter a valid email.'");
		
		// Fill valid details and submit
		buildAndPricePage.enterFirstName("sendto");
		buildAndPricePage.enterEmail("icx.dallas.testing@gmail.com");
		buildAndPricePage.enterRecipientEmail("icx.dallas.testing@gmail.com");
		buildAndPricePage.clickSubmitButton();
		reportLog("Fill valid details with manadatory fields only");
		
		buildAndPricePage.verifyConfirmationMsg("Confirmation");
		reportLog("Verify confirmation message");
	}

}