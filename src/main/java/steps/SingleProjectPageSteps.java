package steps;

import io.qameta.allure.Step;
import pages.SingleProjectPage;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;


public class SingleProjectPageSteps {

    private SingleProjectPage singleProjectPage;

    public SingleProjectPageSteps(SingleProjectPage singleProjectPage) {
        this.singleProjectPage = singleProjectPage;
    }

    @Step("Click Edit project")
    public CreateProjectPageSteps clickEditLink() {
        return new CreateProjectPageSteps(singleProjectPage.clickEditLink());
    }

    @Step("Click add new component")
    public CreateComponentPageSteps clickAddComponent() {
        return new CreateComponentPageSteps(singleProjectPage.clickAddComponent());
    }

    @Step("Check last component name")
    public SingleProjectPageSteps checkLastComponentName(String expName) {
        String componentName = singleProjectPage.getLastComponentName();
        assertThat(componentName, is(containsString(expName)));
        return this;
    }

    @Step
    public CreateComponentPageSteps openLastComponent() {
        return new CreateComponentPageSteps(singleProjectPage.openLastComponent());
    }

}
