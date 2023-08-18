package pageObject.pageObjectPattern;

import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
public class RegistrationWithPageObjectsTests extends TestBase {
    @Test
    void successfulRegistrationTest() {
        registrationPage.openPage()
                .setFirstName("Andrei")
                .setSecondName("Avdeenko")
                .setEmail("andrei_avdeenko@outlook.com")
                .setGender("Other")
                .setNumber("9999999999")
                .setBirthDate("09", "May","1997");




        $("#subjectsInput").setValue("Math").pressEnter();
        $("#hobbiesWrapper").$(byText("Sports")).click();
        $("#uploadPicture").uploadFromClasspath("pageObjectPattern/images.jfif");
        $("#currentAddress").setValue("Some address 1");
        $("#state").click();
        $("#stateCity-wrapper").$(byText("NCR")).click();
        $("#city").click();
        $("#stateCity-wrapper").$(byText("Delhi")).click();
        $("#submit").click();
        registrationPage.verifyResultsModalAppears();
        registrationPage.verifyResult("Student Name", "");

        $(".table-responsive").shouldHave(text("Andrei"), text("Avdeenko"),
                text("alex@egorov.com"), text("1234567890"));
    }
}