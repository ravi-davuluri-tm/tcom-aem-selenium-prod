package com.test.raqemail.email;

import org.testng.Assert;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

import com.toy.datamodel.RequestQuoteModel;
import com.toy.dataproviders.DataProviderRAQEmail;
import com.toy.gmail.GmailAPI;
import com.toy.selenium.core.BaseTest;
import com.toy.selenium.core.ExcelStatus;
import com.toy.validation.ValidationData;

@Guice
public class RAQEmailDataVerification extends BaseTest {

	public RAQEmailDataVerification() {
		ExcelStatus.getInstance().setStatus( true);
	}
	
	@Test(dataProvider = "RAQEmail", dataProviderClass = DataProviderRAQEmail.class )
	public void test1(RequestQuoteModel raqModel) throws Exception {
		System.out.println(raqModel.toString());
		String from = "tmsusaincsaleslead@tmsusaconnect.com";		
		String mailContent = GmailAPI.check(raqModel.getEmail(), gmailPass, from, raqModel.getCommentText());		
		reportLog(mailContent);
		
		Assert.assertNotEquals("Not found", mailContent, "Email not recieved in 2 minutes");
		if(raqModel.getCommentText().contains("Standalone")) {
			ValidationData.validateRAQEmailData(raqModel, mailContent, raqModel.getLanguage());
		}else {
			ValidationData.validateOtherRAQTextEmailData(raqModel, mailContent, raqModel.getLanguage());
		}
		reportLog("Verify email content successfully");
	}
}