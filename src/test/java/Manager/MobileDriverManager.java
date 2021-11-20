package Manager;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.net.MalformedURLException;
import java.net.URL;

public final class MobileDriverManager {

    // instance of singleton class
    private static MobileDriverManager instanceOfSingletonDriverClass=null;


    private AppiumDriver driver;


    // Constructor
    private MobileDriverManager() throws MalformedURLException {

        DesiredCapabilities cap = new DesiredCapabilities();

        cap.setCapability("deviceName","Samsung A52");
        cap.setCapability("udid","192.168.1.107:5555"); //752edd7e - Real me device //emulator-5554
        cap.setCapability("platformName","Android");
        cap.setCapability("platformVersion","11");
        cap.setCapability("appPackage","com.locon.housing");
        cap.setCapability("appActivity","com.locon.housing.MainActivity");
        cap.setCapability("autoGrantPermissions", true);
        cap.setCapability("automationName","uiautomator2");
        URL url = new URL("http://127.0.0.1:4723/wd/hub");
        driver = new AppiumDriver(url,cap );
    }


    // TO create instance of class
    public static MobileDriverManager getInstanceOfSingletonDriverClass() throws MalformedURLException {
        if(instanceOfSingletonDriverClass==null){
            instanceOfSingletonDriverClass = new MobileDriverManager();
        }
        return instanceOfSingletonDriverClass;
    }

    // To get driver
    public AppiumDriver getDriver()
    {
        return driver;
    }

}
