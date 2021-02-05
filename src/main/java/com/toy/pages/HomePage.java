package com.toy.pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.toy.constant.PageTitleConstant;
import com.toy.datamodel.RequestQuoteModel;
import com.toy.selenium.core.BasePage;
import com.toy.utilities.Utilities;


public class HomePage extends BasePage {

	public HomePage(WebDriver driver) {
		super(driver);
	}

	@FindBy(css = "[href='/findyourmatch/']")
	private WebElement findMatchMenu;
	
	@FindBy(css = "[data-view='select-vehicle']")
	private WebElement selectVehicleMenu;
		
	@FindBy(css = "[class='hamburger']")
	private WebElement hamburgerMobile;
	
	@FindBy(css = "[class='shopping-tools primary'] button")
	private WebElement shoppingToolMenuMobile;
	
	@FindBy(css = "[class='guía-de compra primary'] button")
	private WebElement shoppingToolMenuMobileEsp;
	
	@FindBy(css = "[data-service='shoppingTools']")
	private WebElement shoppingToolMenu;
	
	@FindBy(css = "[class='vehicles primary'] button")
	private WebElement vehiclesMenu;
	
	@FindBy(css = "[href='/local-specials/']")
	private WebElement localSpecials;

	@FindBy(css = "[class='ofertas-locales primary'] a")
	private WebElement localSpecialsEspMobile;

	//@FindBy(css = "[href='/espanol/local-specials/']")
	@FindBy(css = "[class='shopping-tools'] [href='/espanol/local-specials/']")
	private WebElement localSpecialsEsp;

	@FindBy(xpath = "//a[@href='/request-a-quote/']")
	private WebElement requestAQuote;

	@FindBy(css = "[href='/what-fits-my-budget/']")
	private WebElement whatFitsMyBudgetMenu;
	
	//@FindBy(xpath = "//div[@class='secondary-links-wrapper']//li[@class='pedir-cotización']/a")
	@FindBy(xpath="//a[@href='/espanol/request-a-quote/']")
	private WebElement requestAQuoteEsp;
	
	@FindBy(css = "[class='pedir-cotización primary'] a")
	private WebElement requestAQuoteMobileEsp;
	
	@FindBy(xpath = "//div[@id='tcom-shopping-tools-drawer']//span[text()='Contact A Dealer']")
	private WebElement contactADealer;

	@FindBy(css = "[class='build-price primary'] [href*='/configurator']")
	private WebElement buildAndPriceMobile;

	@FindBy(css = "[href^='/configurator']")
	private WebElement buildAndPrice;
	
	@FindBy(css = "[class='shopping-tools'] [href^='/configurator']")
	private WebElement buildAndPriceShoppingToolSubMenu;
	
	@FindBy(css = "[href='/espanol/configurator/']")
	private WebElement buildAndPriceEsp;
	
	@FindBy(css = "[href='/search-inventory/']")
	private WebElement searchInventory;

	@FindBy(css = "[href='/espanol/search-inventory/']")
	private WebElement searchInventoryEsp;	
	
	@FindBy(css = "[id='tcom-header'] [href='/espanol/']")
	private WebElement espanolMenu;	
	
	@FindBy(xpath = "//*[@class='tcom-footer-links-title'][text()='Language']")
	private WebElement lanugageFooterMobileMenu;
	
	@FindBy(css = "[class='language-toggle']")
	private WebElement espanolFooterMobileMenu;
	
	@FindBy(css = "[id='find-dealer']")
	private WebElement findDealers;

	@FindBy(css = "[class='menu loaded'] [href='/dealers/']")
	private WebElement findDealersMobile;

	@FindBy(css="[href='/accessories/']")
	private WebElement accessoriesElement;
	
	@FindBy(css="[href='/payment-estimator/']")
	private WebElement paymentEstimatorEle;
	
	@FindBy(css="[href='/brochures/']")
	private WebElement viewBrochuresElement;
	
	@FindBy(css="[href='/contact-a-dealer/']")
	private WebElement contactADealerMenu;
		

