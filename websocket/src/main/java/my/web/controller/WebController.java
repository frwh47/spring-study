package my.web.controller;


import my.web.model.Book;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.Random;

@RestController
public class WebController {

    @GetMapping("/book/{id}")
    public Book get(@PathVariable Integer id) {
        Book book = new Book();
        book.setId(id);
        book.setName("Spring");
        book.setAuthor("tom");

        Random ran = new Random();
        LocalDate date = LocalDate.now().minusDays(ran.nextInt(1000));
//        Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant())
        book.setPublish(date);
        return book;
    }

}
