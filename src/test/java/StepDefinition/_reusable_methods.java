package StepDefinition;



import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.io.FileInputStream;
import java.util.Properties;

public class _reusable_methods {

    static WebDriverWait wait;
    public static WebDriver driver;
    static FileInputStream fis;
    static Properties p;


    public static void _general() throws Exception {

        wait = new WebDriverWait(driver, 15);
        fis = new FileInputStream(System.getProperty("user.dir")+"/src/test/resources/Properties_file/locator.properties");
        p = new Properties();
        p.load(fis);
    }

    public WebDriver getDriver() {
        return this.driver;
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
