package com.test.us.functional;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.toy.constant.GlobalPagesConstant;
import com.toy.datamodel.RequestQuoteModel;
import com.toy.pages.AccessoriesPage;
import com.toy.pages.BuildAndPricePage;
import com.toy.pages.HomePage;
import com.toy.selenium.core.BaseTest;

public class Accessories extends BaseTest {

	private AccessoriesPage accessoriesPage;
	private HomePage homePage;
	private BuildAndPricePage buildAndPricePage;

	String vehicleName = "2020 Yaris Hatchback";
	String zipCode = "75010";

	@Test
	public void VerifyAccessoriesButtonsTest() {
		homePage = PageFactory.initElements(getWebDriver(), HomePage.class);
		accessoriesPage = PageFactory.initElements(getWebDriver(), AccessoriesPage.class);
		buildAndPricePage = PageFactory.initElements(getWebDriver(), BuildAndPricePage.class);
				
		String newUrl = this.applicationUrl + "?zipcode=" + zipCode;
		getWebDriver().navigate().to(newUrl);
		super.reportLog("Navigate Url with zip code " + zipCode);

		homePage.clickOnFooterLink(GlobalPagesConstant.Accessories);
		super.reportLog("Click on link "+GlobalPagesConstant.Accessories);
		
		RequestQuoteModel raqModel = new RequestQuoteModel();
		raqModel.setSeriesName(vehicleName);
		
		accessoriesPage.selectAccessoriesOfVehicle(raqModel);
		super.reportLog("Selected accessories for model "+raqModel.getSeriesName());
		
		accessoriesPage.clickOnLinkHowToBuy();
		super.reportLog("Click on link how to buy");
		
		accessoriesPage.verfiyButtons();
		super.reportLog("Verify build, find a dealer and accessories buttons");
		
		accessoriesPage.clickOnBuildButton();
		super.reportLog("Click on Build button");
		
		String seriesName = buildAndPricePage.getSeriesTitleName();
		Assert.assertTrue(seriesName.contains(vehicleName) , "Build and price page not opened");
		super.reportLog("Verify Build and Price page opned");
		getWebDriver().navigate().back();
		
		accessoriesPage.clickOnFindADealerButton();
		super.reportLog("Click on Find a dealer button");
		
		accessoriesPage.sleepInSecond(3);
		String currentUrl = getWebDriver().getCurrentUrl();
		Assert.assertTrue(currentUrl.contains("dealers"), "Find a dealer page not opened");
		super.reportLog("Verify find a dealer page opned");		
	}

}