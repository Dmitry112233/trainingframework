import data.Data;
import driver.DriverManager;
import driver.DriverManagerFactory;
import driver.DriverType;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import pages.DemoChatPage;
import steps.DemoChatStep;

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
        driver.get(Data.getDemoChatUrl());
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
    public void checkDemoVersionWindowAppeared() {
        demoChatStep.sendTenMessage().checkDemoVersionWindowIsDisplayed();
    }

    @Test
    public void fillInProfileTest(){
        demoChatStep.fillInProfile(Data.getUserName(), Data.getUserEmail(), Data.getPhotoUrl())
                .checkUserName(Data.getUserName()).checkUserPhoto(Data.getPhotoUrl())
        .checkProfileData(Data.getUserName(), Data.getUserEmail(), Data.getPhotoUrl());
    }

    @Test
    public void editProfileTest(){
        demoChatStep.fillInProfile(Data.getUserName(), Data.getUserEmail(), Data.getPhotoUrl())
                .fillInProfile(Data.getEditingUserName(), Data.getEditingUserEmail(), Data.getEditingPhotoUrl())
                .checkProfileData(Data.getEditingUserName(), Data.getEditingUserEmail(), Data.getEditingPhotoUrl());

    }
    @AfterMethod
    public void quite() {
       driverManager.quitWebDriver();
    }
}