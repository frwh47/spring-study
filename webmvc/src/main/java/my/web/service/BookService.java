package my.web.service;

import my.web.model.Book;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Random;

@Service
public class BookService {
    private Logger log = LoggerFactory.getLogger(BookService.class);

    public Book get(Integer id) {
        Book book = new Book();
        book.setId(id);
        book.setName("Spring");
        book.setAuthor("tom");

        Random ran = new Random();
        LocalDate date = LocalDate.now().minusDays(ran.nextInt(1000));
//        Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant())
        book.setPublish(date);

        log.info("{}", book);
        return book;
    }

    public int update(Book book) {
        get(book.getId());
        return 0;
    }
}
