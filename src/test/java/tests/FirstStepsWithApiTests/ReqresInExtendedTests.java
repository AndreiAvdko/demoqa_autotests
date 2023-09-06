package tests.FirstStepsWithApiTests;

import io.restassured.http.ContentType;
import models.LoginBodyModel;
import models.LoginResponseModel;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.assertj.core.api.Assertions.assertThat;

public class ReqresInExtendedTests {

    @Test
    void checkAuthWithApi() {
        LoginBodyModel loginbody = new LoginBodyModel();
        loginbody.setEmail("eve.holt@reqres.in");
        loginbody.setPassword("cityslicka");

        given().
                log().uri()
                .contentType(ContentType.JSON)
                .body(loginbody)
                .when()
                .post("https://reqres.in/api/login")
                .then()
                .log().status()
                .log().body()
                .statusCode(200);
    }

    @Test
    void checkAuthWithApiWithResponseModel() {
        LoginBodyModel loginBody = new LoginBodyModel();
        loginBody.setEmail("eve.holt@reqres.in");
        loginBody.setPassword("cityslicka");

        LoginResponseModel response = given().
                log().uri()
                .contentType(ContentType.JSON)
                .body(loginBody)
                .when()
                .post("https://reqres.in/api/login")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .extract().as(LoginResponseModel.class);

        // С использованием Junit
        assertEquals("QpwL5tke4Pnpja7X4", response.getToken());
        // Используем библиотеку AssertJ
        assertThat(response.getToken()).isEqualTo("QpwL5tke4Pnpja7X4");
    }


}
