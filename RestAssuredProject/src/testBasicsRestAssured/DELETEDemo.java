package testBasicsRestAssured;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.Assert;

public class DELETEDemo {
	public static void main(String[] args) {
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		String deleteResponce = given().log().all().queryParam("key", "qaclick123")
								.header("Content-Type", "application/json")
								.body("{\r\n"
										+ "    \"place_id\":\"63393a3b9929dc7a4ec73e6dbf695d26\"\r\n"
										+ "}")
								.when().delete("/maps/api/place/delete")
								.then().log().all().assertThat().statusCode(200)
								.body("status", equalTo("OK"))
								.header("server", "Apache/2.4.52 (Ubuntu)")
								.header("Access-Control-Allow-Methods", "POST")
								.extract().response().asString();
		
		JsonPath js = new JsonPath(deleteResponce);
		String status_actual = js.getString("status");
		String status_expected = "OK";
		
		
		Assert.assertEquals(status_actual, status_expected, "Both the statuses are different");
		System.out.println("status : "+js.getString("status"));
		
		
	}

}
