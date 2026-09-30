package Tests;

import LocatorsAndActions.LocatorsMainPage;
import io.qameta.allure.Step;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TabsIngredientsTest {
    private WebDriver driver;
    private final DriverFactory driverFactory = new DriverFactory();

    LocatorsMainPage objLocatorsPage;

    @BeforeEach
    void setUp() {
        driverFactory.initDriver();
        driver = driverFactory.getDriver();
        driver.get("https://qa-stellarburgers.education-services.ru/");

        objLocatorsPage = new LocatorsMainPage(driver);

        //Загрузка главной страницы
        objLocatorsPage.waitForLoadCountIngredient();
    }

    @Test
    @Step("Переходы по разделам")
    public void clickTabIngredients() {
        objLocatorsPage.clickTabSauce();
        objLocatorsPage.clickTabFilling();
        objLocatorsPage.clickTabBread();
    }


    @AfterEach
    void tearDown() {
        driver.quit();
    }

}
