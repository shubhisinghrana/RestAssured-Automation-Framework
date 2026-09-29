package Day8;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;

import org.json.JSONObject;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;


public class ChainingAPIs {
	
	static final String BASE_URL = "https://gorest.co.in/public/v2/users";
	static final String BEARER_TOKEN = "0bf8a2b07a068689155ae87870278fae789e11587b82b5b57591dbf433b52de2";
	int userId;
	
	Faker faker=new Faker();
	
	@Test
	void createUser()
	{
		JSONObject requestData=new JSONObject();
		requestData.put("name",faker.name().fullName());
		requestData.put("gender","Male");
		requestData.put("email",faker.internet().emailAddress());
		requestData.put("status","inactive");
		
				
		userId=given()
			.headers("Authorization", "Bearer "+BEARER_TOKEN)
			.contentType("application/json")
			.body(requestData.toString())
		.when()
			.post(BASE_URL)
		.then()
			.statusCode(201)
			.extract().response().jsonPath().getInt("id");
	}
		
	@Test(dependsOnMethods={"createUser"})
	void getUser()
	{
		given()
			.headers("Authorization", "Bearer "+BEARER_TOKEN)
			.pathParam("id", userId)
		.when()
			.get(BASE_URL+"/{id}")
		.then()
			.statusCode(200)
			.log().body();
	}
	
	@Test(dependsOnMethods= {"getUser"})
	void updateUser()
	{
		JSONObject requestData=new JSONObject();
		requestData.put("name",faker.name().fullName());
		requestData.put("gender","Male");
		requestData.put("email",faker.internet().emailAddress());
		requestData.put("status","active");
		
		given()
			.headers("Authorization", "Bearer "+BEARER_TOKEN)
			.contentType("application/json")
			.body(requestData.toString())
			.pathParam("id", userId)
		.when()
			.put(BASE_URL+"/{id}")
		.then()
			.statusCode(200)
			.log().body();
			
	}
	
	@Test(dependsOnMethods= {"updateUser"})
	void deleteUser()
	{
		given()
			.headers("Authorization", "Bearer "+BEARER_TOKEN)
			.pathParam("id", userId)
		.when()
			.delete(BASE_URL+"/{id}")
		.then()
		.statusCode(204);
	}
	
	
}

