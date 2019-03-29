import baseTest.BaseTest;
import baseTest.CustomListener;
import data.Data;
import org.testng.annotations.*;
import pages.BillingPage;
import pages.LoginPage;
import steps.BillingPageSteps;
import steps.LoginPageSteps;
import utils.PropertyReader;

@Listeners(CustomListener.class)
public class PaymentTests extends BaseTest {

    private LoginPageSteps loginPageSteps;
    private BillingPageSteps billingPageSteps;

    @BeforeMethod
    public void init() {
        configureBrowser();
        driver.get(PropertyReader.getInstance().get("url.path.login.dev"));
        loginPageSteps = new LoginPageSteps(new LoginPage(driver));
        billingPageSteps = new BillingPageSteps(new BillingPage(driver));
    }

    @Test(dataProvider = "cards")
    public void addCardTest(String cardNumber, String cardType) {
        loginPageSteps.logIn(Data.LOGIN_EMAIL, Data.LOGIN_PASSWORD)
                .clickBillingItem()
                .clickAddNewCardButton()
                .fillInCardData(cardNumber, Data.CARD_MONTH, Data.CARD_YEAR, Data.CARD_NAME)
                .clickAddButton()
                .checkLastCardNumberAndType(cardNumber, cardType);
    }

    @Test
    public void setCardDefaultTest(){
        loginPageSteps.logIn(Data.LOGIN_EMAIL, Data.LOGIN_PASSWORD)
                .clickBillingItem()
                .clickAddNewCardButton()
                .fillInCardData(Data.AMERICAN_EXPRESS_NUMBER, Data.CARD_MONTH, Data.CARD_YEAR, Data.CARD_NAME)
                .clickAddButton()
                .clickMakeDeafaultLastCard()
                .checkLastCardMark(Data.DEFAULT_MARK);
    }

    @Test
    public void removeCardTest(){
        int numberOfCards = loginPageSteps.logIn(Data.LOGIN_EMAIL, Data.LOGIN_PASSWORD)
                .clickBillingItem().getCardsNumber();
        billingPageSteps.clickAddNewCardButton()
                .fillInCardData(Data.AMERICAN_EXPRESS_NUMBER, Data.CARD_MONTH, Data.CARD_YEAR, Data.CARD_NAME)
                .clickAddButton()
                .clickRemoveLastCard()
                .checkNumberOfCards(numberOfCards);
    }

    @DataProvider(name = "cards")
    public Object[][] getCards() {
        return new Object[][]{
                {Data.VISA_NUMBER, Data.VISA_TYPE},
                {Data.MASTERCARD_NUMBER, Data.MASTERCARD_TYPE},
                {Data.AMERICAN_EXPRESS_NUMBER, Data.AMERICAN_EXPRESS_TYPE}};
    }


    @AfterMethod
    public void quit() {
       quitBrowser();
    }
}
