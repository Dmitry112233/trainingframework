package driver;

import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public abstract class DriverManager {

    protected WebDriver driver;
    protected static Properties property;
    protected static File file = new File("./src/main/resources/setting.properties");
    protected static FileInputStream fis;

    static {
        try {
            if (property == null) {
                property = new Properties();
                fis = new FileInputStream(file);
                property.load(fis);
            }
        } catch (IOException e) {
            System.err.println("Файл setting не найден");
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    protected abstract void createWebDriver();

    public void quitWebDriver() {
        if (null != driver) {
            driver.quit();
            driver = null;
        }
    }

    public WebDriver getWebDriver() {
        if (null == driver) {
            createWebDriver();
        }
        return driver;
    }
}
