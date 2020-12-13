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

public class SpartanAddingUpdatingTest {

    @BeforeAll
    public static void setUp(){
        baseURI = "http://3.89.238.187:8000";
        basePath = "/api" ;
    }
    @AfterAll
    public static void tearDown(){
        reset();
    }

    @DisplayName("Testing GET / api/Spartans with basic auth")
    @Test
    public void testAllSpartanWithBasicAuth(){
        given()
                .log().all()
                .auth().basic("admin" , "admin").
        when()
                .get("/spartans").
        then()
                .log().all()
                .statusCode(is(200));
    }

    @DisplayName("Add 1 Data with Raw Json String POST /api/spartans")
    @Test
    public void testAddOneDAta(){

        String newSpartansStr = "\n"+"{\n" +
                "    \"name\": \"Terence\",\n" +
                "    \"gender\": \"Male\",\n" +
                "    \"phone\": 1311814806\n" +
                "}";
        System.out.println(newSpartansStr);
        given()
                .log().all()
               // .auth().basic("admin" , "admin")
                .contentType(ContentType.JSON)
                .body(newSpartansStr).
        when()
                .post("/spartans").
        then()
                .log().all()
                .statusCode(is(201))
                .contentType(ContentType.JSON)
                .body("success" , is("A Spartan is Born!"))
                .body("data.name" , is("Terence"))
                .body("data.gender" , is("Male"))
                .body("data.phone" , is(1311814806));


    }

    @DisplayName("Add 1 Data with Map Object POST /api/spartans")
    @Test
    public void testAddOneDataWithMapAsBody(){
        Map<String, Object> payloadMap = new LinkedHashMap<>();
        payloadMap.put("name" , "Tucky");
        payloadMap.put("gender" , "Male");
        payloadMap.put("phone" , 9876543210L);

        System.out.println("payloadMap = " + payloadMap);

        given()
                .log().all()
                .contentType(ContentType.JSON)
                .body(payloadMap).
        when()
                .post("/spartans").
        then()
                .log().all()
                .statusCode(is(201))
                .contentType(ContentType.JSON)
                .body("success" , is("A Spartan is Born!"))
                .body("data.name" , is("Tucky"))
                .body("data.gender" , is("Male"))
                .body("data.phone" , is(9876543210L));





    }

    @DisplayName("Add 1 Data with External json file POST /api/spartans")
    @Test
    public void testAddOneDataWithJsonFileAsBody(){
        // Create a file called singleSpartan.json right under root directory
        // with below content
    /*
    {
        "name": "Olivia",
        "gender": "Female",
        "phone": 6549873210
    }
    add below code to point File object to this singleSpartan.json
     */
        File externalJson = new File("singleSpartan.json");
        given()
                .log().all()
                .contentType(ContentType.JSON)
                .body(externalJson).
        when()
                .post("/spartans").
        then()
                .log().all()
                .statusCode(is(201))
                .contentType(ContentType.JSON)
                .body("success" , is("A Spartan is Born!"))
                .body("data.name" , is("Olivia"))
                .body("data.gender" , is("Female"))
                .body("data.phone" , is(6549873210L));






    }







}
