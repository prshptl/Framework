package Practice.Practice;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Files.Payload;

public class BookTesting 
{
	//Adding book in database - Day 34,35
	@Test(dataProvider="BooksData")
	public void addbook(String isbn,String aisle)
	{
		RestAssured.baseURI="https://rahulshettyacademy.com";
		String response=given().log().all().header("Content-Type","application/json")
		.body(Payload.AddingBook(isbn,aisle))
		.when().post("/Library/Addbook.php")
		.then().log().all().assertThat().statusCode(200)
		.extract().response().asString();
		
		
		//System.out.println(response);
		
		JsonPath js=new JsonPath(response);
		String id=js.get("ID");
		System.out.println("ID Generated: "+id);
		
	}
	
	@Test(dataProvider="BooksData")
	public void deletebook(String isbn,String aisle)
	{
		RestAssured.baseURI="https://rahulshettyacademy.com";
		String resp=given().log().all().header("Content-Type","application/json")
		.body(Payload.AddingBook(isbn, aisle))
		.when().delete("/Library/Addbook.php")
		.then().log().all().assertThat().statusCode(200)
		.extract().response().asString();
		
		System.out.println(resp);
	}
	
	//Day 36
	@DataProvider(name="BooksData")
	public Object[][] getData()
	{
		return new Object[][] {{"abcde","12345"},{"defgh","45678"},{"ghijk","78901"}};
	}
}