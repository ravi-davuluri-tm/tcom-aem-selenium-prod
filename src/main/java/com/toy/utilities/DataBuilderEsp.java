package com.toy.utilities;

import java.util.ArrayList;
import java.util.List;

import com.toy.datamodel.RequestQuoteModel;

public class DataBuilderEsp {

	String testDataSheet = "TestData.xlsx";

	public List<RequestQuoteModel> prepareRequestQuoteData() {
		List<RequestQuoteModel> raqData = new ArrayList<RequestQuoteModel>();
		Read_XLS read = new Read_XLS(testDataSheet, "data/");
		Object[][] objs = new Object[read.retrieveNoOfRows("RAQStandaloneEsp") - 1][read.retrieveNoOfCols("RAQStandaloneEsp")];

		objs = read.retrieveTestData1("RAQStandaloneEsp");
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

	public List<RequestQuoteModel> prepareRAQZEVEspData() {
		List<RequestQuoteModel> raqData = new ArrayList<RequestQuoteModel>();
		Read_XLS read = new Read_XLS(testDataSheet, "data/");
		Object[][] objs = new Object[read.retrieveNoOfRows("RAQZEVEsp") - 1][read
				.retrieveNoOfCols("RAQZEVEsp")];

		objs = read.retrieveTestData1("RAQZEVEsp");
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
		Object[][] objs = new Object[read.retrieveNoOfRows("RAQBuildAndPriceEsp") - 1][read.retrieveNoOfCols("RAQBuildAndPriceEsp")];

		objs = read.retrieveTestData1("RAQBuildAndPriceEsp");
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
		Object[][] objs = new Object[read.retrieveNoOfRows("RAQLocalSpecialEsp") - 1][read.retrieveNoOfCols("RAQLocalSpecialEsp")];

		objs = read.retrieveTestData1("RAQLocalSpecialEsp");
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
			raqData.add(requestQuoteData);
		}
		return raqData;
	}

	public List<RequestQuoteModel> prepareSearchInventoryData() {
		List<RequestQuoteModel> raqData = new ArrayList<RequestQuoteModel>();
		Read_XLS read = new Read_XLS(testDataSheet, "data/");
		Object[][] objs = new Object[read.retrieveNoOfRows("RAQSearchInventoryEsp") - 1][read.retrieveNoOfCols("RAQSearchInventoryEsp")];

		objs = read.retrieveTestData1("RAQSearchInventoryEsp");
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
			raqData.add(requestQuoteData);
		}
		return raqData;
	}


}