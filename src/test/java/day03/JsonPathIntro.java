package day03;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
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

    }


}
