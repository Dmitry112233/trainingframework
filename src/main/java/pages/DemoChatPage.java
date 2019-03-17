package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class DemoChatPage extends Page {

    public DemoChatPage(WebDriver driver) {
        super(driver);
    }

    private static final By MESSAGE_FIELD = By.xpath("//textarea[@placeholder='Start typing here']");
    private static final By OWN_MESSAGE_LIST = By.xpath("//div[contains(@class,'integri-chat-message-own')]//" +
            "div[@class='integri-chat-message-text']");
    private static final By SEND_BUTTON = By.xpath("//button[@title='Send message']");
    private static final By SETTINGS_BUTTON = By.xpath("//span[contains(@class,'integri-chat-settings')]");
    private static final By DRAG_AND_DROP_BUTTON = By.xpath("//span[text() = 'Drag & drop to upload']");
    private static final By EDIT_OWN_LAST_MESSAGE_BUTTON = By.xpath("//div[contains(@class,'integri-chat-message-own')]" +
            "[last()]//span[contains(@class,'integri-chat-edit-message')]");
    private static final By DELETE_OWN_LAST_MESSAGE_BUTTON = By.xpath("//div[contains(@class,'integri-chat-message-own')]" +
            "[last()]//span[contains(@class,'integri-chat-remove-message')]");
    private static final By MESSAGE_FIELD_FOR_EDIT = By.xpath("//div[contains(@class,'integri-chat-message-own')]" +
            "[last()]//textarea");
    private static final By DEMO_VERSION_WINDOW = By.xpath("//div[@class='integri-demo-version']");
    private static final By NAME_FIELD = By.xpath("//input[@placeholder='Name']");
    private static final By EMAIL_FIELD = By.xpath("//input[@placeholder='Email']");
    private static final By URL_FIELD = By.xpath("//input[@placeholder='Photo URL']");
    private static final By SAVE_BUTTON = By.xpath("//button[@class='integri-user-settings-save integri-button-blue']");
    private static final By OVERLAPS_WINDOW = By.xpath("//div[@class='integri-modal integri-modal-shown']");
    private static final By NAME = By.xpath("//div[@class='integri-session-user-name']");
    private static final By PHOTO = By.xpath("//div[@class='integri-chat-session']/div[1]");

    public DemoChatPage typeMessage(String message) {
        try {
            Thread.sleep(150);
            driver.findElement(MESSAGE_FIELD).sendKeys(message);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return this;
    }

    public boolean checkMainName(String value) {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(OVERLAPS_WINDOW));
        return driver.findElement(NAME).getText().equals(value);
    }

    public boolean checkMainPhotoUrl(String value) {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(OVERLAPS_WINDOW));
        return getBackgroundImageUrl(driver.findElement(PHOTO).getCssValue("background-image")).equals(value);
    }

    private String getBackgroundImageUrl(String imageUrl) {
        imageUrl = imageUrl.substring(imageUrl.indexOf("\"") + 1, imageUrl.lastIndexOf("\""));
        return imageUrl;
    }

    public DemoChatPage typeUserName(String userName) {
        driver.findElement(NAME_FIELD).clear();
        driver.findElement(NAME_FIELD).sendKeys(userName);
        return this;
    }

    public DemoChatPage clearUserName(String userName) {
        driver.findElement(NAME_FIELD).clear();
        return this;
    }

    public DemoChatPage typeUserEmail(String userEmail) {
        driver.findElement(EMAIL_FIELD).clear();
        driver.findElement(EMAIL_FIELD).sendKeys(userEmail);
        return this;
    }

    public DemoChatPage typePhotoUrl(String photoUrl) {
        driver.findElement(URL_FIELD).clear();
        driver.findElement(URL_FIELD).sendKeys(photoUrl);
        return this;
    }

    public DemoChatPage clickSaveButton() {
        driver.findElement(SAVE_BUTTON).click();
        return this;
    }

    public boolean checkProfileName(String value) {
        String userName = driver.findElement(NAME_FIELD).getAttribute("value");
        return userName.equals(value);
    }

    public boolean checkProfileEmail(String value) {
        return driver.findElement(EMAIL_FIELD).getAttribute("value").equals(value);
    }

    public boolean checkProfilePhotoUrl(String value) {
        return driver.findElement(URL_FIELD).getAttribute("value").equals(value);
    }

    public DemoChatPage clickSendButton() {
        driver.findElement(SEND_BUTTON).click();
        return this;
    }

    public DemoChatPage clickSettingButton() {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(OVERLAPS_WINDOW));
        driver.findElement(SETTINGS_BUTTON).click();
        return this;
    }

    public DemoChatPage clcikDragAndDropButton() {
        driver.findElement(DRAG_AND_DROP_BUTTON).click();
        return this;
    }

    public DemoChatPage clickEditButtonForLastMessage() {
        driver.findElement(EDIT_OWN_LAST_MESSAGE_BUTTON).click();
        return this;
    }

    public DemoChatPage typeEditingMessage(String message) {
        WebElement messageForEdit = driver.findElement(MESSAGE_FIELD_FOR_EDIT);
        messageForEdit.clear();
        messageForEdit.sendKeys(message);
        return this;
    }

    public DemoChatPage sendEditingMessage() {
        driver.findElement(MESSAGE_FIELD_FOR_EDIT).sendKeys(Keys.chord(Keys.ENTER));
        return this;
    }

    public DemoChatPage clickRemoveButtonForOwnLastMessage() {
        driver.findElement(DELETE_OWN_LAST_MESSAGE_BUTTON).click();
        return this;
    }

    public DemoChatPage acceptAlert() {
        driver.switchTo().alert().accept();
        return this;
    }

    private List<WebElement> getMessageList() {
        return driver.findElements(OWN_MESSAGE_LIST);
    }

    public boolean checkOwnLastMessageText(String value) {
        try {
            Thread.sleep(500);
            List<WebElement> messages = getMessageList();
            return messages.get(messages.size() - 1).getAttribute("innerHTML").equals(value);
        } catch (InterruptedException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean checkDemoVersionWindowIsDisplayed() {
        wait.until(ExpectedConditions.presenceOfElementLocated(DEMO_VERSION_WINDOW));
        return driver.findElement(DEMO_VERSION_WINDOW).isDisplayed();
    }
}