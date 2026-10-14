package ru.stellarburgers.pageobjects;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static ru.stellarburgers.pageobjects.WaitElement.waitForElementVisible;

public class LocatorsAccount {

    private WebDriver driver;

    // Кнопка Профиль
    private By buttonProfile = By.cssSelector("a.Account_link__2ETsJ[href='/account/profile']");

    // Кнопка Выход
    private By buttonExit = By.className("Account_button__14Yp3");


    public LocatorsAccount(WebDriver driver) {
        this.driver = driver;
    }


    // ----------------------------------------------------------------

    // Метод проверки открытия Профиля
    @Step("Проверить, что Профиль открыт")
    public void waitForButtonProfile() {
        waitForElementVisible(driver, buttonProfile);
    }

    // Выход из аккаунта
    @Step("Выйти из аккаунта")
    public void logOutAccount() {
        waitForElementVisible(driver, buttonExit);
        driver.findElement(buttonExit).click();

    }


}
