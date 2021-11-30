package Trail;

import Base.BaseUtil;
import io.appium.java_client.AppiumDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.lang.StringUtils;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class App_Launch extends BaseUtil {

    public static void Launch_App() throws Exception {

        WebDriverManager.chromedriver().setup();
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--disable-notifications");
        driver = new ChromeDriver(chromeOptions);
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, 10);
        //Navigating to the Home Page of student portal
        driver.get("https://students.brightchamps.com/");
        //waiting for home page to load
        WaitForTitleToBe(valueForTheGivenKey("Student_Title_Page"));
        //Asserting the Student Home Page
        Assert.assertEquals(valueForTheGivenKey("Student_Title_Page"), driver.getTitle());
        _click(valueForTheGivenKey("Login_with_Password_CTA"));
        _wait(valueForTheGivenKey("Sign_in_CTA"));
        Assert.assertTrue(_is_displayed(valueForTheGivenKey("Sign_in_CTA")));
        driver.findElement(By.xpath(valueForTheGivenKey("Email_field"))).sendKeys("apurva.kushwaha@brightchamps.com");
        driver.findElement(By.xpath(valueForTheGivenKey("Password_field"))).sendKeys("qwerty");
        _click(valueForTheGivenKey("Sign_in_CTA"));
        //waiting for home page to load
        _wait(valueForTheGivenKey("Referral_modal"));
        //Asserting the Student Home Page
        Assert.assertTrue(_is_displayed(valueForTheGivenKey("Referral_modal")));
        _click(valueForTheGivenKey("Referral_modal"));
        //waiting for home page to load
        _wait(valueForTheGivenKey("Profile_button"));
        //Asserting the Student Home Page
        Assert.assertTrue(_is_displayed(valueForTheGivenKey("Profile_button")));
        //clicking on profile button
        _click(valueForTheGivenKey("Profile_button"));
        //waiting for Profile page to load
        _wait(valueForTheGivenKey("Edit_Profile"));

        Thread.sleep(2000);
        _click(valueForTheGivenKey("Edit_Profile"));
        Thread.sleep(2000);




    }

    public static void On_boardingScreen() {

        driver.findElement(By.xpath("//android.widget.TextView[@index = '0' and @text = 'Pay Your Rent']")).click();
        driver.findElement(By.xpath("//android.widget.TextView[@text = 'Pay Rent' and @index = '0']/following::android.view.ViewGroup[@index='6']")).click();
        driver.findElement(By.xpath("//div/iy-/"));
    }

    public static  String _converted_date(String date)

    {

        String New_date_format = null;

        // Creating an empty HashMap
        HashMap<String, String> hash_map = new HashMap();

        // Mapping String values to String keys
        hash_map.put("Jan", "01");
        hash_map.put("Feb", "02");
        hash_map.put("Mar", "03");
        hash_map.put("Apr", "04");
        hash_map.put("May", "05");
        hash_map.put("Jun", "06");
        hash_map.put("Jul", "07");
        hash_map.put("Aug", "08");
        hash_map.put("Sep", "09");
        hash_map.put("Oct", "10");
        hash_map.put("Nov", "11");
        hash_map.put("Dec", "12");

        if(date.contains(","))
        {
            //Seperating month from original date
            String initial_date = StringUtils.substringBefore(date, " ");

            //Converting month into numberic using HashMap
            String converted_month = hash_map.get(initial_date);


            //Seperating Year from original date
            String Year = StringUtils.substringAfter(date, ", ");

            //Seperating DATE from original date
            String DATE = StringUtils.substringBefore(StringUtils.substringAfter(date, " "), ",");

            if (DATE.length() == 1) {
                DATE = "0" + DATE;
            }

            New_date_format = DATE + "-" + converted_month + "-" + Year;


        }

        else {

            //Separating month from original date
            String initial_date = StringUtils.substringBefore(StringUtils.substringAfter(date, " "), " ");


            //Converting month into numeric using HashMap
            String converted_month =  hash_map.get(initial_date);

            //Separating Year from original date
            String Year = StringUtils.substringAfter(StringUtils.substringAfter(date, " "), " ");

            //Separating DATE from original date
            String DATE = StringUtils.substringBefore(date, " ");

            if(DATE.length()==1)
            {
                DATE = "0"+DATE;
            }

            New_date_format = DATE+"-"+converted_month+"-"+Year;


        }


        return New_date_format;
    }



    public static String _get_text(String xpath) {

       return driver.findElement(By.xpath(xpath)).getText();

    }

    public static boolean _is_dispalyed(String xpath) {

        return driver.findElement(By.xpath(xpath)).isDisplayed();

    }

    public static int _currenttimestamp(){

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());

        String minute = StringUtils.substringBefore(StringUtils.substringAfter(timestamp.toString(), ":"), ":");

        System.out.println(Integer.parseInt(minute));
        return Integer.parseInt(minute);

    }

    public static void main (String []args) throws Exception {

        Launch_App();

    }

    }


