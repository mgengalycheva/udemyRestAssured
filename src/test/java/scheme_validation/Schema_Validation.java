package scheme_validation;

import org.junit.Test;

import java.io.File;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static io.restassured.module.jsv.JsonSchemaValidator.*;

public class Schema_Validation {

    /*
    * JSON Schema validation
     */

    @Test
    public void json_schema_validation() {

        File file = new File("resources/json_schema1.json");

        given()
            .baseUri("https://petstore.swagger.io/v2")
            .queryParam("username", "Lex").queryParam("password", "lexpassword").
        when()
            .get("/user/login").
        then()
            .log().all()
            .statusCode(200)
            .body(matchesJsonSchema(file));

    }


    /*
    * XML DTD Schema Validation
     */

    @Test
    public void xml_dtd_schema_validation() {

        File file = new File("resources/xml_dtd_schema.dtd");
        given()
            .baseUri("url")
            .queryParam("APPID", "appid code")
            .queryParams("q", "London.uk")
            .queryParams("mode", "xml").
        when()
            .get("path of url").
        then()
            .body(matchesDtd(file))
            .log().all()
            .statusCode(200);

    }

    /*
    *XML XSD schema Validation
     */

    @Test
    public void xml_xsd_Schema_validation() {

        File file = new File("resources/xml_xsd_schema.xsd");
        given()
            .baseUri("url")
            .queryParam("APPID", "appid code")
            .queryParams("q", "London.uk")
            .queryParams("mode", "xml").
        when()
            .get("path of url").
        then()
            .body(matchesXsd(file))
            .log().all()
            .statusCode(200);

    }
}
