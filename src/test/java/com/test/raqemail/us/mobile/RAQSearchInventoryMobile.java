package com.test.raqemail.us.mobile;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

import com.toy.constant.GlobalConstant;
import com.toy.constant.GlobalPagesConstant;
import com.toy.datamodel.RequestQuoteModel;
import com.toy.dataproviders.DataProvidersUs;
import com.toy.pages.HomePage;
import com.toy.pages.RequestAQuotePage;
import com.toy.pages.SearchInventoryPage;
import com.toy.selenium.core.BaseTest;
import com.toy.utilities.Read_XLS;
import com.toy.utilities.Utilities;

@Guice
public class RAQSearchInventoryMobile extends BaseTest {

	public RAQSearchInventoryMobile(){
		mobileTest = true;
	}
	
	@Test(dataProvider = "SearchInventory", dataProviderClass=DataProvidersUs.class)
	public void RAQSearchInventoryMobileTest(RequestQuoteModel requestQuoteModel) throws Exception{
		
		RequestAQuotePage requestAQuotePage = PageFactory.initElements(getWebDriver(),
				RequestAQuotePage.class);		
		HomePage homePage = PageFactory.initElements(getWebDriver(), HomePage.class);
		
		requestQuoteModel.setCommentText(super.getComment("TestUs Mobile RAQ Search And Inventory"));
		requestQuoteModel.setCampaignCode("TMTT31850000");
		requestQuoteModel.setSiteName("mobile");
		
		String newUrl = getWebDriver().getCurrentUrl() + "?zipcode="+requestQuoteModel.getZipCode();
		getWebDriver().navigate().to(newUrl);
		super.reportLog("Navigate Url with zip code "+requestQuoteModel.getZipCode());
		homePage.waitZipUpdatedMobile(requestQuoteModel.getZipCode());
		
		reportLog("click on shopping tool menu");
	
		SearchInventoryPage searchInventoryPage = homePage.gotoSearchInventoryMobile();
		reportLog("Go to search inventory page");
		
		searchInventoryPage.selectCarBySeries(requestQuoteModel);
		reportLog("Select car from search inventory page");
		
		searchInventoryPage.clickViewDetails();
		reportLog("click on view details page");
		
		requestAQuotePage.fillRQDetail(requestQuoteModel);
		requestQuoteModel.setVendorName(requestAQuotePage.getVendorName());
		requestQuoteModel.setDealerCode(requestAQuotePage.dealerCode());
		requestQuoteModel = searchInventoryPage.getPriceMobile(requestQuoteModel);
		String currentUrl = getWebDriver().getCurrentUrl();
		requestQuoteModel = Utilities.setColor(requestQuoteModel, currentUrl);
		reportLog("fill request detail " + requestQuoteModel.toString());
		
		//GmailAPI.delete(requestQuoteModel.getEmail(), gmailPass);
		requestAQuotePage.clickSubmitButton();
		reportLog("click on submit button");
				
		String message = requestAQuotePage.getMessage();
		Assert.assertEquals(message.trim(), GlobalPagesConstant.RequestSent);
		reportLog("Verify message request send successfully");
			
		String raqConfirmationMessage = requestAQuotePage.getConfirmationThankYouMessage();
		System.out.println(raqConfirmationMessage);
		System.out.println(GlobalPagesConstant.ThankYouConfirmation.toLowerCase() + " "
						+ requestQuoteModel.getSeriesName().toLowerCase());
		
		Assert.assertTrue(raqConfirmationMessage.trim().toLowerCase().contains(
				GlobalPagesConstant.ThankYouConfirmation.toLowerCase() + " "
						+ requestQuoteModel.getSeriesName().toLowerCase()));		
		reportLog("Verify message Thank you! A dealer will contact you soon with a quote on your new Vehicle.");

		Read_XLS.writeContactListExcel(requestQuoteModel,  GlobalConstant.US);
	}
}
