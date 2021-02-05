package com.test.us.functional;

import java.util.Collections;
import java.util.List;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.toy.constant.GlobalPagesConstant;
import com.toy.datamodel.RequestQuoteModel;
import com.toy.pages.HomePage;
import com.toy.pages.SearchInventoryPage;
import com.toy.selenium.core.BaseTest;

public class SearchInventory extends BaseTest {
	
	private SearchInventoryPage searchInventoryPage ;
	String vehicleName = "2020 Corolla";
	String zipCode = "75010";
	
	@BeforeMethod
	public void setupSearhIventory() {
						
		HomePage homePage = PageFactory.initElements(getWebDriver(),
				HomePage.class);				
		String newUrl = getWebDriver().getCurrentUrl() + "?zipcode="+zipCode;
		getWebDriver().navigate().to(newUrl);
		super.reportLog("Navigate Url with zip code "+zipCode);		
		
		homePage.selectShoppingToolMenu();	
		searchInventoryPage = homePage.gotoSearchInventory();
		super.reportLog("click on shopping tool menu and navigate search inventory page");
		
		RequestQuoteModel raqModel = new RequestQuoteModel();
		raqModel.setSeriesName(vehicleName);
		
		searchInventoryPage.selectCarBySeries(raqModel);
		super.reportLog("Select first vehicle");
	}
	
	@Test 
	public void SortPriceTest() {	
		
		List<Integer> priceBeforeFilter = searchInventoryPage.getAllVehiclePrice();
		super.reportLog("Get all vehicles price before apply filter");
		
		searchInventoryPage.filterPriceByValue(GlobalPagesConstant.PriceHighToLow);
		super.reportLog("Select price "+GlobalPagesConstant.PriceHighToLow);
		
		List<Integer> priceAfterFilter = searchInventoryPage.getAllVehiclePrice();
		super.reportLog("Get all vehicles price after apply filter");
		
		Assert.assertNotEquals(priceBeforeFilter, priceAfterFilter);
		super.reportLog("Verify before sorted prices are not equal with after apply sorting");
		
		List<Integer> prices = priceAfterFilter;
		Collections.sort(priceAfterFilter, Collections.reverseOrder());		
		Assert.assertEquals(prices, priceAfterFilter);
		super.reportLog("Verify prices are sorted in decending order");
		
		searchInventoryPage.filterPriceByValue(GlobalPagesConstant.PriceLowToHigh);
		super.reportLog("Select price "+GlobalPagesConstant.PriceLowToHigh);
		
		List<Integer> priceAfterLowToHighFilter = searchInventoryPage.getAllVehiclePrice();
		prices = priceAfterLowToHighFilter;
		super.reportLog("Get all vehicles price after apply filter "+GlobalPagesConstant.PriceLowToHigh);
		
		Collections.sort(priceAfterFilter, Collections.reverseOrder());
		Assert.assertEquals(prices, priceAfterLowToHighFilter);
		super.reportLog("Verify prices are sorted in acending order");
	}

	@Test
	public void FilterByModelEngineColorTest() {
		
		String filterValue = searchInventoryPage.selectFirstModelFilter();
		super.reportLog("select model filter and get model " +filterValue);
		
		List<String> models = searchInventoryPage.getListModel();
		super.reportLog("get all vehicle model name");
	
		for(String model : models) {
			Assert.assertEquals(model.trim(), filterValue.trim());
		}
		super.reportLog("Verify all model is equal to "+filterValue);
	
		searchInventoryPage.clearFilter();
		super.reportLog("clear filter");
		
		String filterEngineValue = searchInventoryPage.selectFirstEngineFilter();
		super.reportLog("select engine transmission filter and get engine " +filterEngineValue);
		
		List<String> engines = searchInventoryPage.getListEngineTransmission();
		super.reportLog("get all vehicle engine transmission name");
	
		for(String engine : engines) {
			Assert.assertTrue(engine.trim().contains(filterEngineValue.trim()) , " expected engine transmission name "
					+engine+" not contains "+filterEngineValue.trim());
		}
		super.reportLog("Verify all engine transmission is equal to "+filterEngineValue);
		
		searchInventoryPage.clearFilter();
		super.reportLog("clear filter");
		
		String extColor = searchInventoryPage.selectExtColorFilter();
		searchInventoryPage.sleepInSecond(4);
		super.reportLog("select exterior color filter and get color " +extColor);
		
		List<String> extColors = searchInventoryPage.getListofExtColor();
		super.reportLog("get all vehicle exterior color name");
	
		for(String color : extColors) {
			Assert.assertEquals(color.trim(), extColor.trim());
		}
		super.reportLog("Verify all exterior color equal to "+extColors);
		
		searchInventoryPage.clearFilter();
		super.reportLog("clear filter");
		
		String intColor = searchInventoryPage.selectIntColorFilter();
		searchInventoryPage.sleepInSecond(4);
		super.reportLog("select engine interior color filter and get interior " +intColor);
		
		List<String> intColors = searchInventoryPage.getListofIntColor();
		super.reportLog("get all vehicle interior color name");
	
		for(String color : intColors) {
			Assert.assertEquals(color.trim(), intColor.trim());
		}
		super.reportLog("Verify all interior color is equal to "+intColor);		
	}
	
	
	@Test 
	public void testFilterAccessories() {				
		searchInventoryPage.clickAccessoryFilterPlusButton();
		super.reportLog("Expand accessories button");
		
		String selectedAccessory = searchInventoryPage.selectAccessoryFilter();
		super.reportLog("Selected first accessory from left side filder "+selectedAccessory);
		
		List<String> accessories = searchInventoryPage.getListOfVehicleAccessories();
		super.reportLog("Get all accessories name from vehicle list");
		
		for(String accessory : accessories ) {
			Assert.assertTrue(accessory.contains(selectedAccessory), "Selected accessory "+selectedAccessory+ " not found in "+accessory);
		}
		super.reportLog("Verify vehicle contains selected accessory");		
	}

	@Test 
	public void testVerifySpecialOffer() {				
		searchInventoryPage.viewOffer();
		super.reportLog("Click on view offer");
		
		searchInventoryPage.verifyModelDialogTitle("Regional Offer Details");
		super.reportLog("Verify offer modal dialog opend");
	}
}