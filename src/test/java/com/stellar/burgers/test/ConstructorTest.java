package com.stellar.burgers.test;

import com.stellar.burgers.config.BaseTest;
import com.stellar.burgers.pageobject.MainPage;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class ConstructorTest extends BaseTest {

    @Test
    @DisplayName("Переход к разделу «Булки»")
    @Description("Проверка перехода к разделу «Булки» в конструкторе. Сначала кликаем на «Соусы», затем на «Булки»")
    public void switchToBuns() {
        MainPage mainPage = new MainPage(driver);

        // Сначала кликаем на «Соусы», чтобы уйти с дефолтной вкладки
        mainPage.clickSaucesTab();

        // Затем кликаем на «Булки»
        mainPage.clickBunsTab();

        assertTrue("Вкладка 'Булки' должна быть активна", mainPage.isBunsTabActive());
    }

    @Test
    @DisplayName("Переход к разделу «Соусы»")
    @Description("Проверка перехода к разделу «Соусы» в конструкторе")
    public void switchToSauces() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickSaucesTab();
        assertTrue("Вкладка 'Соусы' должна быть активна", mainPage.isSaucesTabActive());
    }

    @Test
    @DisplayName("Переход к разделу «Начинки»")
    @Description("Проверка перехода к разделу «Начинки» в конструкторе")
    public void switchToFillings() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickFillingsTab();
        assertTrue("Вкладка 'Начинки' должна быть активна", mainPage.isFillingsTabActive());
    }
}