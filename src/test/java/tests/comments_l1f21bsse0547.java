package tests;

import config.ConfigReader;
import io.restassured.RestAssured;
import org.json.JSONObject;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.equalTo;

public class comments_l1f21bsse0547 {

    String Token= ConfigReader.getProperty("Token_0547");
    int commentId;

    @Test
    public void createComment() {

        // Creating request payload using JSON object
        JSONObject reqObj = new JSONObject();
        reqObj.put("post_id", 188955);
        reqObj.put("name", "Rayyan");
        reqObj.put("email", "rayyan@gmail.com");
        reqObj.put("body", "new comment added");

        commentId   =  RestAssured
                    .given()
                    .baseUri("https://gorest.co.in")
                    .header("Accept","application/json")
                    .header("Content-Type", "application/json")
                    .header("Authorization", Token)
                    .body(reqObj.toString())
                    .when()
                    .post("/public/v2/comments")
                    .then()
                    .statusCode(201)
                    .extract().body().jsonPath().get("id");

        }

        @Test
        public void updateComment(){

            JSONObject updObj = new JSONObject();
            updObj.put("id", commentId);
            updObj.put("post_id", 188955);
            updObj.put("name", "Rayyan Azhar");
            updObj.put("email", "rayyanazhar@gmail.com");
            updObj.put("body", "comment updated");

            RestAssured
                    .given()
                    .baseUri("https://gorest.co.in")
                    .header("Accept","application/json")
                    .header("Content-Type","application/json")
                    .header("Authorization",Token)
                    .body(updObj.toString())
                    .when()
                    .put("/public/v2/comments/"+commentId)
                    .then()
                    .body("name", equalTo("Rayyan Azhar"));
        }
    }