package StepDefinition;

import io.appium.java_client.AppiumDriver;
import org.apache.commons.lang.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
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

public class App_Launch {

    static WebDriver driver;
    static WebDriverWait wait;
    static FileInputStream fis;
    static Properties p;
    public static void Launch_App() throws Exception {

        //Setting system properties of ChromeDriver
        System.setProperty("webdriver.chrome.driver", "/Users/apurvakushwaha/IdeaProjects/BrightChamps/src/test/resources/Driver/chromedriver");

        //Creating an object of ChromeDriver
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        //Deleting all the cookies
        driver.manage().deleteAllCookies();

        //Specifying pageLoadTimeout and Implicit wait
        driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        //launching the specified URL
        driver.get("https://www.google.com/");

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

    public static int _Coins_count(String coinscount){

        int count = Integer.parseInt(StringUtils.substringBefore(coinscount," "));
        return count;
    }

    public static String _key(String keys) throws IOException {

        fis = new FileInputStream(System.getProperty("user.dir")+"/src/test/resources/locator.properties");
        p = new Properties();
        p.load(fis);

        return p.getProperty(keys);

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


