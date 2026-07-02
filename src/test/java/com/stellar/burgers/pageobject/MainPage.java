package com.stellar.burgers.pageobject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MainPage {

    public static final String BASE_URL = "https://stellarburgers.education-services.ru";

    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By loginButton = By.xpath(".//button[text()='Войти в аккаунт']");
    private final By personalAccountButton = By.xpath(".//a[@href='/account']");
    private final By constructorButton = By.xpath(".//a[@href='/']");
    private final By orderFeedButton = By.xpath(".//a[@href='/feed']");
    private final By bunsTab = By.xpath("//div[contains(@class, 'tab_tab__1SPyG') and .//span[text()='Булки']]");
    private final By saucesTab = By.xpath("//div[contains(@class, 'tab_tab__1SPyG') and .//span[text()='Соусы']]");
    private final By fillingsTab = By.xpath("//div[contains(@class, 'tab_tab__1SPyG') and .//span[text()='Начинки']]");

    public MainPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Step("Клик на кнопку 'Войти в аккаунт'")
    public void clickLoginButton() {
        wait.until(ExpectedConditions.elementToBeClickable(loginButton)).click();
    }

    @Step("Клик на 'Личный кабинет'")
    public void clickPersonalAccount() {
        wait.until(ExpectedConditions.elementToBeClickable(personalAccountButton)).click();
    }

    @Step("Клик на 'Конструктор'")
    public void clickConstructor() {
        wait.until(ExpectedConditions.elementToBeClickable(constructorButton)).click();
    }

    @Step("Клик на 'Лента заказов'")
    public void clickOrderFeed() {
        wait.until(ExpectedConditions.elementToBeClickable(orderFeedButton)).click();
    }

    private void clickWithJS(By locator) {
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
    }

    @Step("Переход к разделу 'Булки'")
    public void clickBunsTab() {
        clickWithJS(bunsTab);
    }

    @Step("Переход к разделу 'Соусы'")
    public void clickSaucesTab() {
        clickWithJS(saucesTab);
    }

    @Step("Переход к разделу 'Начинки'")
    public void clickFillingsTab() {
        clickWithJS(fillingsTab);
    }

    @Step("Проверка активности вкладки 'Булки'")
    public boolean isBunsTabActive() {
        return driver.findElement(bunsTab).getAttribute("class").contains("tab_tab_type_current__2BEPc");
    }

    @Step("Проверка активности вкладки 'Соусы'")
    public boolean isSaucesTabActive() {
        return driver.findElement(saucesTab).getAttribute("class").contains("tab_tab_type_current__2BEPc");
    }

    @Step("Проверка активности вкладки 'Начинки'")
    public boolean isFillingsTabActive() {
        return driver.findElement(fillingsTab).getAttribute("class").contains("tab_tab_type_current__2BEPc");
    }
}
