package tests;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.Matchers.is;


/*
1. Make request to https://selenoid.autotests.cloud/status
2. Get response (in resources directory)
3. Check total is 20
*/

public class RestAssuredExamples {

    @Test
    void checkTotal() {
        //
        // given().when()
                get("https://selenoid.autotests.cloud/status")
                .then()
                .statusCode(200)
                .body("total", is(20));
    }

    @Test
    void checkTotal2() {
        given()
                // .log() можно размещать до и после выполнения
                // Возможные варианты
                // .log().uri()
                // .log().body()
                // log().status()
                .log().all()
                .when()
                .get("https://selenoid.autotests.cloud/status")
                .then()
                .statusCode(200)
                .body("total", is(20));
    }

    @Test
    void checkChromeVersion() {
        given()
                .log().uri()
                .when()
                .get("https://selenoid.autotests.cloud/status")
                .then()
                .log().status()
                .statusCode(200)
                .body("browsers.chrome", hasKey("100.0"));
    }


}
