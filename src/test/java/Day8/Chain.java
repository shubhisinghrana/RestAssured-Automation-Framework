package Day8;
import org.testng.annotations.*;
import static io.restassured.RestAssured.*;
import com.github.javafaker.*;
import org.json.JSONObject;
public class Chain {
	
	 final String Base_Url="https://gorest.co.in/public/v2/users";
	 final String Token_id="0bf8a2b07a068689155ae87870278fae789e11587b82b5b57591dbf433b52de2";
	 int userId;
	 
	 Faker faker = new Faker();
	 
	 @Test
	void createUser()
	{
		JSONObject data=new JSONObject();
		data.put("name", faker.name().fullName());
		data.put("gender","male");
		data.put("email", faker.internet().emailAddress());
		data.put("status","active");
		userId = given()
		   .contentType("application/json")
		   .header("Authorization","Bearer " + Token_id)
		   .body(data.toString())
		   
		.when()
		    .post(Base_Url)
		   
		.then()
		   .statusCode(201)
		   .extract().response().jsonPath().getInt("id");
		    
	}
	 @Test(dependsOnMethods= {"createUser"})
	 void getUser()
	 {
		 given()
		    .header("Authorization","Bearer " + Token_id)
		    .pathParam("id",userId)
		 .when()
		    .get(Base_Url+"/{id}")
		 .then()
		   .statusCode(200)
		   .log().body();
	 }
	
	 @Test(dependsOnMethods= {"getUser"})
	 void updateUser()
	 {
		 JSONObject data=new JSONObject();
		 data.put("name", faker.name().fullName());
		 data.put("gender","male");
		 data.put("email", faker.internet().emailAddress());
		 data.put("status", "inactive");
		 
		 given()
		    .contentType("application/json")
		    .header("Authorization","Bearer " + Token_id)
		    .pathParam("id", userId)
		    .body(data.toString())
		 .when()
		    .put(Base_Url+"/{id}")
		 .then()
		     .statusCode(200)
		     .log().body();
		 
	 }
	 
	 void deleteUser()
	 {
		 given()
		    .header("Authorization","Bearer " + Token_id)
		    .pathParam("id", userId)
		 .when()
		     .delete(Base_Url+"/{id}")
		 .then()
		      .statusCode(204);
	 }
	

}
