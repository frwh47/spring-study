package my.web.aop;

import org.aopalliance.aop.Advice;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.Pointcut;
import org.springframework.aop.support.AbstractPointcutAdvisor;
import org.springframework.aop.support.annotation.AnnotationMatchingPointcut;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@ConditionalOnClass
@Configuration
public class ServiceTimeConfiguration extends AbstractPointcutAdvisor {
    private Logger logger = LoggerFactory.getLogger(ServiceTimeConfiguration.class);
    private Pointcut pointcut;
    private Advice advice;

    @PostConstruct
    public void postConstruct() {
        this.pointcut = AnnotationMatchingPointcut.forClassAnnotation(Service.class);
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