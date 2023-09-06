package tests.FirstStepsWithApiTests;

import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class BadPractice {
    // ПЛОХАЯ ПРАКТИКА НАПИСАНИЯ АВТОТЕСТА
    // ТАК НИЗЯ
    // tests.FirstStepsWithApiTests.BadPractice
    @Test
    void checkResponseBadPractice() {
        String expectedResponseString = "{\"total\":20,\"used\":0,\"queued\":0,\"pending\":0,\"browsers\"" +
                ":{\"android\":{\"8.1\":{}}," +
                "\"chrome\":{\"100.0\":{},\"99.0\":{}}," +
                "\"chrome-mobile\":{\"86.0\":{}}," +
                "\"firefox\":{\"97.0\":{},\"98.0\":{}}," +
                "\"opera\":{\"84.0\":{},\"85.0\":{}}}}\n";

        Response actualResponse = given()
                .log().uri()
                .when()
                .get("https://selenoid.autotests.cloud/status")
                .then()
                .log().status()
                .statusCode(200)
                .extract().response();

        assertEquals(expectedResponseString, actualResponse.asString());
    }
}
