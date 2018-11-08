package mainpackage.model;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name="params")
public class Params {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "param_id")
    private int paramId;

    @Column(name = "author")
    private String author;

    @Column(name = "language")
    private String language;

    @Column(name = "format")
    private String format;

    @OneToOne(mappedBy = "params")
    @JsonIgnore
    private Items item;

    public Items getItem() {
        return item;
    }

    public void setItems(Items item) {
        this.item = item;
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
}
