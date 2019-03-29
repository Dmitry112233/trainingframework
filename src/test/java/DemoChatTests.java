import baseTest.BaseTest;
import baseTest.CustomListener;
import data.Data;
import org.testng.annotations.*;
import pages.DemoChatPage;
import steps.DemoChatSteps;
import utils.PropertyReader;


@Listeners(CustomListener.class)
public class DemoChatTests extends BaseTest {

    private DemoChatSteps demoChatSteps;

    @BeforeMethod
    public void init() {
        configureBrowser();
        driver.get(PropertyReader.getInstance().get("url.path.chat.dev"));
        demoChatSteps = new DemoChatSteps(new DemoChatPage(driver));
    }

    @Test
    public void editProfileTest() {
        demoChatSteps.fillInProfile(Data.USER_NAME, Data.USER_EMAIL, Data.PHOTO_URL)
                .fillInProfile(Data.EDITING_USER_NAME, Data.EDITING_USER_EMAIL, Data.EDITING_PHOTO_URL)
                .checkMainName(Data.EDITING_USER_NAME).checkMainPhoto(Data.EDITING_PHOTO_URL)
                .checkProfileData(Data.EDITING_USER_NAME, Data.EDITING_USER_EMAIL, Data.EDITING_PHOTO_URL);
    }

    @Test
    public void removeUserNameTest() {
        demoChatSteps.fillInUserName(Data.USER_NAME)
                .fillInUserName("")
                .checkMainName(Data.DEFAULT_NAME);
    }

    @Test(dataProvider = "Data-Provider-Function")
    public void sendFileTest(String path, String fileName) {
        demoChatSteps.clickDragAndDropButton()
                .addFile(System.getProperty("user.dir") + path)
                .clickStartButton()
                .checkLastAttachment(fileName);
    }

    @DataProvider(name = "Data-Provider-Function")
    public Object[][] createData1() {
        return new Object[][]{
                {PropertyReader.getInstance().get("file.txt.path").replace("/", "\\")
                        , PropertyReader.getInstance().getFileName("file.txt.path")},
                {PropertyReader.getInstance().get("file.png.path").replace("/", "\\")
                        , PropertyReader.getInstance().getFileName("file.png.path")},
                {PropertyReader.getInstance().get("file.exe.path").replace("/", "\\")
                        , PropertyReader.getInstance().getFileName("file.exe.path")}};
    }

    @Test
    public void sendFiveFilesTest() {
        demoChatSteps.clickDragAndDropButton().addFile(System.getProperty("user.dir") + PropertyReader.getInstance().get("file.txt.path"))
                .addFile(System.getProperty("user.dir") + PropertyReader.getInstance().get("file.txt.path").replace("/", "\\"))
                .addFile(System.getProperty("user.dir") + PropertyReader.getInstance().get("file.txt.path").replace("/", "\\"))
                .addFile(System.getProperty("user.dir") + PropertyReader.getInstance().get("file.txt.path").replace("/", "\\"))
                .addFile(System.getProperty("user.dir") + PropertyReader.getInstance().get("file.txt.path").replace("/", "\\"))
                .clickStartButton()
                .checkAttachmentSize(Data.ATTACHMENT_SIZE);
    }

    @Test
    public void inviteUsersToChatTest() {
        demoChatSteps.clickInviteButton().checkNotifyMessage(Data.NOTIFY_MESSAGE).checkBufferText();
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