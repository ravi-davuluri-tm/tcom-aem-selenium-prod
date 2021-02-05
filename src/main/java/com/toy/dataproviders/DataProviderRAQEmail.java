package com.toy.dataproviders;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.testng.annotations.DataProvider;
import com.toy.datamodel.RequestQuoteModel;
import com.toy.utilities.DataBuilderRAQEmail;

public class DataProviderRAQEmail {

	@DataProvider(name = "RAQEmail")
	public Iterator<Object[]> requestQuote() throws IOException {

		Collection<Object[]> requestData = new ArrayList<Object[]>();
		{
			{
				DataBuilderRAQEmail dataBuilder = new DataBuilderRAQEmail();
				List<RequestQuoteModel> data = dataBuilder.prepareRQAEmail();
				for (RequestQuoteModel requestQuoteModel : data) {
					requestData.add(new Object[] { requestQuoteModel });
				}
			}
		}
		return requestData.iterator();
	}

}