/*
  Class to initialize all application page objects and manage WebDriver browser
  object. Each and every test script class must extend this. This class does
  not use any of the Selenium APIs directly, and adds support to make this
  framework tool independent.

 */
package com.toy.selenium.core;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Base64;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.safari.SafariDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.Status;
//import com.relevantcodes.extentreports.ExtentReports;
//import com.relevantcodes.extentreports.ExtentTest;
//import com.relevantcodes.extentreports.LogStatus;
import com.toy.enums.DriverType;
import com.toy.pages.HomePage;
import com.toy.report.ReportTestManager;
import com.toy.utilities.Read_XLS;
import com.toy.utilities.Utilities;

import io.github.bonigarcia.wdm.WebDriverManager;

public abstract class BaseTest {

	private static final Logger logger = LoggerFactory.getLogger(BaseTest.class);
	private static final String BREAK_LINE = "\n";// "</br>";
	// public static ExtentTest test;
	// public static ExtentReports extent;

	protected HomePage homePage;

	private String browserType;
	private WebDriver driver;
	protected String applicationUrl;
	protected boolean mobileTest = false;
	protected static String gmailPass;
	protected static String raqExperience;
	private static String osName;
	static String resultPath = "screenshots";

	@BeforeSuite
	public void before() throws Exception {		
		gmailPass = Configuration.readApplicationFile("GmailPass");
		osName = System.getProperty("os.name").toLowerCase();
		raqExperience = Configuration.readApplicationFile("raqExperienceValue");

		// Create Excel template for RAQ
		if(!ExcelStatus.getInstance().getStatus()) {
			Read_XLS.createOutputExcelTemplate();
			Read_XLS.createExcelContentSheet(Configuration.readApplicationFile("URL"),
					Configuration.readApplicationFile("TestUrl"));
			ExcelStatus.getInstance().setStatus( true);
		}

	}
	
	@BeforeClass
	public void setUp() throws Exception {

		browserType = Configuration.readApplicationFile("Browser");
		String chromeProfile = Configuration.readApplicationFile("chromeProfile");
		System.out.println(browserType);

		this.applicationUrl = Configuration.readApplicationFile("URL");

		System.out.println(applicationUrl);
		String systemUser = System.getProperty("user.name");

		if (DriverType.Safari.toString().toLowerCase().equals(browserType.toLowerCase())) {
			
			driver = new SafariDriver();
		} 
		else if (DriverType.Firefox.toString().toLowerCase().equals(browserType.toLowerCase())) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		} else if (DriverType.IE.toString().toLowerCase().equals(browserType.toLowerCase())) {
			WebDriverManager.iedriver().setup();
			DesiredCapabilities capabilities = new DesiredCapabilities();
			capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
			driver = new InternetExplorerDriver();
			
		} else if (DriverType.Chrome.toString().toLowerCase().equals(browserType.toLowerCase())) {
			System.setProperty("webdriver.ie.driver",
					Utilities.getPath() + "//src//test//resources//driver/IEDriverServer.exe");
			WebDriverManager.chromedriver().setup();
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--enable-web-app-frame");
			if (mobileTest) {
				Map<String, String> mobileEmulation = new HashMap<>();
				mobileEmulation.put("deviceName", "iPhone X");
				options.setExperimentalOption("mobileEmulation", mobileEmulation);

			}
			System.out.print(osName);
			System.out.println(systemUser);

			if (chromeProfile.equalsIgnoreCase("On")) {

				this.closeChromeBrowse();
				if (osName.contains("mac"))
					options.addArguments("user-data-dir=//Users//" + systemUser
							+ "//Library//Application Support//Google//Chrome//Default");

				if (osName.contains("win"))
					options.addArguments("user-data-dir=C:\\Users\\" + systemUser
							+ "\\AppData\\Local\\Google\\Chrome\\User Data\\Default");
			}

			if (Configuration.readApplicationFile("ChromeHeadLess").equalsIgnoreCase("true")) {
				options.addArguments("--headless", "--disable-gpu", "--window-size=1920,1200",
						"--ignore-certificate-errors");
			}

			driver = new ChromeDriver(options);
		} else {
			throw new Exception("Please pass a valid browser type value");
		}

