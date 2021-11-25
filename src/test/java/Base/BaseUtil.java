package Base;


import org.apache.commons.lang.StringUtils;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.io.File;
import java.io.FileInputStream;
import java.sql.Timestamp;
import java.util.Properties;
import java.util.concurrent.TimeUnit;


public class BaseUtil {

    public static WebDriver driver;
    public static Properties Pro;
    public static WebDriverWait wait;

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


    public static void _wait(String value){

        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(value)));
        }
        catch (TimeoutException e)
        {
            Assert.fail();
        }

    }



    public void WaitForTitleToBe(String title){

        try {
            wait.until(ExpectedConditions.titleIs(title));
        }
        catch (TimeoutException e)
        {
            Assert.fail();
        }

    }
    public static void _WaitAbsence(String value){

        try {
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(value)));
        }
        catch (TimeoutException e)
        {
            Assert.fail();
        }


    }
    public static void _click(String value){

        driver.findElement(By.xpath(value)).click();

    }

    public static String _get_text(String value) {

        return driver.findElement(By.xpath(value)).getText();

    }

    public static boolean _is_displayed(String xpath) {

        try {
            return driver.findElement(By.xpath(xpath)).isDisplayed();
        }
        catch (NoSuchElementException err)
        {
            return false;
        }
    }

    public static int _CurrentTimeStamp(){

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());

        String minute = StringUtils.substringBefore(StringUtils.substringAfter(timestamp.toString(), ":"), ":");

        return Integer.parseInt(minute);

    }

    public static boolean isClickable(WebElement element) {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(element));
            return true;
        } catch (Exception e) {
            return false;
        }

    }

}