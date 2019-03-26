import baseTest.BaseTest;
import baseTest.CustomListener;
import data.Data;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.DemoChatPage;
import steps.DemoChatSteps;
import utils.PropertyReader;


@Listeners(CustomListener.class)
public class DemoChatTest2 extends BaseTest {

    private DemoChatSteps demoChatSteps;

    @BeforeMethod
    public void init() {
        configureBrowser();
        driver.get(PropertyReader.getInstance().get("url.path.chat.dev"));
        demoChatSteps = new DemoChatSteps(new DemoChatPage(driver));
    }

    @Test
    public void sendMessageTest() {
        demoChatSteps.sendMessage(Data.MESSAGE).checkLastMessage(Data.MESSAGE);
    }

    @Test
    public void editMessageTest() {
        demoChatSteps.sendMessage(Data.MESSAGE).editLastOwnMessage(Data.EDITING_MESSAGE).checkLastEditedMessage(Data.EDITING_MESSAGE);
    }

    @Test
    public void removeMessageTest() {
        demoChatSteps.sendMessage(Data.MESSAGE).removeLastOwnMessage().checkLastDeletedMessage(Data.REMOVED_MESSAGE);
    }

    @Test
    public void checkDemoVersionWindowTest() {
        demoChatSteps.sendTenMessages(Data.MESSAGE).checkDemoVersionWindowIsDisplayed();
    }

    @Test
    public void fillInProfileTest() {
        demoChatSteps.fillInProfile(Data.USER_NAME, Data.USER_EMAIL, Data.PHOTO_URL)
                .checkMainName(Data.USER_NAME).checkMainPhoto(Data.PHOTO_URL)
                .checkProfileData(Data.USER_NAME, Data.USER_EMAIL, Data.PHOTO_URL);
    }


    @AfterMethod
    public void quit() {
        quitBrowser();
    }
}