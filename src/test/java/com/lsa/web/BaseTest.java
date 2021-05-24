package com.lsa.web;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

@ExtendWith(TestWatcher.class)
public class BaseTest {
    static WebDriver driver;
    static WebDriverWait waitVar;

    @BeforeEach
    void setUpTest () {
        driver.navigate().to("https://www.luxoft.com/");
    }

    @BeforeAll
    static void setUp () {
        System.setProperty("webdriver.chrome.driver", "lib/chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        //options.setHeadless(true);
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        waitVar = new WebDriverWait(driver, 5);
    }

    @AfterAll
    static void tearDown() {
        driver.close();
    }
}
