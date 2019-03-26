package steps;

import io.qameta.allure.Step;
import pages.DemoChatPage;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;


public class DemoChatSteps {

    private DemoChatPage demoChatPage;

    public DemoChatSteps(DemoChatPage demoChatPage) {
        this.demoChatPage = demoChatPage;
    }


    @Step("Send message")
    public DemoChatSteps sendMessage(String message) {
        demoChatPage.typeMessage(message).clickSendButton();
        return this;
    }

    @Step("Check last message")
    public void checkLastMessage(String expMessage) {
        String lastMessage = demoChatPage.getOwnLastMessage();
        assertThat("Sending message isn't equal", lastMessage, is(equalTo(expMessage)));
    }

    @Step("Check last message")
    public void checkLastEditedMessage(String expMessage) {
        String editedMessage = demoChatPage.getOwnLastEditedMessage();
        assertThat("Editing message isn't equal", editedMessage, is(equalTo(expMessage)));
    }

    @Step("Check last message")
    public void checkLastDeletedMessage(String expMessage) {
        String removedMessage = demoChatPage.getOwnLastDeletedMessage();
        assertThat("Deleting message isn't equal", removedMessage, is(equalTo(expMessage)));
    }

    @Step("Remove message")
    public DemoChatSteps removeLastOwnMessage() {
        demoChatPage.clickRemoveButtonForOwnLastMessage().acceptAlert();
        return this;
    }

    @Step("Edit message")
    public DemoChatSteps editLastOwnMessage(String message) {
        demoChatPage.clickEditButtonForLastMessage().typeEditedMessage(message)
                .sendEditedMessage();
        return this;
    }

    @Step("Send ten messages")
    public DemoChatSteps sendTenMessages(String message) {
        demoChatPage.sendTenMessages(message);
        return this;
    }

    @Step("Check demo version window is displayed")
    public void checkDemoVersionWindowIsDisplayed() {
        assertThat("Demo version window hasn't appeared", demoChatPage.checkDemoVersionWindowIsDisplayed(), is(true));
    }

    @Step("Fill in profile")
    public DemoChatSteps fillInProfile(String name, String email, String photo) {
        demoChatPage.clickSettingsButton().typeUserName(name).typeUserEmail(email)
                .typePhotoUrl(photo).clickSaveButton();
        return this;
    }

    @Step("Type user name")
    public DemoChatSteps fillInUserName(String username) {
        demoChatPage.clickSettingsButton().typeUserName(username).clickSaveButton();
        return this;
    }

    @Step("Check profile data")
    public void checkProfileData(String expName, String expEmail, String expPhoto) {
        demoChatPage.clickSettingsButton();
        String profileName = demoChatPage.getProfileName();
        String profileEmail = demoChatPage.getProfileEmail();
        String profilePhoto = demoChatPage.getProfilePhotoUrl();
        assertThat("Profile name isn't equal", profileName, is(equalTo(expName)));
        assertThat("Profile email isn't equal", profileEmail, is(equalTo(expEmail)));
        assertThat("Profile photo url isn't equal", profilePhoto, is(equalTo(expPhoto)));
    }

    @Step("Check main name")
    public DemoChatSteps checkMainName(String expName) {
        String avatarName = demoChatPage.getMainName();
        assertThat("Avatar name isn't equal", avatarName, is(equalTo(expName)));
        return this;
    }

    @Step("Check main photo url")
    public DemoChatSteps checkMainPhoto(String expPhoto) {
        String avatarPhoto = demoChatPage.getMainPhotoUrl();
        assertThat("Avatar photo url isn't equal", avatarPhoto, is(equalTo(expPhoto)));
        return this;
    }

    @Step("Click [drag & drop] button")
    public DemoChatSteps clickDragAndDropButton() {
        demoChatPage.clickDragAndDropButton();
        return this;
    }

    @Step("Add file")
    public DemoChatSteps addFile(String filePath) {
        demoChatPage.addFile(filePath);
        return this;
    }

    @Step("Click [start] button")
    public DemoChatSteps clickStartButton() {
        demoChatPage.clickStartButton();
        return this;
    }

    @Step("Check last attachment")
    public void checkLastAttachment(String expFileName) {
        String lastAttachment = demoChatPage.getLastAttachment();
        assertThat("Attachment name isn't equal", lastAttachment, is(equalTo(expFileName)));
    }

    @Step("Check attachments size")
    public void checkAttachmentSize(int expNumebr) {
        int attachmentSize = demoChatPage.getAttachmentSize();
        assertThat("Attachments size is incorrect", attachmentSize, is(equalTo(expNumebr)));
    }

    @Step("Click [invite user to chat] button")
    public DemoChatSteps clickInviteButton() {
        demoChatPage.clickInviteButton();
        return this;
    }

    @Step("Check notify message")
    public DemoChatSteps checkNotifyMessage(String expMessage) {
        String notifyMessage = demoChatPage.getNotifyMessage();
        assertThat("Notify message isn't equal", notifyMessage, is(equalTo(expMessage)));
        return this;
    }

    @Step("Check text in buffer")
    public void checkBufferText() {
        String currentUrl = demoChatPage.getCurrentUrl();
        String bufferUrl = demoChatPage.getUrlFromBuffer();
        assertThat("Buffer link isn't equal to chat link", bufferUrl, is(equalTo(currentUrl)));
    }
}