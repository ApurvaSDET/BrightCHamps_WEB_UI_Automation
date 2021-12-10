package StepDefinition;

import Base.BaseUtil;
import io.cucumber.java.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.net.MalformedURLException;
import java.net.URL;


public class Hook extends BaseUtil {


    public void InvokeRemoteChromeBrowser(Scenario scenario) throws MalformedURLException {

        //Setting up remote browser env on BrowserStack cloud
        BROWSERSTACK_USERNAME = valueForTheGivenKey("BROWSERSTACK_USERNAME");
        BROWSERSTACK_ACCESS_KEY = valueForTheGivenKey("BROWSERSTACK_ACCESS_KEY");
        BROWSERSTACK_URL = "https://" + BROWSERSTACK_USERNAME + ":" + BROWSERSTACK_ACCESS_KEY + "@hub.browserstack.com/wd/hub";


        //Defining capabilities
        DesiredCapabilities capabilities = new DesiredCapabilities();

        //Setting up capabilities
        capabilities.setCapability("os", "OS X");
        capabilities.setCapability("os_version", "Monterey");
        capabilities.setCapability("browser", "Chrome");
        WebDriverManager.chromedriver().setup();
        driver = new RemoteWebDriver(new URL(BROWSERSTACK_URL), capabilities);

        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, 20);
    }

    @Before
    public void InvokeLocalChromeBrowser(Scenario scenario) {

        //Setting up chrome driver
        WebDriverManager.chromedriver().setup();

        //Creating ChromeOptions instance
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setHeadless(true);
        chromeOptions.addArguments("--disable-notifications");

        driver = new ChromeDriver(chromeOptions);
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, 20);
    }

    @After
    public void TearDownTest(Scenario scenario) throws InterruptedException {
        if (scenario.isFailed()) {
            //Take a screenshot after waiting for a sec...
            Thread.sleep(1000);
            final byte[] screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", "screenshot");

        }

        if (driver != null)
            driver.quit();

    }


}
