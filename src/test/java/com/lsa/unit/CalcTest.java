package com.lsa.unit;

import com.lsa.Calculator;
import org.apache.commons.exec.OS;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CalcTest {
    private static Calculator calc;

    @BeforeAll
    static void setUp(){
        calc = new Calculator();
    }

    @ParameterizedTest
    @CsvSource(value = {"sum,2,3,5","sum,20,14,34", "sum,-2,9,7",
                        "minus,10,3,7","minus,5,7,-2", "minus,199,1,198",
                        "divide,10,3,3","divide,120,-3,-40", "divide,3,5,0",
                        "mul,2,2,4","mul,12,11,132", "mul,-2,-6,12"
        })
    void testCalc(String oper, int a, int b, int res) {
        Assumptions.assumingThat(oper.equalsIgnoreCase("sum"),
                ()-> assertThat(calc.sum(a,b), is(res))
        );
        Assumptions.assumingThat(oper.equalsIgnoreCase("minus"),
                ()-> assertThat(calc.minus(a,b), is(res))
        );
        Assumptions.assumingThat(oper.equalsIgnoreCase("divide"),
                ()-> assertThat(calc.divide(a,b), is(res))
        );
        Assumptions.assumingThat(oper.equalsIgnoreCase("mul"),
                ()-> assertThat(calc.multiply(a,b), is(res))
        );
    };
}
