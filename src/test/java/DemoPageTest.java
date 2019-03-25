import data.Data;
import driver.DriverManager;
import driver.DriverManagerFactory;
import driver.DriverType;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.DemoChatPage;
import steps.DemoChatSteps;
import utils.PropertyReader;

import java.util.concurrent.TimeUnit;

public class DemoPageTest {

    private DemoChatSteps demoChatSteps;
    private WebDriver driver;
    private DriverManager driverManager;


    @BeforeMethod
    public void init() {
        driverManager = DriverManagerFactory.getDriverManager(DriverType.CHROME);
        driver = driverManager.getWebDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get(PropertyReader.getInstance().get("url.path.dev"));
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
        demoChatSteps.clickDragAndDropButton().addFile(System.getProperty("user.dir") + PropertyReader.getInstance().get("file.txt.path"))
                .clickStartButton()
                .checkLastAttachment(PropertyReader.getInstance().getFileName("file.txt.path"));
    }

    @Test
    public void sendFilePngTest() {
        demoChatSteps.clickDragAndDropButton().addFile(System.getProperty("user.dir") + PropertyReader.getInstance().get("file.png.path"))
                .clickStartButton()
                .checkLastAttachment(PropertyReader.getInstance().getFileName("file.png.path"));
    }

    @Test
    public void sendFileExeTest() {
        demoChatSteps.clickDragAndDropButton().addFile(System.getProperty("user.dir") + PropertyReader.getInstance().get("file.exe.path"))
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
        driverManager.quitWebDriver();
    }
}