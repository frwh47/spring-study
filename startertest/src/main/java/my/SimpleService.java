package my;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class SimpleService {
    private Logger log = LoggerFactory.getLogger(SimpleService.class);

    @Log
    public void test(int num) {
        System.out.println("----test---- " + num);
        log.info("{}", num);
    }

    @Log
    public void core(int num) {
        System.out.println("----core---- " + num);
    }

    public void work(int num) {
        System.out.println("----work---- " + num);
    }

}