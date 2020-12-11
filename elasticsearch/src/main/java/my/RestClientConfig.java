package my;

import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;
import org.springframework.data.elasticsearch.config.AbstractElasticsearchConfiguration;

@Configuration
public class RestClientConfig extends AbstractElasticsearchConfiguration {
    public static final String ES_HOST = "192.168.1.181";

    @Override
    @Bean
    public RestHighLevelClient elasticsearchClient() {
        ClientConfiguration config = ClientConfiguration.builder()
                .connectedTo(ES_HOST + ":9200")
                .build();

        return RestClients.create(config).rest();
    }
}