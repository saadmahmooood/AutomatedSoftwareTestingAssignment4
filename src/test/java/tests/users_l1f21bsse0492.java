package tests;

import config.ConfigReader;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.equalTo;

public class users_l1f21bsse0492 {

    String Token = ConfigReader.getProperty("Token_0492");

    Response resp;
    int userID;

    @Test
    public void createUser() {
        System.out.println(Token);

        JSONObject userInfo = new JSONObject();
        userInfo.put("name", "sami");
        userInfo.put("gender", "male");
        userInfo.put("email", "l1f21bbase0492@ucp.edu.pk");
        userInfo.put("status", "active");

        resp = RestAssured
                .given()
                .baseUri("https://gorest.co.in")
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .header("Authorization", Token)
                .body(userInfo.toString())
                .when()
                .post("/public/v2/users")
                .then()
                .statusCode(201)
                .extract().response();


        userID = resp.body().jsonPath().get("id");
        System.out.println("Created User ID: " + userID);
    }


    @Test(priority = 2)
    public void updateUser() {

        JSONObject updUser = new JSONObject();
        updUser.put("name", "Samiullah");
        updUser.put("gender", "male");
        updUser.put("email", "sam@gmail.com");
        updUser.put("status", "active");

        RestAssured
                .given()
                .baseUri("https://gorest.co.in")
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .header("Authorization", Token)
                .body(updUser.toString())
                .when()
                .put("/public/v2/users/" + userID)
                .then()
                .extract().response().prettyPrint();

        System.out.println("updated successfully");
    }


}
