package com.lsa.workbook;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginForm {
    public LoginForm(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }
    @FindBy(name = "USER_LOGIN")
    public WebElement username;
    @FindBy(name = "USER_PASSWORD")
    public WebElement password;
    @FindBy(name = "Login")
    public WebElement submitButton;
    @FindBy(className = "jq-checkbox")
    public WebElement chkBox;
}
