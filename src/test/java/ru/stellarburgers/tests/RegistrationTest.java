package ru.stellarburgers.tests;

import org.junit.jupiter.api.DisplayName;
import ru.stellarburgers.pageobjects.Constants;
import ru.stellarburgers.pageobjects.LocatorsAuthorizationAndRegistration;
import ru.stellarburgers.pageobjects.LocatorsMainPage;
import io.restassured.response.Response;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import ru.stellarburgers.pageobjects.User;

public class RegistrationTest extends BaseURI{
    private WebDriver driver;
    private final DriverFactory driverFactory = new DriverFactory();

    private String userName = "Name";
    private String userLogin = "test130@test.ru";
    private String userPassword = "123456FF";
    private String incorrectPassword = "123";
    private String userToken = null;

    LocatorsAuthorizationAndRegistration objLocatorsForm;
    LocatorsMainPage objLocatorsPage;

    @BeforeEach
    void setUp() {
        driverFactory.initDriver();
        driver = driverFactory.getDriver();
        driver.get(Constants.BASE_URI);

        objLocatorsForm = new LocatorsAuthorizationAndRegistration(driver);
        objLocatorsPage = new LocatorsMainPage(driver);

        //Переход к регистрации
        objLocatorsPage.waitForLoadCountIngredient();
        objLocatorsPage.clickButtonPersonalAccount();

        objLocatorsForm.waitForLoadForm();
        objLocatorsForm.clickRegistration();
        objLocatorsForm.waitForLoadForm();
    }

    @Test
    @DisplayName("Успешная регистрация")
    public void registrationUserSuccess(){

        objLocatorsForm.registrationUser(userName, userLogin, userPassword);
        objLocatorsForm.waitForLoadHeadEntry();

    }

    @Test
    @DisplayName("Ошибка регистрации - некорректный пароль")
    public void registrationUserFail(){

        objLocatorsForm.registrationUser(userName, userLogin, incorrectPassword);
        objLocatorsForm.waitMistake();

    }

    @AfterEach
    void tearDown() {
        driver.quit();

        User user = new User(userLogin, userPassword);
        Response response = user.loginUser(user);

        int statusCode = response.getStatusCode();

        if (statusCode == 200) {
            // Действия при успехе
            userToken = response.path("accessToken");
            user.deleteUser(userToken);
        } else if (statusCode != 401) {
            return;
        }

    }


}

