package my.kafka;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;

@Component
public class DemoProducer {
    @Resource
    private KafkaTemplate template;

    @Scheduled(cron = "*/5 * * * * *")
    public void send() {
        String msg = "abc " + new Date();
        System.out.println("send: " + msg);
        template.send(Const.TOPIC, msg);
        template.flush();
    }
}
