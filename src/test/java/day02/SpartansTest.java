package day02;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
public class SpartansTest {

    @BeforeAll
    public static void setUp(){

        RestAssured.baseURI = "http://3.89.238.187:8000";
       RestAssured.basePath = "/api";

       //baseURL + basePath + whaever you provided in http method like get post
        //for example :
        //get("/spartans") -->> get(baseURL + basePath + "/spartans")
    }

    @DisplayName("Testing/api/spartans endpoint")
    @Test
    public void testGetAllSpartan(){

        //Send a get request to above endpoint
        //save the response
        //print out the result
        //try to assert the status code
        //content type header

        Response response = get("/spartans");
        response.prettyPrint();

        assertThat(response.statusCode(), is(200));
        assertThat(response.contentType(), is(ContentType.JSON.toString()));

    }

    @DisplayName("Testing/api/spartans endpoint XML Response")
    @Test
    public void testGetAllSpartanXML(){
        /**
         * given
         *    ----RequestSpecification
         *    used to provide additional information about the request
         *    header, query params , path variable , payload
         *    authentication authorization
         *    logging , cookie
         * When
         * -----This is where you actually send the request with http method
         *   --Like GET POST PUT DELETE .. with the URL
         *    -- We  get Response Object after sending the request
         * Then
         *   -- ValidatableResponse
         *    ---validate status code, header , payload , cookie
         *    --responseTime
         */
                given()
                      .header("accept", "application/xml").
                when()
                      .get("/spartans").

                then()
                        .assertThat()//this is not required , but can be added to make it obvious that
                                     // this is where we start assertion
                        .statusCode(200)
                        .and()//this is not rquired at all , just for readability, optional
                        .header("Content-Type" , "application/xml");
          // This will odo same exact thing as above in slightly different way
        //Since accept header and content type header is so common , RestAssured has good support or those
        //header by providing method directly rather than using header method we used above
                given()
                        .accept(ContentType.XML).
                when()
                        .get("spartans").
                then()
                        .assertThat()
                        .statusCode(is(200) )
                        .and()
                        .contentType(ContentType.XML);


    }




    }
