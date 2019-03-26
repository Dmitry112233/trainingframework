package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SingleProjectPage extends Page{

    private static final By EDITT_LINK = By.xpath("//a[contains(text(),'Edit')]");

    public SingleProjectPage(WebDriver driver) {
        super(driver);
    }

    public CreateProjectPage clickEditLink(){
        driver.findElement(EDITT_LINK).click();
        return new CreateProjectPage(driver);
    }

}
