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


public class GithubRestAPITest {

    //creat a test for testing github rest api usera/user endpoint

    @DisplayName("Test GitHub GET /users/{username}")
    @Test
    public void GithubRestAPITest(){

        //provide your username as path variable in give section of the chain
        given()
                .pathParam("username" ,"gelu4102").
        when()
                .get("https://api.github.com/users/{username}").
        then()
                .assertThat()
                .statusCode(is(200))
                .contentType(ContentType.JSON)
                .header("server" , "GitHub.com")
                .body("login" , is("gelu4102") );



    }


}
