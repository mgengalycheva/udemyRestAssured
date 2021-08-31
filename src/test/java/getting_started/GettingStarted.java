package getting_started;

import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;

public class GettingStarted {

    @Test
    public void simpleGetRequest() {

        given()
            .baseUri("https://restcountries.eu/rest/v2").
        when()
            .get("/all").
        then()
            .statusCode(200);
    }

    @Test
    public void validateJsonResponse() {
        given()
            .baseUri("https://restcountries.eu/rest/v2").
        when()
            .get("/alpha/USA").
        then()
            .statusCode(200)
            .body(
                "name", equalTo("United States of America"),
                "alpha3Code", equalTo("USA"),
                "altSpellings", hasItem("US"),
                "currencies[0].symbol", equalTo("$"),
                "languages[0].name", equalTo("English")
            );
    }

    @Test
    public void extract_response_data() {
        Response res = given()
            .baseUri("https://restcountries.eu/rest/v2").
        when()
            .get("/alpha/USA").
        then()
            .extract().response();
        System.out.println(res.asString());
    }

    @Test
    public void extract_single_value() {
        String res = given()
                .baseUri("https://restcountries.eu/rest/v2").
                        when()
                .get("/alpha/USA").
                        then()
                .extract().path("capital");
        System.out.println(res);
    }

    @Test
    public void verify_status_line() {
        given()
            .baseUri("https://api.printful.com").
        when()
            .get("/variant/1").
        then()
            .statusCode(404)
            .statusLine("HTTP/1.1 404 Not Found");
    }
}
