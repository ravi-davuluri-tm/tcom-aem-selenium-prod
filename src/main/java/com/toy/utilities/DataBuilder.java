package com.toy.utilities;

import java.util.ArrayList;
import java.util.List;

import com.toy.constant.ConstantFactory;
import com.toy.datamodel.AppDataModel;
import com.toy.datamodel.RequestQuoteModel;

public class DataBuilder {

	public static String testDataSheet = "TestData.xlsx";
	public static String smokeTestDataSheet = "Smoke.xlsx";
	public static String functionalSheet = "Functional.xlsx";

	public List<RequestQuoteModel> prepareRequestQuoteData() {
		List<RequestQuoteModel> raqData = new ArrayList<RequestQuoteModel>();
		Read_XLS read = new Read_XLS(testDataSheet, "data/");
		Object[][] objs = new Object[read.retrieveNoOfRows("RAQStandaloneUs") - 1][read
				.retrieveNoOfCols("RAQStandaloneUs")];

		objs = read.retrieveTestData1("RAQStandaloneUs");
		for (Object[] obj : objs) {
			RequestQuoteModel requestQuoteData = new RequestQuoteModel();
			requestQuoteData.setSeriesName(obj[0].toString());
			requestQuoteData.setModelName(obj[1].toString());
			requestQuoteData.setFirstName(obj[2].toString());
			requestQuoteData.setLastName(obj[3].toString());
			requestQuoteData.setEmail(obj[4].toString());
			requestQuoteData.setPhone(obj[5].toString());
			requestQuoteData.setAddress(obj[6].toString());
			requestQuoteData.setCity(obj[7].toString());
			requestQuoteData.setZipCode(obj[8].toString());
			requestQuoteData.setSet(Boolean.parseBoolean(obj[9].toString()));
			raqData.add(requestQuoteData);
		}
		return raqData;
	}

	public List<RequestQuoteModel> prepareRAQZEVUSData() {
		List<RequestQuoteModel> raqData = new ArrayList<RequestQuoteModel>();
		Read_XLS read = new Read_XLS(testDataSheet, "data/");
		Object[][] objs = new Object[read.retrieveNoOfRows("RAQZEVUS") - 1][read
				.retrieveNoOfCols("RAQZEVUS")];

		objs = read.retrieveTestData1("RAQZEVUS");
		for (Object[] obj : objs) {
			RequestQuoteModel requestQuoteData = new RequestQuoteModel();
			requestQuoteData.setSeriesName(obj[0].toString());
			requestQuoteData.setModelName(obj[1].toString());
			requestQuoteData.setFirstName(obj[2].toString());
			requestQuoteData.setLastName(obj[3].toString());
			requestQuoteData.setEmail(obj[4].toString());
			requestQuoteData.setPhone(obj[5].toString());
			requestQuoteData.setAddress(obj[6].toString());
			requestQuoteData.setCity(obj[7].toString());
			requestQuoteData.setZipCode(obj[8].toString());
			requestQuoteData.setSet(Boolean.parseBoolean(obj[9].toString()));
			raqData.add(requestQuoteData);
		}
		return raqData;
	}
	
	public List<RequestQuoteModel> prepareBuildAndPriceData() {
		List<RequestQuoteModel> raqData = new ArrayList<RequestQuoteModel>();
		Read_XLS read = new Read_XLS(testDataSheet, "data/");
		Object[][] objs = new Object[read.retrieveNoOfRows("RAQBuildAndPriceUs") - 1][read
				.retrieveNoOfCols("RAQBuildAndPriceUs")];

		objs = read.retrieveTestData1("RAQBuildAndPriceUs");
		for (Object[] obj : objs) {
			RequestQuoteModel requestQuoteData = new RequestQuoteModel();
			requestQuoteData.setSeriesName(obj[0].toString());
			requestQuoteData.setFirstName(obj[1].toString());
			requestQuoteData.setLastName(obj[2].toString());
			requestQuoteData.setEmail(obj[3].toString());
			requestQuoteData.setPhone(obj[4].toString());
			requestQuoteData.setAddress(obj[5].toString());
			requestQuoteData.setCity(obj[6].toString());
			requestQuoteData.setZipCode(obj[7].toString());
			requestQuoteData.setSet(Boolean.parseBoolean(obj[8].toString()));
			raqData.add(requestQuoteData);
		}
		return raqData;
	}

