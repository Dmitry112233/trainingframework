package pages;

import data.Data;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.util.List;

public class DemoChatPage extends Page {

    private static final Logger logger = Logger.getLogger(DemoChatPage.class);


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


    public DemoChatPage typeMessage(String message) {
        logger.info("Type message: " + "\"" + message + "\"");
        driver.findElement(MESSAGE_FIELD).sendKeys(message);
        return this;
    }

    public DemoChatPage sendTenMessages(String message) {
        for (int i = 0; i < 11; i++) {
            logger.info("Type message: " + "\"" + message + "\"");
            wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(OWN_MESSAGE_LIST, i - 1));
            driver.findElement(MESSAGE_FIELD).sendKeys(message);
            clickSendButton();
        }
        return this;
    }

    public DemoChatPage clickInviteButton() {
        logger.info("Click [Invite users to chat] button");
        driver.findElement(INVITE_BUTTON).click();
        return this;
    }

    public String getNotifyMessage() {
        wait.until(ExpectedConditions.presenceOfElementLocated(NOTIFY_MESSAGE));
        String notifyMessage = driver.findElement(NOTIFY_MESSAGE).getText();
        logger.info("Get notify message: " + "\"" + notifyMessage + "\"");
        return notifyMessage;
    }

    public String getCurrentUrl() {
        String currentUrl = driver.getCurrentUrl();
        logger.info("Get current url: " + "\"" + currentUrl + "\"");
        return currentUrl;
    }

    public String getUrlFromBuffer() {
        String bufferUrl = "";
        try {
            bufferUrl = (String) Toolkit.getDefaultToolkit()
                    .getSystemClipboard().getData(DataFlavor.stringFlavor);
            logger.info("Get url form buffer: " + "\"" + bufferUrl + "\"");
        } catch (UnsupportedFlavorException e) {
            logger.warn("Can't read from buffer " + e);
            e.printStackTrace();
        } catch (IOException e) {
            logger.warn("Can't read from buffer " + e);
            e.printStackTrace();
        }
        return bufferUrl;
    }

    public DemoChatPage addFile(String filePath) {
        logger.info("Add file by path: " + filePath);
        driver.findElement(INPUT_FILE).sendKeys(filePath);
        return this;
    }

    private List<WebElement> getAttachmentList() {
        logger.info("Get all attachments");
        return driver.findElements(ATTACHMENT_FILES);
    }

    public int getAttachmentSize() {
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(ATTACHMENT_FILES, Data.ATTACHMENT_SIZE - 1));
        List<WebElement> attachments = getAttachmentList();
        logger.info("Get attachments size: " + "\"" + attachments.size() + "\"");
        return attachments.size();
    }

    public String getLastAttachment() {
        List<WebElement> attachments = getAttachmentList();
        if (attachments.size() > 0) {
            String lastAttachment = attachments.get(attachments.size() - 1).getText().split(" ")[0];
            logger.info("Get last attachment name: " + "\"" + lastAttachment + "\"");
            return lastAttachment;
        } else {
            logger.warn("There isn't attachment");
            return "There isn't attachment";
        }
    }

    public DemoChatPage clickStartButton() {
        logger.info("Click [Start] button");
        driver.findElement(START_BUTTON).click();
        return this;
    }

    public String getMainName() {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(OVERLAPS_NAME_WINDOW));
        String avatarName = driver.findElement(NAME).getText();
        logger.info("Get avatar name: " + "\"" + avatarName + "\"");
        return avatarName;
    }

    public String getMainPhotoUrl() {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(OVERLAPS_NAME_WINDOW));
        String avatarPhoto = getBackgroundImageUrl(driver.findElement(PHOTO).getCssValue("background-image"));
        logger.info("Get avatar photo url: " + "\"" + avatarPhoto + "\"");
        return avatarPhoto;
    }

    private String getBackgroundImageUrl(String imageUrl) {
        imageUrl = imageUrl.substring(imageUrl.indexOf("\"") + 1, imageUrl.lastIndexOf("\""));
        return imageUrl;
    }

    public DemoChatPage typeUserName(String userName) {
        logger.info("Type user name: " + "\"" + userName + "\"");
        driver.findElement(NAME_FIELD).clear();
        driver.findElement(NAME_FIELD).sendKeys(userName);
        return this;
    }

    public DemoChatPage typeUserEmail(String userEmail) {
        logger.info("Type user email: " + "\"" + userEmail + "\"");
        driver.findElement(EMAIL_FIELD).clear();
        driver.findElement(EMAIL_FIELD).sendKeys(userEmail);
        return this;
    }

    public DemoChatPage typePhotoUrl(String photoUrl) {
        logger.info("Type photo url: " + "\"" + photoUrl + "\"");
        driver.findElement(URL_FIELD).clear();
        driver.findElement(URL_FIELD).sendKeys(photoUrl);
        return this;
    }

    public DemoChatPage clickSaveButton() {
        logger.info("Click [Save] button");
        driver.findElement(SAVE_BUTTON).click();
        return this;
    }

    public String getProfileName() {
        String profileName = driver.findElement(NAME_FIELD).getAttribute("value");
        logger.info("Get profile name: " + "\"" + profileName + "\"");
        return profileName;
    }

    public String getProfileEmail() {
        String profileEmail = driver.findElement(EMAIL_FIELD).getAttribute("value");
        logger.info("Get profile email: " + "\"" + profileEmail + "\"");
        return profileEmail;
    }

    public String getProfilePhotoUrl() {
        String profilePhoto = driver.findElement(URL_FIELD).getAttribute("value");
        logger.info("Get profile photo url: " + "\"" + profilePhoto + "\"");
        return profilePhoto;
    }

    public DemoChatPage clickSendButton() {
        logger.info("Click [Send] button");
        wait.until(ExpectedConditions.visibilityOfElementLocated(SEND_BUTTON));
        driver.findElement(SEND_BUTTON).click();
        return this;
    }

    public DemoChatPage clickSettingsButton() {
        logger.info("Click [Settings] button");
        wait.until(ExpectedConditions.invisibilityOfElementLocated(OVERLAPS_NAME_WINDOW));
        driver.findElement(SETTINGS_BUTTON).click();
        return this;
    }

    public DemoChatPage clickDragAndDropButton() {
        logger.info("Click [Drag & drop] button");
        driver.findElement(DRAG_AND_DROP_BUTTON).click();
        return this;
    }

    public DemoChatPage clickEditButtonForLastMessage() {
        logger.info("Click [Edit] button");
        driver.findElement(EDIT_OWN_LAST_MESSAGE_BUTTON).click();
        return this;
    }

    public DemoChatPage typeEditedMessage(String message) {
        logger.info("Type edited message: " + "\"" + message + "\"");
        WebElement messageForEdit = driver.findElement(MESSAGE_FIELD_FOR_EDIT);
        messageForEdit.clear();
        messageForEdit.sendKeys(message);
        return this;
    }

    public DemoChatPage sendEditedMessage() {
        logger.info("Send edited message");
        driver.findElement(MESSAGE_FIELD_FOR_EDIT).sendKeys(Keys.chord(Keys.ENTER));
        return this;
    }

    public DemoChatPage clickRemoveButtonForOwnLastMessage() {
        logger.info("Click [Remove] button");
        driver.findElement(DELETE_OWN_LAST_MESSAGE_BUTTON).click();
        return this;
    }

    public DemoChatPage acceptAlert() {
        logger.info("Accept alert");
        driver.switchTo().alert().accept();
        return this;
    }

    private List<WebElement> getMessageList() {
        logger.info("Get all messages");
        return driver.findElements(OWN_MESSAGE_LIST);
    }

    public String getOwnLastMessage() {
        List<WebElement> messages = getMessageList();
        String lastMessage = messages.get(messages.size() - 1).getText();
        logger.info("Get last message: " + "\"" + lastMessage + "\"");
        return lastMessage;
    }

    public String getOwnLastDeletedMessage() {
        wait.until(ExpectedConditions.presenceOfElementLocated(DELETING_MESSAGE_DIV));
        List<WebElement> messages = getMessageList();
        String deletedMessage = messages.get(messages.size() - 1).getText();
        logger.info("Get removed message: " + "\"" + deletedMessage + "\"");
        return deletedMessage;
    }

    public String getOwnLastEditedMessage() {
        wait.until(new ExpectedCondition<String>() {
            public String apply(WebDriver driver) {
                JavascriptExecutor js = (JavascriptExecutor) driver;
                String str = "";
                while (!str.equals("\"(edited)\"")) {
                    Object content = js.executeScript("return window.getComputedStyle" +
                            "(document.getElementsByClassName('integri-chat-message-text')[0], '::after').getPropertyValue('content');");
                    str = content.toString();
                }
                return str;
            }
        });
        List<WebElement> messages = getMessageList();
        String editedMessage = messages.get(messages.size() - 1).getText();
        logger.info("Get edited message: " + "\"" + editedMessage + "\"");
        return editedMessage;
    }

    public boolean checkDemoVersionWindowIsDisplayed() {
        logger.info("Check demo version window has appeared");
        wait.until(ExpectedConditions.presenceOfElementLocated(DEMO_VERSION_WINDOW));
        return driver.findElement(DEMO_VERSION_WINDOW).isDisplayed();
    }
}