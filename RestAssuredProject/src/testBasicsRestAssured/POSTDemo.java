package testBasicsRestAssured;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.Assert;


public class POSTDemo {
	public static void main(String[] args) {
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		String postResponce = given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
							.body("{\r\n"
								+ "  \"location\": {\r\n"
								+ "    \"lat\": -38.383494,\r\n"
								+ "    \"lng\": 33.427362\r\n"
								+ "  },\r\n"
								+ "  \"accuracy\": 50,\r\n"
								+ "  \"name\": \"Viren Kumar\",\r\n"
								+ "  \"phone_number\": \"(+91) 983 893 3937\",\r\n"
								+ "  \"address\": \"209, side layout, cohen 09\",\r\n"
								+ "  \"types\": [\r\n"
								+ "    \"shoe park\",\r\n"
								+ "    \"shop\"\r\n"
								+ "  ],\r\n"
								+ "  \"website\": \"http://virendra.com\",\r\n"
								+ "  \"language\": \"French-IN\"\r\n"
								+ "}")
							.when().post("maps/api/place/add/json")
							.then().log().all().assertThat().statusCode(200)
							.body("scope", equalTo("APP"))
							.body("status", equalTo("OK")).header("Server", "Apache/2.4.52 (Ubuntu)")
							.header("Access-Control-Allow-Methods", "POST")
							.extract().response().asString();
		
		JsonPath js = new JsonPath(postResponce);
		String scope_actual = js.getString("scope");
		String scope_expected = "APP";
		
		
		Assert.assertEquals(scope_actual, scope_expected, "Both the names are different");
		System.out.println("place_id : "+js.getString("place_id"));
		System.out.println("reference : "+js.getString("reference"));

	}
}
