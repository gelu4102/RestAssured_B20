package day03;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;


public class JsonPathIntro {
    @BeforeAll
    public static void setUp() {

        RestAssured.baseURI = "http://3.89.238.187:8000";
        RestAssured.basePath = "/api";
    }

    @AfterAll
    public static void tearDown(){

        reset();
    }

    @DisplayName("Extracting data out of Spartan Json Object")
    @Test
    public void test1SpartanPayload(){

        //send a request to get 1 spartan
        //by providing path params with valid id
        //save it into Response object
        //NEW : create an object with type JsonPath
        //by calling the method jsonPath() on response object
        //extract id, name, gender, phone
        //and save iyt into variable of correct type

     Response response =   given()
                                  .pathParam("id", 34).
                           when()
                                  .get("/spartans/{id}")
                                  .prettyPeek()
                                      ;
     //response.prettyPrint();
     //JsonPath is used to navigate through the json payload
        //and extract the value according to the valid "jsonpath" provided
        JsonPath jp = response.jsonPath();
        int myId = jp.getInt("id");
        String myName = jp.getString("name");
        String gender = jp.getString("gender");
        long phone = jp.getLong("phone");
        System.out.println("myId = " + myId);
        System.out.println("myName = " + myName);
        System.out.println("myGender = " + gender);
        System.out.println("myPhone = " + phone);

    }


}
