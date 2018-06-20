package org.springframework.context.annotation;

import org.junit.Test;
import org.springframework.context.support.GenericApplicationContext;

public class ClassPathBeanDefinitionScannerTest {
    private GenericApplicationContext context = new GenericApplicationContext();
    private ClassPathBeanDefinitionScanner scanner = new ClassPathBeanDefinitionScanner(context);

    @Test
    public void test() {
        scanner.scan("my");
    }
}
