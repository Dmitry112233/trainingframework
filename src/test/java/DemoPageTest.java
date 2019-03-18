import data.Data;
import driver.DriverManager;
import driver.DriverManagerFactory;
import driver.DriverType;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.DemoChatPage;
import steps.DemoChatStep;
import utils.PropertyReader;

import java.util.concurrent.TimeUnit;

public class DemoPageTest {

    private DemoChatStep demoChatStep;
    private WebDriver driver;
    private DriverManager driverManager;

    @BeforeMethod
    public void init() {
        driverManager = DriverManagerFactory.getDriverManager(DriverType.CHROME);
        driver = driverManager.getWebDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get(PropertyReader.getInstance().get("stage"));
        demoChatStep = new DemoChatStep(new DemoChatPage(driver));
    }

    @Test
    public void sendMessageTest() {
        demoChatStep.sendMessage().checkLastMessage(Data.getMessage());
    }

    @Test
    public void editMessageTest() {
        demoChatStep.sendMessage().editLastOwnMessage().checkLastMessage(Data.getEditingMessage());
    }

    @Test
    public void removeMessageTest() {
        demoChatStep.sendMessage().removeLastOwnMessage().checkLastMessage(Data.getRemovedMessage());
    }

    @Test
    public void checkDemoVersionWindowTest() {
        demoChatStep.sendTenMessage().checkDemoVersionWindowIsDisplayed();
    }

    @Test
    public void fillInProfileTest() {
        demoChatStep.fillInProfile(Data.getUserName(), Data.getUserEmail(), Data.getPhotoUrl())
                .checkMainName(Data.getUserName()).checkMainPhoto(Data.getPhotoUrl())
                .checkProfileData(Data.getUserName(), Data.getUserEmail(), Data.getPhotoUrl());
    }

    @Test
    public void editProfileTest() {
        demoChatStep.fillInProfile(Data.getUserName(), Data.getUserEmail(), Data.getPhotoUrl())
                .fillInProfile(Data.getEditingUserName(), Data.getEditingUserEmail(), Data.getEditingPhotoUrl())
                .checkMainName(Data.getEditingUserName()).checkMainPhoto(Data.getEditingPhotoUrl())
                .checkProfileData(Data.getEditingUserName(), Data.getEditingUserEmail(), Data.getEditingPhotoUrl());
    }

    @Test
    public void removeUserNameTest() {
        demoChatStep.fillInUserName(Data.getUserName()).fillInUserName("").checkMainName(Data.getDefaultName());
    }

    @Test
    public void sendFileTxtTest() {
        demoChatStep.clickDragAndDropButton().addFile(System.getProperty("user.dir") + PropertyReader.getInstance().get("file_txt"))
                .clickStartButton()
                .checkLastAttachment(PropertyReader.getInstance().getFileName("file_txt"));
    }

    @Test
    public void sendFilePngTest() {
        demoChatStep.clickDragAndDropButton().addFile(System.getProperty("user.dir") + PropertyReader.getInstance().get("file_png"))
                .clickStartButton()
                .checkLastAttachment(PropertyReader.getInstance().getFileName("file_png"));
    }

    @Test
    public void sendFileExeTest() {
        demoChatStep.clickDragAndDropButton().addFile(System.getProperty("user.dir") + PropertyReader.getInstance().get("file_exe"))
                .clickStartButton()
                .checkLastAttachment(PropertyReader.getInstance().getFileName("file_exe"));
    }

    @Test
    public void sendFilesTest() {
        demoChatStep.clickDragAndDropButton().addFile(System.getProperty("user.dir") + PropertyReader.getInstance().get("file_txt"))
                .addFile(System.getProperty("user.dir") + PropertyReader.getInstance().get("file_txt"))
                .addFile(System.getProperty("user.dir") + PropertyReader.getInstance().get("file_txt"))
                .addFile(System.getProperty("user.dir") + PropertyReader.getInstance().get("file_txt"))
                .addFile(System.getProperty("user.dir") + PropertyReader.getInstance().get("file_txt"))
                .clickStartButton()
                .checkAttachmentSize(Data.getAttachmentSize());
    }

    @AfterMethod
    public void quite() {
        driverManager.quitWebDriver();
    }
}