	public List<RequestQuoteModel> prepareLocalSpecialData() {
		List<RequestQuoteModel> raqData = new ArrayList<RequestQuoteModel>();
		Read_XLS read = new Read_XLS(testDataSheet, "data/");
		Object[][] objs = new Object[read.retrieveNoOfRows("RAQLocalSpecialUs") - 1][read
				.retrieveNoOfCols("RAQLocalSpecialUs")];

		objs = read.retrieveTestData1("RAQLocalSpecialUs");

		for (Object[] obj : objs) {
			RequestQuoteModel requestQuoteData = new RequestQuoteModel();
			requestQuoteData.setOfferRibbon(obj[0].toString());
			requestQuoteData.setFirstName(obj[1].toString());
			requestQuoteData.setLastName(obj[2].toString());
			requestQuoteData.setEmail(obj[3].toString());
			requestQuoteData.setPhone(obj[4].toString());
			requestQuoteData.setAddress(obj[5].toString());
			requestQuoteData.setCity(obj[6].toString());
			requestQuoteData.setZipCode(obj[7].toString());
			requestQuoteData.setSet(Boolean.parseBoolean(obj[8].toString()));
			raqData.add(requestQuoteData);
		}
		return raqData;
	}

	public List<RequestQuoteModel> prepareSearchInventoryData() {
		List<RequestQuoteModel> raqData = new ArrayList<RequestQuoteModel>();
		Read_XLS read = new Read_XLS(testDataSheet, "data/");
		Object[][] objs = new Object[read.retrieveNoOfRows("RAQSearchInventoryUs") - 1][read
				.retrieveNoOfCols("RAQSearchInventoryUs")];

		objs = read.retrieveTestData1("RAQSearchInventoryUs");

		for (Object[] obj : objs) {
			RequestQuoteModel requestQuoteData = new RequestQuoteModel();
			requestQuoteData.setSeriesName(obj[0].toString());
			requestQuoteData.setFirstName(obj[1].toString());
			requestQuoteData.setLastName(obj[2].toString());
			requestQuoteData.setEmail(obj[3].toString());
			requestQuoteData.setPhone(obj[4].toString());
			requestQuoteData.setAddress(obj[5].toString());
			requestQuoteData.setCity(obj[6].toString());
			requestQuoteData.setZipCode(obj[7].toString());
			requestQuoteData.setSet(Boolean.parseBoolean(obj[8].toString()));
			raqData.add(requestQuoteData);
		}
		return raqData;
	}

	public List<RequestQuoteModel> prepareFindADealer() {
		List<RequestQuoteModel> raqData = new ArrayList<RequestQuoteModel>();
		Read_XLS read = new Read_XLS(testDataSheet, "data/");
		Object[][] objs = new Object[read.retrieveNoOfRows("RAQFindADealer") - 1][read
				.retrieveNoOfCols("RAQFindADealer")];
		objs = read.retrieveTestData1("RAQFindADealer");
		for (Object[] obj : objs) {
			RequestQuoteModel requestQuoteData = new RequestQuoteModel();
			requestQuoteData.setSeriesName(obj[0].toString());
			requestQuoteData.setModelName(obj[1].toString());
			requestQuoteData.setFirstName(obj[2].toString());
			requestQuoteData.setLastName(obj[3].toString());
			requestQuoteData.setEmail(obj[4].toString());
			requestQuoteData.setPhone(obj[5].toString());
			requestQuoteData.setAddress(obj[6].toString());
			requestQuoteData.setCity(obj[7].toString());
			requestQuoteData.setZipCode(obj[8].toString());
			requestQuoteData.setDealerCode(obj[9].toString());
			raqData.add(requestQuoteData);
		}
		return raqData;
	}

	public List<RequestQuoteModel> prepareVehicle() {
		List<RequestQuoteModel> raqData = new ArrayList<RequestQuoteModel>();
		Read_XLS read = new Read_XLS(testDataSheet, "data/");
		Object[][] objs = new Object[read.retrieveNoOfRows("Vehicle") - 1][read.retrieveNoOfCols("Vehicle")];
		objs = read.retrieveTestData1("Vehicle");
		for (Object[] obj : objs) {
			RequestQuoteModel requestQuoteData = new RequestQuoteModel();
			requestQuoteData.setSeriesName(obj[0].toString());
			
			requestQuoteData.setModelName(obj[1].toString());
			requestQuoteData.setFirstName(obj[2].toString());
			requestQuoteData.setLastName(obj[3].toString());
			requestQuoteData.setEmail(obj[4].toString());
			requestQuoteData.setPhone(obj[5].toString());
			requestQuoteData.setAddress(obj[6].toString());
			requestQuoteData.setCity(obj[7].toString());
			requestQuoteData.setZipCode(obj[8].toString());
			requestQuoteData.setSet(Boolean.parseBoolean(obj[9].toString()));
			raqData.add(requestQuoteData);
		}
		return raqData;
	}

