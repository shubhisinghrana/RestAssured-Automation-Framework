package Day2;
import static io.restassured.RestAssured.*;
import org.testng.annotations.Test;



public class ParameterDemo {
	//@Test
	void Pathparams()
	{
		 given()
		      .pathParam("Country", "India")
		.when()
		     .get("https://restcountries.com/v3.1/name/{Country}")//URL: https://restcountries.com/v2/name/India
		.then()
		     .statusCode(200)
		     .log().body();
		     
	}
	@Test
	void queryParam()
	{
		given()
		     .queryParam("page",2)
		     .queryParam("id", 5)
		.when()
		     .get("https://reqres.in/api/users") //URL: https://reqres.in/api/users?page=2&id=5
		.then()
		   .statusCode(200)
		   .log().all();
		
	}
}

	
