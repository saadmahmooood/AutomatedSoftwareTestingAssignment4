package tests;

import config.ConfigReader;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.equalTo;


public class posts_l1f21bsse0491 {

    String Token= ConfigReader.getProperty("Token_0491");
    int id;
    Response resp;

    // curl -i -H "Accept:application/json" -H "Content-Type:application/json" -H "Authorization: Bearer ACCESS-TOKEN"
    // -XPOST "https://gorest.co.in/public/v2/posts"
    // -d '{"user_id":12345, "title":"XYZ", "body":"abcdefg"}'
    @Test
    public void createNewPost() {


        JSONObject reqObj = new JSONObject();
        reqObj.put("user_id", 7666713);
        reqObj.put("title", "Ast 4");
        reqObj.put("body", "nayi post bn gyii");

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
        //curl -i -H "Accept:application/json" -H "Content-Type:application/json" -H "Authorization: Bearer 0839199a291af4f7b35dd0defe81d2335eaf1d4cdc0b1197d98cabfe8c474d90"
        // -XPATCH "https://gorest.co.in/public/v2/posts/7001923"
        // -d '{"name":"Allasani Peddana", "email":"allasani.peddana@15ce.com", "status":"active"}'

        JSONObject updObj = new JSONObject();
        updObj.put("id", id);
        updObj.put("user_id", 7665419);
        updObj.put("title", "Automated Software Testing Assignment 4");
        updObj.put("body", "Post update ho gyi");


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
                .body("body", equalTo("Post update ho gyi"));

    }

//    @Test
//    public void cafeTest() throws InterruptedException {
//        // Initialize the CafePage object
//        CafePage_L1F21BSSE0491 cafePage = new CafePage_L1F21BSSE0491(driver);
//
//        // Maximize the browser window
//        //driver.manage().window().maximize();
//
//        // Create an Actions object for advanced interactions
//        Actions actions = new Actions(driver);
//
//        // Navigate to the Cafe URL from the configuration file
//        String cafeUrl = ConfigReader.getProperty("cafeUrl");
//        driver.navigate().to(cafeUrl);
//
//        // Scroll to the "Fourth Page" section and click on it
//        actions.moveToElement(cafePage.fourthPage).perform();
//        cafePage.fourthPage.click();
//
//        // Scroll to the "Loaded Fries" item and select it
//        actions.moveToElement(cafePage.loadedFries).perform();
//        cafePage.loadedFries.click();
//        int loop = Integer.parseInt(ConfigReader.getProperty("loop"));
//        // Scroll to the "Add" button and click it multiple times (4 times in this case)
//        actions.moveToElement(cafePage.addBtn).perform();
//        for (int i = 0; i <loop ; i++) {
//            cafePage.addBtn.click();
//        }
//
//        // Wait for 2 seconds to allow for any visual confirmation (not recommended for production)
//        Thread.sleep(Long.parseLong(ConfigReader.getProperty("wait_time")));
//
//        // Add the item to the cart
//        cafePage.addCart.click();
//
//        // Wait for 3 seconds to ensure the item is added to the cart
//        Thread.sleep(Long.parseLong(ConfigReader.getProperty("wait_time")));
//    }


//    public class L1F21S6_API {
//        int bkID;
//        String Token;
//        @Test
//        public void pingAPITest(){
//
//            Response pingTestResp = RestAssured.get("https://restful-booker.herokuapp.com/ping");
//            Assert.assertEquals(pingTestResp.getStatusCode(),201,"wrong status code");
//            pingTestResp.print();
//
//            RestAssured
//                    .given()
//                    .baseUri("https://restful-booker.herokuapp.com")
//                    .when()
//                    .get("/ping")
//                    .then()
//                    .statusCode(201)
//                    .extract().response().print();
//        }
//        @Test
//        public void CreateTokenTest() {
//            JSONObject userInfo = new JSONObject();
//            userInfo.put("username", "admin");
//            userInfo.put("password", "password123");
//
//            Token = RestAssured
//                    .given()
//                    .baseUri("https://restful-booker.herokuapp.com")
//                    .header("Content-Type", "application/json")
//                    .body(userInfo.toString())
//                    .when()
//                    .post("/auth")
//                    .then()
//                    .statusCode(200)
//                    .extract().body().jsonPath().get("token");
//            System.out.println("Generated token is "+ Token);
//        }
//
//        @Test(priority = 1)
//        public void CreateBookingTest(){
//            JSONObject bookingInfo=new JSONObject();
//            JSONObject bkDate =new JSONObject();
//            bookingInfo.put("firstname","uswah");
//            bookingInfo.put("lastname","alvi");
//            bookingInfo.put("totalprice",111);
//            bookingInfo.put("depositpaid",true);
//            bkDate.put("checkin" , "2018-01-01");
//            bkDate.put("checkout" , "2019-01-01");
//            bookingInfo.put("bookingdates",bkDate);
//            bookingInfo.put("additionalneeds" , "Breakfast");
//
//            bkID=RestAssured
//                    .given()
//                    .baseUri("https://restful-booker.herokuapp.com")
//                    .header("Content-Type", "application/json")
//                    .body(bookingInfo.toString())
//
//                    .when()
//                    .post("/booking")
//                    .then()
//                    .extract().body().jsonPath().get("bookingid");
//            //.extract().response().prettyPrint();
//
//            System.out.println("Bokking ID : "+bkID);
//        }
//        @Test(priority = 2)
//        public void updateBookingTest(){
//            JSONObject upBookingInfo =new JSONObject();
//            JSONObject upBkDate =new JSONObject();
//            upBookingInfo.put("firstname","sarmad");
//            upBookingInfo.put("lastname","ali");
//            upBookingInfo.put("totalprice",111);
//            upBookingInfo.put("depositpaid",true);
//            upBkDate.put("checkin" , "2018-01-01");
//            upBkDate.put("checkout" , "2019-01-01");
//            upBookingInfo.put("bookingdates", upBkDate);
//            upBookingInfo.put("additionalneeds" , "Breakfast");
//
//            RestAssured
//                    .given()
//                    .baseUri("https://restful-booker.herokuapp.com")
//                    .header("Content-Type", "application/json")
//                    .header("Accept", "application/json")
//                    .header("Cookie" ,"token="+Token)
//                    .pathParams("id",bkID)
//                    //.auth().preemptive().basic("admin","password123")
//                    .body(upBookingInfo.toString())
//
//                    .when()
//                    .put("/booking/{id}")   //path parameter
//                    .then()
//                    .body("firstname",equalTo("sarmad"))
//                    //.extract().body().jsonPath().get("bookingid");
//                    .extract().response().prettyPrint();
//
//            System.out.println("Bo0king ID : "+bkID);
//
//
//        }
//
//        @Test (priority = 5)
//        public void deleteBookingTest() {
//            RestAssured
//                    .given()
//                    .baseUri("https://restful-booker.herokuapp.com")
//                    .header("Cookie", "token=" + Token)
//                    .header("Content-Type", "application/json")
//                    .when()
//                    .delete("/booking/"+bkID)   // Delete the booking by its ID
//                    .then()
//                    .statusCode(201)   // The status code for successful deletion is 201
//                    .log().all();
//
//            System.out.println("Booking ID " + bkID + " has been deleted.");
//        }
//
//        @Test(priority = 4)
//        public void getBookingByName(){
//            //curl -i https://restful-booker.herokuapp.com/booking/1
//            RestAssured
//                    .given()
//                    .baseUri("https://restful-booker.herokuapp.com")
//                    .queryParam("firstname","sarmad")
//                    .queryParam("lastname","ali")
//                    .when()
//                    .get("/booking")
//                    .then()
//                    .statusCode(200)
//                    .extract().response().prettyPrint();
//
//        }
//    }


}

