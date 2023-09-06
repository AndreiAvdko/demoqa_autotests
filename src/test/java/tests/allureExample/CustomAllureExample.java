package tests.allureExample;

import io.restassured.http.ContentType;
import models.LoginBodyModel;
import models.LoginResponseModel;
import org.junit.jupiter.api.Test;

import static helpers.CustomApiListener.withCustomTemplates;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CustomAllureExample {
    @Test
    void loginWithAllureTest() {
        LoginBodyModel loginBody = new LoginBodyModel();
        loginBody.setEmail("eve.holt@reqres.in");
        loginBody.setPassword("cityslicka");

        LoginResponseModel response = given()
                .filter(withCustomTemplates())
                //.log().uri()
                //.log().body()
                .contentType(ContentType.JSON)
                .body(loginBody)
                .when()
                .post("https://reqres.in/api/login")
                .then()
                //.log().status()
                //.log().body()
                .statusCode(200)
                .extract().as(LoginResponseModel.class);

        // С использованием Junit
        assertEquals("QpwL5tke4Pnpja7X4", response.getToken());
        // Используем библиотеку AssertJ
        assertThat(response.getToken()).isEqualTo("QpwL5tke4Pnpja7X4");
    }
}