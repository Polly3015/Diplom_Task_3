package ru.stellarburgers.tests;

import org.junit.jupiter.api.DisplayName;
import ru.stellarburgers.pageobjects.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.openqa.selenium.WebDriver;

public class TransitionFromAccountTest extends BaseURI {

    private WebDriver driver;
    private final DriverFactory driverFactory = new DriverFactory();

    private String userLogin = "test130@test.ru";
    private String userPassword = "123456FF";
    private String userName = "Polly";
    private String userToken = null;

    User user = new User(userLogin, userPassword, userName);

    LocatorsAuthorizationAndRegistration objLocatorsForm;
    LocatorsMainPage objLocatorsPage;
    LocatorsAccount objLocatorsAccount;

    @BeforeEach
    void setUp() {
        driverFactory.initDriver();
        driver = driverFactory.getDriver();
        driver.get(Constants.BASE_URI);

        userToken = user.successRegisterUser(user);

        objLocatorsForm = new LocatorsAuthorizationAndRegistration(driver);
        objLocatorsPage = new LocatorsMainPage(driver);
        objLocatorsAccount = new LocatorsAccount(driver);

        //Авторизация в аккаунте и переход в личный кабинет
        objLocatorsPage.waitForLoadCountIngredient();
        objLocatorsPage.clickButtonPersonalAccount();
        objLocatorsForm.authorizationUser(userLogin, userPassword);
        objLocatorsPage.waitForLoadCountIngredient();
        objLocatorsPage.clickButtonPersonalAccount();
    }

    @Test
    @DisplayName("Переход в конструктор по клику на «Конструктор»")
    public void clickConstructorButton() {
        objLocatorsPage.clickButtonConstructor();
        objLocatorsPage.waitForLoadCountIngredient();
    }

    @Test
    @DisplayName("Переход в конструктор по клику на логотип Stellar Burgers")
    public void clickLogo() {
        objLocatorsPage.clickLogo();
        objLocatorsPage.waitForLoadCountIngredient();
    }

    @Test
    @DisplayName("Выход из аккаунота по кнопке «Выйти»")
    public void clickbuttonExit() {
        objLocatorsAccount.logOutAccount();
    }

    @AfterEach
    void tearDown() {
        driver.quit();
        user.deleteUser(userToken);
    }

}

