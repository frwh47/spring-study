package my.web.aop;

import org.aopalliance.aop.Advice;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.Pointcut;
import org.springframework.aop.support.AbstractPointcutAdvisor;
import org.springframework.aop.support.annotation.AnnotationMatchingPointcut;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;

import javax.annotation.PostConstruct;

@Configuration
public class ControllerTimeConfiguration extends AbstractPointcutAdvisor {
    private Logger logger = LoggerFactory.getLogger(ControllerTimeConfiguration.class);
    private Pointcut pointcut;
    private Advice advice;

    @PostConstruct
    public void postConstruct() {
        this.pointcut = new AnnotationMatchingPointcut(Controller.class, true);
        this.advice = new MethodTimeCostInterceptor();
        logger.info("pointcut={}, advice={}", pointcut, advice);
    }

    @Override
    public Pointcut getPointcut() {
        return pointcut;
    }

    @Override
    public Advice getAdvice() {
        return advice;
    }
}