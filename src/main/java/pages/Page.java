package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Page {

    protected WebDriver driver;

    protected WebDriverWait wait;

    protected Page(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 10);
    }

    public WebDriver getDriver() {
        return driver;
    }

    public String getCurrentUrl(){
        return driver.getCurrentUrl();
    }
}
