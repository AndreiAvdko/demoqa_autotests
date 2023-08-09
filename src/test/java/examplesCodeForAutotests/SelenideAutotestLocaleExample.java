package examplesCodeForAutotests;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.open;

public class SelenideAutotestLocaleExample {

    @BeforeEach
    void setUp() {
        Configuration.pageLoadStrategy = "eager";
    }

    static Stream<Arguments> selenideSiteShouldContainAllButtonsForGivenLocale () {
        return Stream.of(
             Arguments.of(Locale.EN, List.of(
                                            "Quick start",
                                            "Docs",
                                            "FAQ",
                                            "Blog",
                                            "Javadoc",
                                            "Users",
                                            "Quotes")),
             Arguments.of(Locale.RU, List.of(
                                            "С чего начать?",
                                            "Док",
                                            "ЧАВО",
                                            "Блог",
                                            "Javadoc",
                                            "Пользователи",
                                            "Отзывы",
                                            "Мы говорим спасибо"))
        );
    }

    @MethodSource  // Обязывает написать метод выше со строго определённой сигнатурой
    @ParameterizedTest (name = "Для локали {0} отображаются кнопки {1}")
    @Tag("BLOCKER")
    void selenideSiteShouldContainAllButtonsForGivenLocale(Locale locale,
                                                           List<String> buttons) {
        open("https://ru.selenide.org");
        $$("#languages a").find(text(locale.name())).click();
        $$(".main-menu-pages a").shouldHave(CollectionCondition.texts(buttons));
    }
}
