package Day2;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;

import org.json.JSONObject;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
public class PostRequestBodyExamples {
	String Studentid;
	//@Test
	public void postTest()
	{
		HashMap <String , Object> hp=new HashMap<>();
		hp.put("name","Abhi" );
		hp.put("location","banglore");
		hp.put("phone", "876543");
		String[] courses= {"Java","c","c++"};
		hp.put("courses",courses);
	Studentid=given()
	 .contentType("application/json")
	 .body(hp)
	.when()
	 .post("http://localhost:3000/students")
	.then()
	  .statusCode(201)
	  .body("name", equalTo("Abhi"))
	  .log().body()
	  .extract().jsonPath().getString("id");
	  System.out.println("Student id is "+Studentid);
	}
	

		// TODO Auto-generated method stub
	//create post Request body using org.json library
	//@Test
	public void postTestOrg() 
	{
		JSONObject data=new JSONObject();
		data.put("name","Abhi" );
		data.put("location","banglore");
		data.put("phone", "876543");
		String[] courses= {"Java","c","c++"};
		data.put("courses",courses);
		//data.put("")
		Studentid=given()
				 .contentType("application/json")
				 .body(data.toString())
				.when()
				 .post("http://localhost:3000/students")
				.then()
				  .statusCode(201)
				  .body("name", equalTo("Abhi"))
				  .log().body()
				  .extract().jsonPath().getString("id");
				  System.out.println("Student id is "+Studentid);
		
	}
	
	@Test
	public void PojoTest()
	{
		Pojo dt=new Pojo();
		dt.setName("Shubhi");
		dt.setLocation("Ranchi");
		dt.setPhone("34567123");
		String courses[]= {"c","c++","java"};
		dt.setCourses(courses);
		
		Studentid=given()
				 .contentType("application/json")
				 .body(dt)
				.when()
				 .post("http://localhost:3000/students")
				.then()
				  .statusCode(201)
				  .body("name", equalTo(dt.getName()))
				  .log().body()
				  .extract().jsonPath().getString("id");
				  System.out.println("Student id is "+Studentid);
		
	}


	@AfterMethod()
	public void deleteTest()
	{
		given()
		.when()
		    .delete("http://localhost:3000/students/"+Studentid)
		.then()
		   .statusCode(200);
	}

}
