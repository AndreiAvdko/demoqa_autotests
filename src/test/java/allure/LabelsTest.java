package allure;

import io.qameta.allure.*;
import org.apache.xmlbeans.impl.xb.xsdschema.All;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LabelsTest {
    @Test
    @Feature("Issue в репозитории")
    @Story("Создание Issue")
    @Owner("avdeenkoav")
    @Severity(SeverityLevel.BLOCKER)
    @Link(value = "Testing", url = "https://testing.github.com")
    @DisplayName("Создание Issue для авторизованного пользователя")
    public void testStaticLabels() {

    }

    @Test
    public void testDynamicLabels() {
        // Стоит применять для расширения существующих фреймворков
        Allure.getLifecycle().updateTestCase(t-> t.setName("Создание Issue для авторизованного пользователя"));
        // Пример использования
        if (true) {
            Allure.feature("Issue в репозитории");
        } else {
            Allure.feature("Issue не в репозитории");
        }
        Allure.feature("Issue в репозитории");
        Allure.story("Создание Issue");
        Allure.label("owner", "avdeenkoav");
        Allure.label("severity", SeverityLevel.CRITICAL.value());
        Allure.link("Testing", "https://testing.github.com");
    }
}