	public VehiclePage goToSelectVehicle(){
		javaScriptClick(selectVehicleMenu);
		return PageFactory.initElements(driver, VehiclePage.class);
	}
		
	public void selectEspanolLanguage(){
		javaScriptClick(espanolMenu);
	}
	
	public void goToContactDealerPage() {
		this.selectShoppingToolMenu();
		waitForElement(contactADealerMenu);
		javaScriptClick(contactADealerMenu);
	}
	
	public void goToViewBrochuersPage() {
		this.selectShoppingToolMenu();
		waitForElement(viewBrochuresElement);
		javaScriptClick(viewBrochuresElement);
	}
	
	
	
	public void goTopaymentEstimatorPage() {
		waitForElement(paymentEstimatorEle);
		javaScriptClick(paymentEstimatorEle);
	}
	
	/**
	 * Select espanol language 
	 */	
	public void selectEspanolLanguageMobile(){
		javaScriptClick(lanugageFooterMobileMenu);
		javaScriptClick(espanolFooterMobileMenu);
	}
	
	public void selectShoppingToolMenu() {
		waitForElementVisible(shoppingToolMenu);
		javaScriptClick(shoppingToolMenu);
	}
	
	public void gotoBuildAndProceShoppingToolSubMenu() {
		this.selectShoppingToolMenu();
		javaScriptClick(buildAndPriceShoppingToolSubMenu);
	}

	public DealerPage gotofindDealers() {
		waitForElement(findDealers);
		javaScriptClick(findDealers);		
		waitForTitleContains(PageTitleConstant.FindADealerUS);
		String title = super.getTitle();
		Assert.assertTrue(title.contains(PageTitleConstant.FindADealerUS), "Title not contains in "+title);		
		return PageFactory.initElements(driver, DealerPage.class);
	}

	public void goToAccessoriesPage() {
		waitForElement(accessoriesElement);
		javaScriptClick(accessoriesElement);
	}

	public FindDealersPage gotofindDealersMobile() {
		waitForElement(hamburgerMobile);
		javaScriptClick(hamburgerMobile);	
		waitForElement(findDealersMobile);
		javaScriptClick(findDealersMobile);
		waitForTitleContains(PageTitleConstant.FindADealerUS);
		Assert.assertTrue(getTitle().contains(PageTitleConstant.FindADealerUS));	
		return PageFactory.initElements(driver, FindDealersPage.class);
	}
	
	public WhatFitsMyBudgetPage goToWhatFitsMyBudgetPage() {
		waitForElement(whatFitsMyBudgetMenu);
		javaScriptClick(whatFitsMyBudgetMenu);
		waitForTitleContains(PageTitleConstant.WhatFitsMybudgetUs);
		Assert.assertTrue(getTitle().contains(PageTitleConstant.WhatFitsMybudgetUs), "Title not found in " + title);
		return PageFactory.initElements(driver, WhatFitsMyBudgetPage.class);
	}
	
	
	public RequestAQuotePage gotoRequestQuote() {
		waitForElement(requestAQuote);
		javaScriptClick(requestAQuote);	
		waitForTitleContains(PageTitleConstant.RAQStandaloneUS);
		Assert.assertTrue(getTitle().contains(PageTitleConstant.RAQStandaloneUS), "Title not found in " + title);
		return PageFactory.initElements(driver, RequestAQuotePage.class);
	}
	
	
	public RequestAQuotePage gotoRequestQuoteMobile() {
		waitForElement(hamburgerMobile);
		javaScriptClick(hamburgerMobile);
		waitForElement(shoppingToolMenuMobile);
		javaScriptClick(shoppingToolMenuMobile);
		waitForElement(requestAQuote);
		javaScriptClick(requestAQuote);
		waitForTitleContains(PageTitleConstant.RAQStandaloneUS);
		Assert.assertTrue(getTitle().contains(PageTitleConstant.RAQStandaloneUS), "Title not found in " + title);
		return PageFactory.initElements(driver, RequestAQuotePage.class);
	}

