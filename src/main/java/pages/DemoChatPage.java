package pages;

import driver.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DemoChatPage {

    private WebDriver driver;
    DriverManager driverManager;

    public DemoChatPage(WebDriver driver) {
        this.driver = driver;
    }

    By messageField = By.xpath("//div[@class='integri-chat-input']/textarea");
    By sendButton = By.xpath("//div[@class='integri-chat-input']/div/button[@title='Send message']");
    By videoButton = By.xpath("//div[@class='integri-chat-input']/div/button[@title='Video conference']");

}
