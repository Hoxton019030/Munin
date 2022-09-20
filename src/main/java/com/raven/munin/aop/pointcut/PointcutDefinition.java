package com.raven.munin.aop.pointcut;

import org.aspectj.lang.annotation.Pointcut;

/**
 * @author Hoxton
 * @version 1.1.0
 */
public class PointcutDefinition {

    @Pointcut("@within(org.springframework.web.bind.annotation.RestController)")
    public void applicationControllerPackage() {
    }
}
