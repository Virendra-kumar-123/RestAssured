package POJOClasses;

import static io.restassured.RestAssured.given;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class POJOForResponse {
	String grantToken = null;
	
	
	@Test(priority = 0)
	public void clientCredentialsGrantToken()
	{
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		String response = given().formParam("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
											   .formParam("client_secret", "erZOWM9g3UtwNRj340YYaK_W")
											   .formParam("grant_type", "client_credentials")
											   .formParam("scope", "trust")
							.when().post("oauthapi/oauth2/resourceOwner/token")
							.then().assertThat().statusCode(200).extract().response().asString();
		
		JsonPath js = new JsonPath(response);
		grantToken = js.getString("access_token");
		System.out.println("grantToken : "+ grantToken);
	}
	
	
	@Test(priority=1)
	public void getCourseDetailsWithClientCredentialsOauths() {
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		GetResponseAsPOJO pr = given().queryParam("access_token", grantToken)
							  .when().get("oauthapi/getCourseDetails")
							  .then().extract().response().as(GetResponseAsPOJO.class);
		
		
		System.out.println("instructor from POJO: "+pr.getInstructor());
		System.out.println("instructor from POJO: "+pr.getUrl());
		System.out.println("instructor from POJO: "+pr.getServices());
		System.out.println("instructor from POJO: "+pr.getExpertise());
		System.out.println("instructor from POJO: "+pr.getLinkedIn());
		
		String[] expectedString = {"Selenium Webdriver Java","Cypress","Protractor"};
		ArrayList<String> actualList = new ArrayList<String>();
		
		for(int i=0; i<pr.getCourses().getWebAutomation().size(); i++) {
			actualList.add(pr.getCourses().getWebAutomation().get(i).getCourseTitle());	
		}
		List<String> expectedList =Arrays.asList(expectedString);
		Assert.assertEquals(actualList, expectedList);
		
	}	
}
