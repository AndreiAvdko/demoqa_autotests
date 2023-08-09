package examplesCodeForAutotests;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class ParametrizedTests {
    /*@BeforeAll
    void configureBrowser(){

    }*/
    @BeforeEach
    void setUp() {
        Configuration.browser = "chrome";
        Configuration.browserSize = "1920x1080";
        Configuration.pageLoadTimeout = 50000; // 50000
        Configuration.pageLoadStrategy = "eager";

        open("https://www.google.com");

    }

    //
    //  Первый пример
    //
    @CsvSource({
            "Allure testops, https://qameta.io",
            "Selenide, https://ru.selenide.org"
    })

    // Пример использования с аннотацией @CsvFileSource
    // @CsvFileSource(resources = "/exampleCodeForAutotests")

    @ParameterizedTest(name = "Адрес {1} должен быть в выдаче гугла по запросу {0}")
    @Tags({@Tag("BLOCKER"), @Tag("UI_TEST")})
    void productSiteUrlShouldBePresentInResultOfSearchInGoogleByProductNameQuerry(
            String productName,
            String productUrl
    ){
        $("[name=q]").setValue(productName).pressEnter();
        $("[id=search]").shouldHave(text(productUrl));
    }

    //
    //  Второй пример
    //

    // Пример использования с @ValueSource - удобно использовать, когда для теста используется вектор значений
    @ValueSource(
            strings = { "Allure testops",
                        "Selenide" }
    )

    @ParameterizedTest(name = "Количество результатов выдачи в Гугл должно быть больше 5")
    @Tags({@Tag("BLOCKER"), @Tag("UI_TEST")})
    void howMuchSnippetsinSearchResultsAfterQuerryInGoogle( String productName ){
        $("[name=q]").setValue(productName).pressEnter();
        $$("div[class='g']").shouldHave(CollectionCondition.sizeGreaterThan(5));
    }

}

