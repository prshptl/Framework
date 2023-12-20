package Practice.Practice;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

import Files.Payload;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class Magenta {

	//private static String ;
	
	public static void main(String[] args) 
	{
	
	//	String bearerToken = authResponse.jsonPath().getString("access_token");
		
	//	System.out.println("Bearer Token: " + bearerToken);
		
	//	String bearerToken="eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6ImdYS2ZNcXZFejlMV2NxNFE5OEl3IiwicmVxdWVzdElkIjoiY2RjYTFmZjMtOTMyMS00NzA0LTgyNjktMWIxNDk4YmUxZjJlIiwiaWF0IjoxNzAxOTUzNzk0LCJleHAiOjE3MDE5NTczOTR9.ef28iSwZdU696ghDZCGGAtRQ5tggq5KruGdnDi0E8YU";
		
	//	String token = bearerToken;

		RestAssured.baseURI="https://api-demo.phyon.crif.com";
		
		String response = getTransactionID();
        System.out.println("Transaction ID: " + response);
        
        String result = getResult(response);
        System.out.println("Result: \n"+result);
				
	}
	
	private static String getResult(String response)
	{
		
		String requestbody="{\r\n"
				+ "    \"transactionId\": \""+response+"\"\r\n"
				+ "}";
		Response result=RestAssured.given()
				.auth().oauth2(getDynamicbearerToken())
				.contentType(ContentType.JSON)
				.body(requestbody)
				.post("/result");
		
		//String results = result.jsonPath().getString("");
		
		return result.jsonPath().getString("");
	}
	
	private static String getTransactionID()
	{
		String requestbody = "{\r\n"
				+ "\"firstName\": \"Yogesh\",\r\n"
				+ "\"lastName\": \"Patil\",\r\n"
				+ "\"birthday\": \"05.06.1992\",\r\n"
				+ "\"entityType\": \"consumer\",\r\n"
				+ "\"returnUrl\":\"https://www.crif.at/\",\r\n"
				+ "\"companyName\":\"Test\",\r\n"
				+ "\"reference\":\"12345\"\r\n"
				+ "}";
		
		
		Response response=RestAssured.given()
			//	.head(getDynamicbearerToken())
				.auth().oauth2(getDynamicbearerToken())
				.contentType(ContentType.JSON)
				.body(requestbody)
				.when().post("/registration");
		
		//String responsee = response.jsonPath().getString("transactionId");
		
		//System.out.println("Transaction ID: "+response.asString());
		
		//System.out.println("Transaction ID: "+responsee);
		return response.jsonPath().getString("transactionId");
	}
	
	
	private static String getDynamicbearerToken()
	{
	
		String requestbody = "{\r\n"
				+ "    \"client\": \"201300\",\r\n"
				+ "    \"apiSecret\": \"SlkDbjaj79cT\"\r\n"
				+ "}";
		
		Response authResponse=RestAssured.given()
				.contentType(ContentType.JSON)
				.body(requestbody)
				.post("/auth");
				
		
		int statusCode = authResponse.getStatusCode();
		//System.out.println("Status Code: " + statusCode);
				
		if (statusCode == 200) 
		{
		    String bearerToken = authResponse.jsonPath().getString("token");
		    System.out.println("Bearer Token: " + bearerToken);
		} 
		else 
		{
		    System.err.println("Authentication failed. Status code: " + statusCode);
		}
		return authResponse.jsonPath().getString("token");
	}

}
