package my.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class DemoConsumer {
    private Logger log = LoggerFactory.getLogger(DemoConsumer.class);

    @KafkaListener(topics = Const.TOPIC)
    public void receive(String message) {
        log.info(message);
    }
}