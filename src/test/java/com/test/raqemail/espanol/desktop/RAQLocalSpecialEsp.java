package com.test.raqemail.espanol.desktop;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

import com.toy.constant.GlobalConstant;
import com.toy.constant.GlobalPagesConstant;
import com.toy.datamodel.RequestQuoteModel;
import com.toy.dataproviders.DataProvidersEsp;
import com.toy.gmail.GmailAPI;
import com.toy.pages.HomePage;
import com.toy.pages.LocalSpecialPage;
import com.toy.pages.RequestAQuotePage;
import com.toy.selenium.core.BaseTest;
import com.toy.utilities.Read_XLS;
import com.toy.utilities.Utilities;
import com.toy.validation.ValidationData;

@Guice
public class RAQLocalSpecialEsp extends BaseTest {
	//
	
//	@Test(dataProvider = "LocalSpecial", dataProviderClass=DataProvidersEsp.class)
	public void RAQLocalSpecialEspTest(RequestQuoteModel requestQuoteModel) throws Exception {
		RequestAQuotePage requestAQuotePage = PageFactory.initElements(getWebDriver(),
				RequestAQuotePage.class);		
		HomePage homePage = PageFactory.initElements(getWebDriver(), HomePage.class);
		
		requestQuoteModel.setCommentText(super.getComment("Test ESP Desktop RAQ Local Specials"));
		requestQuoteModel.setCampaignCode("TWTT12790000");
		requestQuoteModel.setSiteName("website");
		
		homePage.selectEspanolLanguage();
		reportLog("select espanol language");
		
		String newUrl = getWebDriver().getCurrentUrl() + "?zipcode="+requestQuoteModel.getZipCode();
		getWebDriver().navigate().to(newUrl);
		super.reportLog("Navigate Url with zip code "+requestQuoteModel.getZipCode());
		
		reportLog("click on shopping tool menu");		
		homePage.selectShoppingToolMenu();
		LocalSpecialPage localSpecialPage = homePage.gotoLocalSpecialEsp();		
		reportLog("Go to local special page");		
			
		localSpecialPage.clickViewDetails(requestQuoteModel);	
		reportLog("Click on view details");
		
		requestQuoteModel.setSeriesName(Utilities.splitStringWithNewLine(
				localSpecialPage.getSeriesNameEsp())[0]);
		
		String name = localSpecialPage.getSeriesNameEsp();
		System.out.println("series Name" + name);
		requestAQuotePage = localSpecialPage.clickRequestAQuoteButton();
		reportLog("Click on view details button and click on request a quote button");
		
		requestAQuotePage.fillRQDetailForLocalSpecial(requestQuoteModel);
		requestQuoteModel.setVendorName(requestAQuotePage.getVendorNameLocalSpecial());
		requestQuoteModel.setDealerCode(requestAQuotePage.dealerCodeLocalSpecial());
		reportLog("fill request detail " + requestQuoteModel.toString());

		//GmailAPI.delete(requestQuoteModel.getEmail(), gmailPass);
		requestAQuotePage.clickRAQLocalSpecialSubmit();
		reportLog("click on submit button");

		String message = requestAQuotePage.getMessage();
		Assert.assertEquals(message.trim(), GlobalPagesConstant.RequestSentEsp);
		reportLog("Verify message request send successfully");
		System.out.println(requestQuoteModel.toString());
		
		String raqConfirmationMessage = requestAQuotePage.getConfirmationThankYouMessage();
		Assert.assertTrue(raqConfirmationMessage.trim().toLowerCase().contains(
				GlobalPagesConstant.ThankYouConfirmationEsp.toLowerCase() + " "
						+ requestQuoteModel.getSeriesName().toLowerCase()));		
		reportLog("Verify message Thank you! A dealer will contact you soon with a quote on your new Vehicle.");

		Read_XLS.writeContactListExcel(requestQuoteModel, GlobalConstant.US);
		
		requestAQuotePage.verifyButtons();
		reportLog("Verify 'Search vender' and 'Build your own' buttons");
		
			
	}

}
