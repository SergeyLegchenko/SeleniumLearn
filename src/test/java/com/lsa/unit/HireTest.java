package com.lsa.unit;

import com.lsa.Hire;
import org.aeonbits.owner.ConfigFactory;
import org.apache.commons.exec.OS;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class HireTest {
    private static Hire hire;

    @BeforeAll
    static void setUp(){
        hire = new Hire();
    }

//    @Test
    @RepeatedTest(3)
    @Disabled
    void test1() {
//        Assumptions.assumeTrue(OS.isFamilyWindows());
        Assumptions.assumeTrue(OS.isFamilyUnix());
        System.out.println(Math.random());
    }

    @ParameterizedTest                          
    @ValueSource(ints = {15, 16, 17})
    void test2(int age) {
        Assumptions.assumingThat(OS.isFamilyWindows(),
                ()-> assertTrue(hire.isHired(age).equalsIgnoreCase("half"))
        );

        Assumptions.assumingThat(OS.isFamilyUnix(),
                ()-> assertTrue(hire.isHired(age).equalsIgnoreCase("yes"))
        );

    }

    @ParameterizedTest
    @CsvSource(value = {"15#half","20#yes", "4#no"}, delimiter = '#', emptyValue = "1")
    void test3(int age, String responce) {
                Assertions.assertAll(
                        ()-> assertTrue(hire.isHired(age).equalsIgnoreCase(responce)),
                        ()-> assertTrue(responce.equalsIgnoreCase("half")),
                        ()->Assertions.assertEquals(5,5)
                );
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/testData.csv", delimiter = '$', numLinesToSkip = 1)
    void test4(int age, String responce) {
//                assertTrue(hire.isHired(age).equalsIgnoreCase(responce));
                assertThat(hire.isHired(age), is(responce));
    }

    @ParameterizedTest
//    @EnumSource(value = TestEnum.class, names = {"CASE_HALF"}, mode = EnumSource.Mode.EXCLUDE)
    @EnumSource(value = TestEnum.class, names = {".*_YES", ".*_NO"}, mode = EnumSource.Mode.MATCH_ANY)
    void test5(TestEnum data) {
        assertTrue(hire.isHired(data.getAge()).equalsIgnoreCase(data.getResponse()));
    }

    @ParameterizedTest
    @MethodSource(value = "getData")
    void test6(int age, String responce) {
        assertTrue(hire.isHired(age).equalsIgnoreCase(responce));
    }

    @ParameterizedTest
    @ArgumentsSource(value = ArgsProv.class)
    void test7(int age, String responce) {
        assertTrue(hire.isHired(age).equalsIgnoreCase(responce));
    }

    private static Stream<Arguments> getData(){
        return Stream.of(
                Arguments.of(4,"no"),
                Arguments.of(15,"half"),
                Arguments.of(100, "yes")
        );
    }

    @Test
    void test44() {
        TestConfig config = ConfigFactory.create(TestConfig.class);
        assertTrue(hire.isHired(config.age()).equalsIgnoreCase(config.response()));
    }

}
