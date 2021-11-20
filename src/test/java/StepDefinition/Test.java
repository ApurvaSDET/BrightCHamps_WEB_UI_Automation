package StepDefinition;

import io.cucumber.java.en.Given;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class Test extends _reusable_methods {

    @Given("User is at Chrome Browser")
    public void user_is_at_chrome_browser() {
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

        System.out.println(driver.getTitle());
    }
}
