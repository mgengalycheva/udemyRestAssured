package getting_started;

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
}
