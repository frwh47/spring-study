package my.web.aop;


import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;

public class MethodTimeCostInterceptor implements MethodInterceptor {
    private Logger logger = LoggerFactory.getLogger(MethodTimeCostInterceptor.class);

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        long start = System.currentTimeMillis();
        Object result = invocation.proceed();
        long end = System.currentTimeMillis();

        Method method = invocation.getMethod();
        String className = method.getDeclaringClass().getName();
        String methodName = method.getName();
        logger.info("execute {}.{} cost {} milliseconds ", className, methodName, (end - start));
        return result;
    }
}
