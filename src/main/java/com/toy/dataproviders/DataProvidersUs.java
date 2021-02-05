package com.toy.dataproviders;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import com.toy.datamodel.AppDataModel;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.toy.datamodel.RequestQuoteModel;
import com.toy.utilities.DataBuilder;
import com.toy.utilities.Read_XLS;

/**
 * This class contains testng data provider which holds test data
 * 
 * @author Admin
 *
 */

public class DataProvidersUs {

	@DataProvider(name = "Standalone")
	public Iterator<Object[]> requestQuote() throws IOException {

		Collection<Object[]> requestData = new ArrayList<Object[]>();
		{
			{
				DataBuilder dataBuilder = new DataBuilder();
				List<RequestQuoteModel> data = dataBuilder.prepareRequestQuoteData();
				for (RequestQuoteModel requestQuoteModel : data) {
					requestData.add(new Object[] { requestQuoteModel });
				}
			}
		}
		return requestData.iterator();
	}

	@DataProvider(name = "RAQZEV")
	public Iterator<Object[]> requestRAQZEVData() throws IOException {
		Collection<Object[]> requestData = new ArrayList<Object[]>();
		{
			{
				DataBuilder dataBuilder = new DataBuilder();
				List<RequestQuoteModel> data = dataBuilder.prepareRAQZEVUSData();
				for (RequestQuoteModel requestQuoteModel : data) {
					requestData.add(new Object[] { requestQuoteModel });
				}
			}
		}
		return requestData.iterator();
	}
	
	
	
	/**
	 * Data Provider for build and price
	 * 
	 * @return
	 * @throws IOException
	 */
	@DataProvider(name = "BuildAndPrice")
	public Iterator<Object[]> requestBuildAndPrice() throws IOException {

		Collection<Object[]> requestData = new ArrayList<Object[]>();
		{
			{
				DataBuilder dataBuilder = new DataBuilder();
				List<RequestQuoteModel> data = dataBuilder.prepareBuildAndPriceData();
				for (RequestQuoteModel requestQuoteModel : data) {
					requestData.add(new Object[] { requestQuoteModel });
				}
			}
		}
		return requestData.iterator();
	}

	/**
	 * Data Provider for local special
	 * 
	 * @return
	 * @throws IOException
	 */
	@DataProvider(name = "LocalSpecial")
	public Iterator<Object[]> requestLocalSpecial() throws IOException {

		Collection<Object[]> requestData = new ArrayList<Object[]>();
		{
			{
				DataBuilder dataBuilder = new DataBuilder();
				List<RequestQuoteModel> data = dataBuilder.prepareLocalSpecialData();
				for (RequestQuoteModel requestQuoteModel : data) {
					requestData.add(new Object[] { requestQuoteModel });
				}
			}
		}
		return requestData.iterator();
	}

	/**
	 * Data Provider for search inventory
	 * 
	 * @return
	 * @throws IOException
	 */
	@DataProvider(name = "SearchInventory")
	public Iterator<Object[]> requestSearchInventory() throws IOException {
		Collection<Object[]> requestData = new ArrayList<Object[]>();
		{
			{
				DataBuilder dataBuilder = new DataBuilder();
				List<RequestQuoteModel> data = dataBuilder.prepareSearchInventoryData();
				for (RequestQuoteModel requestQuoteModel : data) {
					requestData.add(new Object[] { requestQuoteModel });
				}
			}
		}
		return requestData.iterator();
	}

	/**
	 * Data Provider for Find a dealer
	 * 
	 * @return
	 * @throws IOException
	 */
	@DataProvider(name = "FindADealer")
	public static Iterator<Object[]> requestFindADealer() throws IOException {
		Collection<Object[]> requestData = new ArrayList<Object[]>();
		{
			{
				DataBuilder dataBuilder = new DataBuilder();
				List<RequestQuoteModel> data = dataBuilder.prepareFindADealer();
				for (RequestQuoteModel requestQuoteModel : data) {
					requestData.add(new Object[] { requestQuoteModel });
				}
			}
		}
		return requestData.iterator();
	}

	/**
	 * Data Provider for Vehicle
	 * 
	 * @return
	 * @throws IOException
	 */
	@DataProvider(name = "Vehicle")
	public static Iterator<Object[]> requestVehicle() throws IOException {
		Collection<Object[]> requestData = new ArrayList<Object[]>();
		{
			{
				DataBuilder dataBuilder = new DataBuilder();
				List<RequestQuoteModel> data = dataBuilder.prepareVehicle();
				for (RequestQuoteModel requestQuoteModel : data) {
					requestData.add(new Object[] { requestQuoteModel });
				}
			}
		}
		return requestData.iterator();
	}
	
	@DataProvider(name = "Disclaimer")
	public Object[][] disclaimerData() {

		Read_XLS read = new Read_XLS(DataBuilder.functionalSheet, "data/");
		Object[][] objs = new Object[read.retrieveNoOfRows("Disclaimer") - 1][read.retrieveNoOfCols("Disclaimer")];
		objs = read.retrieveTestData1("Disclaimer");
		return objs;

	}

	
	@DataProvider(name = "findMatch")
	public static Object[][] findMacth() {
		Read_XLS read = new Read_XLS(DataBuilder.testDataSheet, "data/");
		int column = read.retrieveNoOfCols("FindAMatch");
		int row = read.retrieveNoOfRows("FindAMatch");
		Object[][] objs = new Object[row - 1][column - 1];
		objs = read.retrieveTestData1("FindAMatch");
		Object[][] objData = new Object[row - 1][column - 1];
		int i = 0;
		for (Object[] obj : objs) {
			Object[] data = new Object[2];
			RequestQuoteModel requestQuoteData = new RequestQuoteModel();
			data[0] = obj[0].toString();
			requestQuoteData.setModelName(obj[1].toString());
			requestQuoteData.setFirstName(obj[2].toString());
			requestQuoteData.setLastName(obj[3].toString());
			requestQuoteData.setEmail(obj[4].toString());
			requestQuoteData.setPhone(obj[5].toString());
			requestQuoteData.setAddress(obj[6].toString());
			requestQuoteData.setCity(obj[7].toString());
			requestQuoteData.setZipCode(obj[8].toString());
			data[1] = requestQuoteData;
			objData[i++] = data;
		}
		return objData;
	}
	@DataProvider(name = "AppSmoke")
	public static Iterator<Object[]> appSmoke() throws IOException {
		Collection<Object[]> requestData = new ArrayList<Object[]>();
		{
			{
				DataBuilder dataBuilder = new DataBuilder();
				List<AppDataModel> data = dataBuilder.prepareAppData();
				for (AppDataModel appDataModel : data) {
					requestData.add(new Object[] { appDataModel });
				}
			}
		}
		return requestData.iterator();
	}

	@DataProvider(name = "FooterLinks")
	public static Object[][] footerLinksData() {
		Read_XLS read = new Read_XLS(DataBuilder.functionalSheet, "data/");
		Object[][] objs = new Object[read.retrieveNoOfRows("FooterLinks") - 1][read.retrieveNoOfCols("FooterLinks")];
		objs = read.retrieveTestData1("FooterLinks");
		return objs;
	}
	
	@Test(dataProvider = "AppSmoke", dataProviderClass = DataProvidersUs.class)
	public void testss(AppDataModel model) {
		System.out.println(model.getAppName() + "		" + model.getUrl() + " " + model.getTitle());
}
	
	
}