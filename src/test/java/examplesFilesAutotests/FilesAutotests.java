package examplesFilesAutotests;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.FileDownloadMode;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Collection;

import static com.codeborne.selenide.Selenide.*;
import static org.assertj.core.api.Assertions.assertThat;


public class FilesAutotests {

    // Настройка для случая, если у кнопки для загрузки ресурса
    // нет атрибута href
    /* static {
        Configuration.fileDownload = FileDownloadMode.PROXY;
        Configuration.downloadFolder = "Путь куда скачивать файлы";
    } */

    @BeforeAll
    static void beforeAll() {
        Configuration.browser = "chrome";
        Configuration.browserSize = "1920x1080";
        Configuration.pageLoadTimeout = 50000;
        Configuration.pageLoadTimeout = 500000;
        Configuration.pageLoadStrategy = "eager";
    }

    @Test
    void selenideDownloadTest() throws IOException {
        open("https://github.com/junit-team/junit5/blob/main/README.md");
        // Файлы скачиваются в build>downloads> __sessionIDfolder__ > file
        File downloadedFile = $("[data-testid=raw-button]").download();
        // Для чтения файла Inpu
        try (InputStream is = new FileInputStream(downloadedFile)) {
            byte[] bytes = is.readAllBytes();
            String textContent = new String(bytes, StandardCharsets.UTF_8);
            assertThat(textContent).contains("This repository is the home of JUnit 5.");
        }
    }
    @Test
    void selenideUploadFile() {
        open("https://fineuploader.com/demos.html");
        // Если элемент для загрузки файла input[type=file] скрыт
        // Можно с помощью JavaScript кода сделать его видимым и затем обратиться к нему:
        // executeJavaScript("");
        $("input[type=file]").uploadFromClasspath("exampleFilesAutotests/images.png");
        System.out.println("");
        $("div.qq-file-name").shouldHave(   Condition.text("images.png"));
    }
}