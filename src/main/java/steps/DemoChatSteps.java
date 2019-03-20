package steps;

import org.apache.log4j.Logger;
import org.testng.Assert;
import pages.DemoChatPage;

public class DemoChatSteps {

    private DemoChatPage demoChatPage;

    private static final Logger logger = Logger.getLogger(DemoChatSteps.class);


    public DemoChatSteps(DemoChatPage demoChatPage) {
        this.demoChatPage = demoChatPage;
    }

    public DemoChatSteps sendMessage(String message) {
        demoChatPage.typeMessage(message).clickSendButton();
        return this;
    }

    public void checkLastMessage(String message) {
        String lastMessage = demoChatPage.getOwnLastMessage();
        logger.info("Check message equals to: " + "\"" + message + "\"");
        Assert.assertEquals(lastMessage, message, "Sending message isn't equal");
    }

    public void checkLastEditedMessage(String message) {
        String editedMessage = demoChatPage.getOwnLastEditedMessage();
        logger.info("Check message equals to: " + "\"" + message + "\"");
        Assert.assertEquals(editedMessage, message, "Editing message isn't equal");
    }

    public void checkLastDeletedMessage(String message) {
        String removedMessage = demoChatPage.getOwnLastDeletedMessage();
        logger.info("Check message equals to: " + "\"" + message + "\"");
        Assert.assertEquals(removedMessage, message, "Deleting message isn't equal");
    }

    public DemoChatSteps removeLastOwnMessage() {
        demoChatPage.clickRemoveButtonForOwnLastMessage().acceptAlert();
        return this;
    }

    public DemoChatSteps editLastOwnMessage(String message) {
        demoChatPage.clickEditButtonForLastMessage().typeEditedMessage(message)
                .sendEditedMessage();
        return this;
    }

    public DemoChatSteps sendTenMessages(String message) {
        demoChatPage.sendTenMessages(message);
        return this;
    }

    public void checkDemoVersionWindowIsDisplayed() {
        Assert.assertTrue(demoChatPage.checkDemoVersionWindowIsDisplayed(), "Demo version window hasn't appeared");
    }

    public DemoChatSteps fillInProfile(String name, String email, String photo) {
        demoChatPage.clickSettingsButton().typeUserName(name).typeUserEmail(email)
                .typePhotoUrl(photo).clickSaveButton();
        return this;
    }

    public DemoChatSteps fillInUserName(String username) {
        demoChatPage.clickSettingsButton().typeUserName(username).clickSaveButton();
        return this;
    }

    public void checkProfileData(String name, String email, String photo) {
        demoChatPage.clickSettingsButton();
        String profileName = demoChatPage.getProfileName();
        String profileEmail = demoChatPage.getProfileEmail();
        String profilePhoto = demoChatPage.getProfilePhotoUrl();
        logger.info("Check profile data equal name: " + "\"" + name + "\"" + " email: " + "\"" + email +"\""
                + " photo: " + "\"" + photo + "\"");
        Assert.assertEquals(profileName, name, "Profile name isn't equal");
        Assert.assertEquals(profileEmail, email, "Profile email isn't equal");
        Assert.assertEquals(profilePhoto, photo, "Profile photo url isn't equal");
    }

    public DemoChatSteps checkMainName(String name) {
        String avatarName = demoChatPage.getMainName();
        logger.info("Check avatar name equals to: " + "\"" + name + "\"");
        Assert.assertEquals(avatarName, name, "Avatar name isn't equal");
        return this;
    }

    public DemoChatSteps checkMainPhoto(String photo) {
        String avatarPhoto = demoChatPage.getMainPhotoUrl();
        logger.info("Check avatar photo equals to: " + "\"" + photo + "\"");
        Assert.assertEquals(avatarPhoto, photo, "Avatar photo url isn't equal");
        return this;
    }

    public DemoChatSteps clickDragAndDropButton() {
        demoChatPage.clickDragAndDropButton();
        return this;
    }

    public DemoChatSteps addFile(String filePath) {
        demoChatPage.addFile(filePath);
        return this;
    }

    public DemoChatSteps clickStartButton() {
        demoChatPage.clickStartButton();
        return this;
    }

    public void checkLastAttachment(String fileName) {
        String lastAttachment = demoChatPage.getLastAttachment();
        logger.info("Check attachment name equals: " + "\"" + fileName  + "\"");
        Assert.assertEquals(lastAttachment, fileName, "Attachment name isn't equal");
    }

    public void checkAttachmentSize(int size) {
        int attachmentSize = demoChatPage.getAttachmentSize();
        logger.info("Check attachment size is: " + size);
        Assert.assertEquals(attachmentSize, size, "Attachments size incorrect");
    }

    public DemoChatSteps clickInviteButton() {
        demoChatPage.clickInviteButton();
        return this;
    }

    public DemoChatSteps checkNotifyMessage(String message) {
        String notifyMessage = demoChatPage.getNotifyMessage();
        logger.info("Check notifyMessage is: " + message);
        Assert.assertEquals(notifyMessage, message, "Notify message isn't equal");
        return this;
    }

    public void checkBufferText() {
        String currentUrl = demoChatPage.getCurrentUrl();
        String bufferUrl = demoChatPage.getUrlFromBuffer();
        logger.info("Check url in buffer equal current url");
        Assert.assertEquals(bufferUrl, currentUrl, "Buffer link isn't equal");
    }

}