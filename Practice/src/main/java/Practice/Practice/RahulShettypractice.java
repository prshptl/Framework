package Practice.Practice;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import io.restassured.response.Response;
import junit.framework.Assert;

import static org.hamcrest.Matchers.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import Files.Payload;

public class RahulShettypractice {

	public static void main(String[] args) throws IOException 
	{
		// TODO Auto-generated method stub
		// To Add location********************************************************
		
		RestAssured.baseURI="https://rahulshettyacademy.com";
		
		//------------------------------------------------------------------------------
		
		String response=given().log().all().queryParam("key", "qaclick123")
		.body(new String(Files.readAllBytes(Paths.get("C:\\Users\\inb252parpat\\Downloads\\Payload.json"))))
		.when().post("/maps/api/place/add/json")
		.then().assertThat().statusCode(200)
		.extract().response().asString();
		
		System.out.println(response);
		
		JsonPath js=new JsonPath(response);
		String placeId=js.getString("place_id");
		System.out.println("Place ID is: "+placeId);
		 
		//To update location**********************************************************************
		
		String newaddress="269 New Sangvi, India";
		
		given().log().all().queryParam("key", "qaclick123")
		.body("{\r\n"
	            + "\"place_id\":\"" + placeId + "\",\r\n"
	            + "\"address\":\""+newaddress+"\",\r\n"
	            + "\"key\":\"qaclick123\"\r\n"
	            + "}")
		.when().put("maps/api/place/update/json")
		.then().assertThat().log().all().statusCode(200).body("msg", equalTo("Address successfully updated"));
		
		
	//	JsonPath js1=new JsonPath(update);
	//	String updated=js1.getString("msg");
	//	System.out.println(update);
		
		//To Get location details *************************************************************************
		
		Response getplace=given().log().all().queryParam("key", "qaclick123")
		.queryParam("place_id", placeId)
		.when().get("maps/api/place/get/json")
		.then().assertThat().log().all().statusCode(200).extract().response();
		
		//System.out.println(getplace);

		JsonPath js2= getplace.jsonPath();
		String actualaddress=js2.getString("address");
		System.out.println("Actual Address Is:"+actualaddress);
		Assert.assertEquals(actualaddress, newaddress);
				
	}

}
