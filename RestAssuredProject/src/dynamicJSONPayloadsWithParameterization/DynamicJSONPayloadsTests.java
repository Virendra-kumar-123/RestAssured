package dynamicJSONPayloadsWithParameterization;

import static org.hamcrest.Matchers.equalTo;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

public class DynamicJSONPayloadsTests {

/* 
	Advance payloads creation stretagy : Playing with dynamic JSON payloads

	1: Dynamics build JSON payloads with external data inputs
	2: Parameterizing the API tests with multiple data sets
	3: How to send static JSON files (paylods) directly into the POST methods in RestAssured
	4: Feed JSON payloads From excel using HashMaps
	
*/
	
	
	@Test(priority=0)
	public void testDynamicBuildJSON() {
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		String postResponce = given().log().all().queryParam("key", "qaclick123")
												.header("Content-Type", "application/json")
												.body(TestDataJSON.getJSONData("Viren", "3339"))
							.when().post("maps/api/place/add/json")
							.then().log().all().assertThat().statusCode(200).extract().response().asString();
		JsonPath js = new JsonPath(postResponce);
		System.out.println("place_id : "+js.get("place_id"));
	}
	
	
	@Test(priority=1, dataProvider="provideTestData")
	public void testDynamicBuildJSONDataprovider(String name, String add) {
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		String postResponce = given().log().all().queryParam("key", "qaclick123")
												.header("Content-Type", "application/json")
												.body(TestDataJSON.getJSONData(name, add))
							.when().post("maps/api/place/add/json")
							.then().log().all().assertThat().statusCode(200).extract().response().asString();
		JsonPath js = new JsonPath(postResponce);
		System.out.println("place_id : "+js.get("place_id"));
	}
	
	
	@DataProvider(name= "provideTestData")
	public String[][] provideData() {
		return new String[][] {{"Virendraa", "42"},{"Virend", "391"},{"Virendr", "239"}};
	}
	
	
	
	@Test(priority=2)
	public void testDynamicBuildJSONUsingJSONFiles() throws IOException {
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		String postResponce = given().log().all().queryParam("key", "qaclick123")
												.header("Content-Type", "application/json")
												.body(new String(Files.readAllBytes(Paths.get("D:\\Users\\vumarvaishy\\eclipse-workspace\\RestAssuredProject\\src\\dynamicJSONPayloadsWithParameterization\\testData.json"))))
							.when().post("maps/api/place/add/json")
							.then().log().all().assertThat().statusCode(200).extract().response().asString();
		JsonPath js = new JsonPath(postResponce);
		System.out.println("place_id : "+js.get("place_id"));
	}
	
}
