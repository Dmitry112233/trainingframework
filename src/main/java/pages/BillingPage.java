package pages;

import lombok.extern.log4j.Log4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

@Log4j
public class BillingPage extends Page {

    private static final By ADD_NEW_BUTTON = By.xpath("//a[contains(text(),'Add new')]");
    private static final By CARDS_LIST = By.xpath("//div[@class='cards']/div");
    private static final By CARD_NUMBER_AND_TYPE = By.xpath("//div[@class='col-md-7']");
    private static final By MAKE_DEFAULT_LINK = By.xpath("//div[@class='col-md-3']/a");
    private static final By DEFAULT_MARK = By.xpath("//div[@class='col-md-3']");
    private static final By REMOVE_LINK = By.xpath("//div[@class='col-md-2']/a");

    public BillingPage(WebDriver driver) {
        super(driver);
    }

    private List<WebElement> getCards(){
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(CARDS_LIST));
        return driver.findElements(CARDS_LIST);
    }

    private WebElement getLastCard(){
        List<WebElement> cards = getCards();
        return cards.get(cards.size()-1);
    }

    public String getLastCardNumberAndType(){
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(CARD_NUMBER_AND_TYPE));
        String cardNumberAndType = getLastCard().findElement(CARD_NUMBER_AND_TYPE).getText();
        log.info("Get last card number and type: " + cardNumberAndType);
        return cardNumberAndType;
    }

    public String getLastCardMark(){
        String cardMark = getLastCard().findElement(DEFAULT_MARK).getText();
        log.info("Get last card mark:" + cardMark);
        return cardMark;
    }

    public int getNumberOfCards(){
        int numberOfCards = getCards().size();
        log.info("Get number of cards : " + numberOfCards);
        return numberOfCards;
    }

    public BillingPage removeLastCard(){
        wait.until(ExpectedConditions.presenceOfElementLocated(REMOVE_LINK));
        getLastCard().findElement(REMOVE_LINK).click();
        return this;
    }

    public BillingPage clickMakeDefaultLastCard(){
        wait.until(ExpectedConditions.presenceOfElementLocated(MAKE_DEFAULT_LINK));
        getLastCard().findElement(MAKE_DEFAULT_LINK).click();
        return this;
    }

    public AddNewCardPage clickAddNewCardButton(){
        wait.until(ExpectedConditions.presenceOfElementLocated(ADD_NEW_BUTTON));
        driver.findElement(ADD_NEW_BUTTON).click();
        return new AddNewCardPage(driver);
    }

}