	public static List<String[]> getBuildAndPrice() {
		Read_XLS read = new Read_XLS(functionalSheet, "data/");
		Object[][] objs = new Object[read.retrieveNoOfRows("BuildAndPrice") - 1][read
				.retrieveNoOfCols("BuildAndPrice")];

		objs = read.retrieveTestData1("BuildAndPrice");
		List<String[]> list = new ArrayList<String[]>();
		for (Object[] obj : objs) {
			String[] str = new String[3];
			str[0] = obj[0].toString();
			str[1] = obj[1].toString();
			str[2] = obj[2].toString();
			list.add(str);
		}
		return list;
	}

	public static List<String[]> getBuildAndPriceTab() {
		Read_XLS read = new Read_XLS(functionalSheet, "data/");
		Object[][] objs = new Object[read.retrieveNoOfRows("BAPTabs") - 1][read.retrieveNoOfCols("BAPTabs")];

		objs = read.retrieveTestData1("BAPTabs");
		List<String[]> list = new ArrayList<String[]>();
		for (Object[] obj : objs) {
			String[] str = new String[3];
			str[0] = obj[0].toString();
			str[1] = obj[1].toString();
			str[2] = obj[2].toString();
			list.add(str);
		}
		return list;
	}

	public static List<String[]> getBuildAndPriceEmail() {
		Read_XLS read = new Read_XLS(functionalSheet, "data/");
		Object[][] objs = new Object[read.retrieveNoOfRows("BuildAndPriceEmail") - 1][read
				.retrieveNoOfCols("BuildAndPriceEmail")];

		objs = read.retrieveTestData1("BuildAndPriceEmail");
		List<String[]> list = new ArrayList<String[]>();
		for (Object[] obj : objs) {
			String[] str = new String[3];
			str[0] = obj[0].toString();
			str[1] = obj[1].toString();
			list.add(str);
		}
		return list;
	}

	public static List<String[]> getBuildAndPriceColor() {

		Read_XLS read = new Read_XLS(functionalSheet, "data/");
		Object[][] objs = new Object[read.retrieveNoOfRows("BuildAndPriceColor") - 1][read
				.retrieveNoOfCols("BuildAndPriceColor")];

		objs = read.retrieveTestData1("BuildAndPriceColor");
		List<String[]> list = new ArrayList<String[]>();
		for (Object[] obj : objs) {
			String[] str = new String[3];
			str[0] = obj[0].toString();
			str[1] = obj[1].toString();
			list.add(str);
		}
		return list;
	}

	public static List<String[]> getBuildAndPriceTrucks() {

		Read_XLS read = new Read_XLS(functionalSheet, "data/");
		Object[][] objs = new Object[read.retrieveNoOfRows("BuildAndPriceTrucks") - 1][read
				.retrieveNoOfCols("BuildAndPriceTrucks")];

		objs = read.retrieveTestData1("BuildAndPriceTrucks");
		List<String[]> list = new ArrayList<String[]>();
		for (Object[] obj : objs) {
			String[] str = new String[2];
			str[0] = obj[0].toString();
			str[1] = obj[1].toString();
			list.add(str);
		}
		return list;
	}

	public static List<String[]> getBuildAndPriceSUV() {

		Read_XLS read = new Read_XLS(functionalSheet, "data/");
		Object[][] objs = new Object[read.retrieveNoOfRows("BuildAndPriceSUV") - 1][read
				.retrieveNoOfCols("BuildAndPriceSUV")];

		objs = read.retrieveTestData1("BuildAndPriceSUV");
		List<String[]> list = new ArrayList<String[]>();
		for (Object[] obj : objs) {
			String[] str = new String[2];
			str[0] = obj[0].toString();
			str[1] = obj[1].toString();
			list.add(str);
		}
		return list;
	}

	public static List<String[]> getOfferTypeData() {
		Read_XLS read = new Read_XLS(functionalSheet, "data/");
		Object[][] objs = new Object[read.retrieveNoOfRows("OfferType") - 1][read.retrieveNoOfCols("OfferType")];
		objs = read.retrieveTestData1("OfferType");
		List<String[]> list = new ArrayList<String[]>();
		for (Object[] obj : objs) {
			String[] str = new String[3];
			str[0] = obj[0].toString();
			str[1] = obj[1].toString();
			str[2] = obj[2].toString();
			list.add(str);
		}
		return list;
	}

	public List<AppDataModel> prepareAppData() {
		List<AppDataModel> appDataModel = new ArrayList<AppDataModel>();
		Read_XLS read = new Read_XLS(smokeTestDataSheet, "data/");
		String sheetName = "TCOM";
		if(ConstantFactory.getInstance().getExcelSheetName() != null || ! ConstantFactory.getInstance().getExcelSheetName().isEmpty() )
			sheetName = ConstantFactory.getInstance().getExcelSheetName();			
		Object[][] objs = new Object[read.retrieveNoOfRows(sheetName) - 1][read.retrieveNoOfCols(sheetName)];
		objs = read.retrieveTestData1(sheetName);
		for (Object[] obj : objs) {
			if (obj[3].toString().equalsIgnoreCase("true")) {
				AppDataModel requestQuoteData = new AppDataModel();
				requestQuoteData.setAppName(obj[0].toString());
				requestQuoteData.setUrl(obj[1].toString());
				requestQuoteData.setTitle(obj[2].toString());
				appDataModel.add(requestQuoteData);
			}
		}
		return appDataModel;
	}
	
