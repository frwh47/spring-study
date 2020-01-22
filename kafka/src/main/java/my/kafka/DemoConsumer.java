package my.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.stereotype.Component;

@Component
public class DemoConsumer {
    private Logger log = LoggerFactory.getLogger(DemoConsumer.class);

    @KafkaListener(topics = {Config.TOPIC})
    public void receive(String message) {
//        log.info("receive <== " + message);
    }

    @KafkaListener(topicPartitions = {
            @TopicPartition(topic = Config.TOPIC, partitions = {"0", "1", "2"})
    })
    public void listen2(ConsumerRecord record) {
//    enable-auto-commit: true
        log.info("partition = {}, offset = {}, value = {}",
                record.partition(), record.offset(), record.value());
    }
}