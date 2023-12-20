package Practice.Practice;

import io.restassured.RestAssured;
import io.restassured.config.SSLConfig;
import io.restassured.response.Response;
import junit.framework.Assert;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

import Files.Payload;

public class basicTest {

	
	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
		
		/*	
		Response resp=RestAssured.get("https://api-demo.phyon.crif.com/auth");
		
		String data=resp.asString();
		
		System.out.println("Data is"+data);
		
		String resp=RestAssured.baseURI="https://api-demo.phyon.crif.com";
		given().log().all()
		.body(Payload.AddPlace())
		.when().post("/auth")
		.then().log().all().assertThat().statusCode(201);
		System.out.println("Data is"+resp);
		
	*/
		
		/*
		try 
		{
			RestAssured.config = RestAssured.config().sslConfig(new SSLConfig().allowAllHostnames().relaxedHTTPSValidation());
			String response = given().log().all()
	                .body(Payload.AddPlace())
	                .when().post()
	                .then().log().all().assertThat().statusCode(201)
	                .extract().response().asString();

					System.out.println("Response Body: " + response);

		}
		catch(Exception obj)
		{
			System.out.println(obj);
		}
		
		*/
				
		
		RestAssured.baseURI="https://reqres.in";
		given().log().all()
		.body(Payload.AddPlace())
		.when().post("/api/users")
		.then().log().all().assertThat().statusCode(201);
		
		
		
		/*
		RestAssured.baseURI="https://rahulshettyacademy.com";
		given().log().all().queryParam("key", "123").header("Content-Type","application/json")
		.body(Payload.AddPlace())
		.when().post("maps/api/place/add/json")
		.then().log().all().assertThat().statusCode(200);
		*/
	}

}