	public RequestAQuotePage gotoRequestQuoteMobileEsp() {
		waitForElement(hamburgerMobile);
		javaScriptClick(hamburgerMobile);
		waitForElement(shoppingToolMenuMobileEsp);
		javaScriptClick(shoppingToolMenuMobileEsp);
		waitForElement(requestAQuoteMobileEsp);
		javaScriptClick(requestAQuoteMobileEsp);
		waitForTitleContains(PageTitleConstant.RAQStandaloneEsp);
		Assert.assertTrue(getTitle().contains(PageTitleConstant.RAQStandaloneEsp), "Title not found in " + title);
		return PageFactory.initElements(driver, RequestAQuotePage.class);
	}
	
	public RequestAQuotePage gotoRequestQuoteESP() {		
		javaScriptClick(requestAQuoteEsp);
		waitForTitleContains(PageTitleConstant.RAQStandaloneEsp);
		Assert.assertTrue(getTitle().contains(PageTitleConstant.RAQStandaloneEsp), "Title not found in " + title);
		return PageFactory.initElements(driver, RequestAQuotePage.class);
	}
	
	public SearchInventoryPage gotoSearchInventory() {
		waitForElement(searchInventory);
		javaScriptClick(searchInventory);
		waitForTitleContains(PageTitleConstant.SearchInventoryUS);	
		String title = getTitle();		
		Assert.assertTrue(title.contains(PageTitleConstant.SearchInventoryUS), "Title not found in " + title);
		return PageFactory.initElements(driver, SearchInventoryPage.class);
	}

	public SearchInventoryPage gotoSearchInventoryMobile() {
		waitForElement(hamburgerMobile);
		javaScriptClick(hamburgerMobile);
		waitForElement(shoppingToolMenuMobile);
		javaScriptClick(shoppingToolMenuMobile);
		waitForElement(searchInventory);
		javaScriptClick(searchInventory);
		waitForTitleContains(PageTitleConstant.SearchInventoryUS);
		Assert.assertTrue(getTitle().contains(PageTitleConstant.SearchInventoryUS), "Title not found in " + title);
		return PageFactory.initElements(driver, SearchInventoryPage.class);
	}

	public SearchInventoryPage gotoSearchInventoryEspMobilePage(){
		waitForElement(hamburgerMobile);
		javaScriptClick(hamburgerMobile);
		waitForElement(shoppingToolMenuMobileEsp);
		javaScriptClick(shoppingToolMenuMobileEsp);
		waitForElement(searchInventoryEsp);
		javaScriptClick(searchInventoryEsp);
		waitForTitleContains(PageTitleConstant.SearchInventoryESP);	
		Assert.assertTrue(getTitle().contains(PageTitleConstant.SearchInventoryESP),
				"Title not matched");
		return PageFactory.initElements(driver, SearchInventoryPage.class);
	}
	
	public FindYourMatchPage gotoFindMatch() {
		this.selectShoppingToolMenu();
		super.javaScriptClick(findMatchMenu);
		waitForTitleContains(PageTitleConstant.FindYourMatchUS);
		Assert.assertTrue(getTitle().contains(PageTitleConstant.FindYourMatchUS), "Title not found in " + title);
		return PageFactory.initElements(driver, FindYourMatchPage.class);
	}
	
	public SearchInventoryPage gotoSearchInventoryEsp() {
		javaScriptClick(searchInventoryEsp);
		waitForTitleContains(PageTitleConstant.SearchInventoryESP);	
		Assert.assertTrue(getTitle().contains(PageTitleConstant.SearchInventoryESP),
				"Title not matched");
		return PageFactory.initElements(driver, SearchInventoryPage.class);
	}

	public RequestAQuotePage gotoContactDealer() {
		waitAndClick(contactADealer);
		return PageFactory.initElements(driver, RequestAQuotePage.class);
	}

