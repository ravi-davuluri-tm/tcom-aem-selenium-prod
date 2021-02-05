
package com.toy.pages;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.toy.selenium.core.BasePage;

public class FindDealersPage extends BasePage {

	public FindDealersPage(WebDriver driver) {
		super(driver);
		
	}
	
	@FindBy(css = "[data-di-id='#tfresh-btn-tfresh-btn-one']")
	private WebElement RAQDealers;
	
	
}
