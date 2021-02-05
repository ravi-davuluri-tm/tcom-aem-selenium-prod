package com.test.api;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import com.toy.utilities.JsonFileUtil;
import com.toy.selenium.core.BaseAPI;
import io.restassured.response.Response;

public class HandraiserAPI extends BaseAPI {
		
		@Test
		public void handraiserAPITest() throws Exception{	
			JSONObject jsonData = (JSONObject) JsonFileUtil.readJsonData("data/APIJSON", "Handraiser");
			System.out.println(jsonData);
			Response response = given().body(jsonData).post("/handraiser/tcom");
			reportLog("Below is response detail :");
			reportLog(response.asString());

			response.then().statusCode(200);
			reportLog("Verified response code 200");		
			response.then().contentType("application/json");
			
			System.out.println(response.asString());
			response.then().body("success", equalTo(true))
				.body("acxiom.activityTypeCode", equalTo("leadsAndHandRaisers"))
				//.body("acxiom.systemUserID", equalTo("toyotavortexprod"))
				.body("acxiom.status", equalTo(201))
				.body("acxiom.appName", equalTo("TCOM"));
			reportLog("Verified json data for success, acxiom.activityTypeCode, acxiom.systemUserID, acxiom.status and acxiom.appName");
		}
}