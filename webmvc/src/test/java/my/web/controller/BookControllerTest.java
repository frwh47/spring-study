package my.web.controller;

import my.Application;
import my.web.model.Book;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class BookControllerTest {
    @Resource
    BookController bookController;

    @Test
    public void get() {
        bookController.get(1);
    }

    @Test
    public void update() {
        Book book = new Book();
        book.setId(123);
        book.setName("Spring");
        book.setAuthor("tom");
        bookController.update(book);
    }
}