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
        demoChatPage
                .typeMessage(Data.getMessage())
                .clickSendButton();
        return this;
    }

    public void checkLastMessage(String message) {
        Assert.assertTrue(this.demoChatPage.checkOwnLastMessageText(message));
    }

    public DemoChatStep removeLastOwnMessage() {
        demoChatPage
                .removeOwnLastMessage();
        return this;
    }

    public DemoChatStep editLastOwnMessage(){
        demoChatPage
                .clickEditButtonForLastMessage()
                .typeEditingMessage(Data.getEditingMessage())
                .sendEditingMessage();
        return this;
    }
}
