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
        Assert.assertEquals(demoChatPage.getOwnLastMessage(), message);
    }

    public void checkLastEditingMessage(String message) {
        Assert.assertEquals(demoChatPage.getOwnLastEditMessage(), message);
    }

    public void checkLastDeletingMessage(String message) {
        Assert.assertEquals(demoChatPage.getOwnLastDeleteMessage(), message);
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
        Assert.assertTrue(demoChatPage.checkDemoVersionWindowIsDisplayed());
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
        Assert.assertEquals(demoChatPage.getProfileName(), name);
        Assert.assertEquals(demoChatPage.getProfileEmail(), email);
        Assert.assertEquals(demoChatPage.getProfilePhotoUrl(), photo);
    }

    public DemoChatSteps checkMainName(String name) {
        Assert.assertEquals(demoChatPage.getMainName(), name);
        return this;
    }

    public DemoChatSteps checkMainPhoto(String photo) {
        Assert.assertEquals(demoChatPage.getMainPhotoUrl(), photo);
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
        Assert.assertEquals(demoChatPage.getLastAttachment(), fileName);
    }

    public void checkAttachmentSize(int size) {
        Assert.assertEquals(demoChatPage.getAttachmentSize(), size);
    }

    public DemoChatSteps clickInviteButton(){
        demoChatPage.clickInviteButton();
        return this;
    }

    public DemoChatSteps checkNotifyMessage(String message){
        Assert.assertEquals(demoChatPage.getNotifyMessage(), message);
        return this;
    }

    public void checkBufferText(){
        Assert.assertEquals(demoChatPage.getUrlFromBuffer(), demoChatPage.getCurrentUrl());
    }

}