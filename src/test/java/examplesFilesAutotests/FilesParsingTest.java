package examplesFilesAutotests;

import com.codeborne.pdftest.PDF;
import com.codeborne.selenide.Configuration;
import com.codeborne.xlstest.XLS;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.opencsv.CSVReader;
import examplesFilesAutotests.model.Glossary;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.InstanceOfAssertFactories.LIST;

public class FilesParsingTest {

    ClassLoader cl = FilesParsingTest.class.getClassLoader();
    @BeforeAll
    static void beforeAll() {
        Configuration.browser = "chrome";
        Configuration.browserSize = "1920x1080";
        Configuration.pageLoadTimeout = 50000;
        Configuration.pageLoadTimeout = 500000;
        Configuration.pageLoadStrategy = "eager";
    }

    @Test
    void checkPDF() throws Exception {
        open("https://junit.org/junit5/docs/5.0.2/user-guide/");
        File downloadPDF = $("a[href='index.pdf']").download();
        try (InputStream is = new FileInputStream(downloadPDF)) {
            PDF content = new PDF(downloadPDF);
            assertThat(content.author).contains("Sam Brannen");
        }
    }

    @Test
    void checkXLS() throws Exception {
        try(InputStream resourceAsStream = cl.getResourceAsStream("exampleFilesAutotests/file_example_XLS_10.xls")) {
            XLS content = new XLS(resourceAsStream);
            assertThat(content.excel.getSheetAt(0).getRow(0).getCell(1).toString()).contains("First Name");
        }
    }
    @Test
    void checkCSV() throws Exception {
        try(InputStream resource = cl.getResourceAsStream("exampleFilesAutotests/checkCSVmethod.csv");
            CSVReader reader = new CSVReader(new InputStreamReader(resource))
        ) {
            List<String[]> content = reader.readAll();
            assertThat(content.get(0)[1].contains("description"));
        }
    }

    @Test
    void checkZIPParseText() throws Exception {
        try(InputStream resource = cl.getResourceAsStream("exampleFilesAutotests/exampleArchive.zip");
            ZipInputStream zis = new ZipInputStream(resource)
        ) {
            ZipEntry entry;
            while((entry = zis.getNextEntry()) != null)
            {
                assertThat(entry.getName()).isEqualTo("i.png");
            }
        }
    }

    @Test
    void jsonParsText() throws Exception{
        Gson gson = new Gson();
        try(InputStream resource = cl.getResourceAsStream("exampleFilesAutotests/glossary.json");
            InputStreamReader reader = new InputStreamReader(resource)
        ) {
            JsonObject jsonObject = gson.fromJson(reader, JsonObject.class);
            assertThat(jsonObject.get("title").getAsString()).isEqualTo("example glossary");
            assertThat(jsonObject.get("GlossDiv").getAsJsonObject().get("title").getAsString()).isEqualTo("S");
            assertThat(jsonObject.get("GlossDiv").getAsJsonObject().get("flag").getAsBoolean()).isTrue();

            // Обычно используются модели для представления JSON-файла в класс
            // exampleFilesAutotests > model > Glossary
        }
    }

    @Test
    void jsonParsTextWithModel() throws Exception{
        Gson gson = new Gson();
        try(InputStream resource = cl.getResourceAsStream("exampleFilesAutotests/glossary.json");
            InputStreamReader reader = new InputStreamReader(resource)
        ) {
            Glossary jsonObject = gson.fromJson(reader, Glossary.class);
            assertThat(jsonObject.title).isEqualTo("example glossary");
            assertThat(jsonObject.glossDiv.title).isEqualTo("S");
            assertThat(jsonObject.glossDiv.flag).isTrue();

            // Обычно используются модели для представления JSON-файла в класс
            // exampleFilesAutotests > model > Glossary
        }
    }
}
