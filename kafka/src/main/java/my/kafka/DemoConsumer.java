package my.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class DemoConsumer {

    @KafkaListener(topics = Const.TOPIC)//"parkingRecordOut")
    public void handleCarOutMessage(String message) {
        System.out.println("receive: " + message);
    }
}