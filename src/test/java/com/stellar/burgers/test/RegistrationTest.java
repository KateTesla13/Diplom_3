package com.stellar.burgers.test;

import com.stellar.burgers.config.BaseTest;
import com.stellar.burgers.pageobject.LoginPage;
import com.stellar.burgers.pageobject.MainPage;
import com.stellar.burgers.pageobject.RegisterPage;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.UUID;

import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class RegistrationTest extends BaseTest {

    private final String name;
    private final String email;
    private final String password;
    private final boolean expectedSuccess;

    public RegistrationTest(String name, String email, String password, boolean expectedSuccess) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.expectedSuccess = expectedSuccess;
    }

    @Parameterized.Parameters(name = "Тестовые данные: {0}, {1}, пароль: {2}")
    public static Object[][] getData() {
        return new Object[][]{
                {"Иван", "testuser_" + UUID.randomUUID() + "@yandex.ru", "Password1", true},
                {"Петр", "testuser_" + UUID.randomUUID() + "@yandex.ru", "12345", false}
        };
    }

    @Test
    public void registrationTest() {
        System.out.println("📝 Регистрируем пользователя:");
        System.out.println("Имя: " + name);
        System.out.println("Email: " + email);
        System.out.println("Пароль: " + password);

        MainPage mainPage = new MainPage(driver);
        mainPage.clickPersonalAccount();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickRegisterLink();

        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.setName(name);
        registerPage.setEmail(email);
        registerPage.setPassword(password);
        registerPage.clickRegisterButton();

        // Ждём, пока появится результат (редирект на /login или сообщение об ошибке)
        boolean isPageReady = registerPage.waitForSuccessOrError();
        System.out.println("✅ Страница обновилась: " + isPageReady);

        System.out.println("📍 Текущий URL после регистрации: " + driver.getCurrentUrl());

        if (expectedSuccess) {
            assertTrue("Успешная регистрация: должен быть переход на логин",
                    driver.getCurrentUrl().contains("login"));
        } else {
            assertTrue("Некорректный пароль: должно появиться сообщение об ошибке",
                    registerPage.isErrorMessageVisible());
        }
    }
}