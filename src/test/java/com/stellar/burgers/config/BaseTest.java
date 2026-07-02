package com.stellar.burgers.config;

import com.stellar.burgers.pageobject.LoginPage;
import com.stellar.burgers.pageobject.MainPage;
import com.stellar.burgers.pageobject.RegisterPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;
import java.util.UUID;

public class BaseTest {

    protected WebDriver driver;
    protected static final String BASE_URL = "https://stellarburgers.education-services.ru";
    protected String email;
    protected static final String PASSWORD = "Password1";
    protected static final String NAME = "Тестовый Пользователь";

    @Before
    public void setUp() {
        String browser = System.getProperty("browser", "chrome");
        System.out.println("🚀 Запуск теста для браузера: " + browser);

        if ("yandex".equals(browser)) {
            WebDriverManager.chromedriver().driverVersion("146.0.7680.188").setup();
            ChromeOptions yandexOptions = new ChromeOptions();
            yandexOptions.setBinary("/Applications/Yandex.app/Contents/MacOS/Yandex");
            yandexOptions.addArguments("--no-sandbox", "--disable-dev-shm-usage",
                    "--disable-gpu", "--remote-allow-origins=*");
            driver = new ChromeDriver(yandexOptions);
        } else {
            WebDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--no-sandbox", "--disable-dev-shm-usage");
            driver = new ChromeDriver(options);
        }

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get(BASE_URL);
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    //регистрация пользователя
    protected void registerUser() {
        email = "testuser_" + UUID.randomUUID() + "@yandex.ru";

        MainPage mainPage = new MainPage(driver);
        mainPage.clickPersonalAccount();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickRegisterLink();

        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.setName(NAME);
        registerPage.setEmail(email);
        registerPage.setPassword(PASSWORD);
        registerPage.clickRegisterButton();

        registerPage.waitForSuccessOrError();

        System.out.println("✅ Зарегистрирован пользователь: " + email);
    }
}
