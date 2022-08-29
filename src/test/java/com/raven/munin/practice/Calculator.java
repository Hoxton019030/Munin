package com.raven.munin.practice;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


public class Calculator {
    public int add(int number1, int number2) {
        return number1 + number2;
    }

    @Test
    public void testCase() {
        //1 Arrange
        int number1 = 5;
        int number2 = 10;
        int expected = 15;
        //2 Act
        int actual = add(number1, number2);
        //3 Assert
        assertThat(actual).isEqualTo(expected);

    }
}
