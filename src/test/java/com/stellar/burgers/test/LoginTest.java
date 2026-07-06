package com.stellar.burgers.test;

import com.stellar.burgers.config.BaseTest;
import com.stellar.burgers.pageobject.ForgotPasswordPage;
import com.stellar.burgers.pageobject.LoginPage;
import com.stellar.burgers.pageobject.MainPage;
import com.stellar.burgers.pageobject.RegisterPage;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class LoginTest extends BaseTest {

    @Before
    public void setUpLoginTest() {
        registerUser();
    }

    @Test
    @DisplayName("Вход через кнопку «Войти в аккаунт» на главной")
    @Description("Проверка входа в систему по кнопке «Войти в аккаунт» на главной странице")
    public void loginFromMainPage() {
        driver.get(BASE_URL);

        MainPage mainPage = new MainPage(driver);
        mainPage.clickLoginButton();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.setEmail(email);
        loginPage.setPassword(PASSWORD);
        loginPage.clickLoginButton();

        assertTrue(driver.getCurrentUrl().contains("stellarburgers"));
    }

    @Test
    @DisplayName("Вход через кнопку «Личный кабинет»")
    @Description("Проверка входа в систему через клик на «Личный кабинет» в шапке сайта")
    public void loginFromPersonalAccount() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickPersonalAccount();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.setEmail(email);
        loginPage.setPassword(PASSWORD);
        loginPage.clickLoginButton();

        assertTrue(driver.getCurrentUrl().contains("stellarburgers"));
    }

    @Test
    @DisplayName("Вход через кнопку в форме регистрации")
    @Description("Проверка входа в систему через ссылку «Войти» на странице регистрации")
    public void loginFromRegisterPage() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickPersonalAccount();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickRegisterLink();

        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.clickLoginLink();

        loginPage.setEmail(email);
        loginPage.setPassword(PASSWORD);
        loginPage.clickLoginButton();

        assertTrue(driver.getCurrentUrl().contains("stellarburgers"));
    }

    @Test
    @DisplayName("Вход через кнопку в форме восстановления пароля")
    @Description("Проверка входа в систему через ссылку «Войти» на странице восстановления пароля")
    public void loginFromForgotPasswordPage() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickPersonalAccount();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickForgotPasswordLink();

        ForgotPasswordPage forgotPasswordPage = new ForgotPasswordPage(driver);
        forgotPasswordPage.clickLoginLink();

        loginPage.setEmail(email);
        loginPage.setPassword(PASSWORD);
        loginPage.clickLoginButton();

        assertTrue(driver.getCurrentUrl().contains("stellarburgers"));
    }
}