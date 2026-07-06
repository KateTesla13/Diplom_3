package com.stellar.burgers.config;

import com.stellar.burgers.client.UserClient;
import com.stellar.burgers.model.User;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.restassured.RestAssured;
import io.restassured.response.Response;
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
    protected static final String API_BASE_URL = "https://stellarburgers.education-services.ru";
    protected String email;
    protected String accessToken;
    protected UserClient userClient;
    protected static final String PASSWORD = "Password1";
    protected static final String NAME = "Тестовый Пользователь";

    @Before
    public void setUp() {
        String browser = System.getProperty("browser", "chrome");
        System.out.println("🚀 Запуск теста для браузера: " + browser);

        RestAssured.baseURI = API_BASE_URL;
        userClient = new UserClient();

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
        if (accessToken != null) {
            userClient.deleteUser(accessToken);
        }
        if (driver != null) {
            driver.quit();
        }
    }

    // Регистрация пользователя через API
    protected void registerUser() {
        email = "testuser_" + UUID.randomUUID() + "@yandex.ru";
        User user = new User(email, PASSWORD, NAME);

        Response response = userClient.createUser(user);
        response.then().statusCode(200);

        accessToken = userClient.getAccessToken(response);
        System.out.println("✅ Зарегистрирован пользователь через API: " + email);
    }
}