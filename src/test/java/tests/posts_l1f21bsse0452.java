package tests;

import config.ConfigReader;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.equalTo;


public class posts_l1f21bsse0452 {

    String Token= ConfigReader.getProperty("Token_0452");
    int id;
    Response resp;
    @Test
    public void createNewPost() {


        JSONObject reqObj = new JSONObject();
        reqObj.put("user_id", 7666712);
        reqObj.put("title", "Ast 4");
        reqObj.put("body", "new post added");

        resp = RestAssured
                .given()
                .baseUri("https://gorest.co.in")
                .header("Accept","application/json")
                .header("Content-Type", "application/json")
                .header("Authorization", Token)
                .body(reqObj.toString())
                .when()
                .post("/public/v2/posts")
                .then()
                .statusCode(201)
                .extract().response();
                resp.body().prettyPrint();
        id = resp.body().jsonPath().get("id");
        System.out.println("Post ID: " + id);
    }



    @Test
    public void updatePost(){

        JSONObject updObj = new JSONObject();
        updObj.put("id", id);
        updObj.put("user_id", 7666712);
        updObj.put("title", "Automated Software Testing Assignment 4");
        updObj.put("body", "Post updated");


        RestAssured
                .given()
                .baseUri("https://gorest.co.in")
                .header("Accept","application/json")
                .header("Content-Type","application/json")
                .header("Authorization",Token)
                .body(updObj.toString())
                .when()
                .put("/public/v2/posts/"+id)
                .then()
                .body("body", equalTo("Post updated"));

    }
}

