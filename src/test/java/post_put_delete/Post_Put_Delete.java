package post_put_delete;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.annotations.Test;

import java.io.File;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class Post_Put_Delete {

    @Test
    public void post_request() {

        File file = new File("resources/create_employee.json");

        Response res = given()
                .baseUri("http://dummy.restapiexample.com/api/v1")
                .contentType(ContentType.JSON)
                .body(file).
            when()
                .post("/create");

        System.out.println("result of post response = " + res.print());

        int id = res.then()
                    .statusCode(200)
                    .body("data.name", equalTo("Marina"))
                    .extract()
                    .path("data.id");
        System.out.println("the ID is: " + id);

        /*int id = given()
            .baseUri("http://dummy.restapiexample.com/api/v1")
            .contentType(ContentType.JSON)
            .body(file).
        when()
            .post("/create").
        then()
            .statusCode(200)
            .body("data.name", equalTo("Marina"))
            .extract().path("data.id");
            System.out.println("Id is: " + id);*/
    }

    @Test
    public void post_request_json_object() {

        JSONObject objectBody = new JSONObject();
        objectBody.put("name", "Andy");
        objectBody.put("salary", 210000);
        objectBody.put("age", 24);

        Response res = given()
                .baseUri("http://dummy.restapiexample.com/api/v1")
                .contentType(ContentType.JSON)
                .body(objectBody.toString()).
                        when()
                .post("/create");

        System.out.println("result of response: " + res.print());

        int id = res.then()
                .statusCode(200)
                .body("data.name", equalTo("Andy"))
                .extract()
                .path("data.id");
        System.out.println("the ID is: " + id);

        /*int id = given()
                .baseUri("http://dummy.restapiexample.com/api/v1")
                .contentType(ContentType.JSON)
                .body(objectBody.toString()).
            when()
                .post("/create").
            then()
                .statusCode(200)
                .body("data.name", equalTo("Andy"))
                .extract().path("data.id");
        System.out.println("Id is: " + id);*/
    }

    @Test
    public void put_request_using_json_object() {
        JSONObject objectBody = new JSONObject();
        objectBody.put("name", "Sam");
        objectBody.put("salary", "1000");
        objectBody.put("age", 24);

        Response res = given()
                .baseUri("http://dummy.restapiexample.com/api/v1")
                .contentType(ContentType.JSON)
                .body(objectBody.toString()).
                        when()
                .put("/update/24");

        System.out.println("result of response: " + res.print());

        res.then()
                .statusCode(200);
    }

    @Test
    public void delete_request() {
        String msg = given()
            .baseUri("http://dummy.restapiexample.com/api/v1")
            .contentType(ContentType.JSON).
        when()
            .delete("/delete/24").
        then()
           .statusCode(200)
           .extract().path("message");

        System.out.println("The message is: " + msg);

    }
}
