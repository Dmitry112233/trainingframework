package baseTest;

import driver.DriverManager;
import driver.DriverManagerFactory;
import driver.DriverType;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

public class BaseTest {

    protected WebDriver driver;

    private int timeout = 5;

    public WebDriver getDriver() {
        return driver;
    }

    private DriverManager driverManager;


    public void configureBrowser() {
        driverManager = DriverManagerFactory.getDriverManager(DriverType.CHROME);
        driver = driverManager.getWebDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(timeout, TimeUnit.SECONDS);
    }

    public void quitBrowser() {
        driverManager.quitWebDriver();
    }
}
