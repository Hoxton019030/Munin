package com.raven.munin.constant;

/**
 * @author Hoxton
 * @version 1.1.0
 */
public class AOPCons {
    private static final String POINTCUT_DEFINITION_PATH=
            "com.raven.munin.aop.aspectJ.pointcut.PointcutDefinition.";

    public static final String POINTCUT_applicationControllerPackage =
            POINTCUT_DEFINITION_PATH+"applicationControllerPackage()";

    public static final int LOGGER_ORDER =1;
}
