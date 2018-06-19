package my;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class SimpleService {
    private Logger log = LoggerFactory.getLogger(SimpleService.class);

    public String say(String name) {
        return "hello " + name;
    }
}