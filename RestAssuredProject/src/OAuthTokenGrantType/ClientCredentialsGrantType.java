package OAuthTokenGrantType;

import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;


public class ClientCredentialsGrantType {
	String grantToken = null;
/*  
	 Authorization server API grants the token on 3 ways  
	 
	1. Client Credentials [Client id , Client secrets]
	2. Password
	3. Authorization codes
	
*/
	
	@Test(priority = 0)
	public void clientCredentialsGrantToken()
	{
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		String response = given().log().all().formParam("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
											   .formParam("client_secret", "erZOWM9g3UtwNRj340YYaK_W")
											   .formParam("grant_type", "client_credentials")
											   .formParam("scope", "trust")
							.when().post("oauthapi/oauth2/resourceOwner/token")
							.then().log().all().assertThat().statusCode(200).extract().response().asString();
		
		JsonPath js = new JsonPath(response);
		grantToken = js.getString("access_token");
		System.out.println("grantToken : "+ grantToken);
	}
	
	
	@Test(priority=1)
	public void getCourseDetailsWithClientCredentialsOauths() {
		RestAssured.baseURI = "https://rahulshettyacademy.com";
						given().log().all().queryParam("access_token", grantToken)
						.when().get("oauthapi/getCourseDetails")
						.then().log().all();
	}
}
