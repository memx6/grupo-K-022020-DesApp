package ar.edu.unq.desapp.grupoK.backenddesappapi.aspects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.Order;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;
import java.util.Arrays;

@Aspect
@Component
@Order(0)
public class LoggingAspect {
    private static final Logger LOGGER = LogManager.getLogger(LoggingAspect.class);
 
    //AOP expression for which methods shall be intercepted
    @Around("execution(* ar.edu.unq.desapp.grupoK.backenddesappapi.webservices.*.*(..))")
    public Object profileAllMethods(ProceedingJoinPoint proceedingJoinPoint) throws Throwable
    {
        MethodSignature methodSignature = (MethodSignature) proceedingJoinPoint.getSignature();
         
        //Get intercepted method details
        String className = methodSignature.getDeclaringType().getSimpleName();
        String methodName = methodSignature.getName();
        String parameters = Arrays.toString(proceedingJoinPoint.getArgs());

        final StopWatch stopWatch = new StopWatch();
         
        //Measure method execution time
        stopWatch.start();
        Object result = proceedingJoinPoint.proceed();
        stopWatch.stop();
 
        //Log method execution time
        LOGGER.info("Class: " + className + " Method: " + methodName + ", Args: " + parameters + " Execution time of :: " + stopWatch.getTotalTimeMillis() + " ms");
 
        return result;
    }
}