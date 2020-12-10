package day02;
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


public class SingleSpartanTest {
    @BeforeAll
    public static void setUp() {

        RestAssured.baseURI = "http://3.89.238.187:8000";
        RestAssured.basePath = "/api";
    }

    @AfterAll
    public static void tearDown(){
        reset();
    }

    @DisplayName("Testing GET /spartan/{id} endpoint")
    @Test
    public void test1Spartan(){
    // I want to get json result out
    //When i send get request to /spartans/{id}endpoint
    // and expecting 200 status code
                given()
                      .accept(ContentType.JSON).
                when()
                      .get("/spartans/100").
                then()
                       .statusCode(is(200))
                       .contentType(ContentType.JSON);
    // I want to make it obvious for
    // the value 100 is path variable|params
    // to uniquely identify the resource

        //tis will be whole Request URL 0 this test
        http://3.89.238.187:8000/api/spartans/100
    given()
         .accept(ContentType.JSON)
         .pathParam("id" , 100).
    when()
          .get("/spartans/{id}").
    then()
            .assertThat()
            .statusCode(is(200))
            .contentType(ContentType.JSON);


    // This is the easiest one , same result
    given()
            .accept(ContentType.JSON).
    when()
            .get("/spartans/{id}" , 100).
    then()
            .assertThat()
            .statusCode(is(200))
            .contentType(ContentType.JSON);
    }

    @DisplayName("Testing GET /spartans/{id} endpoint payload")
    @Test
    public void test1SpartanPayload(){
        /**
         *   "id": 100,
         *     "name": "Terence",
         *     "gender": "Male",
         *     "phone": 1311814806
         */
        given()
                .accept(ContentType.JSON).
        when()
                .get("/spartans/{id}" , 100).
        then()
                .assertThat()
                .statusCode(is(200))
                .contentType(ContentType.JSON)
                .body("id", is(100))
                .body("name" , equalTo("Terence"))
                .body("gender" , is(equalTo("Male")))
                .body("phone" , equalTo(1311814806));
    }

}
