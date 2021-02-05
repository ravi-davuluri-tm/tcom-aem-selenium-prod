package com.toy.api.functions;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.toy.selenium.core.Configuration;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class APIFunctions {

	public static List<String> getPMADealers(String zipcode) throws Exception {
		RestAssured.baseURI = Configuration.readApplicationFile("URL");
		Response response = given().get("/ToyotaSite/rest/dealerLocator/locateDealers?brandId=1&zipCode="+zipcode);
		response.then().statusCode(200);
		response.then().contentType("application/json");
		response.then().body("success", equalTo(true));
		response.then().body("searchZipCode", equalTo(zipcode));
		String jsson = response.asString();
		JSONParser parser = new JSONParser();
		JSONObject jsonObject = (JSONObject) parser.parse(jsson);
		JSONArray dealers = (JSONArray) jsonObject.get("dealers");
		System.out.println(dealers.size());
		List<String> dealerName = new ArrayList<String>();
		
		for(int i =0; i<dealers.size(); i++) {
			JSONObject dealer = (JSONObject) dealers.get(i);				
			if(dealer.get("pma").toString() == "true") {					
				dealerName.add(dealer.get("name").toString());
			}
		}
		return dealerName;		
	}
}