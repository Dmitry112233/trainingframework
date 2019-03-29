package steps;

import io.qameta.allure.Step;
import pages.BillingPage;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.core.Is.is;


public class BillingPageSteps {

    private BillingPage billingPage;

    public BillingPageSteps(BillingPage billingPage) {
        this.billingPage = billingPage;
    }

    @Step("Click [Add new] button")
    public AddNewCardPageSteps clickAddNewCardButton(){
        return new AddNewCardPageSteps(billingPage.clickAddNewCardButton());
    }

    @Step("Get number of cards")
    public int getCardsNumber(){
        return billingPage.getNumberOfCards();
    }

    @Step("Click [Remove] link for last card")
    public BillingPageSteps clickRemoveLastCard(){
        billingPage.removeLastCard();
        return this;
    }

    @Step("Click [Make default] link for last card")
    public BillingPageSteps clickMakeDeafaultLastCard(){
        billingPage.clickMakeDefaultLastCard();
        return this;
    }

    @Step("Check number of cards")
    public void checkNumberOfCards(int number){
        int numberOfCards = billingPage.getNumberOfCards();
        assertThat("Number of cards isn't equal", numberOfCards, is(equalTo(number)));
    }

    @Step("Check last card mark")
    public void checkLastCardMark(String mark){
        String lastCardMark = billingPage.getLastCardMark();
        assertThat("Last card isn't default", lastCardMark, is(equalTo(mark)));
    }

    @Step("Check last card number and type")
    public void checkLastCardNumberAndType(String number, String type){
        String lastCardNumberAndType = billingPage.getLastCardNumberAndType();
        assertThat("Last card number isn't equal", lastCardNumberAndType, containsString(number.substring(number.length()-4)));
        assertThat("Last card type isn't equal", lastCardNumberAndType, containsStringIgnoringCase(type));
    }
}
