package pageObject.pageObjectPattern.pages;

import com.codeborne.selenide.SelenideElement;
import pageObject.pageObjectPattern.pages.components.CalendarComponent;
import pageObject.pageObjectPattern.pages.components.RegistrationResultsModal;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.executeJavaScript;

public class RegistrationPage {
    CalendarComponent calendarComponent = new CalendarComponent();
    RegistrationResultsModal registrationResultsModal = new RegistrationResultsModal();
    private final String TITLE_TEXT = "Student Registration Form";
    private SelenideElement
            firstName = $("#firstName"),
            secondName = $("#lastName"),
            email = $("#userEmail"),
            gender = $("#genterWrapper"),
            phoneNumber = $("#userNumber"),
            dateOfBirthInput = $("#dateOfBirthInput");

    public RegistrationPage openPage() {
        open("/automation-practice-form");
        $(".practice-form-wrapper").shouldHave(text(TITLE_TEXT));
        executeJavaScript("$('#fixedban').remove()");
        executeJavaScript("$('footer').remove()");
        return this;
    }
    public RegistrationPage setFirstName (String value) {
        firstName.setValue(value);
        return this;
    }
    public RegistrationPage setSecondName (String value) {
        secondName.setValue(value);
        return this;
    }
    public RegistrationPage setEmail(String value) {
        email.setValue(value);
        return this;
    }
    public RegistrationPage setGender(String value) {
        gender.$(byText(value)).click();
        return this;
    }
    public RegistrationPage setNumber(String value) {
        phoneNumber.setValue(value);
        return this;
    }
    public RegistrationPage setBirthDate(String day, String month, String year) {
        dateOfBirthInput.click();
        calendarComponent.setDate(day,month,year);
        return this;
    }
    public RegistrationPage verifyResultsModalAppears() {
        registrationResultsModal.verifyModalAppears();
        return this;
    }
    public RegistrationPage verifyResult(String key, String value) {
        registrationResultsModal.veifyResult(key, value);
        return this;
    }
}
