package allure;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.Issue;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.logevents.SelenideLogger.step;
import static org.openqa.selenium.By.linkText;

public class StepsTests {
    private static final String REPOSITORY = "eroshenkoam/allure-example";
    private static final int ISSUE = 80;

    @BeforeAll
    static void beforeAll() {
        Configuration.browser = "chrome";
        Configuration.browserSize = "1920x1080";
        Configuration.pageLoadTimeout = 50000; // 50000

        Configuration.pageLoadStrategy = "eager"; //  eager || none || normal
        Configuration.holdBrowserOpen = true;
    }
    @Test
    public void testLambdaStep() {
        SelenideLogger.addListener("allure", new AllureSelenide());

        step("Открываем главную страницу", ()-> {
            open("https://github.com");
        });

        step("Ищем репозиторий " + REPOSITORY, ()-> {
            $(".search-input").click();
            $("#query-builder-test").sendKeys("eroshenkoam/allure-example");
            $("#query-builder-test").submit();
        });

        step("Кликаем по ссылке репозитория", ()-> {
            $(linkText("eroshenkoam/allure-example")).click();
        });

        step("Открываем таб Issues" , ()-> {
            $("#issues-tab").click();
        });

        step("Проверяем таб Issues с номером " + ISSUE, ()-> {
            $(withText("#" + ISSUE)).should(Condition.exist);
        });
    }
    @Test
    public void testAnnotatedStep() {
        WebSteps steps = new WebSteps();

        steps.openMainPage();
        steps.searchForRepository(REPOSITORY);
        steps.clickOnRepositoryLink(REPOSITORY);
        steps.openIssuesTab();
        steps.shouldSeeIssuesWithNumber(ISSUE);
    }
}
