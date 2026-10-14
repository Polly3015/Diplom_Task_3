package ru.stellarburgers.tests;

import org.junit.jupiter.api.DisplayName;
import ru.stellarburgers.pageobjects.Constants;
import ru.stellarburgers.pageobjects.LocatorsMainPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.openqa.selenium.WebDriver;

public class TabsIngredientsTest {
    private WebDriver driver;
    private final DriverFactory driverFactory = new DriverFactory();

    LocatorsMainPage objLocatorsPage;

    @BeforeEach
    void setUp() {
        driverFactory.initDriver();
        driver = driverFactory.getDriver();
        driver.get(Constants.BASE_URI);

        objLocatorsPage = new LocatorsMainPage(driver);

        //Загрузка главной страницы
        objLocatorsPage.waitForLoadCountIngredient();
    }

    @Test
    @DisplayName("Переход в раздел 'Булки'")
    public void clickTabBread() {
        objLocatorsPage.clickTabSauce();
        objLocatorsPage.clickTabBread();
        objLocatorsPage.checkingTabBreadActive();
    }

    @Test
    @DisplayName("Переход в раздел 'Соусы'")
    public void clickTabSauce() {
        objLocatorsPage.clickTabSauce();
        objLocatorsPage.checkingTabSauceActive();
    }

    @Test
    @DisplayName("Переход в раздел 'Начинки'")
    public void clickTabFilling() {
        objLocatorsPage.clickTabFilling();
        objLocatorsPage.checkingTabFillingActive();
    }


    @AfterEach
    void tearDown() {
        driver.quit();
    }

}
