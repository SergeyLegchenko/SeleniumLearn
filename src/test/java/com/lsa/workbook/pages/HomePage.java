package com.lsa.workbook.pages;

import com.lsa.workbook.LoginForm;
import com.lsa.workbook.SetUp;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;

public class HomePage {

    @FindBy(xpath = "//div[@class=\"header__control _nav\"]")
    WebElement signInLink;

    @FindBy(xpath = "//div[@class=\"hidden-menu-header\"]/a")
    WebElement openFormIcon;

    @FindBy(className = "navigation__list")
    WebElement mainMenu;

    private final WebDriver driver;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public LoginForm openLoginForm(){
        signInLink.click();
        openFormIcon.click();
        return new LoginForm(driver);
    }

    private List<WebElement> getMainLinks(){
        return mainMenu.findElements(By.tagName("a"));
    }

    public void compareLinkTexts(List<String> texts){
        List<WebElement> links = getMainLinks();
        int size = 0;
        for (String value : texts) {
            for (int j = 0; j < links.size(); j++) {
                if (value.equalsIgnoreCase(links.get(j).getText())) {
                    size++;
                    j = links.size();
                }
            }
        }
        Assertions.assertEquals(size, texts.size());
    }

    public void clickLink(String name) {
        driver.findElement(By.linkText(name)).click();
    }

    public void switchTab(int tabNumber) {
        ArrayList<String> tabs = new ArrayList(driver.getWindowHandles());
        if (tabs.size()==1)
            Assertions.fail("additional tab not opened");
        //переключаем управление вебдрайвером на новую вкладку
        SetUp.driver.switchTo().window(tabs.get(tabNumber));
    }


    public String getEmbedType(){
        return driver.findElement(By.tagName("embed")).getAttribute("type");
    }


    public String getCourseLink(String courseName){
        return driver.findElement(By.partialLinkText(courseName)).getAttribute("href");
    }

    public void openNewTab(String url){
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("window.open()");
        switchTab(1);
        driver.navigate().to(url);
    }

}
