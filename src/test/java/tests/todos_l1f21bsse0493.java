package tests;

import config.ConfigReader;
import io.restassured.RestAssured;
import org.json.JSONObject;
import org.testng.annotations.Test;


public class todos_l1f21bsse0493 {

    String Token= ConfigReader.getProperty("Token_0493");
    int id;

    @Test(priority = 3)
    public void getTodo(){


        RestAssured
                .given()
                    .baseUri("https://gorest.co.in")
                    .header("Accept","application/json")
                    .header("Content-Type","application/json")
                    .header("Authorization",Token)
                .when()
                    .get("/public/v2/todos")
                .then()
                    .statusCode(200)
                    .extract().response().prettyPrint();
    }

    @Test(priority=1)
    public void createNewTodo() {

        JSONObject userInfo = new JSONObject();
        userInfo.put("user_id", 7665419);
        userInfo.put("title", "Ast Assignment 4");
        userInfo.put("due_on", "2024-01-29");
        userInfo.put("status", "pending");

        id = RestAssured
                .given()
                    .baseUri("https://gorest.co.in")
                    .header("Accept","application/json")
                    .header("Content-Type", "application/json")
                    .header("Authorization", Token)
                    .body(userInfo.toString())
                .when()
                    .post("/public/v2/todos")
                .then()
                    .statusCode(201)
                    .extract().body().jsonPath().get("id");
                //.extract().response().prettyPrint();

        System.out.println("\n\nCreated User ID: " + id);
    }

    @Test(priority = 4)
    public void deleteTodo(){

        RestAssured
                .given()
                    .baseUri("https://gorest.co.in")
                    .header("Accept","application/json")
                    .header("Content-Type","application/json")
                    .header("Authorization",Token)
                .when()
                    .delete("/public/v2/todos/"+id)
                .then()
                    .statusCode(204)
                    .log().all();

    }

    @Test(priority = 2)
    public void updateTodo(){


        JSONObject userInfo = new JSONObject();
        userInfo.put("user_id", 7665419);
        userInfo.put("title", "Automated Software Testing Assignment 4");
        userInfo.put("due_on", "2025-01-29");
        userInfo.put("status", "completed");

        RestAssured
                .given()
                    .baseUri("https://gorest.co.in")
                    .header("Accept","application/json")
                    .header("Content-Type","application/json")
                    .header("Authorization",Token)
                    .body(userInfo.toString())
                .when()
                    .put("/public/v2/todos/"+id)
                .then()
                    .extract().response().prettyPrint();

        System.out.println("Updated User ID : "+id+"\n\n");
    }
}

