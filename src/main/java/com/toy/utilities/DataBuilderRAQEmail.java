package com.toy.utilities;

import java.util.ArrayList;
import java.util.List;

import com.toy.constant.GlobalConstant;
import com.toy.datamodel.RequestQuoteModel;

public class DataBuilderRAQEmail {
	
	public List<RequestQuoteModel> prepareRQAEmail() {
		List<RequestQuoteModel> raqData = new ArrayList<RequestQuoteModel>();
		Read_XLS read = new Read_XLS(GlobalConstant.RAQ_OutPut_File, "");
		Object[][] objs = new Object[read.retrieveNoOfRows(GlobalConstant.RAQ_OutPut_Sheet) - 1][read.retrieveNoOfCols(GlobalConstant.RAQ_OutPut_Sheet)];
		objs = read.retrieveTestData1(GlobalConstant.RAQ_OutPut_Sheet);

		for (Object[] obj : objs) {
			RequestQuoteModel requestQuoteData = new RequestQuoteModel();	
			requestQuoteData.setSeriesName(obj[0].toString());
			requestQuoteData.setFirstName(obj[1].toString());
			requestQuoteData.setLastName(obj[2].toString());
			requestQuoteData.setZipCode(obj[3].toString());	
			requestQuoteData.setEmail(obj[4].toString());			
			requestQuoteData.setPhone(obj[5].toString());
			requestQuoteData.setAddress(obj[6].toString());
			requestQuoteData.setCity(obj[7].toString());
			requestQuoteData.setTrim(obj[8].toString());
			requestQuoteData.setVendorName(obj[9].toString());
			requestQuoteData.setDealerCode(obj[10].toString());
			requestQuoteData.setCommentText(obj[11].toString());
			requestQuoteData.setExteriorColor(obj[12].toString());
			requestQuoteData.setInteriorColor(obj[13].toString());
			requestQuoteData.setPrice(obj[14].toString());
			requestQuoteData.setOfferRibbon(obj[15].toString());
			requestQuoteData.setOfferDescription(obj[16].toString());
			requestQuoteData.setSiteName(obj[17].toString());
			requestQuoteData.setCampaignCode(obj[18].toString());
			requestQuoteData.setLanguage(obj[19].toString());
			raqData.add(requestQuoteData);
		}
		return raqData;
	}	

}