package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class ProjectsPage extends Page {

    private static final By PROJECTS_LIST = By.xpath("//div[@class='col-xl-4 col-sm-6']//a");

    public ProjectsPage(WebDriver driver) {
        super(driver);
    }

    private List<WebElement> getProjectsList(){
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(PROJECTS_LIST));
        List<WebElement> projects = driver.findElements(PROJECTS_LIST);
        return projects;
    }

    public CreateProjectPage clickAddProject(){
        List<WebElement> projects =  getProjectsList();
        projects.get(projects.size() - 1).click();
        return new CreateProjectPage(driver);
    }

    public SingleProjectPage openLastProject(){
        List<WebElement> projects =  getProjectsList();
        projects.get(projects.size() - 2).click();
        return new SingleProjectPage(driver);
    }

    public int getProjectsNumber(){
        return getProjectsList().size();
    }

}
