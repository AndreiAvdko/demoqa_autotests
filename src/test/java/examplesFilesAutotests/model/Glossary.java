package examplesFilesAutotests.model;

import com.google.gson.annotations.SerializedName;

public class Glossary {
    public String title;

    // Если написано название поля с маленькой буквы, а в JSON-файле оно с большой
    // то нужно использовать след. аннотацию (В скобках название объекта в JSON):
    @SerializedName("GlossDiv")
    public GlossDiv glossDiv;
    public static class GlossDiv {
        public String title;
        public Boolean flag;
    }
}
