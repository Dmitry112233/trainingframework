package pages;

import elements.Input;
import lombok.extern.log4j.Log4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

@Log4j
public class CreateComponentPage extends Page{

    private static final By COMPONENT_LIST = By.xpath("//span[contains(text(),'Video Chat')]");
    private static final String COMPONENT_OPTION = "//li[contains(text(),'%s')]";
    private static final By COMPONENT_NAME = By.xpath("//input[@placeholder='New component']");
    private static final By CREATE_BUTTON = By.xpath("//button[@class='btn']");
    private static final By INPUT_TYPE = By.xpath("//input[@name='type']");
    private static final By BODY_FOR_UPDATE = By.xpath("//body[@class='integri-video app projects components']");

    public CreateComponentPage(WebDriver driver) {
        super(driver);
    }

    public CreateComponentPage selectComponentType(String component){
        log.info("Select component type is: " + component);
        driver.findElement(COMPONENT_LIST).click();
        String xpath = String.format(COMPONENT_OPTION, component);
        driver.findElement(By.xpath(xpath)).click();
        return this;
    }

    public CreateComponentPage typeComponentName(String name){
        log.info("Type component name is: " + name);
        new Input(COMPONENT_NAME, driver).sendKeys(name);
        return this;
    }

    public CreateComponentPage clickCreateButton(){
        driver.findElement(CREATE_BUTTON).click();
        return this;
    }

    public SingleProjectPage clickUpdateButton(){
        wait.until(ExpectedConditions.presenceOfElementLocated(BODY_FOR_UPDATE));
        driver.findElement(CREATE_BUTTON).click();
        return new SingleProjectPage(driver);
    }

    public String getComponentType(){
        String componentType = driver.findElement(INPUT_TYPE).getAttribute("value");
        log.info("Get component type: " + componentType);
        return componentType;
    }

    public String getComponentName(){
        String componentName = driver.findElement(COMPONENT_NAME).getAttribute("value");
        log.info("Get component name: " + componentName);
        return componentName;
    }

}
