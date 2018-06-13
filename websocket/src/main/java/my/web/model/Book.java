package my.web.model;

import java.time.LocalDate;

public class Book {
    private Integer id;
    private String name;
    private String author;
    private LocalDate publish;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public LocalDate getPublish() {
        return publish;
    }

    public void setPublish(LocalDate publish) {
        this.publish = publish;
    }
}
