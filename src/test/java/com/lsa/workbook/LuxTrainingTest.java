package com.lsa.workbook;

import com.lsa.workbook.pages.CataloguePage;
import com.lsa.workbook.pages.CoursePage;
import com.lsa.workbook.pages.HomePage;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Story;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Arrays;

@Epic("Люксофт тренинг")
@Story("название стори")
@ExtendWith(TestWatcher.class)
public class LuxTrainingTest {
    private static HomePage homePage;
    private static CoursePage coursePage;
    private static CataloguePage cataloguePage;

    @BeforeAll
    static void init(){
        new SetUp();
        homePage = new HomePage(SetUp.driver);
        coursePage = new CoursePage(SetUp.driver);
        cataloguePage = new CataloguePage(SetUp.driver);
    }

    @Test
    void verifySignInForm(){
        LoginForm loginForm = homePage.openLoginForm();
        Assertions.assertAll(
                ()-> Assertions.assertTrue(loginForm.username.isDisplayed()),
                ()-> Assertions.assertTrue(loginForm.password.isDisplayed()),
                ()-> Assertions.assertTrue(loginForm.submitButton.isDisplayed()),
                ()-> Assertions.assertTrue(loginForm.chkBox.isDisplayed())
        );
    }

    @AfterAll
    static void tearDown(){
        SetUp.driver.quit();
    }

    @AfterEach
    void goStartPage(){
        SetUp.goStartPage();
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/test2.csv", numLinesToSkip = 1, delimiter = ',')
    void verifyMainMenuLinks(String text1, String text2, String text3) {
        homePage.compareLinkTexts(Arrays.asList(text1, text2, text3));
    }

    @ParameterizedTest
    @CsvSource(value = {"Каталог,Скачать каталог", "Расписание,Скачать прайс"})
    void testAttribute(String menu, String link){
        homePage.clickLink(menu);
        String color1 = cataloguePage.getColor(link);
        cataloguePage.hoverMouse(link);
        String color2 = cataloguePage.getColor(link);
        Assertions.assertFalse(color1.equalsIgnoreCase(color2));
        Assertions.assertTrue(color2.equalsIgnoreCase("rgba(242, 111, 33, 1)"));
    }

    @Test
    void anotherTabDoc(){
        cataloguePage.openCatalogueFile();
        homePage.switchTab(1);
        if(!homePage.getEmbedType().equalsIgnoreCase("application/pdf")){
            homePage.switchTab(0);
            Assertions.fail("incorrect embed type");
        }
        homePage.switchTab(0);
    }

    @Test
    @Description("Упражнение 6")
    void searchForCourseTest(){
        homePage.clickLink("Каталог");
        cataloguePage.searchForCourse("SQA-050", "Школа автоматизированного тестирования");
        String link = homePage.getCourseLink("Школа автоматизированного тестирования");
        homePage.openNewTab(link);
        coursePage.checkCollectionSize();
        homePage.switchTab(0);
    }

}