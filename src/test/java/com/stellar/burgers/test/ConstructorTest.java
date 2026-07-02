package com.stellar.burgers.test;

import com.stellar.burgers.config.BaseTest;
import com.stellar.burgers.pageobject.MainPage;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class ConstructorTest extends BaseTest {

    @Test
    public void switchToBuns() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickBunsTab();
        assertTrue("Вкладка 'Булки' должна быть активна", mainPage.isBunsTabActive());
    }

    @Test
    public void switchToSauces() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickSaucesTab();
        assertTrue("Вкладка 'Соусы' должна быть активна", mainPage.isSaucesTabActive());
    }

    @Test
    public void switchToFillings() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickFillingsTab();
        assertTrue("Вкладка 'Начинки' должна быть активна", mainPage.isFillingsTabActive());
    }
}