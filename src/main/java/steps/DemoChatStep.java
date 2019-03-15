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
                .typePhotoUrl(photo).clcikSaveButton();
        return this;
    }

    public void checkProfileData(String name, String email, String photo){
        demoChatPage.clickSettingButton();
        Assert.assertTrue(demoChatPage.checkUserName(name));
        Assert.assertTrue(demoChatPage.checkUserEmail(email));
        Assert.assertTrue(demoChatPage.checkPhotoUrl(photo));
    }

    public DemoChatStep checkUserName(String name){
        Assert.assertTrue(demoChatPage.checkName(name));
        return this;
    }

    public DemoChatStep checkUserPhoto(String photo){
        Assert.assertTrue(demoChatPage.checkPhoto(photo));
        return this;
    }
}
