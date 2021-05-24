package com.lsa.web;

import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class HomePageFactory {
    private WebDriver driver;

    @FindBy(id="CybotCookiebotDialogBodyButtonAccept")
    WebElement acceptButton;

    @FindBy(xpath="//span[text()=\"WITH US\"]")
    WebElement withUs;

    @FindAll({
            @FindBy(linkText="CONTACT US"),
            @FindBy(tagName ="div")
    })
    WebElement contactUs;

    @FindBys({
            @FindBy(id = "CONTACT_NAME"),
            @FindBy(id ="form_CONTACT_NAME")
    })
    WebElement firstName;

    @FindBy(id="form_CONTACT_LAST_NAME")
    List<WebElement> lastName;

    @FindBy(id = "menu-switcher")
    WebElement menu;

    @FindBy(partialLinkText = "About us")
    WebElement aboutUs;

    public HomePageFactory(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @Step("Переход на меню ABOUT")
    public void moveToAboutMenu() throws InterruptedException {
        menu.click();
        Actions builder = new Actions(driver);
        waitForLink("About us");
        builder.moveToElement(aboutUs).build().perform();
        Thread.sleep(3000);
    }

    @Step("Проверка размера стрелочек {size}")
    public void checkArrowsSize(int size) {
        List<WebElement> arrows = aboutUs.findElements(By.xpath("./span/i"));
        Assertions.assertEquals(size, arrows.size());
        arrows.forEach(el -> Assertions.assertTrue(el.isDisplayed()));
    }

    @Step("Переход на страницу контакты")
    public void goToContactUsPage() throws InterruptedException {
        acceptButton.click();
        contactUs.click();
        Thread.sleep(3000);

        withUs.click();
//        driver.findElement(By.xpath("//a[contains(text(),'CONNECT')]/span[text()=\"WITH US\"]")).click();
    }

    @Step("Заполнение поля {0}")
    public void fillFields(String text) {
        firstName.sendKeys(text);
    }

    @Step("Проверка заполнения элемента")
    public void checkElements (String text) {
        assertAll(
                ()->lastName.get(0).isDisplayed(),
                ()->assertTrue(firstName.getAttribute("value").equalsIgnoreCase(text))
        );
    }

    @Step("Ожидание отрисовки")
    public static void waitForLink(String link) {
        BaseTest.waitVar.until(ExpectedConditions.presenceOfElementLocated(By.partialLinkText(link)));
    }

    @Step("Открытие новой вкладки и ввод текста")
    public void openNewTab () {
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("window.open()");
        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));
        driver.get("https://google.com");
//        driver.findElement(By.name("q")).sendKeys("Hello");
        driver.findElement(By.name("q1")).sendKeys("Hello");
    }

    @Step("Закрываем вкладку")
    public void closeTab () {
        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.close();
        driver.switchTo().window(tabs.get(0));
        driver.get("https://www.luxoft.com/");
    }


}
