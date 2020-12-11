package my;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class BookTest {

    @Test
    public void build() {
        Book book = Book.build();
        String json = book.toString();

        Book book2 = JsonUtil.fromJson(json, Book.class);

        Assertions.assertEquals(book.getId(), book2.getId());
        Assertions.assertEquals(book.getName(), book2.getName());
        Assertions.assertEquals(book.getPublished(), book2.getPublished());
    }
}