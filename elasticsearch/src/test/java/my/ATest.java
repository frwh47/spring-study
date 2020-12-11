package my;

import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.elasticsearch.core.query.IndexQueryBuilder;

import java.util.Date;

@SpringBootTest
public class ATest {
    private static final Logger log = LoggerFactory.getLogger(ATest.class);
    @Autowired
    RestHighLevelClient highLevelClient;

    RestClient lowLevelClient;

    @Autowired
    ElasticsearchOperations elasticsearchOperations;

    @BeforeEach
    void beforeEach() {
        Assertions.assertNotNull(highLevelClient);

        lowLevelClient = highLevelClient.getLowLevelClient();
        Assertions.assertNotNull(lowLevelClient);

        elasticsearchOperations.delete("5551", IndexCoordinates.of("book"));
    }

    @Test
    public void t() {
//        IndexRequestBuilder builder = new IndexRequestBuilder();
//        IndexRequest request = new IndexRequest("spring-data",
//                UUID.randomUUID().toString())
//                .source(singletonMap("feature", "high-level-rest-client"))
//                .setRefreshPolicy(IMMEDIATE);

//        IndexResponse response = highLevelClient.index(request);
    }

    @Test
    public void index() {
        Book book = Book.build();
        IndexQuery indexQuery = new IndexQueryBuilder()
                .withId(book.getId().toString())
                .withObject(book)
                .build();

        String documentId = elasticsearchOperations.index(indexQuery, IndexCoordinates.of("book"));
        log.info("{}", documentId);
    }

    @Test
    public void save() {
        Book book = Book.build();

        Book book2 = elasticsearchOperations.save(book, IndexCoordinates.of("book"));
        log.info("{}", book2);
    }

    @Test
    public void findById() {
        Book book = elasticsearchOperations.get("3178", Book.class, IndexCoordinates.of("book"));
        log.info("{}", book);
    }

    @Test
    public void testDate() {
        Date date = new Date();
        System.out.println(date);
        date.setTime(date.getTime() - 1000 * 60 * 60 * 24);
        System.out.println(date);
    }
}
