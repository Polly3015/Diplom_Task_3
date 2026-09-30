package LocatorsAndActions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static LocatorsAndActions.WaitElement.waitForElementVisible;


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

    //WaitElement rollAndWait = new WaitElement();

    // метод ожидания прогрузки страницы (ждем появления иконки количества ингредиента)
    public void waitForLoadCountIngredient() {
        waitForElementVisible(driver, countIngredient);
    }

    // Нажать на "Личный кабинет" в верху страницы
    public void clickButtonPersonalAccount() {
        driver.findElement(personalAccount).click();
    }

    // Нажать на "Войти в аккаунт"
    public void clickButtonLogInAccount() {
        driver.findElement(logInAccount).click();
    }

    // Нажать на "Консруктор"
    public void clickButtonConstructor() {
        driver.findElement(buttonConstructor).click();
    }

    // Нажать на логотип вверху страницы
    public void clickLogo() {
        driver.findElement(logo).click();
    }

    // ----- КОНСТРУКТОР ----------------------------------

    // Переход на вкладку Булки и проверка, что вкладка активна
    public void clickTabBread() {
        driver.findElement(tabBread).click();
        waitForElementVisible(driver, activeTabBread);
    }

    // Переход на вкладку Соусы и проверка, что вкладка активна
    public void clickTabSauce() {
        driver.findElement(tabSauce).click();
        waitForElementVisible(driver, activeTabSauce);
    }

    // Переход на вкладку Начинки и проверка, что вкладка активна
    public void clickTabFilling() {
        driver.findElement(tabFilling).click();
        waitForElementVisible(driver, activeTabFilling);
    }

}
