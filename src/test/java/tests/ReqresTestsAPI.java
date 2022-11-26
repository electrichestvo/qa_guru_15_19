package tests;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.core.Is.is;

public class ReqresTests {

    @Test
    void checkEmailUser() {
        given()
                .log().uri()
                .log().body()
                .when()
                .get("https://reqres.in/api/users?page=2")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("data[1].email", is("michael.lawson@reqres.in"));
    }

    @Test
    void checkCreateUser() {
        String body = "{ \"name\": \"morpheus\", \"job\": \"leader\" }";
        given()
                .log().uri()
                .log().body()
                .contentType(JSON) //тип контента
                .body(body)
                .when()
                .post("https://reqres.in/api/users")
                .then()
                .log().status()
                .log().body()
                .statusCode(201)
                .body("name", is("morpheus"))
                .body("job", is("leader"));
    }
    @Test
    void checkUpdateUserPut() {
        String body = "{ \"name\": \"morpheus\", \"job\": \"zion resident\"\n}";
        given()
                .log().uri()
                .log().body()
                .contentType(JSON) //тип контента
                .body(body)
                .when()
                .put("https://reqres.in/api/users/2")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("name", is("morpheus"))
                .body("job", is("zion resident"));
    }

    @Test
    void checkUpdateUserPatch() {
        String body = "{ \"name\": \"morpheus\", \"job\": \"zion\"\n}";
        given()
                .log().uri()
                .log().body()
                .contentType(JSON) //тип контента
                .body(body)
                .when()
                .patch("https://reqres.in/api/users/2")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("name", is("morpheus"))
                .body("job", is("zion"));
    }

    @Test
    void checkDeleteUser() {
        given()
                .log().uri()
                .when()
                .delete("https://reqres.in/api/users/2")
                .then()
                .log().status()
                .log().body()
                .statusCode(204);
    }

    @Test
    void checkNegativUserNot() {
        given()
                .log().uri()
                .when()
                .get("https://reqres.in/api/users/23")
                .then()
                .log().status()
                .log().body()
                .statusCode(404);
    }
}



