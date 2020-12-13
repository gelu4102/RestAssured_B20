package day04;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.Map;

import static io.restassured.RestAssured.* ;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.* ;

public class SpartanUpdatingTest {

    @BeforeAll
    public static void setUp(){
        baseURI = "http://3.89.238.187:8000";
        basePath = "/api" ;
    }
    @AfterAll
    public static void tearDown(){
        reset();
    }

    @DisplayName("Testing PUT / api/Spartans/{id} with String body")
    @Test
    public void testingUpdatingSingleSpartanWithStringBody(){

        String updateStrPayload = "\n"+"{\n" +
                "    \"name\": \"B20 Vola\",\n" +
                "    \"gender\": \"Male\",\n" +
                "    \"phone\": 9876543210\n" +
                "}";
        given()
                .log().all()
                .auth().basic("admin", "admin")
                .contentType(ContentType.JSON)
                .pathParam("id", 1)
                .body(updateStrPayload).
        when()
                .put("/spartans/{id}").
        then()
                .log().all()
                .statusCode(is(204))
                //This is how we check a header exists by checking the
                //using notNullValue() matcher
                .header("Date", is(notNullValue()))
                .body((emptyString()));

    }

    @DisplayName("Testing PATCH /api/spartans/{id} with String body")
    @Test
    public void testPartialUpdatingSingleSpartanWithStringBody(){
        //updated the name to B20 Pached
        //{"name": "B20 Patched"}
        String patchBody = "{\"name\": \"B20 Patched\"}";
        given()
                .auth().basic("admin", "admin")
                .log().all()
                .contentType(ContentType.JSON)
                .pathParam("id" , 111)
                .body(patchBody).
        when()
                .patch("/spartans/{id}").
        then()
                .log().all()
                .assertThat()
                .statusCode(is(204))
                .body(emptyString());
    }

    @DisplayName("Testing Delete /api/spartans/{id}")
    @Test
    public void testDeletingSingleSpartan(){
        given()
                .log().all()
                .auth().basic("admin", "admin")
                .pathParam("id", 105).
        when()
                .delete("/spartans/{id}").
        then()
                .log().all()
                .assertThat()
                .statusCode(is(204))
                .body(emptyString());

    }

}
