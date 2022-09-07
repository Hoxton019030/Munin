package com.raven.munin.controller;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Hoxton
 * @version 1.1.0
 */
class TestControllerTest {




    @Test
    void helloMunin() {
        TestController testController = new TestController();
        String actual = testController.helloMunin();
        assertEquals("Hello Munin",actual);

    }

    @Test
    void helloUser() {
    }
}