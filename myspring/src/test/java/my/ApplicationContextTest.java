package my;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ApplicationContextTest {
    @Test
    public void t1() {
        String xmlFile = "service.xml";
        ApplicationContext ac = null;
        ac = new AnnotationConfigApplicationContext("my");
//        ac = new ClassPathXmlApplicationContext(xmlFile);
        Assert.assertNotNull(ac);

//        ac = new GenericXmlApplicationContext(xmlFile);
//        Assert.assertNotNull(ac);
    }
}
