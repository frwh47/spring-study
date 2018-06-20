package my.web.controller;


import my.web.model.Book;
import my.web.service.BookService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class BookController {
    @Resource
    private BookService bookService;

    @GetMapping("/book/{id}")
    public Book get(@PathVariable Integer id) {
        return bookService.get(id);
    }

    public void update(Book book) {
        bookService.update(book);
    }

}
