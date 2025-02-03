package tests;

import config.ConfigReader;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.equalTo;

public class users_l1f21bsse0395 {


//    API Documentation Link: https://gorest.co.in/

    // Token to be used for authorization in all requests
    String Token = ConfigReader.getProperty("Token_0395");

    // Response object to hold API responses
    Response resp;
    int userID;  // Variable to store the ID of the created user

    // Test to create a new user
    @Test
    public void createUser() {
        // Output the token to check if it's correctly loaded from configuration
        System.out.println(Token);

        // Sample curl command for reference (to create user)
        // curl -i -H "Accept:application/json" -H "Content-Type:application/json" -H "Authorization: Bearer ACCESS-TOKEN" -XPOST "https://gorest.co.in/public/v2/users"
        // -d '{"name":"Tenali Ramakrishna", "gender":"male", "email":"tenali.ramakrishna@15ce.com", "status":"active"}'

        // Create a new JSONObject to send as the body of the POST request
        JSONObject userInfo = new JSONObject();
        userInfo.put("name", "Aaliyan");
        userInfo.put("gender", "male");
        userInfo.put("email", "ll1f210395@ucp.edu.pk");
        userInfo.put("status", "active");

        // Send POST request to create a new user
        resp = RestAssured
                .given()
                .baseUri("https://gorest.co.in")  // Base URI for GoRest API
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .header("Authorization", Token)  // Authorization header with Bearer token
                .body(userInfo.toString())  // Send the user info in the body
                .when()
                .post("/public/v2/users")  // POST request to create a new user
                .then()
                .statusCode(201)
                .body("name", equalTo("Aaliyan")) // Assert that the response code is 201 (Created)
                .extract().response();  // Extract the response for further inspection

        // Extract user ID from the response to use in later requests
        userID = resp.body().jsonPath().get("id");
        System.out.println("Created User ID: " + userID);  // Output the created user's ID
    }

    // Test to update the created user
    @Test(priority = 2)
    public void updateUser(){
        // Create JSONObject for the updated user info
        JSONObject updUser = new JSONObject();
        updUser.put("name", "Aaliyan Umar");
        updUser.put("gender", "male");
        updUser.put("email", "maaliyanumar@gmail.com");
        updUser.put("status", "active");

        // Send PUT request to update the user
        RestAssured
                .given()
                .baseUri("https://gorest.co.in")  // Base URI for GoRest API
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .header("Authorization", Token)  // Authorization header with Bearer token
                .body(updUser.toString())  // Send the updated user info in the body
                .when()
                .put("/public/v2/users/" + userID)  // PUT request to update the user using the userID
                .then()
                .extract().response().prettyPrint();  // Output the response in a readable format

        // Output the user ID to confirm which user was updated
        System.out.println("User ID : " + userID);
    }

    // Test to delete the created user
    @Test(priority = 3)
    public void deleteUser() {
        // Send DELETE request to delete the user
        RestAssured
                .given()
                .baseUri("https://gorest.co.in")  // Base URI for GoRest API
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .header("Authorization", Token)  // Authorization header with Bearer token
                .when()
                .delete("/public/v2/users/" + userID)  // DELETE request to delete the user using the userID
                .then()
                .statusCode(204)  // Assert that the response code is 204 (No Content)
                .log().all();  // Log the response for debugging

        // Output a message confirming that the user has been deleted
        System.out.println("User ID " + userID + " has been deleted.");
    }
}
