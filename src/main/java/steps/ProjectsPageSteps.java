package steps;

import io.qameta.allure.Step;
import pages.CreateProjectPage;
import pages.ProjectsPage;
import pages.SingleProjectPage;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class ProjectsPageSteps {

    private ProjectsPage projectsPage;

    public ProjectsPageSteps(ProjectsPage projectsPage) {
        this.projectsPage = projectsPage;
    }

    @Step("Click [Add project] button")
    public CreateProjectPageSteps clickAddProject(){
        return new CreateProjectPageSteps(projectsPage.clickAddProject());
    }

    @Step("Open last project ")
    public SingleProjectPageSteps openLastProject(){
        return new SingleProjectPageSteps(projectsPage.openLastProject());
    }

    @Step("Check projects number")
    public ProjectsPageSteps checkProjectsNumber(int expNumber){
        int projectNumber = projectsPage.getProjectsNumber();
        assertThat("Number of projects isn't equal", projectNumber, is(equalTo(expNumber + 1)));
        return this;
    }

    @Step("Get projects number")
    public int getProjectsNumber(){
        return projectsPage.getProjectsNumber();
    }

}
