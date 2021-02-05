/*
  Class to initialize all page methods for the actions available
  under that page. All scripts must call the respective methods from the respective
  pages to achieve any action.


 */
package com.toy.selenium.core;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

public abstract class BasePage {

	protected static final int DEFAULT_WAIT_4_ELEMENT = 30;
	protected static final int DEFAULT_WAIT_4_PAGE = 30;
	private static final Logger logger = LoggerFactory.getLogger(BasePage.class);
	protected static WebDriverWait ajaxWait;
	protected WebDriver driver;
	protected String title;
	protected long timeout = 60;

	/** @Inject @Named("framework.implicitTimeout") protected long timeout; */

	public BasePage(WebDriver driver) {
		this.driver = driver;
	}

	/** Click action performed and then wait */
	public void waitAndClick(WebElement element) {
		logger.info("Waiting element "+ element.toString());	
		waitForElementVisible(element);
		clickOn(element);
	}

	public void clickOn(WebElement element) {
		logger.info("click on element " + element.toString());
		for(int i=0; i<3; i++) {
			try {
				element.click();
				break;
			}
			catch(StaleElementReferenceException | ElementNotVisibleException | ElementClickInterceptedException ex) {
				continue;
			}			
		}	
	}

	/** Click on WebElement by using java script */
	public void javaScriptClick(WebElement webElement) {
		logger.info("click on element " + webElement.toString());
		JavascriptExecutor js = (JavascriptExecutor) driver;
		for(int i=0; i<3; i++) {
			try {
				js.executeScript("arguments[0].click();", webElement);
				break;
			}
			catch(StaleElementReferenceException | ElementNotVisibleException | ElementClickInterceptedException ex) {
				continue;
			}			
		}			
	}
	

	/** Click on element by string locator */
	public void clickOn(String locator) {
		WebElement el = getDriver().findElement(ByLocator(locator));
		clickOn(el);	
	}

	public String returnTitle() {
		return title;
	}

	/** Setting up implicite wait that will be used internally */
	public void setImplicitWait(int timeInSec) {
		logger.info("setImplicitWait, timeInSec={}", timeInSec);
		driver.manage().timeouts().implicitlyWait(timeInSec, TimeUnit.SECONDS);
	}

	/** Reset implicit wait */
	public void resetImplicitWait() {
		logger.info("resetImplicitWait");
		driver.manage().timeouts().implicitlyWait(timeout, TimeUnit.SECONDS);
	}

	/** Wait for element */
	public void waitFor(ExpectedCondition<Boolean> expectedCondition) {
		setImplicitWait(0);
		logger.info("wait for expected condition");
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		wait.until(expectedCondition);
		resetImplicitWait();
	}

	/** Input text as string */
	public void inputText(WebElement element, String text) {
		logger.info("inputText, text={}", text);		
		// element.clear();
		waitForElement(element);
		element.sendKeys(text);
	}

	/** Input text as string */
	public void inputTextWitClear(WebElement element, String text) {
		logger.info("inputText, text={}", text);	
		// element.clear();
		waitForElement(element);		
		element.clear();
		element.sendKeys(text);
	}

	/** Wait for element to be present */
	public WebElement waitForElement(WebElement element) {
		logger.info("waitForElement" + element.toString());
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		try {
			return wait.until(ExpectedConditions.elementToBeClickable(element));
		}catch(StaleElementReferenceException | ElementClickInterceptedException ex) {
			return wait.until(ExpectedConditions.visibilityOf(element));
		}
	}


	/** Wait for element to be present */
	public WebElement waitForElementVisible(WebElement element) {
		logger.info("waitForElement" + element.toString());
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		try {
			return wait.until(ExpectedConditions.visibilityOf(element));
		}
		catch(StaleElementReferenceException ex) {
			return wait.until(ExpectedConditions.visibilityOf(element));
		}
	}

	
	/** Wait for element by passing argument as string. */
	public void waitForElement(String locator) {
		logger.info("waitForElement " + locator);
		for(int i=0; i<timeout; i++) {
			try {
				driver.findElement(ByLocator(locator));			
				Thread.sleep(1000);
				break;
			} catch (Exception e) { }
		}
		//WebDriverWait wait = new WebDriverWait(driver, timeout);
		//wait.until(ExpectedConditions.visibilityOfElementLocated(ByLocator(locator)));
	}
	
	/** Wait for JSLoad to load */
	public boolean _waitForJStoLoad() {
		// wait for jQuery to load
		ExpectedCondition<Boolean> jQueryLoad = driver -> {
			try {
				return ((Long) ((JavascriptExecutor) driver).executeScript("return jQuery.active") == 0);
			} catch (Exception e) {
				return true;
			}
		};

		/** wait for JavaScript to load */
		ExpectedCondition<Boolean> jsLoad = driver -> {
			Object rsltJs = ((JavascriptExecutor) driver).executeScript("return document.readyState");
			if (rsltJs == null) {
				rsltJs = "";
			}
			return rsltJs.toString().equals("complete") || rsltJs.toString().equals("loaded");
		};

		WebDriverWait wait = new WebDriverWait(driver, 30);
		return wait.until(jQueryLoad) && wait.until(jsLoad);
	}

