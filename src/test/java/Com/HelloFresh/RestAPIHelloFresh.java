package Com.HelloFresh;


import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import io.restassured.http.ContentType;
import io.restassured.response.Response;


public class RestAPIHelloFresh 
{
   //Get all countries and validate that US, DE and GB were returned in the response
   @Parameters("url")
   @Test(priority=0)
   public void testpresenceofUSDEGB(String url) {
		given().
		    get(url).
		then().
		body("RestResponse.result.alpha2_code", hasItems("US","DE","GB")).
		statusCode(200).
		log().
		all();	
    }
	
   //Get the country (US) individually and validate the response
   @Parameters("urlUS")
   @Test(priority=1)
	public void testUSA(String urlUS) {
	   
		given().
		    get(urlUS).
		then().
		body("RestResponse.result.alpha3_code", equalTo("USA")). 
		statusCode(200).
		log().
		all();
	}
	
    //Get the country (DE) individually and validate the response
   @Parameters("urlDE")
    @Test(priority=2)
    public void testDE(String urlDE) {
		given().
		    get(urlDE).
		then().
		body("RestResponse.result.name", equalTo("Germany")).
		statusCode(200).
		log().
		all();
	}
	
    //Get the country (GB) individually and validate the response
    @Parameters("urlgb")
    @Test(priority=3)
    public void testGB(String urlgb) {
		given().
		    get(urlgb).
		then().
		body("RestResponse.result.alpha2_code", equalTo("GB")).
		statusCode(200).
		log().
		all();
	}

    //Try to get information for inexistent countries and validate the response
  /*  @Parameters("url")
      @Test(priority=4)
     public void testInvalidCountries(String url) {
 	     given().
         get(url).
         then().
         statusCode(200).
         body("RestResponse.result.name", hasItems("Tibet","West Indies")).
         log(). 
         all();       
     }		*/
    
   // This test is to add new country in the List of all countries
   //POST method to validate new country addition
     @Parameters("url")
     @Test(priority=5)
     public void testForPost(String url) {
    	Response resp = given().
         body("  {\"name\":\"Tibet\","
        		 + " \"alpha2_code\":\"TB\","
        		 + " \"alpha3_code\":\"TBT\" }  ").
         when().
         contentType(ContentType.JSON).
         post(url);
   
    	 System.out.println("Get Response is:" +resp.asString());
     }		   
}
     

    