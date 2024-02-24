package POJOClasses2;

import java.util.ArrayList;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;

public class POJOForReq {
	
	/*
	{
  		"location": {
						"lat": -38.383494,
					    "lng": 33.427362
  					},
	  "accuracy": 521210,
	  "name": 	"VVViren Kumarss",
	  "phone_number": "(+91) 983 893 3937",
	  "address": "290, side layout, cohen 09",
	  "types": [
			    "shoe park",
			    "shop"
			  ],
	  "website": "http://virendra.com",
	  "language": "French-IN"
	}
*/
	
	@Test()
	public void reqWithPOJOData() {
		ArrayList<String> types = new ArrayList<>();
		types.add("shoe park");
		types.add("shop");
		Location l = new Location();
		l.setLat("-38.383494");
		l.setLng("33.427362");
		
		
		SendResAsPOJO srap = new SendResAsPOJO();
		srap.setLocation(l);
		srap.setAccuracy(521210);
		srap.setName("VVViren Kumarss");
		srap.setPhone_number("(+91) 983 893 3937");
		srap.setAddress("290, side layout, cohen 09");
		srap.setTypes(types);
		srap.setWebsite("http://virendra.com");		
		srap.setLanguage("French-IN");
		
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		String response = given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
							.body(srap)
							.when().post("maps/api/place/add/json")
							.then().log().all().assertThat().statusCode(200)
							.body("scope", equalTo("APP"))
							.body("status", equalTo("OK")).header("Server", "Apache/2.4.52 (Ubuntu)")
							.header("Access-Control-Allow-Methods", "POST")
							.extract().response().asString();

	}
	
}
