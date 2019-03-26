package steps;

import io.qameta.allure.Step;
import pages.SingleProjectPage;

public class SingleProjectPageSteps {

    private SingleProjectPage singleProjectPage;

    public SingleProjectPageSteps(SingleProjectPage singleProjectPage){
        this.singleProjectPage = singleProjectPage;
    }

    @Step("Click Edit project")
    public CreateProjectPageSteps clickEditLink(){
        return new CreateProjectPageSteps(singleProjectPage.clickEditLink());
    }

}
