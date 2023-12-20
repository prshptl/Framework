package Practice.Practice;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

import Files.Payload;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import junit.framework.Assert;

public class coursepractiveSumvalidation 
{
	@Test
	public void sumvalidation()
	{
		int sum=0;
		JsonPath js1=new JsonPath(Payload.CourcePrice());
		int count=js1.getInt("courses.size()");
		//System.out.println(count);
		for(int i=0;i<count;i++)
		{
			int price= js1.getInt("courses["+i+"].price");
			int copies=js1.getInt("courses["+i+"].copies");
			int Amount=price*copies;
			//System.out.println(Amount);
			sum = sum + Amount;
		}
		System.out.println("Total Amount is :"+sum);
		int PurchaseAmount=js1.getInt("dashboard.purchaseAmount");
		Assert.assertEquals(PurchaseAmount, sum);
		
	}
	
}
