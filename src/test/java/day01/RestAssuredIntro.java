package day01;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.* ;
import static io.restassured.matcher.RestAssuredMatchers.* ;
import static org.hamcrest.Matchers.* ;

import static org.hamcrest.MatcherAssert.assertThat;

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
        assertThat(response.statusCode() , is(200));
        //how to pretty print entire response body
        //prettyPrint() -- print and return the payload as String
        String payload = response.prettyPrint();
        //assertThat te body is Hello from Sparta

        assertThat(payload , is("Hello from Sparta"));

        //There are always multiple way to same thing in RestAssured
        //get the header called ContentType from the response
        System.out.println( response.getHeader("Content-Type") );
        System.out.println( response.getContentType() );
        System.out.println( response.contentType() );

        //assert That Content_Type is text/plain;charset+UTF-8
       assertThat(response.contentType() , is("text/plain;charset=UTF-8"));

       //assert That Content-Type startWith text
        assertThat(response.contentType() , startsWith("text"));

        //Easy way to work Content-type without typing much
        //We can use ContentType Enum from RestAssured to easily get main part content-type
        //ContentType.TEXT -->text/plain as Enum
        //startWith accept a String object
        //so
        assertThat(response.contentType(), startsWith( ContentType.TEXT.toString() ));
        assertThat(response.contentType() , is(not(ContentType.JSON)));



    }



}
