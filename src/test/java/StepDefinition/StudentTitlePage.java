package StepDefinition;


import Base.BaseUtil;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.*;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class StudentTitlePage extends BaseUtil {

    public static String Referral_URL_from_CTA;
    public static String Referral_URL_from_Paste;
    public List<WebElement> UpcomingClasses;

    //Background: User is Logged In

    @Given("User is at Student portal title Page")
    public void user_is_at_student_portal() {

        //Navigating to the Home Page of student portal
        driver.get(System.getenv("WEB_URL"));
        //waiting for home page to load
        _wait(valueForTheGivenKey("Login_with_Password_CTA"));
        //Asserting the Student Home Page
        Assert.assertTrue(_is_displayed(valueForTheGivenKey("Login_with_Password_CTA")));

        //Accepting Cookies
        AcceptCookies(); //Using method to Accept Cookies
    }

    @When("User clicks on 'Login with Password' CTA")
    public void user_clicks_on_login_with_password_cta() {

        _click(valueForTheGivenKey("Login_with_Password_CTA"));

    }

    @Then("User is at 'Login with Password' screen")
    public void user_is_at_login_with_password_screen() {

        _wait(valueForTheGivenKey("Sign_in_CTA"));
        Assert.assertTrue(_is_displayed(valueForTheGivenKey("Sign_in_CTA")));


    }
    @When("User enters valid email and password")
    public void user_enters_valid_email_and_password(DataTable dataTable) {

        //getting values from Datatable as a Key: Value pair in feature file
        List<Map<String,String>> data = dataTable.asMaps(String.class, String.class);

        driver.findElement(By.xpath(valueForTheGivenKey("Email_field"))).sendKeys(data.get(0).get("Email"));
        driver.findElement(By.xpath(valueForTheGivenKey("Password_field"))).sendKeys(data.get(0).get("Password"));
    }

    @And("User clicks on Sign-in CTA")
    public void Sign_in_CTA_click() throws InterruptedException {

        _click(valueForTheGivenKey("Sign_in_CTA"));
        //Providing time to load
        DocumentInReadyState();
        Thread.sleep(3000);

    }

    //Scenario: 1 #Verifying login via email and password for Single user a/c

    @Then("User is at the Home Page of single user account")
    public void user_is_redirected_to_the_home_page_single_user_account() {

        //waiting for home page to load
        _wait(valueForTheGivenKey("triple_dots"));
        //Asserting the Student Home Page
        Assert.assertTrue(_is_displayed(valueForTheGivenKey("triple_dots")));

    }


    // Scenario: 2 #Verifying logout after login via email and password for Single user a/c

    @And("Clicks on Profile button")
    public void Clicks_on_Profile_button() {

        //waiting for profile button
        _wait(valueForTheGivenKey("Profile_button"));

        //clicking on profile button
        _click(valueForTheGivenKey("Profile_button"));

    }

    @Then("User is at Profile screen")
    public void user_is_at_profile_screen() {

        //waiting for document to be in ready state
        DocumentInReadyState();

        //waiting for Profile page to load
        _wait(valueForTheGivenKey("Edit_Profile"));

        //Asserting the Profile pag
        Assert.assertTrue(_is_displayed(valueForTheGivenKey("Edit_Profile")));

    }

    @When("User clicks logout button")
    public void user_clicks_logout_button() {

        //clicking on log-out button
        _wait(valueForTheGivenKey("triple_dots_profile"));
        _click(valueForTheGivenKey("triple_dots_profile"));

        //waiting for log-out dropdown
        _wait(valueForTheGivenKey("logout_button"));

        //clicking on log-out button
        _click(valueForTheGivenKey("logout_button"));
    }

    //Scenario: 3 #Verifying back button of Re-scheduling Next Class screen

    @When("User Clicks on three dots button")
    public void user_clicks_on_three_dots_button() throws InterruptedException {

        //Setting Vertical of test a/c to 1 for BrightChamps User
        _getResult(valueForTheGivenKey("UpdateVertical"), null, null);

        //Reloading WebPage to reflect BrightChamps Vertical
        driver.navigate().refresh();

        //waiting for document to be in ready state
        DocumentInReadyState();

        _wait(valueForTheGivenKey("triple_dots"));

        //Clicking on 3 dots
        Thread.sleep(3000);
        _click(valueForTheGivenKey("triple_dots"));

    }

    @Then("Dropdown should appear")
    public void dropdown_should_appear() {

        //wait for dropdown
        DocumentInReadyState();
        _wait(valueForTheGivenKey("Reschedule_class"));

        //Asserting dropdown
        Assert.assertTrue(_is_displayed(valueForTheGivenKey("Reschedule_class")));

    }

    @When("User Selects Reschedule button")
    public void user_selects_reschedule_button() {

        //Click on Reschedule button
        _click(valueForTheGivenKey("Reschedule_class"));

    }

    @Then("User is at Reschedule screen and Next class should be pre-selected")
    public void user_is_at_reschedule_screen_and_next_class_should_be_pre_selected(){

        //waiting to land on Reschedule screen
        _wait(valueForTheGivenKey("Reschedule_screen"));

        //Asserting Reschedule screen
        Assert.assertTrue(_is_displayed(valueForTheGivenKey("Reschedule_screen")));


        //Asserting pre-selected class should be Next class
        Assert.assertTrue(_is_displayed(valueForTheGivenKey("Next_Class_button")));

    }

    @When("User clicks on back button")
    public void user_clicks_on_back_button() {

        _click(valueForTheGivenKey("Back_button"));

    }



    //Scenario: 4 #Verifying Re-scheduling Next Class

    @When("User selected next class date and time")
    public void user_selected_next_class_date_and_time() {

        //Clicking on Next class Date select dropdown
        _click(valueForTheGivenKey("Next_class_Day_selector"));

        //wait for dropdown to appear
        _wait_till_element_available_in_dropdown(valueForTheGivenKey("List_of_day"), 10);

        //Selecting any date from the available date
        _random_options_from_dropdown(valueForTheGivenKey("List_of_day"));


        //**************Selecting time after date****************


        //Clicking on Next class Time Select dropdown
        _click(valueForTheGivenKey("Next_class_Time_selector"));

        //wait for dropdown to appear
        _wait_till_element_available_in_dropdown(valueForTheGivenKey("List_of_available_time"), 10);

        //Selecting any time from the available time
        _random_options_from_dropdown(valueForTheGivenKey("List_of_available_time"));


    }

    @Then("Book your Slot CTA should get enabled")
    public void cta_should_get_enabled() {

        //Asserting Book your Slot CTA
        _wait(valueForTheGivenKey("Enabled_Book_your_slot"));
        Assert.assertTrue(_is_enabled(valueForTheGivenKey("Enabled_Book_your_slot")));

    }

    @When("User clicks on Book your Slot CTA")
    public void user_clicks_on_cta(){

        _click(valueForTheGivenKey("Enabled_Book_your_slot"));

    }

    @Then("User should be able to successfully Reschedule the class")
    public void user_should_be_able_to_successfully_reschedule_the_class() {

        _wait(valueForTheGivenKey("Successfully_rescheduled_alert"));
        Assert.assertTrue(_is_displayed(valueForTheGivenKey("Successfully_rescheduled_alert")));

    }

    @And("Reason asking for Reschedule should appear")
    public void reason_asking_for_reschedule_should_appear() {

        //Assertion for availability of list of reasons for reschedule
        _is_displayed(valueForTheGivenKey("Reschedule_reason_options"));

    }

    @Then("User should be able to select the reason and click on Submit CTA")
    public void user_should_be_able_to_select_the_reason_and_click_on_submit_cta() {

        //Selecting any options available randomly
        _random_options_from_dropdown(valueForTheGivenKey("Reschedule_reason_options"));


        //Waiting and Clicking on Submit CTA
        _wait(valueForTheGivenKey("Reschedule_Submit_CTA"));
        _click(valueForTheGivenKey("Reschedule_Submit_CTA"));

    }

    @And("User should be redirected to dashboard screen")
    public void user_should_be_redirected_to_dashboard_screen(){

        //wait for the invisibility
        _WaitAbsence(valueForTheGivenKey("Reschedule_SKIP_Button"));

        //wait for any unique dashboard element to be visible
        _wait(valueForTheGivenKey("triple_dots"));

        //waiting for document to be in ready state on dashboard
        DocumentInReadyState();

        //Assertion for redirecting to home page
        Assert.assertTrue(_is_displayed(valueForTheGivenKey("triple_dots")));

    }

    //Scenario: 5 #Verifying Skip button of reschedule screen

    @Given("User is at reschedule successful screen")
    public void user_is_at_reschedule_successful_screen() throws InterruptedException {

        user_clicks_on_three_dots_button();
        dropdown_should_appear();
        user_selects_reschedule_button();
        user_is_at_reschedule_screen_and_next_class_should_be_pre_selected();
        user_selected_next_class_date_and_time();
        cta_should_get_enabled();
        user_clicks_on_cta();
        user_should_be_able_to_successfully_reschedule_the_class();

    }


    @When("User clicks on Skip link")
    public void user_clicks_on_skip_link() {

        //Clicking on SKIP link
        _wait(valueForTheGivenKey("Reschedule_SKIP_Button"));
        _click(valueForTheGivenKey("Reschedule_SKIP_Button"));

    }

    //Scenario: 6 #Verifying back button of reschedule screen



    //Scenario: 7 #Verifying Re-scheduling All Class

    @Then("User is at Reschedule screen")
    public void user_is_at_reschedule_screen() {

        //waiting to land on Reschedule screen
        _wait(valueForTheGivenKey("Reschedule_screen"));

        //Asserting Reschedule screen
        Assert.assertTrue(_is_displayed(valueForTheGivenKey("Reschedule_screen")));

    }

    @And("User selects All Class tab")
    public void user_selects_all_class_tab() {

        _click(valueForTheGivenKey("All_Class_Button"));

    }

    @Then("By default three class per week should be shown")
    public void by_default_three_class_per_week_should_be_shown() {

    //Assertion for showing 3 class slots
    Assert.assertEquals(6, (_get_WebElements_size(valueForTheGivenKey("class_per_week_count"))));

    }

    @When("User selects classes one by one")
    public void user_selects_classes_one_by_one() {

        _selecting_one_by_one_from_dropdown(valueForTheGivenKey("No._of_classes"));

    }

    @Then("User should see the option to select date amd time based on number of classes selected")
    public void user_should_see_the_option_to_select_date_amd_time_based_on_number_of_classes_selected() {

        //Fetching list of WebElements
        List<WebElement> dropdown_menu = driver.findElements(By.xpath(valueForTheGivenKey("No._of_classes")));

        //Creating ArrayList and Random instance
        ArrayList<Integer> al = new ArrayList();

        //Using enhanced for loop to get the elements
        for (WebElement ele : dropdown_menu)

        {
            ele.click();
            al.add(_get_WebElements_size(valueForTheGivenKey("class_per_week_count")));

        }

        //Asserting all 4 classes slots wrt classes selected
        Assert.assertEquals("2", String.valueOf(al.get(0)));
        Assert.assertEquals("4", String.valueOf(al.get(1)));
        Assert.assertEquals("6", String.valueOf(al.get(2)));
        Assert.assertEquals("8", String.valueOf(al.get(3)));

    }

    @When("User selected all four classes date and time")
    public void user_selected_all_four_classes_date_and_time() throws InterruptedException {

        WebDriverWait wait = new WebDriverWait(driver, 2);

        //Fetching list of WebElements
        List<WebElement> dropdown_menu = driver.findElements(By.xpath(valueForTheGivenKey("All_class_Day_selector")));


        //Using enhanced for loop to get the elements
        for (WebElement ele : dropdown_menu)

        {
            try
                {
                    ele.click();
                }
            catch (ElementClickInterceptedException | StaleElementReferenceException e)
                {
                    driver.navigate().refresh();
                    Thread.sleep(1500);
                    ele.click();
                }

            try {

                //wait for dropdown to appear
                wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(valueForTheGivenKey("List_of_Days_available"))));

                //Selecting any option from the available date/time
                _random_options_from_dropdown(valueForTheGivenKey("List_of_Days_available"));
            }
            catch (TimeoutException e)
            {
                //wait for dropdown to appear
                wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(valueForTheGivenKey("List_of_Time_available"))));

                //Selecting any option from the available date/time
                _random_options_from_dropdown(valueForTheGivenKey("List_of_Time_available"));

            }

        }


    }

    //Scenario: 9 #Verifying No button on Cancel Class modal

    @When("User Selects Cancel button")
    public void user_selects_cancel_button() {

        //Click on Cancel button
        _wait(valueForTheGivenKey("Cancel_Class"));
        _click(valueForTheGivenKey("Cancel_Class"));

    }

    @Then("Cancel modal should appear")
    public void cancel_modal_should_appear() {

        //Waiting for Cancel modal to appear
        _wait(valueForTheGivenKey("Cancel_modal_checkbox"));
        //Asserting Cancel modal to appear
        waitForGivenSec(1);
        Assert.assertTrue(_is_displayed_link_text(valueForTheGivenKey("Student_Policy_link_text")));

    }

    @When("User clicks on NO button")
    public void user_clicks_on_no_button() {

        _wait(valueForTheGivenKey("No_CTA_Cancel_modal"));
        _click(valueForTheGivenKey("No_CTA_Cancel_modal"));
    }

    @Then("Cancel modal should get closed")
    public void cancel_modal_should_get_closed() {

        _WaitAbsence(valueForTheGivenKey("Cancel_modal_checkbox"));
        Assert.assertFalse(_is_displayed(valueForTheGivenKey("Cancel_modal_checkbox")));

    }


    //Scenario: 10 #Verifying YES button on Cancel Class modal without accepting the student policy

    @And("YES button should be disabled")
    public void yes_button_should_be_disabled(){

        //Asserting if YES CTA is disabled

        try {
            Assert.assertFalse(driver.findElement(By.xpath(valueForTheGivenKey("Disabled_Yes_button"))).isEnabled());
        }
        catch (NoSuchElementException e)
        {
            Assert.fail();
        }

    }


    //Scenario: 11 #Verifying student policy link on Cancel Class modal

    @When("User clicks on student policy link")
    public void user_clicks_on_student_policy_link() {

        //Getting current window ID
        Parent_Window = driver.getWindowHandle();

        //Clicks on link available on cancel modal
        driver.findElement(By.linkText(valueForTheGivenKey("Student_Policy_link_text"))).click();

    }

    @Then("A web page with student policy should get opened")
    public void a_web_page_with_student_policy_should_get_opened() {

        Set<String> multiple_window = driver.getWindowHandles();

        for(String e : multiple_window)
        {
            if(!e.equalsIgnoreCase(Parent_Window))
            {
                driver.switchTo().window(e);
            }
        }

        String PageTitle = driver.getTitle();

        if(PageTitle.equals("Student Policy") || PageTitle.equals("Refund Policy -10x"))
            Assert.assertTrue(true);
        else
            Assert.fail();
    }

    // Scenario: 12 #Verifying YES button on Cancel Class modal after accepting the student policy

    @When("User selects the checkbox to accept the agreement")
    public void user_selects_the_checkbox_to_accept_the_agreement() {

        //Selecting the checkbox
        _click(valueForTheGivenKey("Cancel_modal_checkbox"));
    }

    @Then("YES button should be enabled")
    public void yes_button_should_be_enabled() {

        //Asserting if YES CTA is enabled

        try {
            Assert.assertTrue(driver.findElement(By.xpath(valueForTheGivenKey("Disabled_Yes_button"))).isEnabled());
        }
        catch (NoSuchElementException e)
        {
            Assert.assertTrue(true);
        }

    }

    @When("User clicks on YES button")
    public void user_clicks_on_yes_button() {

        //Clicking Yes after selecting Checkbox
        try {
            driver.findElement(By.xpath(valueForTheGivenKey("Enabled_Yes_button"))).click();
        } catch (NoSuchElementException | StaleElementReferenceException | ElementClickInterceptedException e) {
            if (e.getMessage().contains("StaleElementReferenceException")) {
                e.getMessage();
            } else {
                Assert.fail();
            }

        }

    }

    @Then("User should be able to successfully cancel the class")
    public void user_should_be_able_to_successfully_cancel_the_class() {

        //waiting for success text
        _wait(valueForTheGivenKey("Successfully_cancel_alert"));

        //Asserting Cancel Successful pop-up
        Assert.assertEquals("This class has been cancelled",_get_text(valueForTheGivenKey("Successfully_cancel_alert")));

    }

    @Then("Reason asking for Cancel should appear")
    public void reason_asking_for_cancel_should_appear() {

        //Assertion for availability of list of reasons for cancellation
        _is_displayed(valueForTheGivenKey("Cancel_reason_options"));

    }

    @Then("User should be able to select the cancel reason and click on Submit CTA")
    public void user_should_be_able_to_select_the_cancel_reason_and_click_on_submit_cta() {

        //Selecting any options available randomly
        _random_options_from_dropdown(valueForTheGivenKey("Cancel_reason_options"));

        //Clicking on Submit CTA
        _click(valueForTheGivenKey("Enabled_Submit_CTA"));


    }

    @And("Successful cancel modal should get closed and Webpage should reload")
    public void Successful_cancel_modal_should_get_closed() {

        //wait for the invisibility
        _WaitAbsence(valueForTheGivenKey("SKIP_Button"));

        //Assertion for reloading of the web page
        _WaitAbsence(valueForTheGivenKey("Cancel_Class"));
        Assert.assertFalse(_is_displayed(valueForTheGivenKey("Cancel_Class")));

    }

    //Scenario: 13 #Verifying Skip button of Successful cancel screen

    @Given("User is at cancel successful screen")
    public void user_is_at_cancel_successful_screen() throws InterruptedException {

        //Reusing above methods to launch Cancel Success Screen
        user_clicks_on_three_dots_button();
        dropdown_should_appear();
        user_selects_cancel_button();
        cancel_modal_should_appear();
        user_selects_the_checkbox_to_accept_the_agreement();
        user_clicks_on_yes_button();
        user_should_be_able_to_successfully_cancel_the_class();

    }

    @Given("User clicks on Skip link of Cancel Modal")
    public void User_clicks_Skip_of_Cancel_Modal(){

        //Clicking on SKIP link
        _wait(valueForTheGivenKey("SKIP_Button"));
        _click(valueForTheGivenKey("SKIP_Button"));

    }

    //Scenario: 13 #Verifying disabled JOIN CTA logic

    @When("User has an Upcoming Appointment")
    public void User_has_an_Upcoming_Appointment(){

        //Clicking on SKIP link
        _wait(valueForTheGivenKey("triple_dots"));
        Assert.assertTrue(_is_displayed(valueForTheGivenKey("triple_dots")));

    }

    //Scenario: 14 #Verifying global House CTA

    @When("User clicks on global house banner")
    public void user_clicks_on_global_house_banner() {

        //Clering Data of Test User from DB before Running this Test
        _getResult(valueForTheGivenKey("DeleteGlobalHouseEntry"), null, null);

        //Setting Vertical of test a/c to 1 for BrightChamps User
        _getResult(valueForTheGivenKey("UpdateVertical"), null, null);

        //Reloading WebPage to reflect Global House Banner
        driver.navigate().refresh();

        //Storing Current Window Handle in Static String Variable 'Parent_Window'
        Parent_Window = driver.getWindowHandle();

        //Waiting for the Global House Card to load on Web Page
        _wait(valueForTheGivenKey("Global_House_banner"));

        //Clicking on Confirm_Now Button
        _click(valueForTheGivenKey("Global_House_banner"));

    }

    @Then("Verify new tab with global house detail should get opened")
    public void user_is_redirected_to_global_house_screen() throws InterruptedException {

        //Switching driver focus to next Window
        Switch_to_next_tab(Parent_Window);
        Thread.sleep(1500);

        //Asserting Book Your Slot disabled CTA
        _Scrolling_throughout_the_WebPage("Disabled_Book_your_slot");
        Assert.assertTrue(_is_displayed(valueForTheGivenKey("Disabled_Book_your_slot")));

    }

    @When("User selects all the Preference")
    public void user_selects_all_the_preference() throws InterruptedException {

        //Reusing Reschedule all classes method
        user_selected_all_four_classes_date_and_time();
    }

    @Then("Success alert message should appear")
    public void success_alert_should_appear() {

        _wait(valueForTheGivenKey("OTP_Sent_Alert"));
        Assert.assertEquals("Congratulations! Your entry into the Global House is confirmed :)",_get_text(valueForTheGivenKey("OTP_Sent_Alert")));

    }

    @Then("Verify Congratulations! message on Global House Screen")
    public void verify_congratulations_message_on_global_house_screen() {

         //Waiting for the Global House Card
        _Scrolling_throughout_the_WebPage("Global_House_Success_Msg");

        //Asserting the Global House Card
        Assert.assertEquals("Your entry into Global House is confirmed. We will schedule your session very soon.\n" +
                "Till then stay connected :)", _get_text(valueForTheGivenKey("Global_House_Success_Msg")));

    }

    //Scenario: 15 #Verifying referral modal on Home Page of paid user

    @When("User clicks on Book Free Trial CTA of referral card")
    public void user_clicks_on_book_free_trial_cta_of_referral_card() throws InterruptedException {

        //Making page to stop loading referral webpage and throw exception, since it's loading infinitely
        driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);

        //Storing Current Window Handle in Static String Variable 'Parent_Window'
        Parent_Window = driver.getWindowHandle();

        //Waiting for the page to load and navigating to the Book_Free_Trial CTA
        Thread.sleep(2000);
        _search_throughout_webpage("Book_Free_Trial_CTA");

    }

    @Then("User should be navigated to the new tab with referral link in it")
    public void user_should_be_navigated_to_the_new_tab_with_referral_link_in_it() {

        // Executing rest of the logic in try catch block after exception is thrown
        try {
            //Switching driver focus to next Window
            Switch_to_next_tab(Parent_Window);
            Referral_URL_from_CTA = driver.getCurrentUrl();

        } catch (TimeoutException e) {

            Referral_URL_from_CTA = driver.getCurrentUrl();
        }

        //Validating if new tab is opened with referral URL
        Assert.assertTrue(Referral_URL_from_CTA.contains("utm_source=referral"));

    }

    @When("User click on COPY LINK")
    public void user_click_on_copy_link() {

        //Switching to the default window
        driver.close();
        driver.switchTo().window(Parent_Window);

        //Click on COPY LINK CTA on referral modal
        _search_throughout_webpage("COPY_LINK_CTA");


    }

    @Then("Text on CTA should be changed to COPIED")
    public void text_on_cta_should_be_changed_to_copied() {

        //Validating COPIED CTA
        _wait(valueForTheGivenKey("COPIED_CTA"));
        Assert.assertTrue(_is_displayed(valueForTheGivenKey("COPIED_CTA")));

    }

    @When("User opens a new tab and paste copied link in it")
    public void user_opens_a_new_tab_and_paste_copied_link_in_it() throws InterruptedException {

        //*************Copying the Copied text on Email field and getting the copied text****************

        //Reusing methods to navigate to Email field and pasting the copied link on Email text input
        Clicks_on_Profile_button();
        user_clicks_logout_button();
        user_is_at_student_portal();
        user_clicks_on_login_with_password_cta();
        user_is_at_login_with_password_screen();

        // Pasting the Copied link using Cmd/Ctrl +V depending upon system
        if(System.getProperty("os.name").contains("Mac"))
            driver.findElement(By.xpath(valueForTheGivenKey("Email_field"))).sendKeys(Keys.COMMAND + "v");

        else
            driver.findElement(By.xpath(valueForTheGivenKey("Email_field"))).sendKeys(Keys.CONTROL + "v");


        //Storing the String value in a local variable
        String PastedURL = driver.findElement(By.xpath(valueForTheGivenKey("Email_field"))).getAttribute("value");

        //Opening a new tab
        openNewTab();

        //Switching driver focus to next Window and navigating to the copied URL from ClipBoard
        Switch_to_next_tab(Parent_Window);

        // Executing rest of the logic in try catch block after exception is thrown
        try {
            driver.get(PastedURL);
        } catch (TimeoutException e) {
            // Ignore the exception.
        }

        //Validating if new tab is opened with referral URL
        Thread.sleep(3000);
        Referral_URL_from_Paste = driver.getCurrentUrl();
        Assert.assertTrue(Referral_URL_from_Paste.contains("utm_source=referral"));
    }

    @Then("Verify Same URL should be there which is associated with Book Free Trial CTA")
    public void verify_same_url_should_be_there_which_is_associated_with_book_free_trial_cta() {

        //Validating if both CTAs are fulfilling same purpose
        Assert.assertEquals(Referral_URL_from_CTA, Referral_URL_from_Paste);

    }

    //Scenario: 16 #Verifying 'Know More' CTA on referral modal

    @When("User clicks on 'Know More' CTA of referral card")
    public void User_clicks_on_Know_More_CTA_of_referral_card() throws InterruptedException {

        //Storing Current Window Handle in Static String Variable 'Parent_Window'
        Parent_Window = driver.getWindowHandle();

        //Waiting for the page to load and navigating to the Book_Free_Trial CTA
        Thread.sleep(1500);
        _search_throughout_webpage("Know_More_CTA");

    }

    @Then("User should be navigated to the new policy tab")
    public void User_should_be_navigated_to_the_new_policy_tab() {

        //Switching driver focus to next Window
        Switch_to_next_tab(Parent_Window);

        //Validating if new tab is opened with Policy URL
        Assert.assertTrue(driver.getCurrentUrl().contains("policy.brightchamps.com"));

    }

    //Scenario: 17 #Verifying 'Facebook' link on referral modal

    @When("User clicks on Facebook link of referral card")
    public void userClicksOnFacebookLinkOfReferralCard() throws InterruptedException {

        //Storing Current Window Handle in Static String Variable 'Parent_Window'
        Parent_Window = driver.getWindowHandle();

        //Waiting for the page to load and navigating to the Book_Free_Trial CTA
        Thread.sleep(1500);
        _search_throughout_webpage("Facebook_Share_icon");

    }

    @Then("Verify new tab with facebook link should get opened")
    public void verifyNewTabWithFacebookLinkShouldGetOpened() {

        //Switching driver focus to next Window
        Switch_to_next_tab(Parent_Window);

        //Validating if new tab is opened with Policy URL
        Assert.assertTrue(driver.getCurrentUrl().contains("www.facebook.com"));

    }


    //Scenario: 18 #Verifying 'WhatsApp' link  on referral modal

    @When("User clicks on WhatsApp link of referral card")
    public void userClicksOnWhatsAppLinkOfReferralCard() throws InterruptedException {

        //Storing Current Window Handle in Static String Variable 'Parent_Window'
        Parent_Window = driver.getWindowHandle();

        //Waiting for the page to load and navigating to the Book_Free_Trial CTA
        Thread.sleep(1500);
        _search_throughout_webpage("WhatsApp_Share_icon");

    }

    @Then("Verify new tab with WhatsApp link should get opened")
    public void verifyNewTabWithWhatsAppLinkShouldGetOpened() {

        //Switching driver focus to next Window
        Switch_to_next_tab(Parent_Window);

        //Validating if new tab is opened with Policy URL
        Assert.assertTrue(driver.getCurrentUrl().contains("web.whatsapp.com"));

    }

    //Scenario: 19 #Verifying Leaderboard section on referral modal

    @When("User clicks on Leaderboard section of referral card")
    public void userClicksOnLeaderboardSectionOfReferralCard() throws InterruptedException {

        //Setting Vertical of test a/c to 1 for BrightChamps User
        _getResult(valueForTheGivenKey("UpdateVertical"), null, null);

        //Reloading WebPage to reflect BrightChamps Vertical
        driver.navigate().refresh();

        //Waiting for the page to load and navigating to the Book_Free_Trial CTA
        Thread.sleep(2500);
        _search_throughout_webpage("LeaderBoard_link");

    }

    @Then("Leaderboard section should gets opened")
    public void leaderboardSectionShouldGetsOpened() {

        //Validating if Correct Data appears on Leaderboard
        Assert.assertTrue(_is_displayed(valueForTheGivenKey("Your_Referrals"))
                &&
                _is_displayed(valueForTheGivenKey("Your_Rank")));


    }

    @And("Verify all the data on Leaderboard section")
    public void verifyAllTheDataOnLeaderboardSection() {

        //Validating if Correct number of Rows and Coloumns appear on Leaderboard
        Assert.assertEquals(4, _get_WebElements_size(valueForTheGivenKey("LeaderBoard_Coloumn_Count")));
        Assert.assertEquals(10, _get_WebElements_size(valueForTheGivenKey("LeaderBoard_Rows_Count")));

    }

    //Scenario: 20 #Verifying 'Check your schedule classes' expand button

    @When("Verify Upcoming Schedule section on Home Page")
    public void Verify_Upcoming_Schedule_section_on_Home_Page() {

        //Waiting till Upcoming Section gets loaded
        _wait(valueForTheGivenKey("Upcoming_Classes"));

        //Validating Upcoming Schedule Section On Home Page
        Assert.assertTrue(_is_displayed(valueForTheGivenKey("Upcoming_Classes")));

    }

    @Then("User clicks on Right Arrow Button")
    public void User_clicks_on_Right_Arrow_Button() {

        //Click on Next Button
        _wait(valueForTheGivenKey("Upcoming_Classes"));
        _click(valueForTheGivenKey("Next_Slide"));

    }

    @When("Upcoming classes crad should be scrolled to left")
    public void upcoming_classes_scroll_left() {

        _wait(valueForTheGivenKey("Previous_Slide"));

        //Validating Previous buttom on Upcoming Schedule Section
        Assert.assertTrue(_is_displayed(valueForTheGivenKey("Previous_Slide")));

    }

    @Then("User clicks on Left Arrow Button")
    public void User_clicks_on_Left_Arrow_Button() {

        //Click on Previous Button
        _click(valueForTheGivenKey("Previous_Slide"));

    }

    @When("Upcoming classes crad should be scrolled to Right")
    public void upcoming_classes_scroll_right() {

        _WaitAbsence(valueForTheGivenKey("Previous_Slide"));

        //Validating Previous buttom on Upcoming Schedule Section
        Assert.assertFalse(_is_displayed(valueForTheGivenKey("Previous_Slide")));

    }

}

