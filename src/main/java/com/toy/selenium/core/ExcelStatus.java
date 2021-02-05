package com.toy.selenium.core;

public class ExcelStatus {

	private static ExcelStatus reportName = null;
	private static boolean status = false;

	private ExcelStatus() {
	}

	public static synchronized ExcelStatus getInstance() {
		if (reportName == null) {
			reportName = new ExcelStatus();			
		}
		return reportName;
	}

	public synchronized boolean getStatus() {
		return status;
	}

	public synchronized void setStatus(boolean status) {
		ExcelStatus.status = status;
	}

} 