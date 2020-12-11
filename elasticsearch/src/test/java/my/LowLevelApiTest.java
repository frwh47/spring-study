package my;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.message.BasicHeader;
import org.elasticsearch.client.*;
import org.junit.jupiter.api.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class LowLevelApiTest {
    private static final Logger log = LoggerFactory.getLogger(LowLevelApiTest.class);
    private static RestClient restClient;

    @BeforeAll
    static void beforeAll() {
        RestClientBuilder builder = RestClient.builder(
                new HttpHost(RestClientConfig.ES_HOST, 9200, "http")
        );

        Header[] defaultHeaders = new Header[]{new BasicHeader("header", "value")};
        builder.setDefaultHeaders(defaultHeaders);

        builder.setFailureListener(new RestClient.FailureListener() {
            @Override
            public void onFailure(Node node) {
                System.out.println(node.getName() + " failed");
            }
        });

        builder.setNodeSelector(NodeSelector.SKIP_DEDICATED_MASTERS);

        builder.setRequestConfigCallback(new RestClientBuilder.RequestConfigCallback() {
            @Override
            public RequestConfig.Builder customizeRequestConfig(
                    RequestConfig.Builder requestConfigBuilder) {
                return requestConfigBuilder
                        .setConnectTimeout(5 * 1000)
                        .setSocketTimeout(5 * 1000);
            }
        });

        restClient = builder.build();
        Assertions.assertNotNull(restClient);
    }

    @AfterAll
    static void afterAll() throws IOException, InterruptedException {
        TimeUnit.SECONDS.sleep(1);
        restClient.close();
    }

    @Test
    public void t1() throws IOException {
        Request request = new Request(
                "GET",
                "/");
        Response response = restClient.performRequest(request);
        log.info("{}", response);

        request = new Request(
                "GET",
                "/");
        Cancellable cancellable = restClient.performRequestAsync(request,
                new ResponseListener() {
                    @Override
                    public void onSuccess(Response response) {
                        log.info("{}", response);
                    }

                    @Override
                    public void onFailure(Exception ex) {
                        log.error(ex.getMessage(), ex);
                    }
                });
    }

    @Disabled
    @Test
    public void create() throws IOException {
        Request request = new Request(
                "PUT",
                "/book");
        Response response = restClient.performRequest(request);
        log.info("{}", response);
    }

    @Test
    public void insert() throws IOException {
        Book book = Book.build();
        Request request = new Request(
                "POST",
                "/book/_doc/" + book.getId());

        request.setJsonEntity(book.toString());
        Response response = restClient.performRequest(request);
        log.info("{}", response);
    }

    @Test
    public void get() throws IOException {
        Request request = new Request(
                "GET",
                "/book/_doc/3178");

        Response response = restClient.performRequest(request);
        log.info("{}", response);
        HttpEntity entity = response.getEntity();
    }


}
