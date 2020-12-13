package day04;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class OpenMovieDB_Test {
    //http://www.omdbapi.com/?t=baby&api=b57735ca

    @BeforeAll

    public static void setUp(){
        RestAssured.baseURI = "http://www.omdbapi.com" ;
    }

    @AfterAll
    public static void tearDown(){
        reset();
    }
    @DisplayName("Test Search Movie or OpenMovieDB Test")

    @Test

    public void testMovie(){
        given()
                .queryParam("apiKey" , "b57735ca")
                .queryParam("t" , "Spartans").
        when()
                .get().prettyPeek().
        then()
                .statusCode(is(200))
                .contentType(ContentType.JSON)
                .body("Title" , is("Spartans"))
                .body("Ratings[0].Source" ,is("Internet Movie Database"));
    }
@DisplayName("Getting the log of request and response")
    @Test
    public void testSendingRequestAndGetTheLog(){
        given()
                .queryParam("apiKey" , "b57735ca")
                .queryParam("t" , "John Wick")
                .log().all().
        when()
                .get().
        then()
                .log().all()
                .statusCode(is(200))
                .body("Plot" , containsString("ex-hit-man"))
                .body("Ratings[1].Source" , is("Rotten Tomatoes"));



    }






}
