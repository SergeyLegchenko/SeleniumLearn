package com.lsa.workbook.pages;

import com.lsa.workbook.SetUp;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CataloguePage {
    private WebDriver driver;
    public CataloguePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    @FindBy(partialLinkText = "Скачать каталог")
    WebElement downloadCatalogue;
    @FindBy(name = "qcat")
    WebElement queryField;
    @FindBy(linkText = "Каталог")
    WebElement catalogueMenu;

    @Step("навести курсор на элемент {partialLinkText}")
    public void hoverMouse(String partialLinkText){
        Actions builder = new Actions(driver);
        builder.moveToElement(driver.findElement(By.partialLinkText(partialLinkText))).build().perform();
    }
    @Step("искать курс {courseName} по коду {course} ")
    public void searchForCourse(String course, String courseName){
        queryField.sendKeys(course);
        queryField.sendKeys(Keys.ENTER);
        SetUp.waitVar.until(ExpectedConditions.presenceOfElementLocated(By.partialLinkText(courseName)));
    }
    public String getColor(String partialLinkText){
        return driver.findElement(By.partialLinkText(partialLinkText)).getCssValue("color");
    }
    public void openCatalogueFile(){
        catalogueMenu.click();
        downloadCatalogue.click();
    }
}
