package com.toy.pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import com.toy.selenium.core.BasePage;

public class SavesPage  extends BasePage {

	public SavesPage(WebDriver driver) {
		super(driver);	
	}
	
	@FindBy(css="[class='dg-items-section-header'] span")
	private List<WebElement> headersTextObj;
	
	@FindBy(css="[class='dg-save-heart']")
	private WebElement saveHeartButton;
	
	@FindBy(css="[class='dg-vehicle']")
	private List<WebElement> savedVehicle;
		
	@FindBy(css="[class='dg-cta confirm-removal-button']")
	private WebElement confirmRemovalButton;
	
	
	public void verifyHeader(String headerText) {
		waitForElement(headersTextObj.get(0));
		boolean status =false;
		for(WebElement header : headersTextObj)
		{
			String text = getText(header);
			if(text.toString().equals(headerText)) {
				status = true;
			}
		}
		Assert.assertTrue(status, "Header " +headerText+ " text not found at Save page");
	}
	
	public void verifySavedVehicle(String seriesName) {
		waitForElement(headersTextObj.get(0));
		boolean status =false;
		for(WebElement vehicleObj : savedVehicle)
		{
			String text = getText(vehicleObj);
			if(text.toString().equals(seriesName)) {
				status = true;
			}
		}
		Assert.assertTrue(status, "Header " +seriesName+ " text not found at Save page");
	}
	
	public void removeSaveBuild(){
		clickOn(saveHeartButton);
		sleepInSecond(1);
		clickOn(confirmRemovalButton);
		sleepInSecond(3);
	}
}