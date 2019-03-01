package my.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import javax.annotation.Resource;
import java.util.Date;

@Component
public class DemoProducer {
    private Logger log = LoggerFactory.getLogger(DemoProducer.class);

    @Resource
    private KafkaTemplate kafkaTemplate;

    @Scheduled(cron = "*/5 * * * * *")
    public void send() {
        String msg = "abc " + new Date();
        ListenableFuture<SendResult<Integer, String>> future = kafkaTemplate.send(Const.TOPIC, msg);
        future.addCallback(new ListenableFutureCallback<SendResult<Integer, String>>() {
            @Override
            public void onSuccess(SendResult<Integer, String> result) {
                log.debug("send success {}", result.getProducerRecord().value());
            }

            @Override
            public void onFailure(Throwable ex) {
                log.error("send fail {}", ex.getMessage(), ex);
            }
        });
    }
}
