package StepDefinition;

import Base.BaseUtil;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.text.ParseException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class Demo_User_Home extends BaseUtil {

    @Given("User is at Student portal master login Page")
    public void user_is_at_student_portal_master_login_page() {

        //Navigating to the Home Page of student portal
        driver.get(valueForTheGivenKey("WEB_URL")+"/login");
        //waiting for home page to load
        _wait(valueForTheGivenKey("Master_login_screen"));
        //Asserting the Student Home Page
        Assert.assertTrue(_is_displayed(valueForTheGivenKey("Master_login_screen")));

    }


    @When("User enters valid Phone no and password")
    public void user_enters_valid_phone_no_and_password(DataTable dataTable) {


        //getting values from Datatable as a Key: Value pair in feature file
        List<Map<String,String>> data = dataTable.asMaps(String.class, String.class);

        driver.findElement(By.xpath(valueForTheGivenKey("Email_field"))).sendKeys(data.get(0).get("Phone"));
        driver.findElement(By.xpath(valueForTheGivenKey("Password_field"))).sendKeys(data.get(0).get("Password"));

    }


    @And("User clicks on SUBMIT CTA")
    public void user_clicks_on_submit_cta() throws InterruptedException {

        _click(valueForTheGivenKey("Master_login_screen"));
        //Providing time to load
        Thread.sleep(5000);

    }


    //Scenario: 1 #Verifying login via email and password for Single user a/c

    @Then("User is at the Home Page of Demo account")
    public void User_is_at_the_Home_Page_of_Demo_account() {

        //waiting for home page to load
        _wait(valueForTheGivenKey("Home_button"));
        //Asserting the Student Home Page
        Assert.assertTrue(_is_displayed(valueForTheGivenKey("Home_button")));

    }


    //Scenario: 2 #Verifying Reschedule Your Class feature for demo user

    @Then("User Clicks on Reschedule class for demo user")
    public void Reschedule_class_for_demo_user() {

        WebDriverWait wait = new WebDriverWait(driver,15);

        try {


            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(valueForTheGivenKey("Reschedule_demo_CTA"))));
            _click(valueForTheGivenKey("Reschedule_demo_CTA"));

        }

        catch (TimeoutException e)

        {
            _wait(valueForTheGivenKey("triple_dots_profile"));
            _click(valueForTheGivenKey("triple_dots_profile"));

            //wait for dropdown to appear
            _wait(valueForTheGivenKey("Reschedule_demo"));
            _click(valueForTheGivenKey("Reschedule_demo"));

        }
    }

    @Then("User should be on Reschedule Trial class screen")
    public void user_should_be_on_reschedule_trial_class_screen() {

        //waiting to land on Reschedule Class screen
        _wait(valueForTheGivenKey("Enabled_Book_your_slot"));

        Assert.assertTrue(_is_displayed(valueForTheGivenKey("Enabled_Book_your_slot")));

    }

    @Then("User should be able to successfully Reschedule the demo class")
    public void successfully_Reschedule_the_demo_class() {

        _wait(valueForTheGivenKey("OTP_Sent_Alert"));
        Assert.assertEquals("Successfully rescheduled",_get_text(valueForTheGivenKey("OTP_Sent_Alert")));

    }

    @And("User should be redirected to dashboard screen of demo user")
    public void user_should_be_redirected_to_dashboard_screen_of_demo_user() {

        WebDriverWait wait = new WebDriverWait(driver,15);

        try {

            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(valueForTheGivenKey("triple_dots_profile"))));
            Assert.assertTrue(driver.findElement(By.xpath(valueForTheGivenKey("triple_dots_profile"))).isDisplayed());

        }

        catch (TimeoutException e)

        {
            _wait(valueForTheGivenKey("Reschedule_demo_CTA"));
            Assert.assertTrue(driver.findElement(By.xpath(valueForTheGivenKey("Reschedule_demo_CTA"))).isDisplayed());

        }


    }

    //Scenario: 3 #Verifying back button of Re-scheduling Trail Class screen


    @When("User Navigate to the Reschedule screen")
    public void user_navigate_to_the_reschedule_screen() {

        Reschedule_class_for_demo_user();
        user_should_be_on_reschedule_trial_class_screen();

    }

    //Scenario: 4 #Verifying disabled JOIN CTA logic

    @When("User has scheduled demo class")
    public void user_has_scheduled_demo_class() {

        WebDriverWait wait = new WebDriverWait(driver,15);

        //Creating object to reuse method from StudentTitlePage class
        StudentTitlePage obj = new StudentTitlePage();

        try {

            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(valueForTheGivenKey("triple_dots_profile"))));
            Assert.assertTrue(driver.findElement(By.xpath(valueForTheGivenKey("triple_dots_profile"))).isDisplayed());

        }

        catch (TimeoutException e)

        {

            //if this is a case of missed demo class, then rescheduling it here to unblock next step
            _wait(valueForTheGivenKey("Reschedule_demo_CTA"));
            _click(valueForTheGivenKey("Reschedule_demo_CTA"));

            //Re-using methods from another class
            user_should_be_on_reschedule_trial_class_screen();
            obj.user_selected_next_class_date_and_time();
            obj.cta_should_get_enabled();
            obj.user_clicks_on_cta();
            successfully_Reschedule_the_demo_class();

            //Asserting redirection to dashboard screen
            _wait(valueForTheGivenKey("triple_dots_profile"));
            Assert.assertTrue(driver.findElement(By.xpath(valueForTheGivenKey("triple_dots_profile"))).isDisplayed());

        }



    }

    @Then("Verify JOIN CTA is enabled or disabled")
    public void verify_join_cta_is_disabled() throws ParseException {

    //Validating if JOIN CTA is disabled

     //Getting Scheduled date and matching it with current date
             if((_get_current_Month().equalsIgnoreCase(_get_text(valueForTheGivenKey("Scheduled_Class_Month"))))
                                             &&
                  (_get_current_Day().equalsIgnoreCase(_get_text(valueForTheGivenKey("Scheduled_Class_Day"))))
                                             &&
                    (_get_current_time_in_sec()>= _get_provided_time_in_sec(_get_text(valueForTheGivenKey("Scheduled_Class_Time")))-600
                                             &&
                        _get_current_time_in_sec()<=_get_provided_time_in_sec(_get_text(valueForTheGivenKey("Scheduled_Class_Time")))+1200))

             {
                 //Logic for Testing JOIN NOW CTA

                 //Getting current window handle
                 String CurrentWindow = driver.getWindowHandle();
                 //Clicking on JOIN CTA
                 _wait(valueForTheGivenKey("Enabled_Join_CTA"));
                 _click(valueForTheGivenKey("Enabled_Join_CTA"));
                 //Switching driver focus on next window
                 Switch_to_next_tab(CurrentWindow);
                 //Asserting the Zoom tab opened
                 Assert.assertTrue(driver.getCurrentUrl().contains("joining.brightchamps.com"));

             }
             else
             {
                 //Asserting the disabled JOIN CTA
                 driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
                 Assert.assertTrue(_is_displayed(valueForTheGivenKey("Disabled_Join_CTA")));
             }

    }


    //Scenario: 5 #Verifying Glimpse of Project section on Demo Home Page


    @When("User clicks on View More link to expand till last video")
    public void user_clicks_on_view_more_link_to_expand_till_last_video() {

        //Clicking on View More link to expand all the videos in this section of Home Page

        for(int i=0; i<20; i++) {

            try {

                _wait(valueForTheGivenKey("Video_Container_Title"));
                _search_throughout_webpage("View_More_Link");

            }
            catch (NoSuchElementException e)
            {
                Assert.assertTrue(true);
            }

        }

    }

    @Then("Verify all the videos are available on the page")
    public void verify_all_the_videos_are_available_on_the_page() {

        //Getting list of all the WebElements
        List <WebElement> Videos =   driver.findElements(By.xpath(valueForTheGivenKey("List_of_Videos")));

        if (Videos.size() > 2)
        {
            Assert.assertTrue(true);
        }

        else {
            Assert.fail();
        }

    }

    @When("User navigates to the top of the Page")
    public void user_navigates_to_the_top_of_the_page() {

        WebElement element = driver.findElement(By.xpath(valueForTheGivenKey("Video_Container_Title")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);

    }

    @Then("User should be able to scroll to top of the Page")
    public void user_should_be_able_to_scroll_to_top_of_the_page() {

        _wait(valueForTheGivenKey("Video_Container_Title"));
        Assert.assertTrue(_is_displayed(valueForTheGivenKey("Video_Container_Title")));

    }

    @And("Verify user should be able to successfully play and close all the videos")
    public void verify_user_should_be_able_to_successfully_play_all_the_videos() throws InterruptedException {

        List <WebElement> Videos =   driver.findElements(By.xpath(valueForTheGivenKey("List_of_Videos")));


        for (WebElement ele : Videos)
        {

            //Waiting for JS to load before click
            DocumentInReadyState();
            ele.click();

            //Playing Video after switching into the frame
            _wait(valueForTheGivenKey("Video_Close_Icon"));
            driver.switchTo().frame(0);
            _wait(valueForTheGivenKey("Player_ID"));
            _click(valueForTheGivenKey("Player_ID"));

            //Validating if Video is correctly loaded and played
            try
            {
                Assert.assertFalse(driver.findElement(By.xpath(valueForTheGivenKey("YouTube_Link_Broken_error"))).isDisplayed());
            }
            catch (NoSuchElementException e)
            {
                Assert.assertTrue(true);
            }


            Thread.sleep(2000);

            //Closing the Video Player Modal
            driver.switchTo().defaultContent(); // switching the default window

            //Waiting and Clicking on closeIcon
            _wait(valueForTheGivenKey("Video_Close_Icon"));
            _click(valueForTheGivenKey("Video_Close_Icon"));

            //Asserting the close Icon of Video Player Modal
            _WaitAbsence(valueForTheGivenKey("Video_Close_Icon"));

            try
            {
                Assert.assertFalse(driver.findElement(By.xpath(valueForTheGivenKey("Video_Close_Icon"))).isDisplayed());

            }
            catch (NoSuchElementException e)
            {
                Assert.assertTrue(true);
            }

        }


    }

    //Scenario: 6 #Verifying ‘Try our Course’ CTA on Demo Home Page

    @When("User clicks on Try our Course CTA")
    public void User_clicks_on_Try_our_Course_CTA() {

        _wait(valueForTheGivenKey("Video_Container_Title"));
        _search_throughout_webpage("Try_Our_Course");

    }

    @Then("User should be redirected to Freemium Videos screen")
    public void User_should_be_redirected_to_Freemium_Videos_screen() {

       _wait(valueForTheGivenKey("Go_To_Course_CTA"));
       Assert.assertTrue(_is_displayed(valueForTheGivenKey("Go_To_Course_CTA")));

    }


    //Scenario: 7 #Verifying ‘Start Creating’ CTA on Demo Home Page

    @When("User clicks on Start Creating CTA")
    public void User_clicks_on_Start_Creating_CTA() {

        //Storing Current Window Handle in Static String Variable 'Parent_Window'
        Parent_Window = driver.getWindowHandle();

        _wait(valueForTheGivenKey("Video_Container_Title"));
        _search_throughout_webpage("Start_Creating_CTA");

    }

    @Then("User should be redirected to Our-Curriculum Page")
    public void User_should_be_redirected_to_Our_Curriculum_Page() throws InterruptedException {

        //Switching driver focus to next Window after a sec
        Thread.sleep(1000);
        Switch_to_next_tab(Parent_Window);

        //Validating if new tab is opened with Curriculum_Page_URL
        wait.until(ExpectedConditions.urlToBe(valueForTheGivenKey("Our_Curriculum_Page_URL")));
        Assert.assertEquals(valueForTheGivenKey("Our_Curriculum_Page_URL"),driver.getCurrentUrl());

    }


    //Scenario: 8 #Verifying ‘Apple App Store’ CTA on Demo Home Page

    @When("User clicks on Apple AppStore CTA")
    public void user_clicks_on_apple_app_store_cta() {

        //Storing Current Window Handle in Static String Variable 'Parent_Window'
        Parent_Window = driver.getWindowHandle();

        //Waiting for the page to load and navigating to the Apple Store CTA
        _wait(valueForTheGivenKey("Video_Container_Title"));
        _search_throughout_webpage("Apple_Store_CTA");

    }


    @Then("User should be redirected to the Apple AppStore Screen")
    public void user_should_be_redirected_to_the_apple_app_store_screen() {

        //Switching driver focus to next Window
        Switch_to_next_tab(Parent_Window);

        //Validating if new tab is opened with apple URL
        wait.until(ExpectedConditions.urlContains("apps.apple.com"));
        Assert.assertTrue(driver.getCurrentUrl().contains("apps.apple.com"));


    }

    //Scenario: 9 #Verifying ‘Google PlayStore’ CTA on Demo Home Page

    @When("User clicks on Google PlayStore CTA")
    public void user_clicks_on_google_play_store_cta() {

        //Storing Current Window Handle in Static String Variable 'Parent_Window'
        Parent_Window = driver.getWindowHandle();

        //Waiting for the page to load and navigating to the Google Store CTA
        _wait(valueForTheGivenKey("Video_Container_Title"));
        _search_throughout_webpage("Google_PlaySTore_CTA");

    }


    @Then("User should be redirected to the Google PlayStore Screen")
    public void user_should_be_redirected_to_the_google_play_store_screen() {

        //Switching driver focus to next Window
        Switch_to_next_tab(Parent_Window);

        //Validating if new tab is opened with Gogle URL
        wait.until(ExpectedConditions.urlContains("play.google.com"));
        Assert.assertTrue(driver.getCurrentUrl().contains("play.google.com"));

    }


    //Scenario: 10 #Verifying ‘Grab Your Seat’ CTA on Demo User

    @When("User clicks on Grab Your Seat CTA")
    public void User_clicks_on_Grab_Your_Seat_CTA() {

        //Storing Current Window Handle in Static String Variable 'Parent_Window'
        Parent_Window = driver.getWindowHandle();

        _wait(valueForTheGivenKey("Grab_Your_Seat"));
        _click(valueForTheGivenKey("Grab_Your_Seat"));

    }

    //Scenario: 11 #Verifying logout for Demo User

    @When("User clicks on logout button")
    public void User_clicks_on_logout_button() {

        _wait(valueForTheGivenKey("logout_demo_user"));
        _click(valueForTheGivenKey("logout_demo_user"));

    }

    @Then("User is at Student Portal Title Page")
    public void user_is_at_student_portal_title_page_demo() {

        //waiting for home page to load
        _wait(valueForTheGivenKey("Login_with_Password_CTA"));
        //Asserting the Student Home Page
        Assert.assertTrue(_is_displayed(valueForTheGivenKey("Login_with_Password_CTA")));
    }

}

