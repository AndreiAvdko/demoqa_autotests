import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.Matchers.is;


public class ReqresInTests {
/*
    1. Make POST request to https://reqres.in/api/login
        {
            "email": "eve.holt@reqres.in",
            "password": "cityslicka"
        }
    2. Get response
        {
            "token": "QpwL5tke4Pnpja7X4"
        }
    3. Check token is QpwL5tke4Pnpja7X4
*/

    @Test
    void loginTest() {
        String data = "{\"email\": \"eve.holt@reqres.in\", \"password\": \"cityslicka\"}";

        given().
                log().uri()
                // Необходимо указывать какого типа передаваемый запрос
                // В данном случае JSON
                .contentType(ContentType.JSON)
                .body(data)
                .when()
                .post("https://reqres.in/api/login")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("token", is("QpwL5tke4Pnpja7X4"));
    }

    @Test
    void unsupportedMediaTypeTest() {
        given().
                log().uri()
                .when()
                .post("https://reqres.in/api/login")
                .then()
                .log().status()
                .log().body()
                .statusCode(415);
    }

    @Test
    void missingEmailOrUsernameTest() {
        given().
                log().uri()
                .body("123")
                .when()
                .post("https://reqres.in/api/login")
                .then()
                .log().status()
                .log().body()
                .statusCode(400)
                .body("error", is("Missing email or username"));
    }

    @Test
    void missingPasswordTest() {
        String data = "{\"email\": \"eve.holt@reqres.in\"}";

        given().
                log().uri()
                // Необходимо указывать какого типа передаваемый запрос
                // В данном случае JSON
                .contentType(ContentType.JSON)
                .body(data)
                .when()
                .post("https://reqres.in/api/login")
                .then()
                .log().status()
                .log().body()
                .statusCode(400)
                .body("error", is("Missing password"));
    }

}
