package my;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.*;

@SpringBootTest
public class ElasticSearchSpringApiTest {
    @Autowired
    private ElasticsearchOperations elasticsearchOperations;

    @Test
    public void save() {
        Assertions.assertNotNull(elasticsearchOperations);

        Book book = Book.build();
        IndexQuery indexQuery = new IndexQueryBuilder()
//                .withId(book.getId().toString())
                .withObject(book)
                .build();
        String documentId = elasticsearchOperations.index(indexQuery, IndexCoordinates.of("book"));
        Assertions.assertNotNull(documentId);
        System.out.println(documentId);
        System.out.println(book);
    }

    @Test
    public void get() {
        int id = 199;
        Book book = elasticsearchOperations.get(String.valueOf(id), Book.class, IndexCoordinates.of("book"));
        System.out.println(book);
        Assertions.assertNotNull(book);
        Assertions.assertEquals(id, book.getId());
    }

    @Test
    public void update() {
        Book book = Book.build();
        book.setId(199);
        book.setName("elastic search in action");
        IndexQuery indexQuery = new IndexQueryBuilder()
//                .withId(book.getId().toString())
                .withObject(book)
                .build();
        String documentId = elasticsearchOperations.index(indexQuery, IndexCoordinates.of("book"));
        Assertions.assertNotNull(documentId);
        System.out.println(book);
    }


    @Test
    public void search() {
        Criteria criteria = new Criteria("name").contains("889b0e40")
                .and("id").is("9995");
        Query query = new CriteriaQuery(criteria);
        SearchHits<Book> hints = elasticsearchOperations.search(query, Book.class, IndexCoordinates.of("book"));

        for (SearchHit<Book> hit : hints.getSearchHits()) {
            Book book2 = hit.getContent();
            System.out.println(book2);
        }
    }

    @Test
    public void search2() {
        Criteria criteria = new Criteria("id").lessThan("1000");
        Query query = new CriteriaQuery(criteria);
        SearchHits<Book> hints = elasticsearchOperations.search(query, Book.class, IndexCoordinates.of("book"));

        for (SearchHit<Book> hit : hints.getSearchHits()) {
            Book book2 = hit.getContent();
            System.out.println(book2);
        }
    }
}