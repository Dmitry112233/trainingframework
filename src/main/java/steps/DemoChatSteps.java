package steps;

import org.testng.Assert;
import pages.DemoChatPage;

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
        Assert.assertEquals(demoChatPage.getOwnLastMessage(), message, "Sending message isn't equal");
    }

    public void checkLastEditingMessage(String message) {
        Assert.assertEquals(demoChatPage.getOwnLastEditMessage(), message, "Editing message isn't equal");
    }

    public void checkLastDeletingMessage(String message) {
        Assert.assertEquals(demoChatPage.getOwnLastDeleteMessage(), message, "Deleting message isn't equal");
    }

    public DemoChatSteps removeLastOwnMessage() {
        demoChatPage.clickRemoveButtonForOwnLastMessage().acceptAlert();
        return this;
    }

    public DemoChatSteps editLastOwnMessage(String message) {
        demoChatPage.clickEditButtonForLastMessage().typeEditingMessage(message)
                .sendEditingMessage();
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
        demoChatPage.clickSettingButton().typeUserName(name).typeUserEmail(email)
                .typePhotoUrl(photo).clickSaveButton();
        return this;
    }

    public DemoChatSteps fillInUserName(String username) {
        demoChatPage.clickSettingButton().typeUserName(username).clickSaveButton();
        return this;
    }

    public void checkProfileData(String name, String email, String photo) {
        demoChatPage.clickSettingButton();
        Assert.assertEquals(demoChatPage.getProfileName(), name, "Profile name isn't equal");
        Assert.assertEquals(demoChatPage.getProfileEmail(), email, "Profile email isn't equal");
        Assert.assertEquals(demoChatPage.getProfilePhotoUrl(), photo, "Profile photo url isn't equal");
    }

    public DemoChatSteps checkMainName(String name) {
        Assert.assertEquals(demoChatPage.getMainName(), name, "Avatar name isn't equal");
        return this;
    }

    public DemoChatSteps checkMainPhoto(String photo) {
        Assert.assertEquals(demoChatPage.getMainPhotoUrl(), photo, "Avatar photo url isn't equal");
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
        Assert.assertEquals(demoChatPage.getLastAttachment(), fileName, "Attachment name isn't equal");
    }

    public void checkAttachmentSize(int size) {
        Assert.assertEquals(demoChatPage.getAttachmentSize(), size, "Attachments size incorrect");
    }

    public DemoChatSteps clickInviteButton(){
        demoChatPage.clickInviteButton();
        return this;
    }

    public DemoChatSteps checkNotifyMessage(String message){
        Assert.assertEquals(demoChatPage.getNotifyMessage(), message, "Notify message isn't equal");
        return this;
    }

    public void checkBufferText(){
        Assert.assertEquals(demoChatPage.getUrlFromBuffer(), demoChatPage.getCurrentUrl(), "Buffer link isn't equal");
    }

}