package Base;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;


public class BaseUtil {

    public static WebDriver driver;
    public static Properties Pro;
    static WebDriverWait wait;

    public String valueForTheGivenKey(String name) {

        File file = new File(System.getProperty("user.dir")+"/src/test/resources/Properties_file/locator.properties");
        FileInputStream fis;
        try {
            fis = new FileInputStream(file);
            Pro = new Properties();
            Pro.load(fis);
        } catch (Exception e) {
            System.out.println("Error Message is --- " + e.getMessage());
        }

        return Pro.getProperty(name);
    }

    public void waitForGivenSec(int i) {
        driver.manage().timeouts().implicitlyWait(i, TimeUnit.SECONDS);
    }


    public void waitForElementToBeVisible(String locatorType, String element) {

        switch (locatorType) {
            case "Xpath":
                wait = new WebDriverWait(driver, 30);
                wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(element)));
                break;
            case "Id":
                wait = new WebDriverWait(driver, 30);
                wait.until(ExpectedConditions.presenceOfElementLocated(By.id(element)));
                break;
        }
    }

    public void waitForElementToBeClickable(String locatorType, String element) {
        switch (locatorType) {
            case "Xpath":
                wait = new WebDriverWait(driver, 30);
                wait.
                        until(ExpectedConditions.elementToBeClickable(By.xpath(element)));
                break;
            case "Id":
                wait = new WebDriverWait(driver, 30);
                wait.until(ExpectedConditions.elementToBeClickable(By.id(element)));
                break;
        }
    }

    public static boolean isClickable(WebElement element) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, 5);
            wait.until(ExpectedConditions.elementToBeClickable(element));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}