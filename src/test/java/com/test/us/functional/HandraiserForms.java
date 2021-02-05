package com.test.us.functional;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import com.toy.pages.FleetPage;
import com.toy.pages.GRSupraPage;
import com.toy.pages.TrialSpecialEditionsPage;
import com.toy.selenium.core.BaseTest;

public class HandraiserForms extends BaseTest {

	private FleetPage fleetPage;
	private GRSupraPage grSupraPage;
	
	String firstName = "sendto";
	String lastName = "text";
	String email = "icxtoyota@gmail.com";
	String phoneNumber = "223-432-4324";
	String companyName = "Toyota";
	String zipCode = "75010";
	
	@Test(priority=1)
	public void contactFleetTest() {
		fleetPage = PageFactory.initElements(getWebDriver(), FleetPage.class);
		String newUrl = getWebDriver().getCurrentUrl();
		getWebDriver().navigate().to(newUrl +"/fleet");
		super.reportLog("Navigated fleet /fleet");
		
		fleetPage.submitForm(firstName, lastName, zipCode , email, phoneNumber, companyName);
		super.reportLog("Fill Contact Toyota Fleet Sales Form");
		fleetPage.verifySubmitMessage("Contact Toyota Fleet Sales", "Request Sent",
				"Thank you! A Fleet Manager will be in touch within a few days.");
		super.reportLog("Verified Request Sent message");
	}
	
	@Test(priority=2)
	public void contactEspanolFleetTest() {
		fleetPage = PageFactory.initElements(getWebDriver(), FleetPage.class);
		String newUrl = getWebDriver().getCurrentUrl();
		getWebDriver().navigate().to(newUrl +"/espanol/fleet/");
		super.reportLog("Navigated espanol fleet /espanol/fleet/");
		
		fleetPage.submitForm(firstName, lastName, zipCode , email, phoneNumber, companyName);
		super.reportLog("Fill Contact Toyota Fleet Sales Form");
		fleetPage.verifySubmitMessage("Contactar centro de ventas", "Enviado",
				"¡Gracias! Un gerente de Toyota Fleet se comunicará contigo dentro de unos días.");
		super.reportLog("Verified Enviado message");
	}
	
	@Test(priority=3)
	public void contactGRSupraTest() {
		String newUrl = getWebDriver().getCurrentUrl();
		getWebDriver().navigate().to(newUrl +"/concept-vehicles/supra/gr-concept/");
		super.reportLog("Navigated /concept-vehicles/supra/gr-concept/");
	
		grSupraPage = PageFactory.initElements(getWebDriver(), GRSupraPage.class);
		grSupraPage.submitForm(firstName, lastName, zipCode, email);
		super.reportLog("Fill Sign Up for GR Supra Racing Concept Updates Form");
		grSupraPage.verifySubmitted("Thank you!", "Request sent");
		super.reportLog("Verified Request Sent message");
	}
	
	@Test(priority=4)
	public void contactGRSupraEspanolTest() {
		String newUrl = getWebDriver().getCurrentUrl();
		getWebDriver().navigate().to(newUrl +"/espanol/concept-vehicles/supra/gr-concept/");
		super.reportLog("Navigated /espanol/concept-vehicles/supra/gr-concept/");

		grSupraPage = PageFactory.initElements(getWebDriver(), GRSupraPage.class);
		grSupraPage.submitForm(firstName, lastName, zipCode, email);
		
		grSupraPage.verifySubmitted("Gracias.", "Se envió tu solicitud.");
		super.reportLog("Verified solicitud message");
	}
	
	@Test(priority=5)
	public void contactSupraXfinitySeriesTest() {
		String newUrl = getWebDriver().getCurrentUrl();
		getWebDriver().navigate().to(newUrl +"/concept-vehicles/supra/xfinity-series/");
		super.reportLog("Navigated /concept-vehicles/supra/xfinity-series/");

		grSupraPage = PageFactory.initElements(getWebDriver(), GRSupraPage.class);
		grSupraPage.submitForm(firstName, lastName, zipCode, email);
		super.reportLog("Fill Sign Up for Supra Xfinity Series Race Car Updates Form");
		grSupraPage.verifySubmitted("Thank you!", "Request sent");
		super.reportLog("Verified Request Sent message");
	}
	
	@Test(priority=6)
	public void contactSupraXfinityEspanolTest() {
		String newUrl = getWebDriver().getCurrentUrl();
		getWebDriver().navigate().to(newUrl +"/espanol/concept-vehicles/supra/xfinity-series");
		super.reportLog("Navigated /espanol/concept-vehicles/supra/xfinity-series");

		grSupraPage = PageFactory.initElements(getWebDriver(), GRSupraPage.class);
		grSupraPage.submitForm(firstName, lastName, zipCode, email);
		super.reportLog("Fill Sign Up for Supra Xfinity Series Race Car Updates Form");
		grSupraPage.verifySubmitted("Gracias.", "Se envió tu solicitud.");
		super.reportLog("Verified Se envio tu solicitud message");
	}
	
	@Test(priority=7)
	public void contactGRHotHatchTest() {
		String newUrl = getWebDriver().getCurrentUrl();
		getWebDriver().navigate().to(newUrl +"/upcoming-vehicles/gr-hot-hatch/");
		super.reportLog("Navigated /upcoming-vehicles/gr-hot-hatch/");

		grSupraPage = PageFactory.initElements(getWebDriver(), GRSupraPage.class);
		grSupraPage.submitForm(firstName, lastName, zipCode, email);
	
		super.reportLog("Fill I'm in. Tell me more Form");
		grSupraPage.verifySubmitted("Thank you!", "Request sent");
		super.reportLog("Verified Request Sent message");
		
	}
	
	

	@Test(priority=8)
	public void contactGRHotHatchEspanolTest() {
		String newUrl = getWebDriver().getCurrentUrl();
		getWebDriver().navigate().to(newUrl +"/espanol/upcoming-vehicles/gr-hot-hatch");
		super.reportLog("Navigated /espanol/upcoming-vehicles/gr-hot-hatch");

		grSupraPage = PageFactory.initElements(getWebDriver(), GRSupraPage.class);
		grSupraPage.submitForm(firstName, lastName, zipCode, email);
		
		grSupraPage.verifySubmitted("Gracias.", "Se envió tu solicitud.");
		super.reportLog("Validated solicitud message");
	}
	
	@Test
	public void UpcomingVehiclesTrailspecialeidtionsFormTest() throws InterruptedException {
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