package com.raven.munin.aop.aspect;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.Date;

import static com.raven.munin.constant.AOPCons.LOGGER_ORDER;
import static com.raven.munin.constant.AOPCons.POINTCUT_applicationControllerPackage;

/**
 * @author Hoxton
 * @version 1.1.0
 */
@Aspect
@Configuration
@Order(LOGGER_ORDER)
public class LoggingAspect {
    private static final Logger logger = LogManager.getLogger(LoggingAspect.class);


    @Pointcut("@within(org.springframework.web.bind.annotation.RestController)")
    public void applicationControllerPackage() {
    }

    /**
     * @param joinPoint\
     * @return Object代表目標的回傳值
     */
    @Around(value ="com.raven.munin.aop.pointcut.PointcutDefinition.applicationControllerPackage()")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        logger.debug("Request for {}.{}() with arguments [s] = {}", joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName(), Arrays.toString(joinPoint.getArgs()));
        logger.info("logAroundxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
        Instant start = Instant.now();

        Object returnValue = joinPoint.proceed();

        Instant finish = Instant.now();
        long timeElapsed = Duration.between(start, finish).toMillis();
        logger.debug("Response for {}.{} with Result ={}", joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName(), returnValue);
        logger.info("Time Taken =" + new SimpleDateFormat("mm:ss:SSS").format(new Date(timeElapsed)));
        return returnValue;
    }

    @Pointcut("within(com.raven.munin.model.service..* )")
    public void applicationExceptionPackage(){
    }

    @AfterThrowing(pointcut = "applicationExceptionPackage()",throwing = "exception")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable exception) {
        logger.error("Exception in {}.{} with cause = {} , with message = {}",
                joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName(),
                exception.getCause() != null ? exception.getCause() : "NULL",
                exception.getMessage() != null ? exception.getMessage() : "NULL");
    }

}
