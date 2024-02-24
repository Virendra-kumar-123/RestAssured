package testBasicsRestAssured;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.Assert;


public class PUTDemo {
	public static void main(String[] args) {
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		String putResponce = given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
							.body("{\r\n"
									+ "\"place_id\":\"63393a3b9929dc7a4ec73e6dbf695d26\",\r\n"
									+ "\"address\":\"170 Summer walk, USA\",\r\n"
									+ "\"key\":\"qaclick123\"\r\n"
									+ "}")
							.when().put("https://rahulshettyacademy.com/maps/api/place/update/json")
							.then().log().all().assertThat().statusCode(200)
							.body("msg", equalTo("Address successfully updated"))
							.header("Server", "Apache/2.4.52 (Ubuntu)")
							.header("Access-Control-Allow-Methods", "POST")
							.extract().response().asString();
		
		JsonPath js = new JsonPath(putResponce);
		String msg_actual = js.getString("msg");
		String msg_expected = "Address successfully updated";
		
		
		
		Assert.assertEquals(msg_actual, msg_expected, "Both the messages are different");
		System.out.println("msg : "+js.getString("msg"));

	}
}
