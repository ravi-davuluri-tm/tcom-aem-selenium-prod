package com.toy.report;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.configuration.Config;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.toy.selenium.core.Configuration;

public class ReportManager {
	private static String path;
	private static ExtentReports extent;
	private static String url;

	public static ExtentReports getInstance() {
    	if (extent == null)
    		createInstance();
    	
        return extent;
    }
    
	/*public synchronized static ExtentReports getReporter(){
	      
	        return extent;
	    }*/
	
	public static ExtentReports createInstance() {
		try {
			url = Configuration.readApplicationFile("URL");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  if(extent == null){
	        	String workingDir = System.getProperty("user.dir");		        	
	            //Set HTML reporting file location
	        	if( System.getProperty("os.name").toLowerCase().contains("mac") || System.getProperty("os.name").toLowerCase().contains("linux") ){
	        		path   = "//target//ToyReport1.html" ;
	        	}else{
	        		path   = "\\target\\ToyReport1.html";
	        	}	           
	           // extent = new ExtentReports(path, true);
	       
		        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(workingDir + path);
		        htmlReporter.config().setTestViewChartLocation(ChartLocation.BOTTOM);
		        htmlReporter.config().setChartVisibilityOnOpen(true);
		        htmlReporter.config().setTheme(Theme.STANDARD);
		        htmlReporter.config().setDocumentTitle("Toyota report");
		        htmlReporter.config().setEncoding("utf-8");
		        htmlReporter.config().setReportName("Toyota Automated Tests - " + url);		        
		        extent = new ExtentReports();
		        extent.attachReporter(htmlReporter);
		  }
        return extent;
    }	
	
}
