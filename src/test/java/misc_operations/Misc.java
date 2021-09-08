package misc_operations;

import io.restassured.RestAssured;
import io.restassured.config.XmlConfig;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

public class Misc {

    @BeforeClass
    public static void setup() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 8888;
    }

    /* specifying request port */

    @Test
    public void specify_port() {
        //if in the future the port will be changed than it's just necessary to change it in the test
//        given()
//            .baseUri("http://localhost")
//            .port(8888).
        when()
            .get("/rest/v2/alpha/co").
        then()
            .log().all()
            .statusCode(200);
    }

    @Test
    public void connect_check() {

//        given()
//            .baseUri("http://localhost")
//            .port(8888).
        when()
            .get("/check").
        then()
            .log().all()
            .statusCode(200);
    }

    @Test
    public void validate_response_time() {

        when()
            .get("/check").
        then()
            .time(lessThan(500L), TimeUnit.MILLISECONDS);
    }


    @Test
    public void validate_xml_nameSpace() {
        XmlConfig xmlc = XmlConfig.xmlConfig().declareNamespace("perctg", "http://localhost:8888");

        given()
            .config(RestAssured.config().xmlConfig(xmlc)).
        when()
            .get("/student/963/score").
        then()
            .log().all()
            .body("student.score[0]", equalTo("369"))
            .body("student.grouping[1]", equalTo("99.66"));
    }


    @Test
    public void validate_response_using_response_part() {

        Response res  = when()
            .get("/get-article/bash").
        then()
            .log().all()
            .extract().response();

        String href = res.path("href");
        String articleId = res.path("articleId");
        String articleUrl = res.path("articleUrl");

        Assert.assertTrue(articleUrl.equals(href + articleId));
    }

    @Test
    public void response_aware_matcher_example() {

        when()
            .get("/get-article/bash").
        then()
            .log().all()
            .body("articleUrl", response -> equalTo(response.path("href").toString() + response.path("articleId").toString()));
    }
}
