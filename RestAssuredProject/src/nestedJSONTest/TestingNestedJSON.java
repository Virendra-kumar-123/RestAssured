package nestedJSONTest;

import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.path.json.JsonPath;


/*
 {
	"dashboard": {
		"purchaseAmount": 910,
		"website": "rahulshettyacademy.com"
		},
	"courses": [
		     {
		"title": "Selenium Python",
		"price": 50,
		"copies": 6
		 },
		    {
		"title": "Cypress",
		"price": 40,
		"copies": 4
		},
		   {
		"title": "RPA",
		"price": 45,
		"copies": 10
		}
	 ]
}

/*
1. Print No of courses returned by API
2. Print Purchase Amount
3. Print Title of the first course
4. Print All course titles and their respective Prices
5. Print no of copies sold by RPA Course
6. Verify if Sum of all Course prices matches with Purchase Amount
 */


public class TestingNestedJSON {	
	static JsonPath js = new JsonPath(TestDataResource.getNestedJSON());
	
	@Test(priority=0)
	public void numberOfCources() {
		System.out.println("No of courses returned by API :"+js.getInt("courses.size()"));
	}
	
	@Test(priority=1)
	public void purchaseAmount() {
		System.out.println("Purchase Amount :"+js.getInt("dashboard.purchaseAmount"));
	}
	
	@Test(priority=2)
	public void titleOfFirstCourse() {
		System.out.println("Title of the first course : "+js.getString("courses[0].title"));
	}
	
	@Test(priority=3)
	public void courseTitleAndAmount() {
		System.out.println("All course titles and their respective Prices : ");
		int size = js.getInt("courses.size()");
		for(int i=0; i<size;i++) {
			System.out.println(js.getString("courses["+i+"].title"));
			System.out.println(js.getInt("courses["+i+"].price"));
		}
	}
	
	@Test(priority=4)
	public void copiesSoldByRPA() {
		int count = js.getInt("courses.size()");
		System.out.println("No of copies of RPA : ");
		
		for(int i=0; i<count; i++) {
			String course = js.getString("courses["+i+"].title");
			if(course.equalsIgnoreCase("RPA")) {
				System.out.println(js.getInt("courses["+i+"].price"));
				break;
			}	
		}
	}

	@Test(priority=5)
	public void sumOfAllCoursesAndPurchaseAmountEquality() {
		System.out.println("Sum of all Course prices matches with Purchase Amount : ");
		int purchaseAmount = js.getInt("dashboard.purchaseAmount");
		int count = js.getInt("courses.size()");
		int sum = 0 ;
		
		for(int i=0; i<count; i++) {
			int price = js.getInt("courses["+i+"].price");
			int copies = js.getInt("courses["+i+"].copies");
			sum = sum + (price * copies);
		}
		Assert.assertEquals(purchaseAmount, sum, "Purchased amount is not equal to the selling amount");
	}
	
}
