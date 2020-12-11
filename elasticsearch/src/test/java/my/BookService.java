package my;

import java.util.HashSet;
import java.util.Set;

public class BookService {
    public Book[] distinct(Book[] books) {
        if (books == null) {
            return null;
        }
        Set<Book> dist = new HashSet<>();
        for (Book book : books) {
            dist.add(book);
        }
        return (Book[]) dist.toArray();
    }
}
