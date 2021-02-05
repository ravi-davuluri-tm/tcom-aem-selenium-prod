package com.toy.selenium.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Reporter;
import org.testng.annotations.BeforeClass;
import com.aventstack.extentreports.Status;
import com.toy.report.ReportTestManager;

import io.restassured.RestAssured;

public class BaseAPI {
	
	private static final Logger logger = LoggerFactory.getLogger(BaseAPI.class);
	private static final String BREAK_LINE = "\n";// "</br>";
	
	@BeforeClass
	public void setup() throws Exception {
		RestAssured.baseURI = Configuration.readApplicationFile("URL");
		System.out.println(Configuration.readApplicationFile("URL"));
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

}