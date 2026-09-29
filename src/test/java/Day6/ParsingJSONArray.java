package Day6;
import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class ParsingJSONArray {
	@Test
	void JsonResBody()
	{
	
	Response response =given()
	    
	.when()
	   .get("http://localhost:3000/employees")
	.then()
	    .statusCode(200)
	    .extract().response();
	
	JsonPath jsonpath=new JsonPath(response.asString());
	//Get the size of the json array
			int employeeCount=jsonpath.getInt("size()");
			// Print all the details of the employee
			for(int i=0;i<employeeCount;i++)
			{
				String firstName=jsonpath.getString("["+i+"].first_name");
				String lastName=jsonpath.getString("["+i+"].last_name");
				String email=jsonpath.getString("["+i+"].email");
				String gender=jsonpath.getString("["+i+"].gender");
				System.out.println(firstName+" "+lastName+" "+email+" "+gender);
			}
			//Search for an employee name "Steve" in the list
			boolean status=false;
			for(int i=0;i<employeeCount;i++)
			{
				String Name=jsonpath.getString("["+i+"].first_name");
				if(Name.equals("Steve"))
				{
					status=true;
					break;
				}
			}
			assertThat(status,is(true));// Steven exists in the list or not
	}

}
