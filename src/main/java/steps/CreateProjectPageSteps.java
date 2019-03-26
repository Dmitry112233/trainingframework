package steps;

import baseTest.CustomListener;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import io.qameta.allure.model.Status;
import io.qameta.allure.model.StepResult;
import pages.CreateProjectPage;

import java.util.UUID;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class CreateProjectPageSteps {

    CreateProjectPage createProjectPage;


    public CreateProjectPageSteps(CreateProjectPage createProjectPage) {
        this.createProjectPage = createProjectPage;
    }

    @Step("Fill in project data")
    public CreateProjectPageSteps fillInProjectData(String name, String description, String domain) {
        createProjectPage.typeProjectName(name)
                .typeProjectDescription(description)
                .typeDomain(domain);
        return this;
    }

    @Step("Click [Create] button")
    public ProjectsPageSteps clickCreateButton() {
        return new ProjectsPageSteps(createProjectPage.clickCreateButton());
    }

    @Step("Check project data")
    public void checkProjectData(String expName, String expDescription, String expDomain) {
        String name = createProjectPage.getProjectName();
        String decription = createProjectPage.getProjectDescription();
        String domain = createProjectPage.getFirstProjectDomain();
        assertThat("Project name isn't equal", name, is(equalTo(expName)));
        assertThat("Project decription isn't equal", decription, is(equalTo(expDescription)));
        assertThat("Project domain isn't equal", domain, is(equalTo(expDomain)));
    }

    @Step("Check domain fields size")
    public CreateProjectPageSteps checkDomainFieldsSize(int expNumber) {
        try {
            int domainFieldsNumber = createProjectPage.getDomainFieldsNumber();
            assertThat("Number of fields isn't equal", domainFieldsNumber, is(equalTo(expNumber)));
        } catch (AssertionError e) {
            CustomListener lst = new CustomListener();
            lst.addAttchment(createProjectPage.getDriver());
            log("failed", Status.FAILED);
        }
        return this;
    }

    public void log(String message, Status status) {
        String uuid = UUID.randomUUID().toString();
        StepResult result = new StepResult().withName(message).withStatus(status);
        Allure.getLifecycle().startStep(uuid, result);
        Allure.getLifecycle().stopStep(uuid);
    }

}
