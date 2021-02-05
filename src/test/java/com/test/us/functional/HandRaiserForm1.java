package com.test.us.functional;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import com.toy.pages.TrialSpecialEditionsPage;
import com.toy.selenium.core.BaseTest;

public class HandRaiserForm1 extends BaseTest {

	@Test
	public void HandRaiserFormTest() throws InterruptedException {
		getWebDriver().manage().deleteAllCookies();
		TrialSpecialEditionsPage trialSpecialEditionsPage = PageFactory.initElements(getWebDriver(), TrialSpecialEditionsPage.class);
		getWebDriver().navigate().to(this.applicationUrl + "/upcoming-vehicles/trailspecialeditions/");
		reportLog("Navigate url "+this.applicationUrl + "/upcoming-vehicles/trailspecialeditions/");
		Thread.sleep(2000);
		trialSpecialEditionsPage.fillForm("sendto", "text", "icxtoyota@gmail.com" ,"90703");
		reportLog("Fill complete form  with valid data");
		
		trialSpecialEditionsPage.clickSubmitButton();
		reportLog("Click on submit button");
		
		trialSpecialEditionsPage.verifySubmitConfirmMsg("Request sent", "Thank you!");
		reportLog("Verify message 'Request sent' and 'Thank you!'");
	}
}