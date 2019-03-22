package steps;

import pages.DemoChatPage;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

//TODO add steps for allure
//TODO add screenshot whe test failed
//TODO start test parallel
public class DemoChatSteps {

    private DemoChatPage demoChatPage;

    public DemoChatSteps(DemoChatPage demoChatPage) {
        this.demoChatPage = demoChatPage;
    }

    public DemoChatSteps sendMessage(String message) {
        demoChatPage.typeMessage(message).clickSendButton();
        return this;
    }

    public void checkLastMessage(String message) {
        String lastMessage = demoChatPage.getOwnLastMessage();
        assertThat(lastMessage, is(equalTo(message)));
    }

    public void checkLastEditedMessage(String message) {
        String editedMessage = demoChatPage.getOwnLastEditedMessage();
        assertThat(editedMessage, is(equalTo(message)));
    }

    public void checkLastDeletedMessage(String message) {
        String removedMessage = demoChatPage.getOwnLastDeletedMessage();
        assertThat(removedMessage, is(equalTo(message)));
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
        assertThat(demoChatPage.checkDemoVersionWindowIsDisplayed(), is(true));
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
        assertThat(profileName, is(equalTo(name)));
        assertThat(profileEmail, is(equalTo(email)));
        assertThat(profilePhoto, is(equalTo(photo)));
    }

    public DemoChatSteps checkMainName(String name) {
        String avatarName = demoChatPage.getMainName();
        assertThat(avatarName, is(equalTo(name)));
        return this;
    }

    public DemoChatSteps checkMainPhoto(String photo) {
        String avatarPhoto = demoChatPage.getMainPhotoUrl();
        assertThat(avatarPhoto, is(equalTo(photo)));
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
        assertThat(lastAttachment, is(equalTo(fileName)));
    }

    public void checkAttachmentSize(int size) {
        int attachmentSize = demoChatPage.getAttachmentSize();
        assertThat(attachmentSize, is(equalTo(size)));
    }

    public DemoChatSteps clickInviteButton() {
        demoChatPage.clickInviteButton();
        return this;
    }

    public DemoChatSteps checkNotifyMessage(String message) {
        String notifyMessage = demoChatPage.getNotifyMessage();
        assertThat(notifyMessage, is(equalTo(message)));
        return this;
    }

    public void checkBufferText() {
        String currentUrl = demoChatPage.getCurrentUrl();
        String bufferUrl = demoChatPage.getUrlFromBuffer();
        assertThat(bufferUrl, is(equalTo(currentUrl)));
    }
}