	public BuildAndPricePage gotoBuildAndPricePage() {
		waitForElement(buildAndPrice);
		javaScriptClick(buildAndPrice);
		waitForTitleContains(PageTitleConstant.BuildAndPriceUS);
		Assert.assertTrue(getTitle().contains(PageTitleConstant.BuildAndPriceUS), 
				"Title notfound in " + title);
		return PageFactory.initElements(driver, BuildAndPricePage.class);
	}

	public BuildAndPricePage gotoBuildAndPriceMobilePage() {
		waitForElement(hamburgerMobile);
		javaScriptClick(hamburgerMobile);
		waitForElement(shoppingToolMenuMobile);
		javaScriptClick(shoppingToolMenuMobile);
		waitForElement(buildAndPriceMobile);
		javaScriptClick(buildAndPriceMobile);
		waitForTitleContains(PageTitleConstant.BuildAndPriceUS);
		Assert.assertTrue(getTitle().contains(PageTitleConstant.BuildAndPriceUS), 
				"Title notfound in " + title);
		return PageFactory.initElements(driver, BuildAndPricePage.class);
	}

	public BuildAndPricePage gotoBuildAndPriceEspMobilePage(){
		waitForElement(hamburgerMobile);
		javaScriptClick(hamburgerMobile);
		waitForElement(shoppingToolMenuMobileEsp);
		javaScriptClick(shoppingToolMenuMobileEsp);
		waitForElement(buildAndPriceEsp);
		javaScriptClick(buildAndPriceEsp);
		waitForTitleContains(PageTitleConstant.BuildAndPriceEsp);	
		Assert.assertTrue(getTitle().contains(PageTitleConstant.BuildAndPriceEsp), 
				"Title notfound in " + title);		
		return PageFactory.initElements(driver, BuildAndPricePage.class);
	}
	
	public BuildAndPricePage gotoBuildAndPriceEspPage(){
		waitAndClick(buildAndPriceEsp);
		waitForTitleContains(PageTitleConstant.BuildAndPriceEsp);
		Assert.assertTrue(getTitle().contains(PageTitleConstant.BuildAndPriceEsp), 
				"Title notfound in " + title);		
		return PageFactory.initElements(driver, BuildAndPricePage.class);		
	}
	
	public LocalSpecialPage gotoLocalSpecial() {
		waitForElement(localSpecials);
		javaScriptClick(localSpecials);
		waitForTitleContains(PageTitleConstant.LocalSpecialUS);
		Assert.assertTrue(getTitle().contains(PageTitleConstant.LocalSpecialUS), 
				"Title notfound in " + title);
		return PageFactory.initElements(driver, LocalSpecialPage.class);
	}
	
	public LocalSpecialPage gotoLocalSpecialMobile() {
		waitForElement(hamburgerMobile);
		javaScriptClick(hamburgerMobile);
		waitForElement(shoppingToolMenuMobile);
		javaScriptClick(shoppingToolMenuMobile);
		waitForElement(localSpecials);
		javaScriptClick(localSpecials);
		waitForTitleContains(PageTitleConstant.LocalSpecialUS);
		Assert.assertTrue(getTitle().contains(PageTitleConstant.LocalSpecialUS), 
				"Title not found in " + title);
		return PageFactory.initElements(driver, LocalSpecialPage.class);
	}
	
	public VehiclePage gotoSelectVehicleMobile(String subMenu) {
		waitForElement(hamburgerMobile);
		javaScriptClick(hamburgerMobile);
		waitForElement(vehiclesMenu);
		javaScriptClick(vehiclesMenu);
		String locator = "//div[@class='menu']//*[contains(text(), '"+subMenu+"')]";
		waitForElement(locator);
		javaScriptClick(driver.findElement(By.xpath(locator)));		
		return PageFactory.initElements(driver, VehiclePage.class);
	}

	
	public LocalSpecialPage gotoLocalSpecialEspMobile() {
		waitForElement(hamburgerMobile);
		javaScriptClick(hamburgerMobile);
		waitForElement(shoppingToolMenuMobileEsp);
		javaScriptClick(shoppingToolMenuMobileEsp);
		waitForElement(localSpecialsEspMobile);
		javaScriptClick(localSpecialsEspMobile);
		waitForTitleContains(PageTitleConstant.LocalSpecialESP);
		Assert.assertTrue(getTitle().contains(PageTitleConstant.LocalSpecialESP),
				"Title not matched");
		return PageFactory.initElements(driver, LocalSpecialPage.class);
	}
	
	
	public LocalSpecialPage gotoLocalSpecialEsp() {
		javaScriptClick(localSpecialsEsp);
		_waitForJStoLoad();		
		//Assert.assertTrue(getTitle().contains(PageTitleConstant.LocalSpecialESP),
				//"Title not matched");
		return PageFactory.initElements(driver, LocalSpecialPage.class);
	}
	
