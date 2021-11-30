package StepDefinition;


import Base.BaseUtil;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import java.util.List;
import java.util.Map;

public class StudentTitlePage extends BaseUtil {

    //Background: User is Logged In

    @Given("User is at Student portal title Page")
    public void user_is_at_student_portal() {

        //Navigating to the Home Page of student portal
        driver.get("https://students.brightchamps.com/");
        //waiting for home page to load
        WaitForTitleToBe(valueForTheGivenKey("Student_Title_Page"));
        //Asserting the Student Home Page
        Assert.assertEquals(valueForTheGivenKey("Student_Title_Page"), driver.getTitle());
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
    public void Sign_in_CTA_click() {

        _click(valueForTheGivenKey("Sign_in_CTA"));
    }

    @Then("Referral Pop-up screen appears after login")
    public void Referral_pop_up_modal() {

        //waiting for home page to load
        _wait(valueForTheGivenKey("Referral_modal"));
        //Asserting the Student Home Page
        Assert.assertTrue(_is_displayed(valueForTheGivenKey("Referral_modal")));

    }

    @And("User dismiss referral modal")
    public void User_dismiss_referral_modal() throws InterruptedException {

        //Closing the referral modal
        _click(valueForTheGivenKey("Referral_modal"));
        //Providing time to load
        Thread.sleep(3000);

    }

    //Scenario: 1 #Verifying login via email and password for Single user a/c

    @Then("User is at the Home Page of single user account")
    public void user_is_redirected_to_the_home_page_single_user_account() {

        //waiting for home page to load
        _wait(valueForTheGivenKey("Profile_button"));
        //Asserting the Student Home Page
        Assert.assertTrue(_is_displayed(valueForTheGivenKey("Profile_button")));

    }


    // Scenario: 2 #Verifying logout after login via email and password for Single user a/c

    @And("Clicks on Profile button")
    public void Clicks_on_Profile_button() {

        //clicking on profile button
        _click(valueForTheGivenKey("Profile_button"));

    }

    @Then("User is at Profile screen")
    public void user_is_at_profile_screen() {

        //waiting for Profile page to load
        _wait(valueForTheGivenKey("Edit_Profile"));
        //Asserting the Profile pag
        Assert.assertTrue(_is_displayed(valueForTheGivenKey("Edit_Profile")));

    }

    @When("User clicks logout button")
    public void user_clicks_logout_button() {

        //clicking on log-out button
        _click(valueForTheGivenKey("triple_dots"));
        //waiting for log-out dropdown
        _wait(valueForTheGivenKey("logout_button"));
        //clicking on log-out button
        _click(valueForTheGivenKey("logout_button"));
    }

    //Scenario: 3 #Verifying Re-scheduling Next Class

    @When("User Clicks on three dots button")
    public void user_clicks_on_three_dots_button(){

        //Clicking on 3 dots
        _click_stale(valueForTheGivenKey("triple_dots"));

    }

    @Then("Dropdown should appear")
    public void dropdown_should_appear() {

        //wait for dropdown
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

    @When("User selected next class date and time")
    public void user_selected_next_class_date_and_time() throws InterruptedException {

        //Clicking on Next class Date select dropdown
        _click(valueForTheGivenKey("Next_class_Day_selector"));

        //wait for dropdown to appear
        _wait(valueForTheGivenKey("List_of_day"));

        //Selecting any date from the available date
        _random_options_from_dropdown(valueForTheGivenKey("List_of_day"));


        //**************Selecting time after date****************


        //Clicking on Next class Time Select dropdown
        _click(valueForTheGivenKey("Next_class_Time_selector"));

        //wait for dropdown to appear
        _wait(valueForTheGivenKey("List_of_available_time"));

        //Selecting any time from the available time
        _random_options_from_dropdown(valueForTheGivenKey("List_of_available_time"));


    }

    @Then("Book your Slot CTA should get enabled")
    public void cta_should_get_enabled() {

        //Asserting Book your Slot CTA
        Assert.assertTrue(_is_enabled(valueForTheGivenKey("Book_your_Slot_CTA")));

    }

    @When("User clicks on Book your Slot CTA")
    public void user_clicks_on_cta() {

        _click(valueForTheGivenKey("Book_your_Slot_CTA"));

    }

    @Then("User should be able to successfully Reschedule the class")
    public void user_should_be_able_to_successfully_reschedule_the_class() {

    }

    @And("Reason asking for Reschedule should appear")
    public void reason_asking_for_reschedule_should_appear() {

    }

    @Then("User should be able to select the reason and click on Submit CTA")
    public void user_should_be_able_to_select_the_reason_and_click_on_submit_cta() {

    }

    @And("User should be redirected to dashboard screen")
    public void user_should_be_redirected_to_dashboard_screen() {

    }

    //Scenario: 4 #Verifying Skip button of reschedule screen

    @Given("User is at reschedule successful screen")
    public void user_is_at_reschedule_successful_screen() {

    }


    @When("User clicks on Skip link")
    public void user_clicks_on_skip_link() {

    }

    //Scenario: 5 #Verifying back button of reschedule screen

    @When("User clicks on back button")
    public void user_clicks_on_back_button() {

    }


    //Scenario: 6 #Verifying Re-scheduling All Class

    @Then("User is at Reschedule screen")
    public void user_is_at_reschedule_screen() {

    }

    @And("User selects All Class tab")
    public void user_selects_all_class_tab() {

    }

    @Then("By default three class per week should be shown")
    public void by_default_three_class_per_week_should_be_shown() {

    }

    @When("User selects classes one by one")
    public void user_selects_classes_one_by_one() {

    }

    @Then("User should see the option to select date amd time based on number of classes selected")
    public void user_should_see_the_option_to_select_date_amd_time_based_on_number_of_classes_selected() {

    }

    @When("User selected all four classes date and time")
    public void user_selected_all_four_classes_date_and_time() {

    }

    }

