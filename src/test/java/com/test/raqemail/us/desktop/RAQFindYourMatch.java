package com.test.raqemail.us.desktop;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import com.toy.constant.GlobalConstant;
import com.toy.constant.GlobalPagesConstant;
import com.toy.datamodel.RequestQuoteModel;
import com.toy.dataproviders.DataProvidersUs;
import com.toy.pages.FindYourMatchPage;
import com.toy.pages.HomePage;
import com.toy.pages.RequestAQuotePage;
import com.toy.selenium.core.BaseTest;
import com.toy.utilities.Read_XLS;

public class RAQFindYourMatch extends BaseTest {

	@Test(dataProvider = "findMatch", dataProviderClass = DataProvidersUs.class)
	public void RAQFindYourMatchTest(String matchOptions, RequestQuoteModel raqModel) throws Exception{
		RequestAQuotePage raqPage = PageFactory.initElements(getWebDriver(), RequestAQuotePage.class);
		HomePage homePage = PageFactory.initElements(getWebDriver(), HomePage.class);
		
		raqModel.setCommentText(super.getComment("TestUS find Your Match "));
		raqModel.setCampaignCode("twtt10570000");
		raqModel.setSiteName("website");

		String newUrl = getWebDriver().getCurrentUrl() + "?zipcode="+raqModel.getZipCode();
		getWebDriver().navigate().to(newUrl);
		super.reportLog("Navigate Url with zip code "+raqModel.getZipCode());
		
		FindYourMatchPage findMatchPage = homePage.gotoFindMatch();
		super.reportLog("Navigated find a match page");
		
		findMatchPage.gotFindAMatchs(matchOptions);
		super.reportLog("Navigated to find a match options");
	
		raqModel.setSeriesName(findMatchPage.getSeriesName());
		super.reportLog("Get series name from list");
				
		findMatchPage.clickOnRAQbutton();
		super.reportLog("Click on RAQ button");
				
		raqPage.fillRQDetail(raqModel);
		raqPage.selectTrim(raqModel.getModelName());
		
		if(raqExperience.equalsIgnoreCase("control")) {
			raqPage.selectTrim(raqModel.getModelName());
		}
		super.reportLog("Fill RAQ form");
		
		raqModel.setVendorName(raqPage.getVendorName());
		raqModel.setDealerCode(raqPage.dealerCode());
		reportLog("fill request detail " + raqModel.toString());

		raqPage.clickSubmitButton();
		reportLog("click on submit button");

		String message = raqPage.getMessage();
		AssertJUnit.assertEquals(message.trim(), GlobalPagesConstant.RequestSent);
		reportLog("Verify message request send successfully");

		String raqConfirmationMessage = raqPage.getConfirmationThankYouMessage();
		Assert.assertTrue(
				raqConfirmationMessage.trim().toLowerCase()
						.contains(GlobalPagesConstant.ThankYouConfirmation.toLowerCase() + " "
								+ raqModel.getSeriesName().toLowerCase()),
				"Thank you message Verification failed");
		reportLog("Verify message Thank you! A dealer will contact you soon with a quote on your new Vehicle.");
		
		Read_XLS.writeContactListExcel(raqModel,  GlobalConstant.US);
		
		raqPage.verifyButtons();
		reportLog("Verify 'Search Inventory' and 'Build your own' buttons");

		
		
	}
}