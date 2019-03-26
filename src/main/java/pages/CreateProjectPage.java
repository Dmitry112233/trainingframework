package pages;

import elements.Input;
import lombok.extern.log4j.Log4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

@Log4j
public class CreateProjectPage extends Page{

    private static final By NAME_FIELD = By.xpath("//input[@placeholder='New project']");
    private static final By DESCRIPTION_FIELD = By.xpath("//textarea[@placeholder='Type here...']");
    private static final By DOMAINS_FIELDS = By.xpath("//input[@placeholder='example.com']");
    private static final By CREATE_BUTTON = By.xpath("//button[normalize-space(@text = 'Create')]");

    public CreateProjectPage(WebDriver driver) {
        super(driver);
    }

    public CreateProjectPage typeProjectName(String name){
        log.info("Type project name: " + name);
        new Input(NAME_FIELD, driver).sendKeys(name);
        return this;
    }

    public CreateProjectPage typeProjectDescription(String description){
        log.info("Type project description: " + description);
        new Input(DESCRIPTION_FIELD, driver).sendKeys(description);
        return this;
    }

    private List<WebElement> getDomainFields(){
        return driver.findElements(DOMAINS_FIELDS);
    }

    public CreateProjectPage typeDomain(String domain){
        log.info("Type domain: " + domain);
        new Input(getDomainFields().get(0)).sendKeys(domain);
        return  this;
    }

    public String getProjectName(){
        String projectName = driver.findElement(NAME_FIELD).getText();
        log.info("Get project name: " + projectName);
        return  projectName;
    }

    public String getProjectDescription(){
        String projectDescription = driver.findElement(DESCRIPTION_FIELD).getText();
        log.info("Get project description: " + projectDescription);
        return  projectDescription;
    }

    public String getFirstProjectDomain(){
        String projectDomain = getDomainFields().get(0).getText();
        log.info("Get first project domain: " + projectDomain);
        return  projectDomain;
    }

    public int getDomainFieldsNumber(){
        return getDomainFields().size();
    }

    public ProjectsPage clickCreateButton(){
        driver.findElement(CREATE_BUTTON).click();
        return new ProjectsPage(driver);
    }
}
