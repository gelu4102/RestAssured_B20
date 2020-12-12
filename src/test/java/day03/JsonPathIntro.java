package day03;
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
    @DisplayName("Extracting data from Json Array Response")
    @Test
    public void getAllSpartanExtractingData(){

        //Response response = get("/spartans");
        //JsonPath jp = response.jsonPath();

        JsonPath jp = get("/spartans").jsonPath();

        //get the first Json object name field
        System.out.println("jp.getString(\"name[0]\")" +
                " = " + jp.getString("name[0]"));
        System.out.println("jp.getLong(\"phone[0]\")" +
                " = " + jp.getLong("phone[0]"));

        System.out.println("jp.getString(\"gender[6]\") " +
                "= " + jp.getString("gender[6]"));

        //getting all the name fields from the jsonArray Response
        //and storing as a list
        List<String> allNames = jp.getList("name");
        System.out.println("allNames = " + allNames);

        //getting all the phone fields from the jsonArray Response
        //and storing as a list

        List<Long> allPhones = jp.getList("phone");
        System.out.println("allPhones = " + allPhones);
    }
     //send to request url
    //http://3.89.238.187:8000/api/spartans/search?nameContains=ad&gender=Male
    //get the name of first guy in the result
    //get the phone of 3rd guy in the result
    //get all names , all phones save it as list
    //save the value of field called empty under pageable in the response
    //print it out
    @DisplayName("Testing /spartans/search and extracting data")
    @Test
    public void testSearch(){

        JsonPath jp = given().queryParam("nameContains" , "ad")
                            .queryParam("gender" , "Male").
                     when()
                            .get("/spartans/search")
                            .jsonPath();
        System.out.println("first guy name "+jp.getString("content[0].name"));
        System.out.println("second guy phone number " + jp.getLong("content[1].phone"));

        System.out.println("allNames " + jp.getList("content.name"));
        System.out.println("allPhones " + jp.getList("content.phone"));

        System.out.println(jp.getBoolean("pageable.sort.empty"));


    }

}
