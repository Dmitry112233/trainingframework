package baseTest;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;
import utils.Screenshot;


public class CustomListener extends TestListenerAdapter {

    @Override
    public void onTestFailure(ITestResult result) {
        Object currentClass = result.getInstance();
        WebDriver driver = ((BaseTest) currentClass).getDriver();
        Screenshot screenshot = new Screenshot();
        screenshot.addAttchment(driver);
    }
}
