package org.example.tests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class ApiTest {

    @Test
    public void testCreateUser() {
        // Setup base URL
        RestAssured.baseURI = "https://reqres.in/";

        // Create user data as JSON object
        String requestBody = "{\n" +
                "  \"name\": \"John Doe\",\n" +
                "  \"job\": \"Software Engineer\"\n" +
                "}";

        // Send POST request to create a user
        given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post("api/users")
                .then()
                .statusCode(201)  // Assert response status code is 201 (Created)
                .body("name", equalTo("John Doe"))  // Assert name is correct
                .body("job", equalTo("Software Engineer"))  // Assert job is correct
                .body("id", notNullValue());  // Assert id is generated
    }
}
