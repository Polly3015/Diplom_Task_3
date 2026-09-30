package Tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

public class DriverFactory {

    private RemoteWebDriver driver;

    public void initDriver() {
        // не заработал, сказали можно заменить
        // if ("yandex".equals(System.getProperty("browser"))){
        //    initYandex();

        if ("firefox".equals(System.getProperty("browser"))){
            setupFirefox();
        } else {
            setupChrome();
        }
    }

    public void setupChrome(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

    public void setupFirefox(){
        WebDriverManager.firefoxdriver().setup();
        var options = new FirefoxOptions().configureFromEnv();

        driver = new FirefoxDriver(options);
    }


    /*
    public void initYandex(){

        WebDriverManager.chromedriver().driverVersion(System.getProperty("driver.version")).setup();

        var options = new ChromeOptions();
        options.setBinary(System.getProperty("webdriver.yandex.bin"));

        driver = new ChromeDriver(options);
    }
     */

    public RemoteWebDriver getDriver() {
        return driver;
    }
}
