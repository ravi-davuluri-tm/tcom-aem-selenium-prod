package com.toy.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.toy.selenium.core.BasePage;

public class DisclaimerPage extends BasePage{
	
	public DisclaimerPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(css = "[class='tcom-ad-disclaimer-list-items'] li")
	private List<WebElement> disclaimerListObj;
	
	@FindBy(css = "[class='tcom-ad-disclaimer-close-button']")
	private WebElement disclaimerCloseBtn;
	
	@FindBy(xpath = "//sup[@data-disclaimer]")
	private List<WebElement> disclaimerElements;
	
	@FindBy(xpath = "//*[@class='tcom-disclaimer-item is-active'] | //*[@class='tcom-disclaimers-item is-active']")
	private WebElement selectedDisclaimer;
	
	
	public String selectEnableDisclaimer() {
		String text = "Not found";
		for(WebElement element : disclaimerElements) {
			try {
				if(element.isDisplayed()) {
					text = getText(element);
					clickOn(element);
					break;
				}
			}
			catch(Exception ex) {
				continue;
			}
		}
		return text;
	}
	
	public String getSelectedDisclaimer() {
		return getText(selectedDisclaimer);
	}
	
	public void closeAdDisclaimerDialog() {
		disclaimerCloseBtn.click();
	}
	
	
	public List<String> getDisclaimerList() throws Exception{
		List<String> disclaimerData = new ArrayList<String>();
		if(disclaimerListObj.size() == 0)
			throw new Exception("No element found");
		for(WebElement element:disclaimerListObj ) {
			String str = element.getText().replaceAll("[^a-zA-Z0-9]","");
			disclaimerData.add(str);
		}
		return disclaimerData;
	}
	
}