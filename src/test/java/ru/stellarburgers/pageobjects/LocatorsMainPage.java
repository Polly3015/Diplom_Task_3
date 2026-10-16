package ru.stellarburgers.pageobjects;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static ru.stellarburgers.pageobjects.WaitElement.waitForElementVisible;


public class LocatorsMainPage {

    private WebDriver driver;

    // Иконка количества ингредиента
    private By countIngredient = By.className("counter_counter__num__3nue1");

    // Кнопка "Личный кабинет"
    private By personalAccount = By.xpath("//p[contains(@class, 'AppHeader_header__linkText__3q_va') and text() = 'Личный Кабинет']");

    // Кнопка "Войти в аккаунт"
    private By logInAccount = By.className("button_button__33qZ0");

    // Кнопка "Конструктор"
    private By buttonConstructor = By.xpath("//p[contains(@class, 'AppHeader_header__linkText__3q_va') and text() = 'Конструктор']");

    // Логотип Stellar Burgers
    private By logo = By.className("AppHeader_header__logo__2D0X2");

        // ----- КОНСТРУКТОР ----------------------------------

        // Вкладка Булки
        private By tabBread = By.xpath("//div[contains(@class, 'tab_tab__1SPyG')]//span[normalize-space()='Булки']");
            private By activeTabBread = By.xpath("//div[contains(@class, 'tab_tab_type_current__2BEPc')]//span[normalize-space()='Булки']");


        // Вкладка Соусы
        private By tabSauce = By.xpath("//div[contains(@class, 'tab_tab__1SPyG')]//span[normalize-space()='Соусы']");
            private By activeTabSauce = By.xpath("//div[contains(@class, 'tab_tab_type_current__2BEPc')]//span[normalize-space()='Соусы']");


        // Вкладка Начинки
        private By tabFilling = By.xpath("//div[contains(@class, 'tab_tab__1SPyG')]//span[normalize-space()='Начинки']");
            private By activeTabFilling = By.xpath("//div[contains(@class, 'tab_tab_type_current__2BEPc')]//span[normalize-space()='Начинки']");




    public LocatorsMainPage(WebDriver driver) {
        this.driver = driver;
    }

    // ----------------------------------------------------------------

    // метод ожидания прогрузки страницы (ждем появления иконки количества ингредиента)
    @Step("Дождаться загрузки страницы")
    public void waitForLoadCountIngredient() {
        waitForElementVisible(driver, countIngredient);
    }

    // Нажать на "Личный кабинет" в верху страницы
    @Step("Перейти в Личный кабинет")
    public void clickButtonPersonalAccount() {
        driver.findElement(personalAccount).click();
    }

    // Нажать на "Войти в аккаунт"
    @Step("Нажать кнопку 'Войти в аккаунт'")
    public void clickButtonLogInAccount() {
        driver.findElement(logInAccount).click();
    }

    // Нажать на "Консруктор"
    @Step("Нажать на 'Конструктор'")
    public void clickButtonConstructor() {
        driver.findElement(buttonConstructor).click();
    }

    // Нажать на логотип вверху страницы
    @Step("Нажать на логотип вверху страницы")
    public void clickLogo() {
        driver.findElement(logo).click();
    }

    // ----- КОНСТРУКТОР ----------------------------------

    // Булочки
    @Step("Перейти на вкладку с булочками")
    public void clickTabBread() {
        driver.findElement(tabBread).click();
    }
        @Step("Проверить, что вкладка с булочками активна")
        public void checkingTabBreadActive() {
            waitForElementVisible(driver, activeTabBread);
        }

    // Соусы
    @Step("Перейти на вкладку с соусамии")
    public void clickTabSauce() {
        driver.findElement(tabSauce).click();
    }
        @Step("Проверить, что вкладка с соусами активна")
        public void checkingTabSauceActive() {
            waitForElementVisible(driver, activeTabSauce);
        }

    // Начинки
    @Step("Перейти на вкладку с начинками")
    public void clickTabFilling() {
        driver.findElement(tabFilling).click();
    }
        @Step("Проверить, что вкладка с начинками активна")
        public void checkingTabFillingActive() {
            waitForElementVisible(driver, activeTabFilling);
        }

}