	public static List<String[]> getMultiTDAData() {		
		Read_XLS read = new Read_XLS(functionalSheet, "data/");
		Object[][] objs = new Object[read.retrieveNoOfRows("MultiTDA") - 1][read.retrieveNoOfCols("MultiTDA")];
		objs = read.retrieveTestData1("MultiTDA");
		List<String[]> list = new ArrayList<String[]>();
		for (Object[] obj : objs) {
			String[] str = new String[2];
			str[0] = obj[0].toString();
			str[1]  = obj[1].toString();			
			list.add(str);
		}
		return list;
	}

	
	public static List<String[]> getGXPData() {		
		Read_XLS read = new Read_XLS(functionalSheet, "data/");
		Object[][] objs = new Object[read.retrieveNoOfRows("GXP") - 1][read.retrieveNoOfCols("GXP")];
		objs = read.retrieveTestData1("GXP");
		List<String[]> list = new ArrayList<String[]>();
		for (Object[] obj : objs) {
			String[] str = new String[2];
			str[0] = obj[0].toString();
			str[1]  = obj[1].toString();			
			list.add(str);
		}
		return list;
	}
	public static List<String[]> getSmartPathModalData() {		
		Read_XLS read = new Read_XLS(functionalSheet, "data/");
		Object[][] objs = new Object[read.retrieveNoOfRows("SmartPathModal") - 1][read.retrieveNoOfCols("SmartPathModal")];
		objs = read.retrieveTestData1("SmartPathModal");
		List<String[]> list = new ArrayList<String[]>();
		for (Object[] obj : objs) {
			String[] str = new String[4];
			str[0] = obj[0].toString();
			str[1]  = obj[1].toString();	
			str[2]  = obj[2].toString();	
			str[3]  = obj[3].toString();
			list.add(str);
		}
		return list;
	}
	
/*
	public static List<String[]> getFindADealerSETProximityData() {		
		Read_XLS read = new Read_XLS(functionalSheet, "data/");
		Object[][] objs = new Object[read.retrieveNoOfRows("DealerProximity") - 1][read.retrieveNoOfCols("DealerProximity")];
		objs = read.retrieveTestData1("DealerProximity");
		List<String[]> list = new ArrayList<String[]>();
		for (Object[] obj : objs) {
			String[] str = new String[3];
			str[0] = obj[0].toString();
			str[1]  = obj[1].toString();	
			str[2]  = obj[2].toString();
			list.add(str);
		}
		return list;
	}
	FindADealerSETProximity
	*/
	

	public static List<String[]> getFindADealerSETProximityData() {		
		Read_XLS read = new Read_XLS(functionalSheet, "data/");
		Object[][] objs = new Object[read.retrieveNoOfRows("FindADealerSETProximity") - 1][read.retrieveNoOfCols("FindADealerSETProximity")];
		objs = read.retrieveTestData1("FindADealerSETProximity");
		List<String[]> list = new ArrayList<String[]>();
		for (Object[] obj : objs) {
			String[] str = new String[3];
			str[0] = obj[0].toString();
			str[1]  = obj[1].toString();	
			str[2]  = obj[2].toString();
			list.add(str);
		}
		return list;
	}
	
	
	public static List<String[]> getTDACookieData() {
		Read_XLS read = new Read_XLS(functionalSheet, "data/");
		Object[][] objs = new Object[read.retrieveNoOfRows("TDACookieZip") - 1][read
				.retrieveNoOfCols("TDACookieZip")];
		objs = read.retrieveTestData1("TDACookieZip");
		List<String[]> list = new ArrayList<String[]>();
		for (Object[] obj : objs) {
			String[] str = new String[2];
			str[0] = obj[0].toString();
			str[1] = obj[1].toString();
			list.add(str);
		}
		return list;
	}
	
	public static List<String[]> getPMADealerProximityData() {		
		Read_XLS read = new Read_XLS(functionalSheet, "data/");
		Object[][] objs = new Object[read.retrieveNoOfRows("PMADealerProximity") - 1][read.retrieveNoOfCols("PMADealerProximity")];
		objs = read.retrieveTestData1("PMADealerProximity");
		List<String[]> list = new ArrayList<String[]>();
		for (Object[] obj : objs) {
			String[] str = new String[1];
			str[0] = obj[0].toString();		
			list.add(str);
		}
		return list;
	}
}
