package Tests;

import LocatorsAndActions.LocatorsAccount;
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

public class TransitionFromAccountTest {

    private WebDriver driver;
    private final DriverFactory driverFactory = new DriverFactory();

    private String userLogin = "test130@test.ru";
    private String userPassword = "123456FF";
    private String userName = "Polly";
    private String userToken = null;

    String user = "{\"email\":\"" + userLogin + "\", \"password\":\"" + userPassword + "\", \"name\":\"" + userName + "\"}";

    LocatorsAuthorizationAndRegistration objLocatorsForm;
    LocatorsMainPage objLocatorsPage;
    LocatorsAccount objLocatorsAccount;

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
        objLocatorsAccount = new LocatorsAccount(driver);

        //Авторизация в аккаунте и переход в личный кабинет
        objLocatorsPage.waitForLoadCountIngredient();
        objLocatorsPage.clickButtonPersonalAccount();
        objLocatorsForm.authorizationUser(userLogin, userPassword);
        objLocatorsPage.waitForLoadCountIngredient();
        objLocatorsPage.clickButtonPersonalAccount();
    }

    @Test
    @Step("Переход в конструктор по клику на «Конструктор»")
    public void clickConstructorButton() {
        objLocatorsPage.clickButtonConstructor();
        objLocatorsPage.waitForLoadCountIngredient();
    }

    @Test
    @Step("Переход в конструктор по клику на логотип Stellar Burgers")
    public void clickLogo() {
        objLocatorsPage.clickLogo();
        objLocatorsPage.waitForLoadCountIngredient();
    }

    @Test
    @Step("Выход из аккаунота по кнопке «Выйти»")
    public void clickbuttonExit() {
        objLocatorsAccount.logOutAccount();
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

