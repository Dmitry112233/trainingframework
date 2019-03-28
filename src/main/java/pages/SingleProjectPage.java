package pages;

import lombok.extern.log4j.Log4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

@Log4j
public class SingleProjectPage extends Page{

    private static final By EDIT_LINK = By.xpath("//a[contains(text(),'Edit')]");
    private static final By COMPONENTS_LIST = By.xpath("//div[@class='col-xl-4 col-sm-6']//a");
    private static final By ADD_NEW_COMPONENT_BUTTON = By.xpath("//div[@class='component new']/a");

    public SingleProjectPage(WebDriver driver) {
        super(driver);
    }

    public CreateProjectPage clickEditLink(){
        driver.findElement(EDIT_LINK).click();
        return new CreateProjectPage(driver);
    }

    public List<WebElement> getComponentsList() {
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(COMPONENTS_LIST));
        return driver.findElements(COMPONENTS_LIST);
    }

    public CreateComponentPage clickAddComponent(){
        wait.until(ExpectedConditions.presenceOfElementLocated(ADD_NEW_COMPONENT_BUTTON));
        driver.findElement(ADD_NEW_COMPONENT_BUTTON).click();
        return new CreateComponentPage(driver);
    }

    public CreateComponentPage openLastComponent(){
        List<WebElement> components = getComponentsList();
        components.get(components.size()-2).click();
        return new CreateComponentPage(driver);
    }

    public String getLastComponentName(){
        List<WebElement> components = getComponentsList();
        String componentName = components.get(components.size()-2).getText();
        log.info("Get last component name: " + componentName);
        return componentName;
    }



}
