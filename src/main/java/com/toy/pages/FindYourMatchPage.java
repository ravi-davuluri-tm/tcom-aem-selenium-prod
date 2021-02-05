package com.toy.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.toy.selenium.core.BasePage;

public class FindYourMatchPage extends BasePage {

	public FindYourMatchPage(WebDriver driver) {
		super(driver);		
	}
	
	@FindBy(xpath = "(//*[@data-flyout-form='REQUEST_QUOTE'])[1]")
	private WebElement requestAQuoteButton;
	
	@FindBy(css = "[class='grade'] a")
	private WebElement seriesName;
	
	public void clickOnRAQbutton(){
		clickOn(requestAQuoteButton);
	}
	
	/**
	 * Get series name
	 * @return
	 */
	public String getSeriesName(){
		return getText(seriesName);
	}
	
	
	public void gotFindAMatchs(String options){
		String[]  str = options.split(",");
		for(int i=0; i<str.length; i++){
			String locator = "//*[@class='vertical-content'][text()='"+str[i].trim() + "']";
			waitForElement(locator);
			waitAndClick(driver.findElement(By.xpath(locator)));
		}
		
	}

}
