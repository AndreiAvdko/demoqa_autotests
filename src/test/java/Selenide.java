import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class Selenide {
    @Test
    void successfulSearchTest() {
        Configuration.browser = "chrome";
        Configuration.pageLoadTimeout = 50000;
        Configuration.headless = true;

        open("https://google.com/");
        $("[name=q]").setValue("selenide").pressEnter();
        $("[id=search]").shouldHave(text("https://ru.selenide.org"));
    }
}