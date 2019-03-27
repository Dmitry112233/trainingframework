package steps;

import io.qameta.allure.Step;
import pages.CreateComponentPage;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;

public class CreateComponentPageSteps {

    private CreateComponentPage createComponentPage;

    public CreateComponentPageSteps(CreateComponentPage createComponentPage) {
        this.createComponentPage = createComponentPage;
    }

    @Step("Fill in profile data")
    public CreateComponentPageSteps fillInComponentData(String component, String name) {
        createComponentPage.selectComponentType(component).typeComponentName(name)
                .clickCreateButton();
        return this;
    }

    @Step("Click [Update] button")
    public SingleProjectPageSteps clickUpdateButton(){
        return new SingleProjectPageSteps(createComponentPage.clickUpdateButton());
    }

    @Step("Type component name")
    public CreateComponentPageSteps typeComponentName(String name){
        createComponentPage.typeComponentName(name);
        return this;
    }

    @Step("Check component data")
    public void checkComponentData(String type, String name){
        String componentType = createComponentPage.getComponentType();
        String componentName = createComponentPage.getComponentName();
        assertThat(componentType, is(equalTo(type)));
        assertThat(componentName, is(equalTo(name)));
    }

}
