package my.kafka;

import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {
    public static final String TOPIC = "demo-topic-1";
    //demo-topic-1  parkingRecordOut parkingRecordIn

//    @Bean
//    public NewTopic create() {
//        return new NewTopic("foo2", 1, (short) 1);
//    }
}
