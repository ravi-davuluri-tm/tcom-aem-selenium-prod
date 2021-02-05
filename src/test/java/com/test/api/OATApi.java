package com.test.api;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.Test;

import com.toy.selenium.core.BaseAPI;

import io.restassured.response.Response;

public class OATApi extends BaseAPI {
	

		@Test // Hawaii
		public void OATApiTest() {

			//Response response = given().get("https://staging.ws.public.oatms.tms.aws.toyota.com/v1/marketing/offers/21117?clientId=139359c778bd4859a4b4266365158927&zipCode=21117&lang=en&series=prius&year=2020&key=C2602ACDAB21FE68546E47BD9EFA85D2\n" + 
			//		"");
		
			Response response = given().get("/v1/marketing/offers/21117?clientId=d67aed0369c24a2c811028ae53b961b9&zipCode=21117&lang=en&series=prius&year=2020&key=C2602ACDAB21FE68546E47BD9EFA85D2\n" + 
					"");
			reportLog("below is reponse detail :");
			reportLog(response.asString());

			response.then().statusCode(200);
			reportLog("Verified response code 200");
			response.then().contentType("application/json");

			System.out.println("body: " + response.body().asString());


		}
}
