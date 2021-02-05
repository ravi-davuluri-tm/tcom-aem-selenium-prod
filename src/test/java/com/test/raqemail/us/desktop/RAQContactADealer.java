package com.test.raqemail.us.desktop;

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
import com.toy.selenium.core.BaseTest;
import com.toy.utilities.Read_XLS;



@Guice
public class RAQContactADealer extends BaseTest {

	/**
	 * Test request quote.
	 *
	 * @param requestQuoteModel the request quote model
	 * @throws Exception
	 */

	@Test(dataProvider = "Standalone", dataProviderClass = DataProvidersUs.class)
	public void RAQContactADealerTest(RequestQuoteModel requestQuoteModel) throws Exception {

		HomePage homePage = PageFactory.initElements(getWebDriver(), HomePage.class);
		RequestAQuotePage requestAQuotePage = PageFactory.initElements(getWebDriver(), RequestAQuotePage.class);
		
		String newUrl = getWebDriver().getCurrentUrl() + "?zipcode="+requestQuoteModel.getZipCode();
		getWebDriver().navigate().to(newUrl);
		super.reportLog("Navigate Url with zip code "+requestQuoteModel.getZipCode());		
		
		requestQuoteModel.setCommentText(super.getComment("TestUs Desktop RAQ Contact a dealer Standalone"));
		requestQuoteModel.setCampaignCode("twtt10570000");
		requestQuoteModel.setSiteName("website");
		
		super.reportLog("Navigate Contact a Dealer menu from shopping tool menu");		
		homePage.goToContactDealerPage();

		requestAQuotePage.fillRequestQuoteDetail(requestQuoteModel);
		requestQuoteModel.setVendorName(requestAQuotePage.getVendorName());

		requestQuoteModel.setDealerCode(requestAQuotePage.dealerCode());
		reportLog("fill request detail " + requestQuoteModel.toString());

		requestAQuotePage.clickSubmitButton();
		reportLog("click on submit button");

		String message = requestAQuotePage.getMessage();
		Assert.assertEquals(message.trim(), GlobalPagesConstant.RequestSent);
		reportLog("Verify message request send successfully");

		String raqConfirmationMessage = requestAQuotePage.getConfirmationThankYouMessage();
		String expectedThanksMsg = "";
		if(requestQuoteModel.getSeriesName() != null && requestQuoteModel.getSeriesName() != "")
			expectedThanksMsg = GlobalPagesConstant.ThankYouConfirmation.toLowerCase() + " "
					+ requestQuoteModel.getSeriesName().toLowerCase();
		else
			expectedThanksMsg = GlobalPagesConstant.ThankYouConfirShortMsg.toLowerCase();
		Assert.assertTrue(
				raqConfirmationMessage.trim().toLowerCase().contains(expectedThanksMsg), "Thank you message Verification failed");				
		reportLog("Verify message Thank you! A dealer will contact you soon with a quote on your new Vehicle.");

		requestAQuotePage.verifyButtons();
		reportLog("Verify 'local special ' and 'Build your own' buttons");

		Read_XLS.writeContactListExcel(requestQuoteModel,  GlobalConstant.US);
		
	}
}