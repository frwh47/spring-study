package my;

import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.search.fetch.subphase.FetchSourceContext;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;

import java.io.IOException;
import java.util.Date;
import java.util.Map;

@SpringBootTest
public class HighLevelApiTest {
    private static final Logger log = LoggerFactory.getLogger(my.ATest.class);
    @Autowired
    private RestHighLevelClient highLevelClient;
    @Autowired
    private ElasticsearchRestTemplate elasticsearchRestTemplate;


    @BeforeEach
    public void beforeEach() {
        Assertions.assertNotNull(highLevelClient);
        Assertions.assertNotNull(elasticsearchRestTemplate);
    }

    @Test
    public void get() throws IOException {
        GetRequest request = new GetRequest("book", "199");
        GetResponse result = highLevelClient.get(request, RequestOptions.DEFAULT);
        log.info("{}", result);

        String json = result.getSourceAsString();
        System.err.println(json);
        Book book = JsonUtil.fromJson(json, Book.class);
        System.err.println(book);

        Map<String, Object> source = result.getSource();
        for (Map.Entry<String, Object> entry : source.entrySet()) {
            log.info("{} - {}, {}", entry.getKey(), entry.getValue(), entry.getValue().getClass());
        }

        Object value = source.get("published");
        if (value instanceof Long) {
            log.info("published {}", new Date((long) value));
        }
    }

    @Test
    void getWithIncludes() throws IOException {
        GetRequest request = new GetRequest("book", "199");
        String[] includes = new String[]{"name"};
        FetchSourceContext fsc = new FetchSourceContext(true, includes, null);
        request.fetchSourceContext(fsc);
        GetResponse result = highLevelClient.get(request, RequestOptions.DEFAULT);
        log.info("{}", result);
    }
}
