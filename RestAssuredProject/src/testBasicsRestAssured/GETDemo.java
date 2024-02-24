package testBasicsRestAssured;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.Assert;


public class GETDemo {
	public static void main(String[] args) {
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		String getResponce = given().log().all().queryParam("key", "qaclick123")
								.queryParam("place_id", "63393a3b9929dc7a4ec73e6dbf695d26")
								.when().get("https://rahulshettyacademy.com/maps/api/place/get/json")
								.then().log().all().assertThat().statusCode(200)
								.body("location.latitude", equalTo("-38.383494"))
								.body("location.longitude", equalTo("33.427362"))
								.body("accuracy", equalTo("50"))
								.header("Server", "Apache/2.4.52 (Ubuntu)")
								.header("Access-Control-Allow-Methods", "POST")
								.extract().response().asString();
		
		
		JsonPath js = new JsonPath(getResponce);
		String accuracy_actual = js.getString("accuracy");
		String accuracy_expected = "50";
		
		
		Assert.assertEquals(accuracy_actual, accuracy_expected, "Both the accuracies are different");
		System.out.println("location.latitude : "+js.getString("location.latitude"));
		System.out.println("accuracy : "+js.getString("accuracy"));
		
	}
}
