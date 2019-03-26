import baseTest.BaseTest;
import baseTest.CustomListener;
import data.Data;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.ProjectsPage;
import steps.LoginPageSteps;
import steps.ProjectsPageSteps;
import utils.PropertyReader;

@Listeners(CustomListener.class)
public class CreateProjectTest extends BaseTest {

    private LoginPageSteps loginPageSteps;
    private ProjectsPageSteps projectsPageSteps;

    @BeforeMethod
    public void init() {
        configureBrowser();
        driver.get(PropertyReader.getInstance().get("url.path.login.dev"));
        loginPageSteps = new LoginPageSteps(new LoginPage(driver));
        projectsPageSteps = new ProjectsPageSteps(new ProjectsPage(driver));
    }

    @Test
    public void createProjectTest() {
        int projectNumber = loginPageSteps.logIn(Data.LOGIN_EMAIL, Data.LOGIN_PASSWORD).getProjectsNumber();
        projectsPageSteps.clickAddProject()
                .fillInProjectData(Data.PROJECT_NAME, Data.PRPJECT_DESCRIPTION, Data.PROJECT_DOMAIN)
                .checkDomainFieldsSize(Data.DOMAIN_FIELDS_NUMBER).clickCreateButton()
                .checkProjectsNumber(projectNumber).openLastProject().clickEditLink()
                .checkProjectData(Data.PROJECT_NAME, Data.PRPJECT_DESCRIPTION, Data.PROJECT_DOMAIN);

    }

    @AfterMethod
    public void quit() {
        //quitBrowser();
    }

}
