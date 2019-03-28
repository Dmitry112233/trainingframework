package pages;

import elements.Input;
import lombok.extern.log4j.Log4j;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

@Log4j
public class AddNewCardPage extends Page{

    private static final By CARD_NUMBER = By.xpath("//input[@name='number']");
    private static final By CARD_MONTH = By.xpath("//input[@name='expirationMonth']");
    private static final By CARD_YEAR = By.xpath("//input[@name='expirationYear']");
    private static final By CARD_NAME = By.xpath("//input[@name='cardholderName']");
    private static final By ADD_BUTTON = By.xpath("//button[@class='btn']");

    public AddNewCardPage(WebDriver driver) {
        super(driver);
    }

    public AddNewCardPage typeCardNumber(String cardNumber){
        wait.until(ExpectedConditions.presenceOfElementLocated(CARD_NUMBER));
        log.info("Type card number: " + cardNumber);
        new Input(CARD_NUMBER, driver).sendKeys(cardNumber);
        return this;
    }

    public AddNewCardPage typeCardMonth(String cardMonth){
        wait.until(ExpectedConditions.presenceOfElementLocated(CARD_MONTH));
        log.info("Type card month: " + cardMonth);
        new Input(CARD_MONTH, driver).sendKeys(cardMonth);
        return this;
    }

    public AddNewCardPage typeCardYear(String cardYear){
        wait.until(ExpectedConditions.presenceOfElementLocated(CARD_YEAR));
        log.info("Type card year: " + cardYear);
        new Input(CARD_YEAR, driver).sendKeys(cardYear);
        return this;
    }

    public AddNewCardPage typeCardName(String cardName){
        wait.until(ExpectedConditions.presenceOfElementLocated(CARD_NAME));
        log.info("Type card name: " + cardName);
        new Input(CARD_NAME, driver).sendKeys(cardName);
        return this;
    }

    public BillingPage clickAddButton(){
        wait.until((ExpectedCondition<Boolean>) driver -> {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            Object content = js.executeScript("return document.getElementsByClassName('btn')[4].disabled;");
            return content.toString().contains("false");

           // "return window.getComputedStyle" +
             //       "(document.getElementsByClassName('integri-chat-message-text')[0], '::after').getPropertyValue('content');"
        });
        driver.findElement(ADD_BUTTON).click();
        return new BillingPage(driver);
    }

}
