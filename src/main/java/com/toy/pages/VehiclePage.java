package com.toy.pages;

import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.toy.datamodel.RequestQuoteModel;
import com.toy.selenium.core.BasePage;

public class VehiclePage extends BasePage {

	public VehiclePage(WebDriver driver) {
		super(driver);	
	}
	
	@FindBy(css="[class='vehicle-image-wrapper']")
	private WebElement firstVehicleImage;
	
	@FindBy(css="[data-role='expand'] div")
	private WebElement expandButton;
	
	//@FindBy(css="[data-di-id='#/highlander/photo-gallery'] span")
	@FindBy(xpath="//*[@data-type='gallery']/a[1]/span")
	private WebElement photoGalaryObj;
	
	@FindBy(css="[href*='/photo-gallery/interior'] span")
	private WebElement viewsObj;
			
	@FindBy(css="[id='footer-raq-cta']")
	private WebElement rqaButton;
	
	@FindBy(xpath="//*[@data-type='gallery']/a")
	private WebElement galleryTab;	

	@FindBy(xpath="//*[@data-type='features']/a/span[text()='Features']")
	private WebElement featuresTab;	

	@FindBy(xpath="//*[@data-type='features']/a/span[text()='Specs']")
	private WebElement specsTab;

	@FindBy(css="li[data-type='build']")
	private WebElement buildButton;

	public void clickOnGalaryTab() {
		waitForElement(galleryTab);
		javaScriptClick(galleryTab);
	}

	public void clickOnFeatureTabs() {
		javaScriptClick(featuresTab);
	}

	public void clickOnSpecsTab() {
		javaScriptClick(specsTab);
	}

	public BuildAndPricePage clickOnBuildButton() {
		try {
			javaScriptClick(buildButton);
			javaScriptClick(buildButton);
		}catch(StaleElementReferenceException ex) {
			javaScriptClick(buildButton);
		}
		return PageFactory.initElements(driver, BuildAndPricePage.class);
	}

	public void selectFirstVehicle() {
		javaScriptClick(firstVehicleImage);
	}
	
	public void selectModelVehicle(RequestQuoteModel raqModel){
		String locator = "//*[@class='model' and contains(text(), '"+raqModel.getSeriesName()+"')]";
		waitForElement(locator);
		clickOn(locator);
	}

	public void selectGalaryInteriorView(){
		sleepInSecond(5);
		waitForElement(expandButton);
		moveAndClickOnElement(expandButton);
		waitForElement(photoGalaryObj);
		javaScriptClick(photoGalaryObj);
		waitForElement(viewsObj);
		javaScriptClick(viewsObj);
	}
	
	public RequestAQuotePage clickRQAButton(){
		waitForElement(rqaButton);
		javaScriptClick(rqaButton);
		return PageFactory.initElements(driver, RequestAQuotePage.class);
	}
}