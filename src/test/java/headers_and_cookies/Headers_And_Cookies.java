package headers_and_cookies;

import io.restassured.http.Cookie;
import io.restassured.http.Cookies;
import io.restassured.http.Headers;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.head;

public class Headers_And_Cookies {

    @Test
    public void sending_request_headers() {
        given()
            .baseUri("https://petstore.swagger.io/v2")
            .queryParam("username", "Lex").queryParam("password", "lexpassword")
            .header("If-Modified-Since", "Tue, 30 Jun 2020 01:01:25 GMT").
                //.header("Accept-Encoding", "gzip, deflate, br").
        when()
            .get("/user/login").
        then()
            .log().all()
            .statusCode(200);
    }

    @Test
    public void sending_headers_object() {

        HashMap<String, Object> headers = new HashMap<String, Object>();

        headers.put("If-Modified-Since", "Tue, 30 Jun 2020 01:01:25 GMT");
        headers.put("Accept-Encoding", "gzip, deflate, br");

        given()
            .baseUri("https://petstore.swagger.io/v2")
            .queryParam("username", "Lex").queryParam("password", "lexpassword")
            .headers(headers).
        when()
            .get("/user/login").
        then()
            .log().all()
            .statusCode(200);
    }

    @Test
    public void sending_request_cookies() {

        given()
            .baseUri("https://petstore.swagger.io/v2")
            .queryParam("username", "Lex").queryParam("password", "lexpassword")
            .cookie("user", "test").
        when()
            .get("/user/login").
        then()
            .log().cookies()
            .statusCode(200);

    }

    @Test
    public void sending_cookies_using_builder() {

        //Cookie cookie = new Cookie.Builder("usertype", "int").build();
        Cookie cookie = new Cookie.Builder("usertype", "int").setSecured(true).setComment("test comment").build();

        given()
            .baseUri("https://petstore.swagger.io/v2")
            .queryParam("username", "Lex").queryParam("password", "lexpassword")
            .cookie(cookie).
        when()
            .get("/user/login").
        then()
            .log().all()
            .statusCode(200);

    }

    @Test
    public void validate_response_header() {

        given()
            .baseUri("https://petstore.swagger.io/v2")
            .queryParam("username", "Lex").queryParam("password", "lexpassword").
        when()
            .get("/user/login").
        then()
            .log().all()
            .statusCode(200)
            .header("X-Rate-Limit", "5000");

    }

    @Test
    public void extract_response_header() {

        Headers headers =  given()
            .baseUri("https://petstore.swagger.io/v2")
            .queryParam("username", "Lex").queryParam("password", "lexpassword").
       when()
            .get("/user/login").
       then()
            .log().all()
            .statusCode(200)
            .extract().headers();

        System.out.println("headers: " + headers.getValue("Access-Control-Allow-Methods"));
        System.out.println("headers: " + headers.getValue("X-Expires-After"));
        System.out.println("headers: " + headers.getValue("X-Rate-Limit"));
        System.out.println("headers: " + headers.getValue("Server"));

    }

    @Test
    public void extract_response_cookies() {

        Map<String, String> cookies =  given()
            .baseUri("https://petstore.swagger.io/v2")
            .queryParam("username", "Lex").queryParam("password", "lexpassword").
        when()
            .get("/user/login").
        then()
            .log().all()
            .statusCode(200)
            .extract().cookies();

        System.out.println("cookies: " + cookies.get("_cfduid"));

    }


}
