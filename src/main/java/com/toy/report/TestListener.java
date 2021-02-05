package com.toy.report;

import java.io.IOException;
import java.util.Arrays;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.toy.selenium.core.BaseTest;

public class TestListener extends BaseTest implements ITestListener{
	
	private static String getTestMethodName(ITestResult iTestResult) {
        return iTestResult.getMethod().getConstructorOrMethod().getName();
    }
    
    //Before starting all tests, below method runs.
    @Override
    public void onStart(ITestContext iTestContext) {
        System.out.println("I am in onStart method " + iTestContext.getName());
        iTestContext.setAttribute("WebDriver", this.getWebDriver());
    }
 
    //After ending all tests, below method runs.
    @Override
    public void onFinish(ITestContext iTestContext) {
        System.out.println("I am in onFinish method " + iTestContext.getName());     
        ReportTestManager.endTest();
        ReportManager.getInstance().flush();       
    }
 
    @Override
    public void onTestStart(ITestResult iTestResult) {
        System.out.println("I am in onTestStart method " +  getTestMethodName(iTestResult) + " start");
        //Start operation for extentreports.       
    }
 
    @Override
    public void onTestSuccess(ITestResult iTestResult) {
    	String description = iTestResult.getMethod().getDescription();
        System.out.println("I am in onTestSuccess method " +  getTestMethodName(iTestResult) + " succeed");
        //Extentreports log operation for passed tests.
        if(description != null )
	        ReportTestManager.startTest(iTestResult.getMethod().getMethodName() + "( " + description + ")",
	        		iTestResult.getInstance().getClass().getCanonicalName());    
        else
        	ReportTestManager.startTest(iTestResult.getMethod().getMethodName(),
	        		iTestResult.getInstance().getClass().getCanonicalName());   
        
        ReportTestManager.getTest().log(Status.PASS, "Test passed");
    }
 
    @Override
    public void onTestFailure(ITestResult iTestResult) {
        System.out.println("I am in onTestFailure method " +  getTestMethodName(iTestResult) + " failed");
        String description = iTestResult.getMethod().getDescription();
        if(description != null )
	        ReportTestManager.startTest(iTestResult.getMethod().getMethodName() + "( " + description + ")",
	        		iTestResult.getInstance().getClass().getCanonicalName());    
        else
        	ReportTestManager.startTest(iTestResult.getMethod().getMethodName(),
	        		iTestResult.getInstance().getClass().getCanonicalName());   
        
        //Get driver from BaseTest and assign to local webdriver variable.
        Object testClass = iTestResult.getInstance();
        WebDriver webDriver = ((BaseTest) testClass).getWebDriver();
 
        //Take base64Screenshot screenshot.
        String base64Screenshot = "data:image/png;base64,"+((TakesScreenshot)webDriver).
                getScreenshotAs(OutputType.BASE64);
        
        ReportTestManager.getTest().log(Status.FAIL, iTestResult.getThrowable());
        try {
			ReportTestManager.getTest().fail("details", MediaEntityBuilder.createScreenCaptureFromBase64String(base64Screenshot).build());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
 
    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        System.out.println("I am in onTestSkipped method "+  getTestMethodName(iTestResult) + " skipped");
        
        //Extentreports log operation for skipped tests.
        ReportTestManager.getTest().log(Status.SKIP, "Test Skipped");
    }
 
    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
        System.out.println("Test failed but it is in defined success ratio " + getTestMethodName(iTestResult));
    }	
}