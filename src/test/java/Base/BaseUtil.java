package Base;


import com.google.common.base.Predicate;
import org.apache.commons.lang.StringUtils;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.io.File;
import java.io.FileInputStream;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.TimeUnit;


public class BaseUtil {

    public static WebDriver driver;
    public static Properties Pro;
    public static WebDriverWait wait;
    public static String Parent_Window;
    public static String BROWSERSTACK_USERNAME;
    public static String BROWSERSTACK_ACCESS_KEY;
    public static String BROWSERSTACK_URL;

    public static String valueForTheGivenKey(String name) {

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

    public void DocumentInReadyState()

    {
        while (true)
        {
            boolean ajaxIsComplete = ((JavascriptExecutor)driver).executeScript("return document.readyState").toString().equals("complete");
            if (ajaxIsComplete){
                break;
            }

        }
    }

    public static void WaitForTitleToBe(String title){

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

        try {
            driver.findElement(By.xpath(value)).click();
        }
        catch (NoSuchElementException | StaleElementReferenceException e)
        {
            if(e.getMessage().contains("stale element reference"))
            {
                e.getMessage();
            }
            else
            {
                Assert.fail();
            }

        }

    }

    public static void _click_stale(String value){

        try {
            driver.findElement(By.xpath(value)).click();
        }
        catch (StaleElementReferenceException e)
        {
            //System.out.println(e.getMessage());
        }

    }

    public static String _get_text(String value) {


        try {
            driver.findElement(By.xpath(value)).getText();
        }
        catch (NoSuchElementException e)
        {
            Assert.fail();
        }

        return driver.findElement(By.xpath(value)).getText();
    }

    public static boolean _is_displayed(String xpath) {


       try
        {
            return driver.findElement(By.xpath(xpath)).isDisplayed();
        }
        catch (NoSuchElementException | StaleElementReferenceException err)
        {
            return err.getMessage().contains("stale element reference");
        }
    }

    public static boolean _is_displayed_link_text(String value) {


        try
        {
            return driver.findElement(By.linkText(value)).isDisplayed();
        }
        catch (NoSuchElementException | StaleElementReferenceException err)
        {
            return err.getMessage().contains("stale element reference");
        }
    }

    public static boolean _is_selected(String xpath) {

        try {
            return driver.findElement(By.xpath(xpath)).isSelected();
        }
        catch (NoSuchElementException err)
        {
            return false;
        }
    }

    public static boolean _is_enabled(String xpath) {

        try {
            return driver.findElement(By.xpath(xpath)).isEnabled();
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

    public static void _random_options_from_dropdown(String locator){

        //Fetching list of WebElements
        List<WebElement> dropdown_menu = driver.findElements(By.xpath(locator));

        //Creating ArrayList and Random instance
        ArrayList<WebElement> al = new ArrayList();
        Random rand = new Random();

        //Using enhanced for loop to get the elements
        for (WebElement ele : dropdown_menu)

        {
            al.add(ele);

        }

        //Clicking on random values from the dropdown
        al.get(rand.nextInt(dropdown_menu.size())).click();

    }

    public static void _selecting_one_by_one_from_dropdown(String locator){

        //Fetching list of WebElements
        List<WebElement> dropdown_menu = driver.findElements(By.xpath(locator));

        //Using enhanced for loop to get the elements
        for (WebElement ele : dropdown_menu)

        {
            ele.click();

        }


    }

    public static void _selecting_particular_options_from_dropdown(String locator, String value_from_dropdown){

        //Fetching list of WebElements
        List<WebElement> dropdown_menu = driver.findElements(By.xpath(locator));

        //Using enhanced for loop to get the elements
        for (WebElement ele : dropdown_menu) {

            // Here we will verify if link (item) is equal to particular value
            if (ele.getAttribute("innerHTML").contains(value_from_dropdown)) {

                // if yes then click on link (item)
                ele.click();

                // break the loop or come out of loop
                break;

            }
        }


        }

}