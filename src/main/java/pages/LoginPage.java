package pages;

import elements.Input;
import lombok.extern.log4j.Log4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

@Log4j
public class LoginPage extends Page{

    private static final By EMAIL_FIELD = By.xpath("//input[@placeholder='Email']");
    private static final By PASSWORD_FIELD = By.xpath("//input[@placeholder='Password']");
    private static final By LOG_IN_BUTTON = By.xpath("//button[@class='btn btn-primary']");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public LoginPage typeEmail(String email){
        log.info("Type user email: " + email);
        new Input(EMAIL_FIELD, driver).sendKeys(email);
        return this;
    }

    public LoginPage typePassword(String password){
        log.info("Type user password: " + password);
        new Input(PASSWORD_FIELD, driver).sendKeys(password);
        return this;
    }

    public ProjectsPage clickLogInButton() {
        driver.findElement(LOG_IN_BUTTON).click();
        return new ProjectsPage(driver);
    }
}
