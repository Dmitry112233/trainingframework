package pages;

import data.Data;
import elements.Input;
import lombok.extern.log4j.Log4j;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

@Log4j
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
    private static final By OVERLAPS_NAME_WINDOW = By.xpath("//div[@class='integri-modal integri-modal-shown']");
    private static final By NAME = By.xpath("//div[@class='integri-session-user-name']");
    private static final By PHOTO = By.xpath("//div[@class='integri-chat-session']/div[1]");
    private static final By INPUT_FILE = By.xpath("//input[@type='file']");
    private static final By START_BUTTON = By.xpath("//button[contains(@class, 'integri-file-upload-start')]");
    private static final By ATTACHMENT_FILES = By.xpath("//span[@class='integri-chat-message-attachment-file-name']");
    private static final By DELETING_MESSAGE_DIV = By.xpath("//div[@class='integri-chat-message integri-chat-message-utility']");
    private static final By INVITE_BUTTON = By.xpath("//button[@class='btn']");
    private static final By NOTIFY_MESSAGE = By.xpath("//span[@data-notify='message']");
    private static final By UPLOADS_FILE_WINDOW = By.xpath("//div[@class='integri-modal integri-modal-shown']");

    public DemoChatPage typeMessage(String message) {
        new Input(MESSAGE_FIELD, driver).sendKeys(message);
        return this;
    }

    public DemoChatPage sendTenMessages(String message) {
        for (int i = 0; i < 11; i++) {
            wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(OWN_MESSAGE_LIST, i - 1));
            new Input(MESSAGE_FIELD, driver).sendKeys(message);
            clickSendButton();
        }
        return this;
    }

    public DemoChatPage clickInviteButton() {
        driver.findElement(INVITE_BUTTON).click();
        return this;
    }

    public String getNotifyMessage() {
        wait.until(ExpectedConditions.presenceOfElementLocated(NOTIFY_MESSAGE));
        String notifyMessage = driver.findElement(NOTIFY_MESSAGE).getText();
        log.info("Get notify message: " + notifyMessage);
        return notifyMessage;
    }

    public String getCurrentUrl() {
        return super.getCurrentUrl();
    }

    public String getUrlFromBuffer() {
        return super.getUrlFromBuffer();
    }

    public DemoChatPage addFile(String filePath) {
        log.info("Add file by path: " + filePath);
        driver.findElement(INPUT_FILE).sendKeys(filePath);
        return this;
    }

    private List<WebElement> getAttachmentList() {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(UPLOADS_FILE_WINDOW));
        return driver.findElements(ATTACHMENT_FILES);
    }

    public int getAttachmentSize() {
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(ATTACHMENT_FILES, Data.ATTACHMENT_SIZE - 1));
        List<WebElement> attachments = getAttachmentList();
        log.info("Get attachments size: " + "\"" + attachments.size() + "\"");
        return attachments.size();
    }

    public String getLastAttachment() {
        List<WebElement> attachments = getAttachmentList();
        if (attachments.size() > 0) {
            String lastAttachment = attachments.get(attachments.size() - 1).getText().split(" ")[0];
            log.info("Get last attachment name: " + "\"" + lastAttachment + "\"");
            return lastAttachment;
        } else {
            log.warn("There isn't attachment");
            return "There isn't attachment";
        }
    }

    public DemoChatPage clickStartButton() {
        driver.findElement(START_BUTTON).click();
        return this;
    }

    public String getMainName() {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(OVERLAPS_NAME_WINDOW));
        String avatarName = driver.findElement(NAME).getText();
        log.info("Get avatar name: " + "\"" + avatarName + "\"");
        return avatarName;
    }

    public String getMainPhotoUrl() {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(OVERLAPS_NAME_WINDOW));
        String avatarPhoto = getBackgroundImageUrl(driver.findElement(PHOTO).getCssValue("background-image"));
        log.info("Get avatar photo url: " + "\"" + avatarPhoto + "\"");
        return avatarPhoto;
    }

    private static String getBackgroundImageUrl(String imageUrl) {
        imageUrl = imageUrl.substring(imageUrl.indexOf("\"") + 1, imageUrl.lastIndexOf("\""));
        return imageUrl;
    }

    public DemoChatPage typeUserName(String userName) {
        new Input(NAME_FIELD, driver).sendKeys(userName);
        return this;
    }

    public DemoChatPage typeUserEmail(String userEmail) {
        new Input(EMAIL_FIELD, driver).sendKeys(userEmail);
        return this;
    }

    public DemoChatPage typePhotoUrl(String photoUrl) {
        new Input(URL_FIELD, driver).sendKeys(photoUrl);
        return this;
    }

    public DemoChatPage clickSaveButton() {
        driver.findElement(SAVE_BUTTON).click();
        return this;
    }

    public String getProfileName() {
        String profileName = driver.findElement(NAME_FIELD).getAttribute("value");
        log.info("Get profile data: " + "\"" + profileName + "\"");
        return profileName;
    }

    public String getProfileEmail() {
        String profileEmail = driver.findElement(EMAIL_FIELD).getAttribute("value");
        log.info("Get profile data: " + "\"" + profileEmail + "\"");
        return profileEmail;
    }

    public String getProfilePhotoUrl() {
        String profilePhoto = driver.findElement(URL_FIELD).getAttribute("value");
        log.info("Get profile data: " + "\"" + profilePhoto + "\"");
        return profilePhoto;
    }

    public DemoChatPage clickSendButton() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(SEND_BUTTON));
        driver.findElement(SEND_BUTTON).click();
        return this;
    }

    public DemoChatPage clickSettingsButton() {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(OVERLAPS_NAME_WINDOW));
        driver.findElement(SETTINGS_BUTTON).click();
        return this;
    }

    public DemoChatPage clickDragAndDropButton() {
        driver.findElement(DRAG_AND_DROP_BUTTON).click();
        return this;
    }

    public DemoChatPage clickEditButtonForLastMessage() {
        driver.findElement(EDIT_OWN_LAST_MESSAGE_BUTTON).click();
        return this;
    }

    public DemoChatPage typeEditedMessage(String message) {
        new Input(MESSAGE_FIELD_FOR_EDIT, driver).sendKeys(message);
        return this;
    }

    public DemoChatPage sendEditedMessage() {
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

    public String getOwnLastMessage() {
        List<WebElement> messages = getMessageList();
        String lastMessage = messages.get(messages.size() - 1).getText();
        log.info("Get last message: " + "\"" + lastMessage + "\"");
        return lastMessage;
    }

    public String getOwnLastDeletedMessage() {
        wait.until(ExpectedConditions.presenceOfElementLocated(DELETING_MESSAGE_DIV));
        List<WebElement> messages = getMessageList();
        String lastMessage = messages.get(messages.size() - 1).getText();
        log.info("Get last deleted message: " + "\"" + lastMessage + "\"");
        return lastMessage;
    }

    public String getOwnLastEditedMessage() {
        wait.until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                JavascriptExecutor js = (JavascriptExecutor) driver;
                    Object content = js.executeScript("return window.getComputedStyle" +
                            "(document.getElementsByClassName('integri-chat-message-text')[0], '::after').getPropertyValue('content');");
                return content.toString().contains("\"(edited)\"");
            }
        });
        List<WebElement> messages = getMessageList();
        String lastMessage = messages.get(messages.size() - 1).getText();
        log.info("Get last edited message: " + "\"" + lastMessage + "\"");
        return lastMessage;
    }

    public boolean checkDemoVersionWindowIsDisplayed() {
        wait.until(ExpectedConditions.presenceOfElementLocated(DEMO_VERSION_WINDOW));
        return driver.findElement(DEMO_VERSION_WINDOW).isDisplayed();
    }
}