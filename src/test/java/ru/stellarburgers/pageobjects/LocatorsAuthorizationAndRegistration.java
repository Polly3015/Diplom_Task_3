package ru.stellarburgers.pageobjects;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static ru.stellarburgers.pageobjects.WaitElement.waitForElementVisible;


public class LocatorsAuthorizationAndRegistration {

    private WebDriver driver;

    // Заголовок "Вход"
    private By headEntry = By.xpath("//div[contains(@class, 'Auth_login__3hAey')]//h2[normalize-space()='Вход']");

    // Кнопка "Войти"/"Зарегистрироваться"
    private By buttonAccept = By.className("button_button__33qZ0");

        // ----- Кнопки под формой авторизации/регистрации ----------------------------------

        // Кнопка "Зарегистрироваться"
        private By registration = By.cssSelector("a.Auth_link__1fOlj[href='/register']");

        // Кнопка Войти (Уже зарегистрированы? / Вспомнили пароль?)
        private By buttonLoginInRegistration = By.className("Auth_link__1fOlj");

        // Кнопка "Восстановить пароль"
        private By buttonRecoverPassword = By.cssSelector("a.Auth_link__1fOlj[href='/forgot-password']");

    // ----- Форма Авторизации и Регистрации ----------------------------------

    // Поля ввода
        // Имя
        private By nameField = By.xpath("//form[contains(@class, 'Auth_form__3qKeq')]//label[normalize-space()='Имя']/following-sibling::input");
        // Email
        private By emailField = By.xpath("//form[contains(@class, 'Auth_form__3qKeq')]//label[normalize-space()='Email']/following-sibling::input");
        // Пароль
        private By passwordField = By.xpath("//form[contains(@class, 'Auth_form__3qKeq')]//label[normalize-space()='Пароль']/following-sibling::input");

    // Ошибка - некорректный пароль
    private By mistakeIncorrectPassword = By.xpath("//div[contains(@class, 'input__container')]//p[normalize-space()='Некорректный пароль']");



    public LocatorsAuthorizationAndRegistration(WebDriver driver) {
        this.driver = driver;
    }


    // ----------------------------------------------------------------



    // метод ожидания прогрузки страницы авторизации/регистрации (ждем появление кнопки)
    @Step("Ожидание прогрузки страницы")
    public void waitForLoadForm() {
        waitForElementVisible(driver, buttonAccept);
    }

    // Перейти на регистрацию
    @Step("Перейти на страницу регистрации")
    public void clickRegistration() {
        driver.findElement(registration).click();
    }

    // Заполнение формы регистрации
    @Step("Заполнить форму регистрации")
    public void registrationUser(String name, String email, String password) {
        driver.findElement(nameField).sendKeys(name);
        driver.findElement(emailField).sendKeys(email);
        driver.findElement(passwordField).sendKeys(password);
        driver.findElement(buttonAccept).click();
    }

    // Отображение ошибки "Некорректный пароль"
    @Step("Проверить ошибку 'Некорректный пароль'")
    public void waitMistake() {
        waitForElementVisible(driver, mistakeIncorrectPassword);
    }


    // поиск заголовка Вход
    @Step("Найти заголовок экрана")
    public void waitForLoadHeadEntry() {
        waitForElementVisible(driver, headEntry);
    }

    // вход через кнопку в форме регистрации
    @Step("Открыть экран авторизации через форму регистрации")
    public void loginButtonInRegistration(){
        driver.findElement(registration).click();
        waitForElementVisible(driver, buttonAccept);
        driver.findElement(buttonLoginInRegistration).click();
    }

    // вход через кнопку в форме восстановления пароля
    @Step("Открыть экран авторизации через форму восстановления пароля")
    public void loginButtonInRecoveryPassword() {
        driver.findElement(buttonRecoverPassword).click();
        waitForElementVisible(driver, buttonAccept);
        driver.findElement(buttonLoginInRegistration).click();
    }


    // Заполнение формы авторизации
    @Step("Заполнить поля авторизации")
    public void authorizationUser(String email, String password) {
        driver.findElement(emailField).sendKeys(email);
        driver.findElement(passwordField).sendKeys(password);
        driver.findElement(buttonAccept).click();
    }





}
