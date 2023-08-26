package allure;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.openqa.selenium.By.linkText;

public class WebSteps {
    @Step("Открываем главную страницу")
    public void openMainPage() {
        open("https://github.com");
    }
    @Step("Ищем репозиторий {repo}")
    public void searchForRepository(String repo) {
        $(".search-input").click();
        $("#query-builder-test").sendKeys("eroshenkoam/allure-example");
        $("#query-builder-test").submit();
    }

    @Step("Кликаем по ссылке репозитория ")
    public void clickOnRepositoryLink(String repository) {
        $(linkText("eroshenkoam/allure-example")).click();
    }
    @Step("Открываем таб Issues")
    public void openIssuesTab() {
        $("#issues-tab").click();
    }
    @Step("Проверяем таб Issues с номером {issue}")
    public void shouldSeeIssuesWithNumber(int issue) {
        $(withText("#" + issue)).should(Condition.exist);
    }

    @Attachment(value="Screenchot", type="image/png", fileExtension = "png")
    public byte[] takeScreenshot() {
        return ((TakesScreenshot) WebDriverRunner.getWebDriver()).getScreenshotAs(OutputType.BYTES);
    }
}
