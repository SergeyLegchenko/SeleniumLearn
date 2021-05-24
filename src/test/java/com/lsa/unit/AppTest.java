package com.lsa.unit;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;

@TestInstance(TestInstance.Lifecycle.PER_METHOD)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AppTest {
    private static int result;

    @BeforeAll
    static void setUp () {
        result = 8;
        System.out.println("Hello " + result);
    };

    @BeforeEach
    void be () {
        System.out.println("== Before == ");
    }

    @AfterEach
    void ae () {
        System.out.println("== After == ");
    }

    @Test
    @EnabledOnOs(value = OS.WINDOWS)
//    @EnabledOnOs(value = OS.LINUX)
    @DisplayName("Первый тест")
    @Order(1)
    @Tag("TAG1")
    public void firstTest () {
        Assertions.assertEquals(result, 6+2, "Equals");
    }

    @Test
    @DisplayName("Копия первый тест")
    @Disabled
    @Order(2)
    @Tag("TAG2")
    public void firstTest1 () {
        Assertions.assertFalse(result==(9-5), "NoEquals");
    }

    @Test
    @DisplayName("Второй тест")
    @Order(3)
    @Tag("TAG3")
    public void secondTest () {
        System.out.println("Result-" + result);
    }

    @AfterAll
    static void tearDown () {
        System.out.println("By " + result);
    }
}
