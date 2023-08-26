package allure;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.openqa.selenium.By.linkText;

public class SelenideTest {

    // 1. Подключить в плагины alure report
    // 2. Добавить в файл конфигурацию для allure ( https://github.com/allure-framework/allure-gradle#readme )
    // 3. После запуска тестов начинает появляться директория директории build/allure-result
    // с результатами о прохождении теста в формате json
    // 4. При вызове таски allureServe в меню Gradle генерируется отчет
    // 5. Подключаем интеграцию с фреймворком, в нашем случае с Selenide (в поиске allure selenide):
    // 6. Добавляем SelenideLogger.addListener("allure", new AllureSelenide());
    // 7. В отчете (Вкладка Test Suite) появляется вкладочка Test body c шагами
    //

    @BeforeAll
    static void beforeAll() {
        Configuration.browser = "chrome";
        Configuration.browserSize = "1920x1080";
        Configuration.pageLoadTimeout = 50000; // 50000

        Configuration.pageLoadStrategy = "eager"; //  eager || none || normal
        Configuration.holdBrowserOpen = true;
    }
    @Test
    public void selenideIsssueSearch() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        open("https://github.com");
        $(".search-input").click();
        $("#query-builder-test").sendKeys("eroshenkoam/allure-example");
        $("#query-builder-test").submit();
        $(linkText("eroshenkoam/allure-example")).click();
        $("#issues-tab").click();
        $(withText("#80")).should(Condition.exist);

    }
}
