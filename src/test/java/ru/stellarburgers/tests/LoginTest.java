package ru.stellarburgers.tests;

import org.junit.jupiter.api.DisplayName;
import ru.stellarburgers.pageobjects.Constants;
import ru.stellarburgers.pageobjects.LocatorsAuthorizationAndRegistration;
import ru.stellarburgers.pageobjects.LocatorsMainPage;
import ru.stellarburgers.pageobjects.User;
import io.restassured.RestAssured;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.openqa.selenium.WebDriver;

public class LoginTest {
    private WebDriver driver;
    private final DriverFactory driverFactory = new DriverFactory();

    private String userLogin = "test130@test.ru";
    private String userPassword = "123456FF";
    private String userName = "Polly";
    private String userToken = null;

    User user = new User(userLogin, userPassword, userName);


    LocatorsAuthorizationAndRegistration objLocatorsForm;
    LocatorsMainPage objLocatorsPage;

    @BeforeEach
    void setUp() {
        driverFactory.initDriver();
        driver = driverFactory.getDriver();

        driver.get(Constants.BASE_URI);

        RestAssured.baseURI = Constants.BASE_URI;

        userToken = user.successRegisterUser(user);

        objLocatorsForm = new LocatorsAuthorizationAndRegistration(driver);
        objLocatorsPage = new LocatorsMainPage(driver);

        //проверка загрузки сайта
        objLocatorsPage.waitForLoadCountIngredient();
    }


    @Test
    @DisplayName("Вход по кнопке «Войти в аккаунт» на главной")
    public void authorizationLogInAccount() {

        objLocatorsPage.clickButtonLogInAccount();
        objLocatorsForm.authorizationUser(userLogin, userPassword);
        objLocatorsPage.waitForLoadCountIngredient();
    }

    @Test
    @DisplayName("Вход через кнопку «Личный кабинет»")
    public void authorizationPersonalAccount() {

        objLocatorsPage.clickButtonPersonalAccount();
        objLocatorsForm.authorizationUser(userLogin, userPassword);
        objLocatorsPage.waitForLoadCountIngredient();
    }

    @Test
    @DisplayName("Вход через кнопку в форме регистрации")
    public void authorizationInRegistration(){

        objLocatorsPage.clickButtonPersonalAccount();
        objLocatorsForm.loginButtonInRegistration();
        objLocatorsForm.authorizationUser(userLogin, userPassword);
        objLocatorsPage.waitForLoadCountIngredient();
    }

    @Test
    @DisplayName("Вход через кнопку в форме восстановления пароля")
    public void authorizationInRecoveryPassword(){

        objLocatorsPage.clickButtonPersonalAccount();
        objLocatorsForm.loginButtonInRecoveryPassword();
        objLocatorsForm.authorizationUser(userLogin, userPassword);
        objLocatorsPage.waitForLoadCountIngredient();
    }


    @AfterEach
    void tearDown() {
        driver.quit();
            user.deleteUser(userToken);
        }

}
