package Tests;

import LocatorsAndActions.LocatorsAuthorizationAndRegistration;
import LocatorsAndActions.LocatorsMainPage;
import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;

public class RegistrationTest {
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
        driver.get("https://qa-stellarburgers.education-services.ru/");

        RestAssured.baseURI = "https://qa-stellarburgers.education-services.ru/";

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
    @Step("Успешная регистрация")
    public void registrationUserSuccess(){

        objLocatorsForm.registrationUser(userName, userLogin, userPassword);
        objLocatorsForm.waitForLoadHeadEntry();

    }

    @Test
    @Step("Ошибка регистрации - некорректный пароль")
    public void registrationUserFail(){

        objLocatorsForm.registrationUser(userName, userLogin, incorrectPassword);
        objLocatorsForm.waitMistake();

    }

    @AfterEach
    void tearDown() {
        driver.quit();

        String user = "{\"email\":\"" + userLogin + "\", \"password\":\"" + userPassword + "\"}";
        Response response = RestAssured.given()
                .header("Content-type", "application/json")
                .body(user)
                .when()
                .post("/api/auth/login");

        int statusCode = response.getStatusCode();

        if (statusCode == 200) {
            // Действия при успехе
            userToken = response.path("accessToken");

            RestAssured.given()
                    .header("Content-type", "application/json")
                    .header("Authorization", userToken)
                    .when()
                    .delete("/api/auth/user")
                    .then()
                    .statusCode(202);
        } else if (statusCode != 401) {
            return;
        }

    }


}

