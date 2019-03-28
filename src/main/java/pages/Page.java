package pages;

import lombok.extern.log4j.Log4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

@Log4j
public class Page {

    private static final By BILLING_ITEM = By.xpath("//a[contains(text(),'Billing')]");

    protected WebDriver driver;

    public WebDriverWait wait;

    protected Page(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 10);
    }

    public WebDriver getDriver() {
        return driver;
    }

    public BillingPage clickBilingItem(){
        wait.until(ExpectedConditions.presenceOfElementLocated(BILLING_ITEM));
        driver.findElement(BILLING_ITEM).click();
        return new BillingPage(driver);
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    public String getUrlFromBuffer() {
        String bufferUrl = "";
        try {
            bufferUrl = (String) Toolkit.getDefaultToolkit()
                    .getSystemClipboard().getData(DataFlavor.stringFlavor);
        } catch (UnsupportedFlavorException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bufferUrl;
    }
}