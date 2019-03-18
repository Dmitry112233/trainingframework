package steps;

import data.Data;
import org.testng.Assert;
import pages.DemoChatPage;

public class DemoChatStep {

    private DemoChatPage demoChatPage;

    public DemoChatStep(DemoChatPage demoChatPage) {
        this.demoChatPage = demoChatPage;
    }

    public DemoChatStep sendMessage() {
        demoChatPage.typeMessage(Data.getMessage()).clickSendButton();
        return this;
    }

    public void checkLastMessage(String message) {
        Assert.assertTrue(demoChatPage.checkOwnLastMessageText(message));
    }

    public DemoChatStep removeLastOwnMessage() {
        demoChatPage.clickRemoveButtonForOwnLastMessage().acceptAlert();
        return this;
    }

    public DemoChatStep editLastOwnMessage() {
        demoChatPage.clickEditButtonForLastMessage().typeEditingMessage(Data.getEditingMessage())
                .sendEditingMessage();
        return this;
    }

    public DemoChatStep sendTenMessage() {
        for (int i = 0; i < 11; i++) {
            demoChatPage.typeMessage(Data.getMessage()).clickSendButton();
        }
        return this;
    }

    public void checkDemoVersionWindowIsDisplayed() {
        demoChatPage.checkDemoVersionWindowIsDisplayed();
    }

    public DemoChatStep fillInProfile(String name, String email, String photo){
        demoChatPage.clickSettingButton().typeUserName(name).typeUserEmail(email)
                .typePhotoUrl(photo).clickSaveButton();
        return this;
    }

    public DemoChatStep fillInUserName(String username){
        demoChatPage.clickSettingButton().typeUserName(username).clickSaveButton();
        return this;
    }

    public void checkProfileData(String name, String email, String photo){
        demoChatPage.clickSettingButton();
        Assert.assertTrue(demoChatPage.checkProfileName(name));
        Assert.assertTrue(demoChatPage.checkProfileEmail(email));
        Assert.assertTrue(demoChatPage.checkProfilePhotoUrl(photo));
    }

    public DemoChatStep checkMainName(String name){
        Assert.assertTrue(demoChatPage.checkMainName(name));
        return this;
    }

    public DemoChatStep checkMainPhoto(String photo){
        Assert.assertTrue(demoChatPage.checkMainPhotoUrl(photo));
        return this;
    }

    public DemoChatStep sendFile(String filePath){
        demoChatPage.clcikDragAndDropButton().addFile(filePath).clickStartButton();
        return this;
    }
}
