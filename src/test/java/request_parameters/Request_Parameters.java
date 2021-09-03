package request_parameters;

import io.restassured.http.ContentType;
import org.junit.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class Request_Parameters {
    //https://example.com/api/v1?param1=value1&param2=value2

    @Test
    public void handling_query_parameters() {
        given()
           .baseUri("https://restcountries.eu/rest/v2")
           //.param("fullText", false).
           .queryParam("fullText", true).
        when()
           .get("name/india").
        then()
           .log().all()
           .statusCode(200);
    }


    @Test
    public void hadling_multiple_parameters() {
        /*given()
                .baseUri("https://petstore.swagger.io/v2").
                queryParam("username", "marina").queryParam("password", "marinapassword").
        when()
           .get("/user/login").
        then()
           .log().all()
           .statusCode(200);*/

        HashMap<String, String> parameters = new HashMap<String, String>();

        parameters.put("username", "marina");
        parameters.put("password", "marinapassword");

        given()
            .baseUri("https://petstore.swagger.io/v2")
            .queryParams(parameters).
        when()
            .get("/user/login").
        then()
            .log().all()
            .statusCode(200);
    }

    @Test
    public void handling_multi_value_parameters() {

        given()
            .baseUri("https://restcountries.eu/rest/v2")
            .queryParam("codes", "col;no;ee;in").
        when()
            .get("alpha").
        then()
            .log().all()
            .statusCode(200);
    }


    @Test
    public void handling_path_parameters() {
        given()
            .baseUri("https://restcountries.eu/rest/v2")
            .pathParam("currency", "usd").
        when()
            .get("/currency/{currency}").
        then()
            .log().all()
            .statusCode(200);
    }

    @Test
    public void handling_form_data() {
        given()
           .baseUri("https://postman-echo.com")
           .contentType("application/x-www-form-urlencoded;charset=UTF-8")
           .formParam("first name", "Name")
           .formParam("last name", "Surname").
        when()
           .post("/post").
        then()
           .log().all()
           .statusCode(200);
    }

}
