package com.toy.constant;

public class ConstantFactory {

	private static final ConstantFactory instance = new ConstantFactory();
	String excelSheetName;

	private ConstantFactory() {
	}

	public static ConstantFactory getInstance() {
		return instance;
	}

	public void setExcelSheetName(String excelSheetName) {
		this.excelSheetName = excelSheetName;
	}

	public String getExcelSheetName() {
		return excelSheetName;
	}

}