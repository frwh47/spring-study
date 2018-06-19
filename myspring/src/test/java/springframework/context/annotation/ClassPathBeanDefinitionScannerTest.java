package springframework.context.annotation;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;

import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;

public class ClassPathBeanDefinitionScannerTest {
    public static void main(String[] args) throws IOException {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        ClassPathBeanDefinitionScanner scanner = new ClassPathBeanDefinitionScanner(beanFactory);
        scanner.scan("my");


        for (String beanName : beanFactory.getBeanDefinitionNames()) {
            System.out.println(beanName + " == " + beanFactory.getBean(beanName));
        }

        ClassLoader cl = ClassPathBeanDefinitionScannerTest.class.getClassLoader();
        String path = "my";
        Enumeration<URL> urls = cl.getResources(path);
        while (urls.hasMoreElements()) {
            URL url = urls.nextElement();
            System.out.println("resource, url = " + url);
        }

        urls = cl.getSystemResources(path);
        while (urls.hasMoreElements()) {
            URL url = urls.nextElement();
            System.out.println("system resource, url = " + url);
        }
    }
}
