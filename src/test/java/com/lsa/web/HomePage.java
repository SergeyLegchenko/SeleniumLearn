package com.lsa.web;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class HomePage {
    private WebDriver driver;
    ///1-st approach - use class By
    By acceptButton = By.id("CybotCookiebotDialogBodyButtonAccept");
    By contactUs = By.linkText("CONTACT US");
    By WithUs = By.xpath("//span[text()=\"WITH US\"]");
    By firstName = By.id("form_CONTACT_NAME");

    By lastName = By.id("form_CONTACT_LAST_NAME");

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void goToContactUsPage() throws InterruptedException {
        driver.findElement(acceptButton).click();
        driver.findElement(contactUs).click();
        Thread.sleep(3000);

        driver.findElement(WithUs).click();
//        driver.findElement(By.xpath("//a[contains(text(),'CONNECT')]/span[text()=\"WITH US\"]")).click();
    }

    public void fillFields(String text) {
        driver.findElement(firstName).sendKeys(text);
    }

    public void checkElements (String text) {
        assertAll(
                ()->driver.findElement(lastName).isDisplayed(),
                ()->assertTrue(driver.findElement(firstName).getAttribute("value").equalsIgnoreCase(text))
        );
    }
}
