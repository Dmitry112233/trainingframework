import baseTest.BaseTest;
import baseTest.CustomListener;
import data.Data;
import org.testng.annotations.*;
import pages.DemoChatPage;
import steps.DemoChatSteps;
import utils.PropertyReader;


@Listeners(CustomListener.class)
public class DemoPageTest extends BaseTest {

    private DemoChatSteps demoChatSteps;

    @BeforeMethod
    public void init() {
        configureBrowser();
        driver.get(PropertyReader.getInstance().get("url.path.dev"));
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
        demoChatSteps.fillInUserName(Data.USER_NAME).fillInUserName("").checkMainName(Data.DEFAULT_NAME);
    }

    @Test
    public void sendFileTxtTest() {
        demoChatSteps.clickDragAndDropButton().addFile(System.getProperty("user.dir") + PropertyReader.getInstance().get("file.txt.path").replace("/" , "\\"))
                .clickStartButton()
                .checkLastAttachment(PropertyReader.getInstance().getFileName("file.txt.path"));
    }

    @Test
    public void sendFilePngTest() {
        demoChatSteps.clickDragAndDropButton().addFile(System.getProperty("user.dir") + PropertyReader.getInstance().get("file.png.path").replace("/" , "\\"))
                .clickStartButton()
                .checkLastAttachment(PropertyReader.getInstance().getFileName("file.png.path"));
    }

    @Test
    public void sendFileExeTest() {
        demoChatSteps.clickDragAndDropButton().addFile(System.getProperty("user.dir") + PropertyReader.getInstance().get("file.exe.path").replace("/" , "\\"))
                .clickStartButton()
                .checkLastAttachment(PropertyReader.getInstance().getFileName("file.exe.path"));
    }

    @Test
    public void sendFiveFilesTest() {
        demoChatSteps.clickDragAndDropButton().addFile(System.getProperty("user.dir") + PropertyReader.getInstance().get("file.txt.path"))
                .addFile(System.getProperty("user.dir") + PropertyReader.getInstance().get("file.txt.path"))
                .addFile(System.getProperty("user.dir") + PropertyReader.getInstance().get("file.txt.path"))
                .addFile(System.getProperty("user.dir") + PropertyReader.getInstance().get("file.txt.path"))
                .addFile(System.getProperty("user.dir") + PropertyReader.getInstance().get("file.txt.path"))
                .clickStartButton()
                .checkAttachmentSize(Data.ATTACHMENT_SIZE);
    }

    @Test
    public void inviteUsersToChatTest() {
        demoChatSteps.clickInviteButton().checkNotifyMessage(Data.NOTIFY_MESSAGE).checkBufferText();
    }

    @AfterMethod
    public void quit() {
        quitBrowser();
    }
}