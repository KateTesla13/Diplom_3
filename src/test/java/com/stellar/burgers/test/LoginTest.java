package com.stellar.burgers.test;

import com.stellar.burgers.config.BaseTest;
import com.stellar.burgers.pageobject.ForgotPasswordPage;
import com.stellar.burgers.pageobject.LoginPage;
import com.stellar.burgers.pageobject.MainPage;
import com.stellar.burgers.pageobject.RegisterPage;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class LoginTest extends BaseTest {

    @Before
    public void setUpLoginTest() {
        registerUser();
    }

    @Test
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