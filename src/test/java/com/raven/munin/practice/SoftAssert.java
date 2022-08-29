package com.raven.munin.practice;

import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;

public class SoftAssert {

    @Test
    public void testSoftAssertion(){
        int actual1=5;
        String actual2 = "10";
        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(actual1).isLessThan(4);
        softAssertions.assertThat(actual2).isEqualTo("11");
        softAssertions.assertAll();
    }
}
