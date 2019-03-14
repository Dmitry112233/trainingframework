package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class DemoChatPage extends Page {

    private WebDriver driver;

    public DemoChatPage(WebDriver driver) {
        super(driver);
    }

    private static final By MESSAGE_FIELD = By.xpath("//textarea[@placeholder='Start typing here']");
    private static final By OWN_MESSAGE_LIST = By.xpath("//div[contains(@class,'integri-chat-message-own')]//div[@class='integri-chat-message-text']");
    private static final By SEND_BUTTON = By.xpath("//button[@title='Send message']");
    private static final By SETTINGS_BUTTON = By.xpath("//span[contains(@class,'integri-chat-settings')]");
    private static final By DRAG_AND_DROP_BUTTON = By.xpath("//span[text() = 'Drag & drop to upload']");
    private static final By EDIT_OWN_LAST_MESSAGE_BUTTON = By.xpath("//div[contains(@class,'integri-chat-message-own')][last()]" +
            "//span[contains(@class,'integri-chat-edit-message')]");
    private static final By DELETE_OWN_LAST_MESSAGE_BUTTON = By.xpath("//div[contains(@class,'integri-chat-message-own')][last()]" +
            "//span[contains(@class,'integri-chat-remove-message')]");
    private static final By MESSAGE_FIELD_FOR_EDIT = By.xpath("//div[contains(@class,'integri-chat-message-own')][last()]//textarea");
    private static final By DEMO_VERSION_WINDOW = By.xpath("//div[@class='integri-demo-version']");


    public DemoChatPage typeMessage(String message) {
        driver.findElement(MESSAGE_FIELD).sendKeys(message);
        return this;
    }

    public DemoChatPage clickSendButton() {
        driver.findElement(SEND_BUTTON).click();
        return this;
    }

    public DemoChatPage clickSettingButton() {
        driver.findElement(SETTINGS_BUTTON).click();
        return this;
    }

    public DemoChatPage clcikDragAndDropButton() {
        driver.findElement(DRAG_AND_DROP_BUTTON).click();
        return this;
    }

    public DemoChatPage editOwnLastMessage(String message) {
        WebElement messageForEdit = driver.findElement(MESSAGE_FIELD_FOR_EDIT);
        driver.findElement(EDIT_OWN_LAST_MESSAGE_BUTTON).click();
        messageForEdit.clear();
        messageForEdit.sendKeys(message);
        messageForEdit.sendKeys(Keys.chord(Keys.ENTER));
        return this;
    }

    public DemoChatPage clickEditButtonForLastMessage(){
        driver.findElement(MESSAGE_FIELD_FOR_EDIT).click();
        return  this;
    }

    public DemoChatPage typeEditingMessage(String message){
        WebElement messageForEdit = driver.findElement(MESSAGE_FIELD_FOR_EDIT);
        messageForEdit.clear();
        messageForEdit.sendKeys(message);
        return this;
    }

    public DemoChatPage sendEditingMessage(){
        driver.findElement(MESSAGE_FIELD_FOR_EDIT).sendKeys(Keys.chord(Keys.ENTER));
        return this;
    }

    public DemoChatPage removeOwnLastMessage() {
        driver.findElement(DELETE_OWN_LAST_MESSAGE_BUTTON).click();
        driver.switchTo().alert().accept();
        return this;
    }

    private List<WebElement> getMessageList() {
        return driver.findElements(OWN_MESSAGE_LIST);
    }

    public boolean checkOwnLastMessageText(String value) {
        List<WebElement> messages = getMessageList();
        return messages.get(messages.size() - 1).getText().equals(value);
    }

    public int getSizeOfOwnMessages() {
        return getMessageList().size();
    }

    public boolean checkDemoVersionWindowIsDisplayed() {
        return driver.findElement(DEMO_VERSION_WINDOW).isDisplayed();
    }
}