package tests;

import base.BaseTest;
import config.ConfigReader;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;
import pages.apiPageBooker;

public class createToken extends BaseTest {

    @Test
    public void createToken() {

        apiPageBooker apiBooker = new apiPageBooker(driver);
        Actions action = new Actions(driver);

        driver.manage().window().maximize();

        String apiDoc = ConfigReader.getProperty("api_doc");
        driver.navigate().to(apiDoc);

        apiBooker.crateToken.click();



        JSONObject user = new JSONObject();
        user.put("username", ConfigReader.getProperty("username"));
        user.put("password", ConfigReader.getProperty("password"));

        Response resp = RestAssured.
                given().
                baseUri(ConfigReader.getProperty("baseURL"))
                .header("Content-Type", "application/json")
                .body(user.toString())
                .when()
                .post("/auth")
                .then()
                .statusCode(200)
                .extract().response();
        resp.body().prettyPrint();
        String token = resp.body().jsonPath().get("token");
        System.out.println("Token: " + token);


    }


}
