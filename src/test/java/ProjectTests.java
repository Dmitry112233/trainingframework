import baseTest.BaseTest;
import baseTest.CustomListener;
import data.Data;
import org.testng.annotations.*;
import pages.LoginPage;
import pages.ProjectsPage;
import steps.LoginPageSteps;
import steps.ProjectsPageSteps;
import utils.PropertyReader;

@Listeners(CustomListener.class)
public class ProjectTests extends BaseTest {

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
        projectsPageSteps.
                clickAddProject()
                .fillInProjectData(Data.PROJECT_NAME, Data.PROJECT_DESCRIPTION, Data.PROJECT_DOMAIN)
                .checkDomainFieldsSize(Data.DOMAIN_FIELDS_NUMBER).clickCreateButton()
                .checkProjectsNumber(projectNumber).openLastProject().clickEditLink()
                .checkProjectData(Data.PROJECT_NAME, Data.PROJECT_DESCRIPTION, Data.PROJECT_DOMAIN);

    }

    @Test
    public void editProjectTest() {
        loginPageSteps.logIn(Data.LOGIN_EMAIL, Data.LOGIN_PASSWORD)
                .openLastProject()
                .clickEditLink()
                .fillInProjectData(Data.EDITING_PROJECT_NAME, Data.EDITING_PROJECT_DESCRIPTION, Data.EDITING_PROJECT_DOMAIN)
                .clickCreateButton()
                .openLastProject()
                .clickEditLink()
                .checkProjectData(Data.EDITING_PROJECT_NAME, Data.EDITING_PROJECT_DESCRIPTION, Data.EDITING_PROJECT_DOMAIN);
    }

    @Test(dataProvider = "components-name")
    public void addComponentTest(String select) {
        loginPageSteps.logIn(Data.LOGIN_EMAIL, Data.LOGIN_PASSWORD)
                .openLastProject()
                .clickAddComponent()
                .fillInComponentData(select, Data.COMPONENT_NAME)
                .clickUpdateButton().checkLastComponentName(select)
                .openLastComponent()
                .checkComponentData(select, Data.COMPONENT_NAME);
    }

    @DataProvider(name = "components-name")
    public Object[][] getComponents() {
        return new Object[][]{
                {Data.SELECT_VIDEO_CHAT},
                {Data.SELECT_MULTIPARTY_VIDEO},
                {Data.SELECT_MD_VIDEO_PLAYER},
                {Data.SELECT_SINGLE_VIDEO}};
    }

    @Test
    public void editComponent() {
        loginPageSteps.logIn(Data.LOGIN_EMAIL, Data.LOGIN_PASSWORD)
                .openLastProject()
                .clickAddComponent()
                .fillInComponentData(Data.SELECT_MULTIPARTY_VIDEO, Data.COMPONENT_NAME)
                .clickUpdateButton()
                .checkLastComponentName(Data.SELECT_MULTIPARTY_VIDEO)
                .openLastComponent()
                .typeComponentName(Data.EDITING_COMPONENT_NAME)
                .clickUpdateButton()
                .openLastComponent()
                .checkComponentData(Data.SELECT_MULTIPARTY_VIDEO, Data.EDITING_COMPONENT_NAME);
    }

    @AfterMethod
    public void quit() {
        quitBrowser();
    }

}
