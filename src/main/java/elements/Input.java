package elements;

import lombok.extern.log4j.Log4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

@Log4j
public class Input extends ElementImpl{

    public Input(By by, WebDriver driver) {
        super(driver.findElement(by));
    }

    public void sendKeys(String text) {
        log.info("Type text: " + text);
        getWrappedElement().clear();
        getWrappedElement().sendKeys(text);
    }
}
