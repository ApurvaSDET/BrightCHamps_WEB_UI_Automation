package Base;


import org.apache.commons.lang.StringUtils;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.io.File;
import java.io.FileInputStream;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.Date;
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

        File file = new File(System.getProperty("user.dir")+"/src/test/resources/Element_Locator_Properties_file/locator.properties");
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

    public static void waitForGivenSec(int i) {
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

    public static int _get_WebElements_size(String value) {

        try {
            return driver.findElements(By.xpath(value)).size();
        } catch (NoSuchElementException e) {
            Assert.fail();
            return 0;
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

            waitForGivenSec(2);
            al.add(ele);

        }

        //Clicking on random values from the dropdown
        try {
            al.get(rand.nextInt(dropdown_menu.size())).click();
        }
        catch (ElementClickInterceptedException e)
        {
            try {
                al.get(rand.nextInt(dropdown_menu.size())).click();
            }
            catch (ElementClickInterceptedException err)
            {
                al.get(rand.nextInt(dropdown_menu.size())).click();
            }
        }

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

    public void openNewTab() {
        ((JavascriptExecutor)driver).executeScript("window.open('about:blank','_blank');");
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

        public static void Switch_to_next_tab(String DefaultWindow){


            Set<String> multiple_window = driver.getWindowHandles();

            for(String e : multiple_window)
            {
                if(!e.equalsIgnoreCase(DefaultWindow))
                {
                    driver.switchTo().window(e);
                }
            }


        }

    public static String _get_current_Month(){

        //Getting the current date value
        LocalDate CurrentDate = LocalDate.now();

        //Getting the current month
        return CurrentDate.getMonth().toString();

    }

    public static String _get_current_Day(){

        //Getting the current date value
        LocalDate CurrentDate = LocalDate.now();

        //Getting the current month
        int currentDay = CurrentDate.getDayOfMonth();

        return String.valueOf(currentDay);

    }

    public static int _get_current_time_in_sec(){

        LocalDateTime date = LocalDateTime.now();
        return date.toLocalTime().toSecondOfDay();

    }

    public static int _get_provided_time_in_sec(String demo_class_time) throws ParseException {

        SimpleDateFormat displayFormat = new SimpleDateFormat("HH:mm");
        SimpleDateFormat parseFormat = new SimpleDateFormat("hh:mm a");
        Date date = parseFormat.parse(StringUtils.substringBefore(demo_class_time, " -"));
        //System.out.println(parseFormat.format(date) + " = " + displayFormat.format(date));



        String time = displayFormat.format(date); //HH:MM
        String[] units = time.split(":"); //will break the string up into an array
        int hours = Integer.parseInt(units[0]); //first element
        int minutes = Integer.parseInt(units[1]); //second element
        int duration = 3600 * hours + 60 * minutes; //add up our values

        return duration;

    }


    public static void _search_throughout_webpage(String element_locator){

        WebElement element = driver.findElement(By.xpath(valueForTheGivenKey(element_locator)));
        Actions actions = new Actions(driver);
        actions.moveToElement(element);
        actions.perform();

        _wait(valueForTheGivenKey(element_locator));
        _click(valueForTheGivenKey(element_locator));

    }


}