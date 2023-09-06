package tests.SpecificationExamples;

import org.junit.jupiter.api.Test;

public class UserTest {
    @Test
    public void singleUser() {
        Specs.request
                .when()
                .get("/users/2")
                .then()
                .log()
                .body();
    }

    @Test
    public void listOfUsers() {
        Specs.request
                .when()
                .get("/users?page=2")
                .then()
                .log()
                .body();
    }
}
