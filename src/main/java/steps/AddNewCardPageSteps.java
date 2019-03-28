package steps;

import io.qameta.allure.Step;
import pages.AddNewCardPage;

public class AddNewCardPageSteps {

    private AddNewCardPage addNewCardPage;

    public AddNewCardPageSteps(AddNewCardPage addNewCardPage) {
        this.addNewCardPage = addNewCardPage;
    }

    @Step("Fill in card data")
    public AddNewCardPageSteps fillInCardData(String number, String month, String year, String name){
        addNewCardPage.typeCardNumber(number).typeCardMonth(month).typeCardYear(year).typeCardName(name);
        return this;
    }

    @Step("Click [Add] button")
    public BillingPageSteps clickAddButton(){
        return new BillingPageSteps(addNewCardPage.clickAddButton());
    }

}
