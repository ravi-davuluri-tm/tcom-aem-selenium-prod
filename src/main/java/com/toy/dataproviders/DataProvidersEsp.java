package com.toy.dataproviders;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.toy.datamodel.RequestQuoteModel;
import com.toy.utilities.DataBuilder;
import com.toy.utilities.DataBuilderEsp;
import com.toy.utilities.Read_XLS;

/**
 * This class contains testng data provider 
 * which holds test data 
 * @author Admin
 *
 */

public class DataProvidersEsp {

	/**
	 * Data provider for request a quote
	 * @return
	 * @throws IOException
	 */
	@DataProvider(name = "Standalone")
	public Iterator<Object[]> requestQuote() throws IOException {
		Collection<Object[]> requestData = new ArrayList<Object[]>();
		{
			{
				DataBuilderEsp dataBuilder = new DataBuilderEsp();
				List<RequestQuoteModel> data = dataBuilder.prepareRequestQuoteData();
				for (RequestQuoteModel requestQuoteModel : data) {
					requestData.add(new Object[] { requestQuoteModel });
				}
			}
		}
		return requestData.iterator();
	}

	@DataProvider(name = "RAQZEVEsp")
	public Iterator<Object[]> requestRAQZEVEspData() throws IOException {
		Collection<Object[]> requestData = new ArrayList<Object[]>();
		{
			{
				DataBuilderEsp dataBuilderEsp = new DataBuilderEsp();
				List<RequestQuoteModel> data = dataBuilderEsp.prepareRAQZEVEspData();
				for (RequestQuoteModel requestQuoteModel : data) {
					requestData.add(new Object[] { requestQuoteModel });
				}
			}
		}
		return requestData.iterator();
	}
	
	/**
	 * Data Provider for build and price
	 * @return
	 * @throws IOException
	 */
	@DataProvider(name = "BuildAndPrice")
	public Iterator<Object[]> requestBuildAndPrice() throws IOException {

		Collection<Object[]> requestData = new ArrayList<Object[]>();
		{
			{
				DataBuilderEsp dataBuilder = new DataBuilderEsp();
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
	 * @return
	 * @throws IOException
	 */
	@DataProvider(name = "LocalSpecial")
	public Iterator<Object[]> requestLocalSpecial() throws IOException {

		Collection<Object[]> requestData = new ArrayList<Object[]>();
		{
			{
				DataBuilderEsp dataBuilder = new DataBuilderEsp();
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
	 * @return
	 * @throws IOException
	 */
	@DataProvider(name = "SearchInventory")
	public Iterator<Object[]> requestSearchInventory() throws IOException {
		Collection<Object[]> requestData = new ArrayList<Object[]>();
		{
			{
				DataBuilderEsp dataBuilder = new DataBuilderEsp();
				List<RequestQuoteModel> data = dataBuilder.prepareSearchInventoryData();
				for (RequestQuoteModel requestQuoteModel : data) {
					requestData.add(new Object[] { requestQuoteModel });
				}
			}
		}
		return requestData.iterator();
	}

	@DataProvider(name = "DisclaimerEsp")
	public Object[][] disclaimerData() {

		Read_XLS read = new Read_XLS(DataBuilder.functionalSheet, "data/");
		Object[][] objs = new Object[read.retrieveNoOfRows("DisclaimerEsp") - 1][read.retrieveNoOfCols("DisclaimerEsp")];
		objs = read.retrieveTestData1("DisclaimerEsp");
		return objs;

	}

	@Test(dataProvider = "Disclaimer", dataProviderClass = DataProvidersEsp.class)
	public void testss(String name, String uri, String disclaimerText) {
		String[] str = disclaimerText.split("\n");
		System.out.println("=========================================================");
		for (String str1 : str) {
			System.out.println(str1);
			System.out.println("");
		}
		// System.out.println(name + " " + uri + " "+disclaimerText);
	}
}