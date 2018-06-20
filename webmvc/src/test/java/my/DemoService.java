package my;

import org.springframework.stereotype.Service;

@Service
public class DemoService {
    public DemoService() {
        throw new RuntimeException("failed");
    }
}
