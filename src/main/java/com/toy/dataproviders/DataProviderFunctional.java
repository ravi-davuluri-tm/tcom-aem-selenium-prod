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
import com.toy.utilities.Read_XLS;
public class DataProviderFunctional {
	
	@DataProvider(name = "MiraiPMAZip")
	public static Object[][] testDataZip() {
		return new Object[][] { { "90210" }, { "94115" }, { "97204" }, { "80203" }, { "10010" }, { "02108" },
				{ "60608" }, { "45212" }, { "64106" }, { "30303" }, { "78726" }, { "23220" } };

	}
	
	/**
	 * Data provider for build and price functional test data
	 * @return
	 * @throws IOException
	 */
	@DataProvider(name = "BuildAndPrice")
	public static Iterator<Object[]> buidlAndPriceData() throws IOException {
		Collection<Object[]> requestData = new ArrayList<Object[]>();
		{
			{			
				List<String[]> data = DataBuilder.getBuildAndPrice();
				for (String[] str : data) {
					requestData.add(new Object[] { str });
				}
			}
		}
		return requestData.iterator();
	}
	
	@DataProvider(name = "BuildAndPriceTab",parallel=false)
	public static Iterator<Object[]> requestBuildAndPriceTab() throws IOException {
		Collection<Object[]> requestData = new ArrayList<Object[]>();
		{
			{				
				List<String[]> data = DataBuilder.getBuildAndPriceTab();
				for (String[] str : data) {
					requestData.add(new Object[] { str });
				}
			}
		}
		return requestData.iterator();
	}

	
	@DataProvider(name = "BuildAndPriceTrucks")
	public static Iterator<Object[]> requestBuildAndPriceTrucks() throws IOException {
		Collection<Object[]> requestData = new ArrayList<Object[]>();
		{
			{				
				List<String[]> data = DataBuilder.getBuildAndPriceTrucks();
				for (String[] str : data) {
					requestData.add(new Object[] { str });
				}
			}
		}
		return requestData.iterator();
	}
	
	
	@DataProvider(name = "BuildAndPriceEmail")
	public static Iterator<Object[]> requestBuildAndPriceEmail() throws IOException {
		Collection<Object[]> requestData = new ArrayList<Object[]>();
		{
			{				
				List<String[]> data = DataBuilder.getBuildAndPriceEmail();
				for (String[] str : data) {
					requestData.add(new Object[] { str });
				}
			}
		}
		return requestData.iterator();
	}
	
	@DataProvider(name = "BuildAndPriceSUV")
	public static Iterator<Object[]> requesBuildAndPriceSUV() throws IOException {
		Collection<Object[]> requestData = new ArrayList<Object[]>();
		{
			{				
				List<String[]> data = DataBuilder.getBuildAndPriceSUV();
				for (String[] str : data) {
					requestData.add(new Object[] { str });
				}
			}
		}
		return requestData.iterator();
	}
	
	@DataProvider(name = "OfferType")
	public static Iterator<Object[]> getOfferType() throws IOException {
		Collection<Object[]> requestData = new ArrayList<Object[]>();
		{
			{				
				List<String[]> data = DataBuilder.getOfferTypeData();
				for (String[] str : data) {
					requestData.add(new Object[] { str });
				}
			}
		}
		return requestData.iterator();
	}
	
	@DataProvider(name = "MultiTDA")
	public static Iterator<Object[]> getMultiTDA() throws IOException {
		Collection<Object[]> requestData = new ArrayList<Object[]>();
		{
			{				
				List<String[]> data = DataBuilder.getMultiTDAData();
				for (String[] str : data) {
					requestData.add(new Object[] { str });
				}
			}
		}
		return requestData.iterator();
	}
	
	@DataProvider(name = "GXPSmartPath")
	public static Iterator<Object[]> getGXP() throws IOException {
		Collection<Object[]> requestData = new ArrayList<Object[]>();
		{
			{				
				List<String[]> data = DataBuilder.getGXPData();
				for (String[] str : data) {
					requestData.add(new Object[] { str });
				}
			}
		}
		return requestData.iterator();
	}
	
	@DataProvider(name = "FindADealerSETProximity")
	public static Iterator<Object[]> getFindADealerSETProximity() throws IOException {
		Collection<Object[]> requestData = new ArrayList<Object[]>();
		{
			{				
				List<String[]> data = DataBuilder.getFindADealerSETProximityData();
				for (String[] str : data) {
					requestData.add(new Object[] { str });
				}
			}
		}
		return requestData.iterator();
	}
	
	@DataProvider(name = "TDACookie")
	public static Iterator<Object[]> getTDACookie() throws IOException {
		Collection<Object[]> requestData = new ArrayList<Object[]>();
		{
			{				
				List<String[]> data = DataBuilder.getTDACookieData();
				for (String[] str : data) {
					requestData.add(new Object[] { str });
				}
			}
		}
		return requestData.iterator();
	}
	
	@DataProvider(name = "SmartPathModal")
	public static Iterator<Object[]> getSmartPathModal() throws IOException {
		Collection<Object[]> requestData = new ArrayList<Object[]>();
		{
			{				
				List<String[]> data = DataBuilder.getSmartPathModalData();
				for (String[] str : data) {
					requestData.add(new Object[] { str });
				}
			}
		}
		return requestData.iterator();
	}
	
	@DataProvider(name = "PMADealerProximity")
	public static Iterator<Object[]> getPMADealerProximity() throws IOException {
		Collection<Object[]> requestData = new ArrayList<Object[]>();
		{
			{				
				List<String[]> data = DataBuilder.getPMADealerProximityData();
				for (String[] str : data) {
					requestData.add(new Object[] { str });
				}
			}
		}
		return requestData.iterator();
	}
	
	@DataProvider(name = "data-provider-url")
	public static Object[][] dataProviderMethod() {
		return new Object[][] { 
			{ "https://test6.tcom.aws.toyota.com/" }, 
			{ "https://test4.tcom.aws.toyota.com/" } };
	}
}
