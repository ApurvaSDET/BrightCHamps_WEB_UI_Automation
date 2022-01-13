package StepDefinition;

import Base.BaseUtil;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Upgrade_Package extends BaseUtil {

    StudentTitlePage obj;

    //Scenario: 1 #Verifying Upgrade Package Card in case of 5 or fewer classes are left

    @When("User is left with below classes in his account to be completed")
    public void user_is_left_with_below_classes_in_his_account_to_be_completed(DataTable dataTable) {

        //getting values from Datatable as a Key: Value pair in feature file
        List<Map<String,String>> data = dataTable.asMaps(String.class, String.class);

        //Creating ArrayList object to store Number of classes in String format
        ArrayList<String> al = new ArrayList<>();

        //Clicking and Waiting for the Profile Page to open
        _click(valueForTheGivenKey("Profile_button"));
        _wait(valueForTheGivenKey("Edit_Profile"));
        _wait_forAllElements(valueForTheGivenKey("Number_of_Classes"));

        //Fetching list of WebElements
        List<WebElement> Classes_Count = driver.findElements(By.xpath(valueForTheGivenKey("Number_of_Classes")));

        //Using enhanced for loop to get the elements
        for (WebElement ele : Classes_Count)

        {
            al.add(ele.getText());
        }

        int Number_of_class_completed = Integer.parseInt(al.get(0));
        int Total_number_of_class = Integer.parseInt(al.get(2));

        //Logic for <=5 classes remaining
        String result;
        if(Total_number_of_class-Number_of_class_completed <=Integer.parseInt(data.get(0).get("ClassesLeft")))
            result = "Less than or equals to "+data.get(0).get("ClassesLeft")+" classes are remaining";
        else
            result = "More than "+data.get(0).get("ClassesLeft")+" classes are remaining";


        Assert.assertEquals("Less than or equals to "+data.get(0).get("ClassesLeft")+" classes are remaining", result);


    }


    @Then("Verify there is an Upgrade Package Card shown for Non-Champion User")
    public void verify_there_is_an_upgrade_package_card_shown_for_non_champion_user() {

        _wait(valueForTheGivenKey("Upgrade_CTA"));
        Assert.assertTrue(_is_displayed(valueForTheGivenKey("Upgrade_CTA")));

    }

    @When("User clicks on Upgrade CTA")
    public void user_clicks_on_upgrade_cta() {

        _click(valueForTheGivenKey("Upgrade_CTA"));

    }

    @Then("User is redirected to the Curriculum selection Page")
    public void user_is_redirected_to_the_curriculum_selection_page() {

        _wait(valueForTheGivenKey("Upgrade_Plan_Page"));

    }

    @When("User selects curriculum")
    public void user_selects_any_curriculum() throws InterruptedException {

        //Storing Current Window Handle in Static String Variable 'Parent_Window'
        Parent_Window = driver.getWindowHandle();

        //Scrolling till Upgrade CTA and clicking on it
        Thread.sleep(1500);
        _search_throughout_webpage("Upgrade_To_CTA");

    }

    @Then("User is redirected to the Payment screen of BrightChamps")
    public void user_is_redirected_to_the_payment_screen_of_bright_champs() throws InterruptedException {

        //Switching driver focus to next Window after a sec
        Thread.sleep(3000);
        Switch_to_next_tab(Parent_Window);

        //Validating if new tab is opened with Curriculum_Page_URL
        wait.until(ExpectedConditions.urlContains(valueForTheGivenKey("Payment_Page_URL")));
        Assert.assertTrue(driver.getCurrentUrl().contains(valueForTheGivenKey("Payment_Page_URL")));

    }


    //Scenario: 2 #Verifying Upgrade Package Card in case no classes are left

    @Then("Navigate back to the Home Page")
    public void Navigate_back_to_the_Home_Page() {

        _click(valueForTheGivenKey("Home_button"));

    }

    @And("Verify there is an Upgrade Now Card at the top of Home Page")
    public void Verify_Upgrade_Now_Card_at_the_top_of_Home_Page() {

        _wait(valueForTheGivenKey("Upgrade_Now_CTA_Home_Page"));

    }

    @When("User clicks on Upgrade Now Card at the top of Home Page")
    public void user_clicks_on_upgrade_now_Card_cta_Home_Page() {

        _click(valueForTheGivenKey("Upgrade_Now_CTA_Home_Page"));

    }


    //Scenario: 3 #Verifying back button of Curriculum Page

    @When("User clicks on back button of Curriculum Page")
    public void User_clicks_back_button_Curriculum_Page() {

        _click(valueForTheGivenKey("Curriculum_Page_Back_Button"));

    }

    //Scenario: 4 #Verifying Class Schedule from Dashboard Screen

    @When("There is no Scheduled Class for a user to attend")
    public void there_is_no_scheduled_class_for_a_user_to_attend() throws InterruptedException {

        //Creating Instance of StudentTitlePage to reuse Cancel methods from that Class
        obj = new StudentTitlePage();

        //Logic to Cancel all the Upcoming Class(if any) until there are no classes remaining
        while(_is_displayed(valueForTheGivenKey("triple_dots"))) {

            obj.user_clicks_on_three_dots_button();
            obj.dropdown_should_appear();
            obj.user_selects_cancel_button();
            obj.cancel_modal_should_appear();
            obj.user_selects_the_checkbox_to_accept_the_agreement();
            obj.yes_button_should_be_enabled();
            obj.user_clicks_on_yes_button();
            obj.user_should_be_able_to_successfully_cancel_the_class();
            obj.reason_asking_for_cancel_should_appear();
            obj.user_should_be_able_to_select_the_cancel_reason_and_click_on_submit_cta();
            obj.Successful_cancel_modal_should_get_closed();

        }

        //Validating No Upcoming class for a user
        _wait(valueForTheGivenKey("Schedule_From_Dashboard"));
        Assert.assertTrue(_is_displayed(valueForTheGivenKey("Schedule_From_Dashboard")));

    }


    @Then("Verify User can schedule a class from Dashboard Screen")
    public void verify_user_can_schedule_a_class_from_dashboard_screen() {


        //Rescheduling all 3 classes from Dashboard Screen
        WebDriverWait wait = new WebDriverWait(driver, 2);

        //Fetching list of WebElements
        List<WebElement> dropdown_menu = driver.findElements(By.xpath(valueForTheGivenKey("Day_Time_Selector")));


        //Using enhanced for loop to get the elements
        for (WebElement ele : dropdown_menu)

        {
            ele.click();

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

        //User Clicks on Schedule CTA after selecting 3 classes per week
        _click(valueForTheGivenKey("Schedule_From_Dashboard"));

        //Waiting for Alert Message after Scheduling
        _wait(valueForTheGivenKey("OTP_Sent_Alert"));

        //validating the Success message
        Assert.assertEquals("Congratulations, your classes have been scheduled.",_get_text(valueForTheGivenKey("OTP_Sent_Alert")));


    }


}
