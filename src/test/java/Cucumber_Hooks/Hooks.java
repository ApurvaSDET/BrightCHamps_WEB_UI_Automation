package Cucumber_Hooks;


import StepDefinition._reusable_methods;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;

public class Hooks extends _reusable_methods {

    //Defining Hooks
    //Launching AppiumDriver and app
    @Before
    public void App_launch() {

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

    @After
    public void teardown(){

        driver.quit();
    }

}
