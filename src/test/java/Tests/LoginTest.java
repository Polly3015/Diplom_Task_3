package Tests;

import LocatorsAndActions.LocatorsAuthorizationAndRegistration;
import LocatorsAndActions.LocatorsMainPage;
import io.qameta.allure.Step;
import io.restassured.RestAssured;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static io.restassured.RestAssured.given;

public class LoginTest {
    private WebDriver driver;
    private final DriverFactory driverFactory = new DriverFactory();

    private String userLogin = "test130@test.ru";
    private String userPassword = "123456FF";
    private String userName = "Polly";
    private String userToken = null;

    String user = "{\"email\":\"" + userLogin + "\", \"password\":\"" + userPassword + "\", \"name\":\"" + userName + "\"}";

    LocatorsAuthorizationAndRegistration objLocatorsForm;
    LocatorsMainPage objLocatorsPage;

    @BeforeEach
    void setUp() {
        driverFactory.initDriver();
        driver = driverFactory.getDriver();
        driver.get("https://qa-stellarburgers.education-services.ru/");

        RestAssured.baseURI = "https://qa-stellarburgers.education-services.ru/";

        userToken =
                given()
                        .header("Content-type", "application/json")
                        .body(user)
                        .when()
                        .post("/api/auth/register")
                        .then()
                        .statusCode(200)
                        .extract()
                        .path("accessToken");

        objLocatorsForm = new LocatorsAuthorizationAndRegistration(driver);
        objLocatorsPage = new LocatorsMainPage(driver);

        //проверка загрузки сайта
        objLocatorsPage.waitForLoadCountIngredient();
    }


    @Test
    @Step("Вход по кнопке «Войти в аккаунт» на главной")
    public void authorizationLogInAccount() {

        objLocatorsPage.clickButtonLogInAccount();
        objLocatorsForm.authorizationUser(userLogin, userPassword);
        objLocatorsPage.waitForLoadCountIngredient();
    }

    @Test
    @Step("Вход через кнопку «Личный кабинет»")
    public void authorizationPersonalAccount() {

        objLocatorsPage.clickButtonPersonalAccount();
        objLocatorsForm.authorizationUser(userLogin, userPassword);
        objLocatorsPage.waitForLoadCountIngredient();
    }

    @Test
    @Step("Вход через кнопку в форме регистрации")
    public void authorizationInRegistration(){

        objLocatorsPage.clickButtonPersonalAccount();
        objLocatorsForm.loginButtonInRegistration();
        objLocatorsForm.authorizationUser(userLogin, userPassword);
        objLocatorsPage.waitForLoadCountIngredient();
    }

    @Test
    @Step("Вход через кнопку в форме восстановления пароля")
    public void authorizationInRecoveryPassword(){

        objLocatorsPage.clickButtonPersonalAccount();
        objLocatorsForm.loginButtonInRecoveryPassword();
        objLocatorsForm.authorizationUser(userLogin, userPassword);
        objLocatorsPage.waitForLoadCountIngredient();
    }


    @AfterEach
    void tearDown() {
        driver.quit();

        given()
                .header("Content-type", "application/json")
                .header("Authorization", userToken)
                .when()
                .delete("/api/auth/user")
                .then()
                .statusCode(202);
    }
}
