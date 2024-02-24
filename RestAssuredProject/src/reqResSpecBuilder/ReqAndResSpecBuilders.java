package reqResSpecBuilder;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
public class ReqAndResSpecBuilders {
	
	
	@Test()
	public void resResSpecBuilder() {
		RequestSpecification rs = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").addQueryParam("key", "qaclick123")
				.addHeader("Content-Type", "application/json")
				.setBody("{\r\n"
						+ "  \"location\": {\r\n"
						+ "    \"lat\": -38.383494,\r\n"
						+ "    \"lng\": 33.427362\r\n"
						+ "  },\r\n"
						+ "  \"accuracy\": 50,\r\n"
						+ "  \"name\": \"Viren Kumar\",\r\n"
						+ "  \"phone_number\": \"(+91) 983 893 3937\",\r\n"
						+ "  \"address\": \"20219, side layout, cohen 09\",\r\n"
						+ "  \"types\": [\r\n"
						+ "    \"shoe park\",\r\n"
						+ "    \"shop\"\r\n"
						+ "  ],\r\n"
						+ "  \"website\": \"http://virendra.com\",\r\n"
						+ "  \"language\": \"French-IN\"\r\n"
						+ "}")
				.build();
		ResponseSpecification rrs = new ResponseSpecBuilder().expectStatusCode(200).expectBody("scope", equalTo("APP"))
				.expectBody("status", equalTo("OK")).expectHeader("Server", "Apache/2.4.52 (Ubuntu)").build();
		
		

		String postResponce = given().log().all().spec(rs)
							.when().post("maps/api/place/add/json")
							.then().log().all().spec(rrs)
							.extract().response().asString();
		
		System.out.println("Response : "+postResponce);
	}
	
}