	@FindBy(css = "[id='set-your-location']")
	private WebElement setLocationText;

	@FindBy(css = "[class='subheader']")
	private WebElement subHeaderZipPopUpText;
	
	@FindBy(css= "[class='error show']")
	private WebElement zipCodeError;
	
	@FindBy(name = "zipcode")
	private WebElement zipInputBox;

	@FindBy(css= "[class='zip']")
	private WebElement zipCodeSpan;
	
	@FindBy(css = "[class='zipcode-form-wrapper'] [name='button']")
	private WebElement zipSubmitButton;
	
	@FindBy(css = "[class='zipcode has-icon-pencil zipcode-cta']")
	private WebElement topZipcodePencilIcon;
	
	@FindBy(css = "[class='zipcode-btn']")
	private WebElement topZipcodeIconMobile;
	
	@FindBy(xpath = "//*[@class='modal-title']")
	private WebElement modelPopUp;

	@FindBy(xpath = "//*[@class='dealers']/li[1]//*[@class='tcom-btn tcom-btn-one']")
	private WebElement firstDealerSelectButton;

	@FindBy(xpath = "//*[@class='dealers']/li[1]//p[@class='address']")
	private WebElement firstDealerAddress;

	@FindBy(xpath = "//*[@class='dealers']/li[1]/div[@class='name-wrapper']/p")
	private WebElement firstDealerName;

		
	public void waitZipUpdatedMobile(String zipCode) {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.textToBePresentInElementLocated(
				By.cssSelector("[class='zipcode zipcode-icon'] span"), zipCode));
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
	 * Get geolocation cod from top your location field
	 * @return
	 */
	public String getZipCode() {		
		String zipCode = getText(zipCodeSpan);
		return zipCode;
	}
	
	public void clickPencilZipIcon() {
		waitAndClick(topZipcodePencilIcon);
	}
	
	public String getZipCodeFromPopUp() {
		return getAttribute(zipInputBox, "value");
	}
	
	public void verifyZipCodePopUpOptions() {
		String text = getText(setLocationText);
		Assert.assertEquals(text.trim(), "SET YOUR LOCATION");
		Assert.assertTrue(isElementDisplay(zipInputBox), "Verify zipcode input box");
		Assert.assertTrue(isElementDisplay(zipSubmitButton), "Verify zipcode submit button");
		Assert.assertEquals(getText(subHeaderZipPopUpText).trim(), "Enter your Zip Code to find dealers, inventory, and special offers near you.");
	}
	
	public void verifyZipSubmitButtonDisabled() {
		String attribute = getAttribute(zipSubmitButton, "disabled");		
		Assert.assertEquals("true", attribute);
	}

	public void clickOnSubmitButton() {
		clickOn(zipSubmitButton);
	}

