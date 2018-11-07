package mainpackage.dto;

public class ParamDTO {

    private int paramId;
    private String author;
    private String language;
    private String format;
    private ItemDTO item;

    public ParamDTO() {
    }

    public int getParamId() {
        return paramId;
    }

    public void setParamId(int paramId) {
        this.paramId = paramId;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public ItemDTO getItem() {
        return item;
    }

    public void setItem(ItemDTO item) {
        this.item = item;
    }

    @Override
    public String toString() {
        return "ParamDTO{" +
                "paramId=" + paramId +
                ", author='" + author + '\'' +
                ", language='" + language + '\'' +
                ", format='" + format + '\'' +
                ", item=" + item +
                '}';
    }
}
