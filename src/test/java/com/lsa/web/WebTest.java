package com.lsa.web;

import io.qameta.allure.Epic;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

@Epic("WEB TEST")
@Story("Learn Selenium")
public class WebTest extends BaseTest {
    HomePageFactory homePage = new HomePageFactory(driver);

    @Test
    @DisplayName("Первый тест")
    void firstTest() {

///        driver.findElement(By.id(""));
///        driver.findElement(By.name(""));
///        driver.findElement(By.className(""));
///        driver.findElement(By.cssSelector(".actions"));
///        driver.findElement(By.linkText("INSIGHTS"));
///        driver.findElement(By.xpath());


        WebElement inside = driver.findElement(By.linkText("INSIGHTS"));
        inside.click();

        WebElement menu = driver.findElement(By.className("header__menu"));
        WebElement services = menu.findElement(By.linkText("SERVICES"));
        services.click();

///        List<WebElement> coll = driver.findElements(By.className("actions"));
///        if (coll.size()>=5)
///            coll.get(5).click();
    }

    @Test
    @DisplayName("Проверка клика")
    void clickElement() throws InterruptedException {

//        HomePage homePage = new HomePage(driver);
        homePage.goToContactUsPage();
        homePage.fillFields("Selenium");
        homePage.checkElements("Selenium");
    }

    @Test
    @DisplayName("Перекрывание курсором меню")
    void act() throws InterruptedException {
        homePage.moveToAboutMenu();
        homePage.checkArrowsSize(3);
    }

    @Test
    @DisplayName("Джава скрипт")
    void jsTest() throws InterruptedException {
        homePage.openNewTab();
        homePage.closeTab();
        System.out.println("111");
    }
}
