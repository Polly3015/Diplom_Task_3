package ru.stellarburgers.tests;

import org.junit.jupiter.api.DisplayName;
import ru.stellarburgers.pageobjects.*;
import io.restassured.RestAssured;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.openqa.selenium.WebDriver;

public class PersonalAccountTest {

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

        RestAssured.baseURI = Constants.BASE_URI;

        userToken = user.successRegisterUser(user);

        objLocatorsForm = new LocatorsAuthorizationAndRegistration(driver);
        objLocatorsPage = new LocatorsMainPage(driver);
        objLocatorsAccount = new LocatorsAccount(driver);


        //Авторизация в аккаунте
        objLocatorsPage.waitForLoadCountIngredient();
        objLocatorsPage.clickButtonPersonalAccount();
        objLocatorsForm.authorizationUser(userLogin, userPassword);
        objLocatorsPage.waitForLoadCountIngredient();
    }

    @Test
    @DisplayName("Переход в Личный кабинет")
    public void authorizationLogInAccount() {

        objLocatorsPage.clickButtonPersonalAccount();
        objLocatorsAccount.waitForButtonProfile();
    }

    @AfterEach
    void tearDown() {
        driver.quit();
        user.deleteUser(userToken);
    }

}
