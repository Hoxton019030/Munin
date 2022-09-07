package com.raven.munin.aop;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Configuration;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.Date;

/**
 * @author Hoxton
 * @version 1.1.0
 */
@Aspect
@Configuration
public class LoggingAspect {
    private static final Logger logger = LogManager.getLogger(LoggingAspect.class);

    /**
     * @param joinPoint\
     * @return Object代表目標的回傳值
     */
    @Around("@within(org.springframework.web.bind.annotation.RestController)" )
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println(joinPoint);
        System.out.println(logger);
        logger.debug("Request for {}.{}() with arguments [s] = {}" ,joinPoint.getSignature().getDeclaringTypeName(),joinPoint.getSignature().getName(), Arrays.toString(joinPoint.getArgs()));
        logger.error("123");
//        System.out.println(123);
//        Instant start = Instant.now();
//        Object returnValue = joinPoint.proceed();
//        Instant finish = Instant.now();
//
//        long timeElapsed = Duration.between(start, finish).toMillis();
//
//        logger.debug("Response for {}.{} with Result ={}",joinPoint.getSignature().getDeclaringTypeName(),joinPoint.getSignature().getName(),returnValue);
//        logger.info("Time Taken =" + new SimpleDateFormat("mm:ss:SSS").format(new Date(timeElapsed)));
//        return returnValue;
        return null;
    }

}