		// Maximize window
		driver.manage().window().maximize();

		// Delete cookies
		driver.manage().deleteAllCookies();

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

	}

	@BeforeMethod
	public void setTest(Method method) {
		// Open application URL
		getWebDriver().get(applicationUrl);
	}

	@AfterMethod
	public void afterMainMethod(ITestResult result) throws IOException, InterruptedException {

		if (result.getStatus() == ITestResult.FAILURE) {
			// test.log(LogStatus.FAIL, result.getThrowable());
			captureScreenshot();
		}
		// extent.endTest(test);
		// driver.quit();
	}

	@AfterClass
	public void afterClass() throws IOException, InterruptedException {
		driver.quit();
	}

	@AfterSuite
	public void tearDownSuite() throws IOException {
		// extent.flush();
		// extent.close();
	}

	public WebDriver getWebDriver() {
		return driver;
	}

	public void closeChromeBrowse() throws IOException {
		Runtime runtime = Runtime.getRuntime();
		if (osName.contains("win")) {
			Runtime.getRuntime().exec("taskkill /F /IM chromedriver.exe");
			runtime.exec("taskkill /F /IM chrome.exe");
		}

		if (osName.contains("mac"))
			runtime.exec("pkill -a -i \"Google Chrome\"");

	}

	/** Capturing screenshot once script is failed */
	public void captureScreenshot() {
		String fileName = System.getProperty("className");
		try {
			String screenshotName = Utilities.getFileName(fileName);
			File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			String screen = null;
			String path = resultPath + "/" + fileName;
			new File(path).mkdirs();
			screen = path + "/" + "Failed_" + screenshotName + ".png";
			File screenshotLocation = new File(screen);
			FileUtils.copyFile(screenshot, screenshotLocation);
			Thread.sleep(2000);
			InputStream is = new FileInputStream(screenshotLocation);
			byte[] imageBytes = IOUtils.toByteArray(is);
			Thread.sleep(2000);
			String base64 = Base64.getEncoder().encodeToString(imageBytes);
			Reporter.log(
					"<a href= '" + screen + "'target='_blank' ><img src='" + screen + "'>" + screenshotName + "</a>");
		} catch (Exception e) {
			System.out.println(e.getStackTrace());
		}
	}

	/**
	 * Report logs
	 *
	 * @param : message
	 */
	public void reportLog(String message) {
		if (ReportTestManager.getTest() != null)
			ReportTestManager.getTest().log(Status.PASS, message);
		logger.info("Message: " + message);
		message = BREAK_LINE + message;
		Reporter.log(message);
	}

	public void reportFailLog(String message) {
		if (ReportTestManager.getTest() != null)
			ReportTestManager.getTest().log(Status.FAIL, message);
		logger.info("Message: " + message);
		message = BREAK_LINE + message;
		Reporter.log(message);
	}
	
	public void main(String[] args) {
		System.out.println(System.getProperty("user.name"));
	}

	// if off then timestamp is added
	public String getComment(String comment) throws Exception {
		String status = Configuration.readApplicationFile("AllowExistingMail");
		if (status.equalsIgnoreCase("on"))
			return comment;
		else
			return comment + " " + Utilities.getTimeStamp();
	}

	public void verifyJSFile(String fileName) {
		List<WebElement> listSrc = getWebDriver().findElements(By.tagName("script"));
		boolean status = false;
		for(WebElement element :listSrc ) {
			String jsSrc = element.getAttribute("src");
			System.out.println(jsSrc);
			if(jsSrc.contains(fileName)) {
				status = true;
			}
		}
		Assert.assertTrue(status, "JS file not loaded " + fileName);
	}

	public String switchWinow() {		
		String parentWinId = driver.getWindowHandle();
		// Switch to new window opened
		for(String winHandle : driver.getWindowHandles()){
		    driver.switchTo().window(winHandle);
		}
		return parentWinId;
	}
	
