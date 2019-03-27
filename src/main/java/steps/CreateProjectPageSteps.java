package steps;

import io.qameta.allure.Step;
import pages.CreateProjectPage;
import utils.AllureUtil;
import utils.Screenshot;

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
        String description = createProjectPage.getProjectDescription();
        String domain = createProjectPage.getFirstProjectDomain();
        assertThat("Project name isn't equal", name, is(equalTo(expName)));
        assertThat("Project decription isn't equal", description, is(equalTo(expDescription)));
        assertThat("Project domain isn't equal", domain, is(equalTo(expDomain)));
    }

    public CreateProjectPageSteps checkDomainFieldsSize(int expNumber) {
        AllureUtil allureUtil = null;
        try {
            allureUtil = new AllureUtil();
            allureUtil.startStep("Check domain fields number");
            checkDomainFieldBody(expNumber);
            allureUtil.setPassedStatus();
        } catch (AssertionError e) {
            Screenshot screenshot = new Screenshot();
            screenshot.addAttchment(createProjectPage.getDriver());
            allureUtil.setFailedStatus();
        } finally {
            allureUtil.finishStep();
        }
        return this;
    }

    public void checkDomainFieldBody(int expNumber) {
        int domainFieldsNumber = createProjectPage.getDomainFieldsNumber();
        assertThat("Number of fields isn't equal", domainFieldsNumber, is(equalTo(expNumber)));
    }


}