	public String getZipErrorMessage() {
		return getText(zipCodeError);
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

	public void selectDealerByNameFromModelPopup(String dealerName) {
		String locator = "//li[div[p[text()=\""+dealerName+"\"]]]/div[@class='select-wrapper']/a";		
		try {
			clickOn(locator);
		}
		catch(StaleElementReferenceException ex ) {
			clickOn(locator);
		}
	}
	
	public void changeZipCodeFromPopUp(String zipCode) {
		clickOn(zipInputBox);
		inputTextWitClear(zipInputBox, zipCode);
	}
	
	public String getModelTitleText() {
		try {
		waitForElement(modelPopUp);
		}
		catch(StaleElementReferenceException ex) {
			waitForElement(modelPopUp);
		}
		return getText(modelPopUp);
	}

	public boolean isModelPopUp() {
		return isElementDisplay(modelPopUp);
	}

	public void selectDealerFromModelPopUp() {
		clickOn(firstDealerSelectButton);		
	}

	public String getDealerZipCode() {
		String zipCode = getText(firstDealerAddress);
		zipCode = zipCode.substring(zipCode.length() - 5);	
		return zipCode;
	}

	public String getDealerName() {
		return getText(firstDealerName);	
	}

	/**
	 * Submit enter zipcode
	 * 
	 * @param model
	 */
	public void submitZipCodeMobile(RequestQuoteModel model) throws InterruptedException {
		submitZipCodeMobile(model.getZipCode());
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

	@FindBy(css="[class='app-container'] button[aria-label='show next slide']")
	private WebElement showNextSlideButton;
	
	@FindBy(css="[class='mlp-infoarea is-localspecials-on'] [class*='mlp-local-specials'] [class='title']")
	private WebElement mlpOfferHeader;
	
	@FindBy(css="[class='mlp-infoarea is-localspecials-on'] [class=\"details-cta\"] > a")
	private WebElement viewOfferButton;
	
	@FindBy(css="[aria-label='close overlay']")
	private WebElement closeButton;
	
	@FindBy(xpath="//*[text()='View Offer']")
	private WebElement viewOfferButtonAtHome;
	
	@FindBy(css="[class='offer-ribbon']")
	private WebElement offerRibbonText;
	
	public void verifyOfferHomePage(String offer) throws Exception{
		boolean status = false;
		for(int i=0; i<=7; i++) {
			sleepInSecond(2);
			if(isElementDisplay(viewOfferButtonAtHome)){
				javaScriptClick(viewOfferButtonAtHome);
				status = true;
				break;
			}
			else{
				clickOn(showNextSlideButton);
			}
		}
		if(offer.equalsIgnoreCase("none"))
			Assert.assertFalse(status, "Offer found for "+ offer);
		else {
			Assert.assertTrue(status, "Offer not found for "+ offer);
			String mlpText = getText(offerRibbonText);
			waitAndClick(closeButton);			
			switch(offer) {
			case "Lease":
				Assert.assertEquals(mlpText, "LEASE");
				break;
			case "APR":
				Assert.assertEquals(mlpText, "FINANCE");
				break;
			case "Cash":
				Assert.assertEquals(mlpText, "CASH BACK");
				break;
			case "none":			
				break;
			default :
				throw new Exception("Please provide correct mlp type");		
			}
		}		
	}
	
	public void verifyOfferType(String offer) throws Exception{
		String mlpText = null;
		if(offer.equalsIgnoreCase("none")) {
			Assert.assertFalse(isElementDisplay(mlpOfferHeader), "Offer type present on page");
		}
		else{
			mlpText = getText(mlpOfferHeader);
			waitAndClick(viewOfferButton);
			waitAndClick(closeButton);			
		}
		switch(offer) {
		case "Lease":
			Assert.assertEquals(mlpText, "LEASE");
			break;
		case "APR":
			Assert.assertEquals(mlpText, "FINANCE");
			break;
		case "Cash":
			Assert.assertEquals(mlpText, "CUSTOMER CASH");
			break;
		case "none":			
			break;
		default :
			throw new Exception("Please provide correct mlp type");		
		}
	}
	
	public void verifyTitle(String title) {
		waitForTitleContains(title);
		Assert.assertTrue(getTitle().contains(title));	
	}
	
	@FindBy(css="[id='dg-component-nav-menu-desktop'] > div > img")
	private WebElement profileIconImage;

	@FindBy(css="[class=\"dg-component-menu-text\"]")
	private WebElement profileSavesObj;

	
	//	@FindBy(css = "[class='shopping-tools'] [href^='/configurator']")
	
	//@FindBy(css = "[alt='Smartpath-Logo']")
	//@FindBy(css="[id=\"dg-mstc-component-modal-close\"]")
	
	/*@FindBys({@FindBy(id="dg-mstc-component-modal-close"),
        @FindBy(id="dg-component-modal-close")})
	private WebElement smartPathPopUp;
	
	//input[@name="username"] | //input[@id="wm_login-username"]
	 * 
	*/
	//@FindBy(xpath="//*[@alt='Smartpath-Logo']")
	@FindBy(css = "[class='dg-close-x')]")
	private WebElement smartPathPopUp;

			
	@FindBy(xpath="//div[contains(@class, 'dg-active')]//*[@id='dg-mstc-component-modal-close' or @id='dg-component-modal-close']")
	private WebElement smartPathPopUpCloseButton;

	@FindBy(css = "[class='dg-alert-header']")
	private WebElement upldateLocationAlertLabel;

	@FindBy(css = "[class='dg-alert-button-red']")
	private WebElement updateLocationButton;
	
	public void verifyAndCloseSmartPopUp() {
		waitForElement(smartPathPopUp);
		Assert.assertTrue(isElementDisplay(smartPathPopUp), "Smart Path popup is not opened");
		clickOn(smartPathPopUpCloseButton);
	}

	public boolean isSmartPath() {
		sleepInSecond(3);
		return isElementDisplay(smartPathPopUp);
	}

	public String getProfileImage() {
		sleepInSecond(4);
		waitForElement(profileIconImage);
		return getAttribute(profileIconImage, "src");
	}

	public boolean isProfileImage() {		
		setImplicitWait(10);
		boolean status = isElementDisplay(profileIconImage);	
		resetImplicitWait();
		return status;
	}
	
	public void clickOnProfileIcon() {
		clickOn(profileIconImage);
	}

	public int getSavesCount() {
		String saveText = getText(profileSavesObj);
		return Utilities.getNumberFromString(saveText);
	}

	public SavesPage openSaveList() {
		clickOn(profileSavesObj);
		return PageFactory.initElements(driver, SavesPage.class);
	}
	
	public void verifyChangeLocationPopup() {
		waitForElement(upldateLocationAlertLabel);
		Assert.assertTrue(isElementDisplay(upldateLocationAlertLabel), "Smart Path popup is not opened");
		String text = getText(upldateLocationAlertLabel);
		Assert.assertEquals(text.trim(), "You are updating your location.");

	}

	public void clickOnUpdateLocation() {
		clickOn(updateLocationButton);
	}
	
	public void clickOnFooterLink(String link) {
		String locator = "//*[@class=\"tcom-footer-links-list\"]/li/a[contains(text(), '"+link+"')]";
		waitForElement(locator);
		WebElement element = driver.findElement(By.xpath(locator));
		executeJsScroolToElement(element);
		clickOn(element);
	}

	public void waitAndVerifyTitle(String title) {
		waitForTitleContains(title);
		String actualTitle = getTitle();
		Assert.assertTrue(actualTitle.contains(title), "Expected Title "+title + " actual title "+actualTitle);
	}
	
	@FindBy(css = "[class='in-page-nav'] [href='/configurator']")
	private WebElement buildAndPriceInPageButton;
	
	@FindBy(css = "[class='in-page-nav'] [href='/search-inventory']")
	private WebElement searchInventoryInPageButton;
	
	@FindBy(css = "[class='in-page-nav'] [href='/dealers']")
	private WebElement findADealerInPageButton;
	
	public void clickBuildAndPriceInPageButton() {
		waitAndClick(buildAndPriceInPageButton);
	}
	
	public void clickSearchInventoryInPageButton() {
		waitAndClick(searchInventoryInPageButton);
	}
	
	public void clickFindADealerInPageButton() {
		waitAndClick(findADealerInPageButton);
	}
} 