	/** Handle locator type */
	public By ByLocator(String locator) {
		By result;
		if (locator.startsWith("//") || locator.startsWith("(//")) {
			result = By.xpath(locator);
		}else if (locator.startsWith("css=")) {
			result = By.cssSelector(locator.replace("css=", ""));
		} else if (locator.startsWith("#")) {
			result = By.id(locator.replace("#", ""));
		} else if (locator.startsWith("name=")) {
			result = By.name(locator.replace("name=", ""));
		} else if (locator.startsWith("link=")) {
			result = By.linkText(locator.replace("link=", ""));
		} else {
			result = By.cssSelector(locator);
		}
		return result;
	}
	

	/** Return driver instance */
	public WebDriver getDriver() {
		return driver;
	}

	/** Set wait for driver */
	public void setWaitTime(WebDriver driver, int waitTime) {
		driver.manage().timeouts().implicitlyWait(waitTime, TimeUnit.SECONDS);
	}

	public void setWaitTimeToZero(WebDriver driver) {
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
	}

	/** Wait for element to be clickable */

	public WebElement waitForElementClickable(WebElement webElement) {
		WebElement element;
		try {
			// setWaitTimeToZero(driver);
			logger.info("wait for element to be clickable "+webElement.toString());
			WebDriverWait wait = new WebDriverWait(driver, 30);
			element = wait.until(ExpectedConditions.elementToBeClickable(webElement));

			// setWaitTime(driver, DEFAULT_WAIT_4_ELEMENT);
			return element;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/** Wait for element to be present */
	public WebElement waitForElementPresent(final By by, int timeOutInSeconds) {
		WebElement element;
		try {
			logger.info("wait for element to be present "+by.toString());			
			WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
			element = wait.until(ExpectedConditions.presenceOfElementLocated(by));
			// setWaitTime(driver, DEFAULT_WAIT_4_ELEMENT);
			return element;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/** Wait for element to be present by web element */
	public WebElement waitForElementPresent(WebElement webElement) {
		WebElement element;
		try {
			logger.info("wait for element to be present "+webElement.toString());		
			WebDriverWait wait = new WebDriverWait(driver, 30);
			element = wait.until(ExpectedConditions.visibilityOf(webElement));
			return element;
		} catch (Exception e) {
			//e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * @param webElement
	 * @param text
	 * @param timeOutInSeconds
	 * @return
	 */
	public boolean waitForTextPresentInElement(WebElement webElement, String text, int timeOutInSeconds) {
		boolean notVisible;
		logger.info("wait for text "+text+ " in to element "+webElement.toString());		
		WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
		notVisible = wait.until(ExpectedConditions.textToBePresentInElement(webElement, text));

		return notVisible;
	}

	public boolean waitForTextPresentInElement(By by, String text, int timeOutInSeconds) {
		boolean notVisible;
		WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
		notVisible = wait.until(ExpectedConditions.textToBePresentInElementLocated(by, text));
		return notVisible;
	}

	/** Verify that element is present */
	public Boolean isElementPresent(WebElement element) {	
		try {
			return element.isDisplayed();
		}
		catch(StaleElementReferenceException ex) {
			return element.isDisplayed();
		}
	}
	
	/** Verify that element is present */
	public Boolean isElementDisplay(WebElement element) {
		try{
		return element.isDisplayed();
		}
		catch(Exception ex ) {
			return false;
		}
		catch(Error ex) {
			return false;
		}
	}

	/**
	 * Select element by visible text
	 *
	 * @param targetValue: visible text
	 */
	public void selectDropDownByText(WebElement element, String targetValue) {
		// waitForElementPresent(element);
		try {
			logger.info("selecting dropdown value "+targetValue+" for element "+element.toString());		
			new Select(element).selectByVisibleText(targetValue);
		}
		catch(StaleElementReferenceException ex) {
			new Select(element).selectByVisibleText(targetValue);
		}
	}

	/** Select element by Index */
	public void selectDropDownByIndex(WebElement element, int index) {
		waitForElement(element);
		try {
			logger.info("selecting dropdown value at index "+index+" for element "+element.toString());	
			new Select(element).selectByIndex(index);
		}catch(StaleElementReferenceException ex) {
			new Select(element).selectByIndex(index);
		}
	}

	/**
	 * Select element by value
	 *
	 * @param targetValue: value
	 */
	public void selectDropDownByValue(WebElement element, String targetValue) {
		waitForElement(element);
		try {
			logger.info("selecting dropdown value "+targetValue+" for element "+element.toString());	
			new Select(element).selectByValue(targetValue);
		}catch(StaleElementReferenceException ex) {
			new Select(element).selectByValue(targetValue);
		}
	}

	/**
	 * @param by
	 * @param driver
	 */
	public void waitForElementToBecomeVisible(By by, WebDriver driver) {
		WebDriverWait wait = new WebDriverWait(driver, DEFAULT_WAIT_4_PAGE);
		wait.until(ExpectedConditions.visibilityOfElementLocated(by));
	}

	/**
	 * @param by
	 */
	public void waitForElementToBecomeInvisible(By by) {
		WebDriverWait wait = new WebDriverWait(driver, DEFAULT_WAIT_4_PAGE);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
	}

	/**
	 * @param element
	 */
	public void waitForWebElementPresent(WebElement element) {
		WebDriverWait ajaxWait = new WebDriverWait(driver, 30);
		ajaxWait.until(ExpectedConditions.visibilityOf(element));
	}
	
	/**
	 * @param driver
	 */
	public void waitForPageLoaded() {
		ExpectedCondition<Boolean> expectation = driver -> ((JavascriptExecutor) driver)
				.executeScript("return document.readyState").equals("complete");
		Wait<WebDriver> wait = new WebDriverWait(driver, 20);
		wait.until(expectation);
	}

	/**
	 * Wait for title contains
	 * @param title
	 */
	public void waitForTitleContains(String title) {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.titleContains(title));
	}
	
	/** Store text from a locator */
	public String getText(WebElement element) {
		_waitForJStoLoad();
		logger.info("getting text for element locator "+element.toString());	
		waitForElementVisible(element);
		Assert.assertTrue(isElementPresent(element), "Element Locator :" + element + " Not found");
		try {
			return element.getText();
		}catch(StaleElementReferenceException ex) {
			return element.getText();
		}
	}

	/** Store text from a locator */
	public String getAttribute(WebElement element, String attributeName) {
		waitForWebElementPresent(element);
		logger.info("getting attribute "+attributeName+ " for element locator "+element.toString());	
		Assert.assertTrue(isElementPresent(element), "Element Locator :" + element + " Not found");
		try {
			return element.getAttribute(attributeName);
		}catch(StaleElementReferenceException ex) {
			return element.getAttribute(attributeName);
		}
	}
	
	/** Store text from a locator */
	public String getCssValue(WebElement element, String cssName) {
		waitForWebElementPresent(element);
		logger.info("getting cssName "+cssName+ " for element locator "+element.toString());
		Assert.assertTrue(isElementPresent(element), "Element Locator :" + element + " Not found");
		try {
			return element.getCssValue(cssName);
		}catch(StaleElementReferenceException ex) {
			return element.getCssValue(cssName);
		}
	}
	
	
	/**
	 * get title
	 * @return
	 */
	public String getTitle() {		
		return driver.getTitle();
	}
	
	
	/** Perform Drag and drop */
	public void dragAndDrop(WebElement drag, WebElement drop) {
		Actions builder = new Actions(driver);
		Action dragAndDrop = builder.clickAndHold(drag).moveToElement(drop).release(drop).build();
		dragAndDrop.perform();
	}
		
	public void moveToElement(WebElement element) {
		Actions builder = new Actions(driver);
		builder.moveToElement(element).build().perform();		
	}

	public void moveAndClickOnElement(WebElement element) {
		Actions builder = new Actions(driver);
		builder.moveToElement(element).click(element).build().perform();
	}
	
	public void sleepInSecond(int timeInSecond){
		int timeOut = timeInSecond*1000;
		try {
			Thread.sleep(timeOut);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void selectFrameById(String id) {
		driver.switchTo().frame(id);
	}

	public void selectFrameById(WebElement element) {
		driver.switchTo().frame(element);
	}

	public void selectFrameByIndex(int index) {
		driver.switchTo().frame(index);
	}
	
	public void executeJsScroolToElement(WebElement element) {
		waitForElementVisible(element);
		((JavascriptExecutor) driver ).executeScript("arguments[0].scrollIntoView(true);", element);
	}
	
	public boolean isCheckBoxSelected(WebElement element) {
		waitForElement(element);		
		return element.isSelected();
	}

	public String getH1Value() {
		waitForElement("//h1");
		return getText(driver.findElement(By.xpath("//h1")));
	}

	public void verifyH1ValueContainsText(String containText) {
		waitForElement("//h1[contains(text(), '"+containText+"']");
		Assert.assertTrue(getText(driver.findElement(By.xpath("//h1[contains(text(), '"+containText+"')]"))).contains(containText), 
				"Header not contains text "+containText);
	}
	
	public void verifyPContainsText(String containText) {
		waitForElement("//p[contains(text(), '"+containText+"']");
		Assert.assertTrue(getText(driver.findElement(By.xpath("//h1[contains(text(), '"+containText+"')]"))).contains(containText), 
				"Header not contains text "+containText);
	}
	
	public String getH2Value() {
		waitForElement("//h2");
		return getText(driver.findElement(By.xpath("//h2")));
	}
	
	public String getHeadingText() {
		waitForElement("//*[@class='tcom-masthead-heading']");
		return getText(driver.findElement(By.xpath("//*[@class='tcom-masthead-heading']")));
	}

	public String getModelTitle() {
		waitForElement("//*[@id='modal-title']");
		return getText(driver.findElement(By.xpath("//*[@id='modal-title']")));
	}

	public void removeDisclaimer() {		
		((JavascriptExecutor) driver ).executeScript("$('[data-disclaimer]').remove()");
	}

}