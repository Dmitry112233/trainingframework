package steps;

import io.qameta.allure.Step;
import pages.DemoChatPage;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;


//TODO start test parallel
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
    public void checkLastMessage(String exp_message) {
        String lastMessage = demoChatPage.getOwnLastMessage();
        assertThat(lastMessage, is(equalTo(exp_message)));
    }

    @Step("Check last message")
    public void checkLastEditedMessage(String exp_message) {
        String editedMessage = demoChatPage.getOwnLastEditedMessage();
        assertThat(editedMessage, is(equalTo(exp_message)));
    }

    @Step("Check last message")
    public void checkLastDeletedMessage(String exp_message) {
        String removedMessage = demoChatPage.getOwnLastDeletedMessage();
        assertThat(removedMessage, is(equalTo(exp_message)));
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

    @Step("Sen ten message")
    public DemoChatSteps sendTenMessages(String message) {
        demoChatPage.sendTenMessages(message);
        return this;
    }

    @Step("Check demo version window is displayed")
    public void checkDemoVersionWindowIsDisplayed() {
        assertThat(demoChatPage.checkDemoVersionWindowIsDisplayed(), is(true));
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
    public void checkProfileData(String exp_name, String exp_email, String exp_photo) {
        demoChatPage.clickSettingsButton();
        String profileName = demoChatPage.getProfileName();
        String profileEmail = demoChatPage.getProfileEmail();
        String profilePhoto = demoChatPage.getProfilePhotoUrl();
        assertThat(profileName, is(equalTo(exp_name)));
        assertThat(profileEmail, is(equalTo(exp_email)));
        assertThat(profilePhoto, is(equalTo(exp_photo)));
    }

    @Step("Check main name")
    public DemoChatSteps checkMainName(String exp_name) {
        String avatarName = demoChatPage.getMainName();
        assertThat(avatarName, is(equalTo(exp_name)));
        return this;
    }

    @Step("Check main photo url")
    public DemoChatSteps checkMainPhoto(String exp_photo) {
        String avatarPhoto = demoChatPage.getMainPhotoUrl();
        assertThat(avatarPhoto, is(equalTo(exp_photo)));
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
    public void checkLastAttachment(String exp_fileName) {
        String lastAttachment = demoChatPage.getLastAttachment();
        assertThat(lastAttachment, is(equalTo(exp_fileName)));
    }

    @Step("Check attachments size")
    public void checkAttachmentSize(int exp_size) {
        int attachmentSize = demoChatPage.getAttachmentSize();
        assertThat(attachmentSize, is(equalTo(exp_size)));
    }

    @Step("Click [invite user to chat] button")
    public DemoChatSteps clickInviteButton() {
        demoChatPage.clickInviteButton();
        return this;
    }

    @Step("Check notify message")
    public DemoChatSteps checkNotifyMessage(String exp_message) {
        String notifyMessage = demoChatPage.getNotifyMessage();
        assertThat(notifyMessage, is(equalTo(exp_message)));
        return this;
    }

    @Step("Check text in buffer")
    public void checkBufferText() {
        String currentUrl = demoChatPage.getCurrentUrl();
        String bufferUrl = demoChatPage.getUrlFromBuffer();
        assertThat(bufferUrl, is(equalTo(currentUrl)));
    }
}