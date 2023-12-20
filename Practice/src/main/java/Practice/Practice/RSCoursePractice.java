package Practice.Practice;

import org.testng.annotations.Test;

import Files.Payload;
import io.restassured.path.json.JsonPath;
import junit.framework.Assert;

public class RSCoursePractice 
{

	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
		
		JsonPath js=new JsonPath(Payload.CourcePrice());
		
		//Number of courses returned by API
		
		int count = js.getInt("courses.size()");
		System.out.println("Number of courses is : "+count);
		System.out.println("---------------------------------------------------------------------");
		
		//Print Purchase Amount
		
		int purchaseamount=js.getInt("dashboard.purchaseAmount");
		System.out.println("Purchase AMount is : "+purchaseamount);
		System.out.println("---------------------------------------------------------------------");
		
		//Print Title of the first course
		
		String firstcourse=js.getString("courses.title[2]");
		System.out.println("Course Title is : "+firstcourse);
		System.out.println("---------------------------------------------------------------------");
		
		//Print All course titles and their respective Prices - Day 28
		
		for(int i=0;i<count;i++)
		{
			String coursetitle=js.get("courses.title["+i+"]");
			System.out.println("Course Title is : "+coursetitle);
			
			System.out.println("Course Price is : "+js.get("courses.price["+i+"]").toString());
			//js.get("courses.price["+i+"]");
			
			
			//System.out.println("Course Price is : "+courseprice);
		}
		System.out.println("---------------------------------------------------------------------");
		
		//Print no of copies sold by RPA Course - Day 29
		
		for(int i=0;i<count;i++)
		{
			String courseTitle=js.get("courses["+i+"].title");
			System.out.println(courseTitle);
			if(courseTitle.equalsIgnoreCase("Cypress"))
			{
				int copies=js.get("courses["+i+"].copies");
				System.out.println("Number of copies are : "+copies);
				break;
			}
		}
	System.out.println("---------------------------------------------------------------------");
		
		//Verify if Sum of all Course prices matches with Purchase Amount - Day 30

	}

}
