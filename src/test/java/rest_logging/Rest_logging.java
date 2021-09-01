package rest_logging;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

public class Rest_logging {

    @Test
    public void log_all_details() {

        given()
            .baseUri("https://restcountries.eu/rest/v2").
        when()
            .get("/alpha/US").
        then()
            .log().everything();
             //.log().all();

    }

    @Test
    public void log_body_or_header_details() {
        given()
            .baseUri("https://restcountries.eu/rest/v2").
        when()
            .get("/alpha/US").
        then()
            .log().headers();
        //.log.body();
    }

    @Test
    public void log_cookies_or_status() {
        given()
            .baseUri("https://restcountries.eu/rest/v2").
        when()
            .get("/alpha/US").
        then()
            //.log().status();
            .log().cookies();
    }

    @Test
    public void log_if_error() {
        given()
            .baseUri("https://restcountries.eu/rest/v2").
        when()
            .get("/alpha/UT").
        then()
              .log().ifStatusCodeIsEqualTo(404);
            //.log().ifError();
    }


    @Test
    public void log_if_validation_fails() {
        given()
            .baseUri("https://restcountries.eu/rest/v2").
        when()
            .get("/alpha/UT").
        then()
            .log().ifValidationFails().statusCode(404);
    }
}
