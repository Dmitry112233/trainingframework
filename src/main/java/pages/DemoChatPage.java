package pages;

import data.Data;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
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
    private static final By OVERLAPS_NAME_WINDOW = By.xpath("//div[@class='integri-modal integri-modal-shown']");
    private static final By NAME = By.xpath("//div[@class='integri-session-user-name']");
    private static final By PHOTO = By.xpath("//div[@class='integri-chat-session']/div[1]");
    private static final By INPUT_FILE = By.xpath("//input[@type='file']");
    private static final By START_BUTTON = By.xpath("//button[contains(@class, 'integri-file-upload-start')]");
    private static final By ATTACHMENT_FILES = By.xpath("//span[@class='integri-chat-message-attachment-file-name']");
    private static final By DELETING_MESSAGE_DIV = By.xpath("//div[@class='integri-chat-message integri-chat-message-utility']");

    public DemoChatPage typeMessage(String message) {
        driver.findElement(MESSAGE_FIELD).sendKeys(message);
        return this;
    }

    public DemoChatPage sendTenMessages(String message) {
        for (int i = 0; i < 11; i++) {
            wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(OWN_MESSAGE_LIST, i - 1));
            driver.findElement(MESSAGE_FIELD).sendKeys(message);
            clickSendButton();
        }
        return this;
    }

    public DemoChatPage addFile(String filePath) {
        driver.findElement(INPUT_FILE).sendKeys(filePath);
        return this;
    }

    private List<WebElement> getAttachmentList() {
        return driver.findElements(ATTACHMENT_FILES);
    }

    public int getAttachmentSize() {
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(ATTACHMENT_FILES, Data.ATTACHMENT_SIZE -1));
        return getAttachmentList().size();
    }

    public String getLastAttachment() {
        List<WebElement> attachments = getAttachmentList();
        if (attachments.size() > 0) {
            return attachments.get(attachments.size() - 1).getText().split(" ")[0];
        } else {
            return "There isn't attachment";
        }
    }

    public DemoChatPage clickStartButton() {
        driver.findElement(START_BUTTON).click();
        return this;
    }

    public String getMainName() {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(OVERLAPS_NAME_WINDOW));
        return driver.findElement(NAME).getText();
    }

    public String getMainPhotoUrl() {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(OVERLAPS_NAME_WINDOW));
        return getBackgroundImageUrl(driver.findElement(PHOTO).getCssValue("background-image"));
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

    public String getProfileName() {
        return driver.findElement(NAME_FIELD).getAttribute("value");
    }

    public String getProfileEmail() {
        return driver.findElement(EMAIL_FIELD).getAttribute("value");
    }

    public String getProfilePhotoUrl() {
        return driver.findElement(URL_FIELD).getAttribute("value");
    }

    public DemoChatPage clickSendButton() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(SEND_BUTTON));
        driver.findElement(SEND_BUTTON).click();
        return this;
    }

    public DemoChatPage clickSettingButton() {
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

    public String getOwnLastMessage() {
        List<WebElement> messages = getMessageList();
        return messages.get(messages.size() - 1).getText();
    }

    public String getOwnLastDeleteMessage() {
        wait.until(ExpectedConditions.presenceOfElementLocated(DELETING_MESSAGE_DIV));
        List<WebElement> messages = getMessageList();
        return messages.get(messages.size() - 1).getText();
    }

    public String getOwnLastEditMessage() {
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
        return messages.get(messages.size() - 1).getText();
    }

    public boolean checkDemoVersionWindowIsDisplayed() {
        wait.until(ExpectedConditions.presenceOfElementLocated(DEMO_VERSION_WINDOW));
        return driver.findElement(DEMO_VERSION_WINDOW).isDisplayed();
    }
}