public void DGEnvironmentValidate(String newUrl) {
		
		Object DGhubDeployEnvdata = ((JavascriptExecutor) getWebDriver())
				.executeScript("return window.DGDataHub.DEPLOY_ENV");
		System.out.println("DGhubDeployEnvdata: " + DGhubDeployEnvdata.toString());

		if (newUrl.contains("test6")) {
			Assert.assertEquals(DGhubDeployEnvdata.toString(), "test");
			this.verifyJSFile("cdn.dgtest.deops.toyota.com/deploy/dg-loader/latest/dg-loader-app.min.js");
			this.reportLog("Verify .dgtest java script file with complete path");
		} else if (newUrl.contains("test4")) {
			Assert.assertEquals(DGhubDeployEnvdata.toString(), "int");
			this.verifyJSFile("cdn.dgint.deops.toyota.com/deploy/dg-loader/latest/dg-loader-app.min.js");
			this.reportLog("Verify .dgint java script file with complete path");
		} else if (newUrl.contains("staging")) {
			Assert.assertEquals(DGhubDeployEnvdata.toString(), "stage");
			this.verifyJSFile("cdn.dgstage.deops.toyota.com/deploy/dg-loader/latest/dg-loader-app.min.js");
			this.reportLog("Verify .dgstage java script file with complete path");
		} else {
			Assert.assertEquals(DGhubDeployEnvdata.toString(), "prod");
			this.verifyJSFile("cdn.dg.toyota.com/deploy/dg-loader/latest/dg-loader-app.min.js");
			this.reportLog("Verify dg-loader-app.min.js java script file with complete path");

		}
		
	}

public Set<String> getLinkList(List<WebElement> links) {
	Set<String> urlList = new HashSet<String>();

	Iterator<WebElement> link = links.iterator();
	while (link.hasNext()) {
		urlList.add(link.next().getAttribute("href"));
	}
	return urlList;
}

public int validateLinks(Set<String> links) {
	String url = "";
	HttpURLConnection urlconnection = null;
	int responseCode = 200;

	/* For skipping email address */
	String mail_to = "mailto";
	String tel = "tel";
	int valid_links = 0;
	int broken_links = 0;
	System.out.println("Total Size :" + links.size());

	Iterator<String> link = links.iterator();
	while (link.hasNext()) {
		url = link.next();
		System.out.println(url);

		if ((url == null) || (url.isEmpty())) {
			this.reportLog(url + "	URL is either not configured for anchor tag or it is empty");
			continue;
		}

		if ((url.startsWith(mail_to)) || (url.startsWith(tel))) {
			this.reportLog(url + " Email address or Telephone detected");
			continue;
		}

		try {
			urlconnection = (HttpURLConnection) (new URL(url).openConnection());				
			urlconnection.connect();
			System.out.println(urlconnection.getResponseMessage());
			System.out.println(urlconnection.getResponseCode());
			responseCode = urlconnection.getResponseCode();
			if (responseCode >= 400 && responseCode != 403 ) {
				String linkUrl = "<a href=\""+url+"\">" + url + "</a>" ;
				this.reportFailLog(linkUrl + " is a broken link");
				broken_links++;

			} else if(responseCode == 403) { 
				urlconnection = (HttpURLConnection) (new URL(url).openConnection());
				urlconnection.setRequestMethod("HEAD");
				urlconnection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");
				responseCode = urlconnection.getResponseCode();
				if (responseCode >= 400) {
					String linkUrl = "<a href=\""+url+"\">" + url + "</a>" ;
					this.reportFailLog(linkUrl + " is a broken link");
					broken_links++;
				} else {
					this.reportLog(url + " is a valid link");
					valid_links++;
				}
				
			}else {
				this.reportLog(url + " is a valid link");
				valid_links++;
			}
		} catch (MalformedURLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
	return broken_links;		
}
}