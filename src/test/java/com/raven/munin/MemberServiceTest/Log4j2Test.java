package com.raven.munin.MemberServiceTest;

import com.raven.munin.controller.TestController;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;

/**
 * @author Hoxton
 * @version 1.1.0
 */
@Slf4j
public class Log4j2Test {

    public static final Logger LOGGER = LogManager.getLogger(Log4j2Test.class);

    @Test
    public void testQuick() throws Exception {
        LOGGER.fatal("fatal");
        LOGGER.error("error");
        LOGGER.trace("trace");
        LOGGER.info("123");
    }

    @Test
    public void test(){
        TestController testController = new TestController();
        log.info("123");
        String s = testController.helloMunin();
    }

}
