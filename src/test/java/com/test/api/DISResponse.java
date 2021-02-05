package com.test.api;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.toy.selenium.core.BaseAPI;
import io.restassured.response.Response;

public class DISResponse extends BaseAPI {

	@Test // Hawaii
	public void DISresponseHawaiiTest() {

		Response response = given().get("/ToyotaSite/rest/dealerLocator/locateDealers?brandId=1&zipCode=96701");
		reportLog("below is reponse detail :");
		reportLog(response.asString());

		response.then().statusCode(200);
		reportLog("Verified response code 200");
		response.then().contentType("application/json");

		// System.out.println("body: " + response.body().asString());

		response.then().body("success", equalTo(true))
				.body("redirectUrl", equalTo("http://toyotahawaii.com/Distributor/Toyota/Dealerships/Default.aspx"))
				.body("redirectDisplayType", equalTo("popup")).body("generalMessage", equalTo("Redirect"))
				.body("totalDealer",equalTo(0));
		reportLog("Verified json data for success, redirectUrl, redirectDisplayType and generalMessage");

	}

	@Test(dataProvider = "zipcode-provider")
	public void DISresponseTest(String city, String zipcode) {
		Response response = given().get("/ToyotaSite/rest/dealerLocator/locateDealers?brandId=1&zipCode=" + zipcode);
		response.then().statusCode(200);
		response.then().contentType("application/json");
		System.out.println(response.asString());
		response.then().body("success", equalTo(true));
		response.then().body("searchZipCode", equalTo(zipcode));

	}

	@DataProvider(name = "zipcode-provider")
	public Object[][] getDataFromDataprovider() {
		return new Object[][] { { "Puerto Rico", "00901" }, { "Virgin Island", "00801" }, {"LA Region" , "90210"}, 
		{"S.F Region", "94115"},{"Portland Region", "97204"}, {"Denvor Region","80203"}, {"N.Y Region", "10010"}, {"Boston Region", "02108"},
		{"Chicago Region", "60608"},{"Cinninati Region", "45212"}, {"Kansas City Region", "64106"} , {"SouthEast Toyota", "30303"}, {"Gulf States", "78726"},
		{"Central Atlantic Toyota","23220"}
		};
	}
}

