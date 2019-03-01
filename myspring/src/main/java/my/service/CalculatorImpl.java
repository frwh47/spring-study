package my.service;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class CalculatorImpl implements Calculator, InitializingBean {
    public CalculatorImpl() {
        System.out.println("CalculatorImpl ...");
    }

    @PostConstruct
    public void postConstruct() {
        System.out.println("PostConstruct ...");
    }

    @Override
    public int add(int a, int b) {
        return a + b;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("afterPropertiesSet ...");
    }
}
