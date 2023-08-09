package com.demoqa;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.*;
import java.io.File;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;


// Правильное название для теста через @DisplayName("Описание теста")   -- (__Описание попадёт в Allure__)

// Игнорирование прохождения теста через @Disabled("JIRA-номер задачи")

// @Tags({@Tags("Blocker"), @Tag("UI_TEST")})
public class AutotestsForPracticeFormWithSelenide {

    @BeforeAll
    static void beforeAll() {
        Configuration.browser = "chrome";
        Configuration.browserSize = "1920x1080";
        Configuration.pageLoadTimeout = 50000; // 50000

        Configuration.pageLoadStrategy = "eager"; //  eager || none || normal
        Configuration.holdBrowserOpen = true;
        // Configuration.headless = true;
        // Configuration.baseUrl = "https://demoqa.com";
    }


    @Test
    @DisplayName("Проверка заполнения формы с личными данными")
    @Tags({@Tag("BLOCKER"), @Tag("UI_TEST")})
    void successfulSearchTest() {
        //setTimeout(function(){debugger;}, 4000); - настройка брейкпоинта в браузере
        open("https://demoqa.com/automation-practice-form/");

        // Имя, фамилия, почта
        $("#firstName").setValue("Andrei");
        $("#lastName").setValue("Avdeenko");
        $("#userEmail").setValue("andrei-avdeenko@outlook.com");

        // Пол
        $("#gender-radio-3").doubleClick();

        // Номер телефона
        $("#userNumber").setValue("89099990990");

        // Календарь
        $("#dateOfBirthInput").click();
        $("[class=react-datepicker__month-select]").selectOption(4);
        $("[class=react-datepicker__year-select]").selectOptionContainingText("1997");
        $("#dateOfBirth-label").click(); // сбрасываем фокус, закрываем календарь

        // Выбор дисциплин
        $(".subjects-auto-complete__value-container").click();
        $("[aria-live=polite]").exists();
        actions().sendKeys("P").perform();
        $(byText("Physics")).click();

        // Выбор хобби
        $("[for=hobbies-checkbox-1]").click();
        $("[for=hobbies-checkbox-2]").click();
        $("[for=hobbies-checkbox-3]").click();
        // Выбор хобби

        // Загрузка файлов
        $("input#uploadPicture").uploadFile(new File("src/test/resources/images.jfif"));
        $("#currentAddress").setValue("г.Санкт-Петербург, Репищева ул., 1");

        // Выбор страны
        $(byText("Select State")).scrollTo();
        $(byText("Select State")).click();
        $(byText("Uttar Pradesh")).click();

        // Выбор города
        $(byText("Select City")).shouldBe(enabled);
        $(byText("Select City")).click();
        $(byText("Lucknow")).click();

        // Отправка формы
        $("#submit").scrollTo();
        $("#submit").click();

        // Проверка заполненных полей
        $$("table tbody tr td+td").get(0).shouldBe(text("Andrei Avdeenko"));
        $$("table tbody tr td+td").get(1).shouldBe(text("andrei-avdeenko@outlook.com"));
        $$("table tbody tr td+td").get(2).shouldBe(text("Other"));
        $$("table tbody tr td+td").get(3).shouldBe(text("8909999099"));
        $$("table tbody tr td+td").get(4).shouldBe(text("08 August,2023"));
        $$("table tbody tr td+td").get(5).shouldBe(text("Physics"));
        $$("table tbody tr td+td").get(6).shouldBe(text("Sports, Reading, Music"));
        $$("table tbody tr td+td").get(7).shouldBe(text("com.demoqa/images.jfif"));
        $$("table tbody tr td+td").get(8).shouldBe(text("г.Санкт-Петербург, Репищева ул., 1"));
        $$("table tbody tr td+td").get(9).shouldBe(text("Uttar Pradesh Lucknow"));

        /* Iterator<SelenideElement> element = $$("table tbody tr td+td").iterator();
        while (element.hasNext())
        {
            String str = element.next().getText();
            String st = "wefw";
        } */
    }
}