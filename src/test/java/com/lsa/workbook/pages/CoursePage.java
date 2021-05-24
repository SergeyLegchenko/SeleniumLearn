package com.lsa.workbook.pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CoursePage {
    @FindBy(linkText = "Записаться на курс")
    List<WebElement> applyLink;
    public CoursePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }
    public void checkCollectionSize(){
        Assertions.assertEquals(applyLink.size(), 2);
    }
}
