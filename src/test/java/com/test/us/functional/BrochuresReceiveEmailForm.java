package com.test.us.functional;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import com.toy.pages.BrochuresPage;
import com.toy.pages.HomePage;
import com.toy.selenium.core.BaseTest;

public class BrochuresReceiveEmailForm extends BaseTest {

	String firstName = "sendto";
	String lastName = "text";
	String email = "icxtoyota@gmail.com";
	String phoneNumber = "223-432-4324";
	String address = "17 los angeles st";
	String city = "LA";
	String state = "California";
	String zipCode = "75010";
	String secondVehicle = "2021 GR Supra";
	
	@Test()
	public void BrochuresReceiveEmailFormTest() {
		homePage = PageFactory.initElements(getWebDriver(), HomePage.class);	
		BrochuresPage brochuresPage = PageFactory.initElements(getWebDriver(), BrochuresPage.class);	
		getWebDriver().navigate().to(applicationUrl + "?zipcode=90210");
		reportLog("Navigate Url "+ applicationUrl);

		homePage.goToViewBrochuersPage();
		super.reportLog("Navigate View Brochures menu from shopping tool menu");
		
		brochuresPage.clickReceiveByMail();
		super.reportLog("Click on Receive by mail button");
		
		brochuresPage.validateFormMandatoryField();
		super.reportLog("Validate form mandatory fields error message");
		
		brochuresPage.selectBrochureField(secondVehicle);
		brochuresPage.fillFormAndSubmit(firstName, lastName, email, phoneNumber, address, city, state, zipCode);
		super.reportLog("Fill form and click on submit button");
		
		brochuresPage.verifySuccessMessage();
		super.reportLog("Verify success message");
	}
}