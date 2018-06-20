package my;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ApplicationTest2 {
    @Test
    public void test() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext();
        ac.scan("my");
        for (String beanName : ac.getBeanDefinitionNames()) {
            System.out.println(beanName);
        }
    }
}