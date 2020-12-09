package day01;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.* ;
import static io.restassured.matcher.RestAssuredMatchers.* ;
import static org.hamcrest.Matchers.* ;

public class RestAssuredIntro {
    @DisplayName("Spartan GET /api/hello Endpoint Test")
    @Test
    public void TestHello(){

        //This is my own IP
      //  http://3.89.238.187:8000/api/hello

        //make sure this is what's imported for data type Response
        //import io.restassured.response.Response;

        Response response = get("http://3.89.238.187:8000/api/hello");
        //get status code out of this Response object
        System.out.println("Status code is : "+response.statusCode() );

        //assert the status code is 200



    }



}
