package Day1;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;

import org.testng.annotations.Test;

public class HttpsMethodsDemo {

    @Test
    public void getTest() {

        //baseURI = "https://reqres.in/api";

        given()

        

        .when()

        .get("https://jsonplaceholder.typicode.com/users")

        .then()

        .statusCode(200)

        //.body("id", equalTo(1))

        //.body("name", equalTo("Leanne Graham"))
        
        .header("Content-Type", containsString("application/json"))

        .log().all();

    }
    
    @Test
    public void getTest1() {

        //baseURI = "https://reqres.in/api";

        given()

        

        .when()

        .get("https://jsonplaceholder.typicode.com/users/1")

        .then()

        .statusCode(200)

        .body("id", equalTo(1))

        .body("name", equalTo("Leanne Graham"))
        
        .header("Content-Type", containsString("application/json"))

        .log().all();

    }
    
    @Test
    
    public void createUser()
    {
    	HashMap<String, String> data=new HashMap<String, String>();
    	data.put("name","shubhi");
    	data.put("email","Rna@gmail.com");
    	given()
    	.contentType("application/json")
    	.body(data)
    	.when()
    	.post("https://jsonplaceholder.typicode.com/users")
    	.then()
    	.statusCode(201)
    	.body("name", equalTo("shubhi"))
    	.body("email", equalTo("Rna@gmail.com"))
    	.header("Content-Type", containsString("application/json"))
    	//.header("Content-Type",equalTo("application/json; charset=utf-8"))
    	.log().all();
    	
    	
    	
    }
    


}