package com.raven.munin.practice;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class HardAssertions {

    @Test
    public void testHardAssertion(){
        int actual1 =5;
        String actual2 ="10";
        Assertions.assertThat(actual1).isLessThan(4);
        Assertions.assertThat(actual2).isEqualTo("11");

    }
}
