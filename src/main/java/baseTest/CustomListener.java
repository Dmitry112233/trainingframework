package baseTest;

import io.qameta.allure.Attachment;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;


public class CustomListener extends TestListenerAdapter {

    @Override
    public void onTestFailure(ITestResult result) {
        Object currentClass = result.getInstance();
        WebDriver driver = ((BaseTest) currentClass).getDriver();
        addAttchment(driver);
    }

    @Attachment
    public byte[] addAttchment(WebDriver driver) {
        try {
            File screenshot = getScreenShot(driver);
            FileUtils.copyFile(screenshot, new File("./target/screenshots/" + screenshot.getName()));
            return Files.readAllBytes(Paths.get("./target/screenshots/", screenshot.getName()));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public File getScreenShot(WebDriver driver) {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
    }

}
