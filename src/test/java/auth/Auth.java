package auth;

import org.junit.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.apache.http.client.methods.RequestBuilder.post;

public class Auth {


    @Test
    public void digest_auth() {

        given()
           .baseUri("https://postman-echo.com")
           .auth().digest("postman", "password").
           //.auth().basic("postman", "password").
           //.auth().preemptive().basic("postman", "password").
        when()
           .get("/digest-auth").
        then()
           .log().all()
           .statusCode(200);

    }


    /*
     * oAuth 1.0
     *
     * Consumer Key (API key)
     * Consumer Secret (API Secret Key)
     * Access Token
     * Access Token Secret
     *
     */

    @Test
    public void post_A_Tweet() {

        String tweet = "This is a test tweet from Rest Assured";

        given()
            .baseUri("https://api.twitter.com./1.1")
            .auth().oauth("API key", "consumer secret", "access token", "secret token")
            .param("status", tweet).
        when()
            .post("/statuses/update.json").
        then()
            .log().all()
            .statusCode(200);
    }

    /*
     * oAuth 2.0
     *
     * Consumer Key (API key)
     * Consumer Secret (API Secret Key)
     * Access Token - On the fly
     *
     */

    @Test
    public void paypal_test() {

        String accessToken = given()
            .baseUri("https://api.sandbox.paypal.com")
            .contentType("application/x-www-form-urlencode;charset=UTF-8")
            .header("Accept-language","en_US")
            .param("grant_type", "client_credentials")
            .auth().preemptive().basic("username", "password").
        when()
            .post("/oauth2/token").
        then()
            .log().all()
            .statusCode(200).extract().path("access_token");

        // using access token for next requests

        //generate next invoice

        given()
            .baseUri("https://api.sandbox.paypal.com/v2")
            .contentType("application/json")
            .auth().oauth2(accessToken).
        when()
            .post("/invoicing/generate-next-invoice-number").
        then()
            .log().all()
            .statusCode(200);


    }

}
