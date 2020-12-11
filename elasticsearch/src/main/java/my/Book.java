package my;

import java.util.Date;
import java.util.Random;
import java.util.UUID;

public class Book {
    private Integer id;
    private String name;
    private Date published;

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

    public Date getPublished() {
        return published;
    }

    public void setPublished(Date published) {
        this.published = published;
    }

    @Override
    public String toString() {
        return JsonUtil.toJson(this);
    }

    public static Book build() {
        Random random = new Random();
        Book book = new Book();
        book.setId(random.nextInt(10000));
        book.setName(UUID.randomUUID().toString());
        book.setPublished(new Date());
        return book;
    }
}