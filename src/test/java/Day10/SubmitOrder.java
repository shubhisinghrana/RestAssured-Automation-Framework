package Day10;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;

import org.json.JSONObject;
//import org.testng.annotations.Test;

public class SubmitOrder {

	 private static final String AUTH_TOKEN = "Bearer 4bd3dfb0613ac51b1411f3afe54e82ca04071bfaad14a56df982b13fa1909408";
	 private static final String BASE_URL = "https://simple-books-api.glitch.me/orders";
	 
	 @Test
	 void testSubmitAndDeleteOrder()
	 {
		 //Submitting order
		 JSONObject requestBody=new JSONObject();
		 requestBody.put("bookId", 1);
		 requestBody.put("customerName", "John");
		 
		  String orderId=given()
		  	.contentType("application/json")
		  	.header("Authorization",AUTH_TOKEN)
		  	.body(requestBody.toString())
		  	
		 .when()
		 	.post(BASE_URL)
		 .then()
		 	.statusCode(201)
		 	.log().body()
		 	.extract().jsonPath().getString("orderId");
		  
		  //Deleting order
		  given()
		  	.header("Authorization",AUTH_TOKEN)
		  	.pathParam("orderId", orderId)
		  	
		  .when()
		  		.delete(BASE_URL+"/{orderId}")
		  .then()
		  	.statusCode(204);
	 }
	    
	
}