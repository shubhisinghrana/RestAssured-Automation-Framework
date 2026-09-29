package Day10;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import org.json.JSONObject;

public class Token {

    @Test
    void createNewToken() {
        JSONObject body = new JSONObject();
        body.put("clientName", "Postman");
        body.put("clientEmail", "testuser_" + System.currentTimeMillis() + "shubhi125678@gmail.com"); // Guarantees a unique email

        String token = given()
            .contentType("application/json")
            .body(body.toString())
        .when()
            .post("https://simple-books-api.glitch.me/api-clients")
        .then()
            .statusCode(201)
            .extract().jsonPath().getString("accessToken");

        System.out.println("Your New Token: Bearer " + token);
    }
}
