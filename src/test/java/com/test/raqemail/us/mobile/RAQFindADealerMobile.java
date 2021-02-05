package com.test.raqemail.us.mobile;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.toy.constant.GlobalConstant;
import com.toy.constant.GlobalPagesConstant;
import com.toy.datamodel.RequestQuoteModel;
import com.toy.dataproviders.DataProvidersUs;
import com.toy.gmail.GmailAPI;
import com.toy.pages.DealerPage;
import com.toy.pages.HomePage;
import com.toy.pages.RequestAQuotePage;
import com.toy.selenium.core.BaseTest;
import com.toy.utilities.Read_XLS;
import com.toy.validation.ValidationData;

public class RAQFindADealerMobile extends BaseTest {

	public RAQFindADealerMobile(){
		mobileTest = true;
	}
	
	@Test(dataProvider = "FindADealer", dataProviderClass = DataProvidersUs.class)
	public void RAQFindADealerByDealerIdMobileTest(RequestQuoteModel raqModel) throws Exception {
		RequestAQuotePage raqPage = PageFactory.initElements(getWebDriver(), RequestAQuotePage.class);

		raqModel.setCommentText(super.getComment("TestUS Mobile FindADealer"));
		raqModel.setCampaignCode("tmtt00310000");
		raqModel.setSiteName("mobile");

		String newUrl = getWebDriver().getCurrentUrl() + "?zipcode="+raqModel.getZipCode();
		getWebDriver().navigate().to(newUrl);
		super.reportLog("Navigate Url with zip code "+raqModel.getZipCode());

		//homePage.waitZipUpdatedMobile(raqModel.getZipCode());
		//homePage.getZipCode();
		getWebDriver().navigate().to(applicationUrl + "/dealers/dealer/" + raqModel.getDealerCode());
		super.reportLog("Navigated to dealer url");

		
		String title = raqPage.getTitle();
		Assert.assertTrue(title.contains("Toyota Dealer Locator | Find a Toyota Car Dealership Near You"));
		super.reportLog("Page title verfied");
		
		DealerPage dealerPage = PageFactory.initElements(super.getWebDriver(), DealerPage.class);
		raqModel.setVendorName(dealerPage.getDealerName());
		
		raqPage = dealerPage.clickRAQButton(raqModel.getDealerCode());
		super.reportLog("Click on RAQ button");

		raqPage.fillRequestQuoteDetail(raqModel);
		String dealer = raqPage.dealerCode();
		Assert.assertEquals(dealer, raqModel.getDealerCode());
		super.reportLog("Verify dealer name");

		raqPage.clickSubmitButton();
		super.reportLog("click on submit button");

		String message = raqPage.getMessage();
		Assert.assertEquals(message.trim(), GlobalPagesConstant.RequestSent);
		reportLog("Verify message request send successfully");

		raqPage.verifyButtons();
		reportLog("Verify 'Search vender' and 'Build your own' buttons");

		String raqConfirmationMessage = raqPage.getConfirmationThankYouMessage();
		String expectedThanksMsg = "";
		if(raqModel.getSeriesName() != null && raqModel.getSeriesName() != "")
			expectedThanksMsg = GlobalPagesConstant.ThankYouConfirmation.toLowerCase() + " "
					+ raqModel.getSeriesName().toLowerCase();
		else
			expectedThanksMsg = GlobalPagesConstant.ThankYouConfirShortMsg.toLowerCase();
		Assert.assertTrue(
				raqConfirmationMessage.trim().toLowerCase()
						.contains(expectedThanksMsg), "Thank you message Verification failed");
		reportLog("Verify message Thank you! A dealer will contact you soon with a quote on your new Vehicle.");

		Read_XLS.writeContactListExcel(raqModel,  GlobalConstant.US);
	}

	@Test(dataProvider = "FindADealer", dataProviderClass = DataProvidersUs.class)
	public void RAQFindADealerByDealersPageMobileTest(RequestQuoteModel raqModel) throws Exception {
		RequestAQuotePage raqPage = PageFactory.initElements(getWebDriver(), RequestAQuotePage.class);
		HomePage homePage = PageFactory.initElements(getWebDriver(), HomePage.class);
		DealerPage dealerPage = PageFactory.initElements(super.getWebDriver(), DealerPage.class);

		raqModel.setCommentText(super.getComment("TestUS Mobile FindADealer"));
		raqModel.setCampaignCode("tmtt00310000");
		raqModel.setSiteName("mobile");

		String newUrl = getWebDriver().getCurrentUrl() + "?zipcode="+raqModel.getZipCode();
		getWebDriver().navigate().to(newUrl);
		super.reportLog("Navigate Url with zip code "+raqModel.getZipCode());
		Thread.sleep(2000);
		
		homePage.gotofindDealersMobile();
		super.reportLog("Navigated find a dealer page");
		
		raqModel.setVendorName(dealerPage.getDealerNameFromDealersPage(raqModel.getDealerCode()));
		raqPage = dealerPage.clickRAQButton(raqModel.getDealerCode());
		super.reportLog("Click on RAQ button");

		raqPage.fillRequestQuoteDetail(raqModel);
		String dealer = raqPage.dealerCode();
		Assert.assertEquals(dealer, raqModel.getDealerCode());
		super.reportLog("Verify dealer name");

		raqPage.clickSubmitButton();
		super.reportLog("click on submit button");

		String message = raqPage.getMessage();
		Assert.assertEquals(message.trim(), GlobalPagesConstant.RequestSent);
		super.reportLog("Verify message request send successfully");

		raqPage.verifyButtons();
		super.reportLog("Verify 'Search vender' and 'Build your own' buttons");

		String raqConfirmationMessage = raqPage.getConfirmationThankYouMessage();
		String expectedThanksMsg = "";
		if(raqModel.getSeriesName() != null && raqModel.getSeriesName() != "")
			expectedThanksMsg = GlobalPagesConstant.ThankYouConfirmation.toLowerCase() + " "
					+ raqModel.getSeriesName().toLowerCase();
		else
			expectedThanksMsg = GlobalPagesConstant.ThankYouConfirShortMsg.toLowerCase();
		Assert.assertTrue(
				raqConfirmationMessage.trim().toLowerCase()
						.contains(expectedThanksMsg), "Thank you message Verification failed");
		reportLog("Verify message Thank you! A dealer will contact you soon with a quote on your new Vehicle.");

		Read_XLS.writeContactListExcel(raqModel,  GlobalConstant.US);

	}

}
