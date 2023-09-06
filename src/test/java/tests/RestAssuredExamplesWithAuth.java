package tests;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.Matchers.is;

/*
1. Make request to https://selenoid.autotests.cloud/wd/hub/status
2. Get response {"value":{"message":"Selenoid 1.10.7 built at 2021-11-21_05:46:32AM","ready":true}}
3. Check value.ready is true
*/

public class RestAssuredExamplesWithAuth {
    @Test
    public void checkWDHubStatusWithoutAuth() {
        given()
                .log().uri()
                .when()
                .get("https://selenoid.autotests.cloud/wd/hub/status")
                .then()
                .log().status()
                .statusCode(401);
    }

    @Test
    public void checkWDHubStatusWithAuth() {
        given()
                .log().uri()
                .auth().basic("user1", "1234")
                .when()
                .get("https://selenoid.autotests.cloud/wd/hub/status")
                .then()
                .log().status()
                .statusCode(200)
                .body("value.ready", is(true));
    }
}
