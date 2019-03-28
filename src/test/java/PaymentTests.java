import baseTest.BaseTest;
import baseTest.CustomListener;
import data.Data;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.LoginPage;
import steps.LoginPageSteps;
import utils.PropertyReader;

@Listeners(CustomListener.class)
public class PaymentTests extends BaseTest {

    private LoginPageSteps loginPageSteps;

    @BeforeMethod
    public void init() {
        configureBrowser();
        driver.get(PropertyReader.getInstance().get("url.path.login.dev"));
        loginPageSteps = new LoginPageSteps(new LoginPage(driver));
    }

    @Test
    public void addCardTest() {
        loginPageSteps.logIn(Data.LOGIN_EMAIL, Data.LOGIN_PASSWORD).clickBillingItem().clickAddNewCardButton()
                .fillInCardData(Data.VISA_NUMBER, Data.CARD_MONTH, Data.CARD_YEAR, Data.CARD_NAME)
                .clickAddButton().checkLastCardNumberAndType(Data.VISA_NUMBER, Data.VISA_TYPE);
    }

    @AfterMethod
    public void quit() {
       //quitBrowser();
    }
}
