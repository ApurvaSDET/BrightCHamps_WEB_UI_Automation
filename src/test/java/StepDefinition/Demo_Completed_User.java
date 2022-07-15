package StepDefinition;

import Base.BaseUtil;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class Demo_Completed_User extends BaseUtil {


    //Background Conditions

    @Then("User is at the Home Page of Demo Completed account")
    public void user_is_at_the_home_page_of_demo_completed_account() {

        //Waiting for YouTube Hackathon Modal
        _wait(valueForTheGivenKey("Profile_Completion_Modal"));

        //Clicking on the Modal to Close
        _click(valueForTheGivenKey("Profile_Completion_Modal"));

        _wait(valueForTheGivenKey("Congrats_Banner"));
        Assert.assertTrue(_is_displayed(valueForTheGivenKey("Congrats_Banner")));

    }


    //Scenario: 1 #Verifying Demo Completed Banner at the top of the Dashboard

    @Then("Demo Completed banner should be present at the top of the Dashboard")
    public void demo_completed_banner_should_be_present_at_the_top_of_the_dashboard() {

        _wait(valueForTheGivenKey("Congrats_Banner"));
        Assert.assertTrue(_is_displayed(valueForTheGivenKey("Congrats_Banner")));

    }

    //Scenario: 2 #Verifying Free Scratch Course Video on the Demo Dashboard

    @Then("Verify Video should be playing on the dashboard")
    public void verify_video_should_be_playing_on_the_dashboard(){

        //Waiting for the Video Player to load up
        _wait(valueForTheGivenKey("YouTube_Video_Frame"));

        //Switching to another frame where YouTube Video is placed
        driver.switchTo().frame(0);

        //Pause Video after switching into the frame
        _wait(valueForTheGivenKey("Player_ID"));
        _click(valueForTheGivenKey("Player_ID"));



        //Validating if Video is correctly loaded and played
        try {
            Assert.assertFalse(driver.findElement(By.xpath(valueForTheGivenKey("YouTube_Link_Broken_error"))).isDisplayed());
        } catch (NoSuchElementException e) {
            Assert.assertTrue(true);
        }


    }

    //Scenario: 3 #Verifying ‘Try our Course’ CTA on Demo Home Page

    @When("User clicks on Try our Course CTA")
    public void User_clicks_on_Try_our_Course_CTA() {

        _wait(valueForTheGivenKey("Congrats_Banner"));
        _search_throughout_webpage("Try_Our_Course");

    }

    @Then("User should be redirected to Scratch Basics screen")
    public void User_should_be_redirected_to_Freemium_Videos_screen() {

        _wait(valueForTheGivenKey("Go_To_Course"));
        Assert.assertTrue(_is_displayed(valueForTheGivenKey("Go_To_Course")));

    }



    //Scenario: 4 #Verifying Buy This Plan CTA of Accelerator Plan
    @When("User clicks on Buy This Plan CTA of Accelerator Plan")
    public void user_clicks_on_buy_this_plan_cta_of_accelerator_plan() {

        //Scrolling till ACCELERATOR_Buy_This_Plan CTA is in view and clicking on it
        _search_throughout_webpage("ACCELERATOR_Buy_This_Plan");

    }

    @Then("User should be redirected to Payment Gateway")
    public void user_should_be_redirected_to_payment_gateway() {


    }

    //Scenario: 5 #Verifying Buy This Plan CTA of Achiever Plan
    @When("User clicks on Buy This Plan CTA of Achiever Plan")
    public void user_clicks_on_buy_this_plan_cta_of_achiever_plan() {

        //Scrolling till ACHIEVER_Buy_This_Plan CTA is in view and clicking on it
        _search_throughout_webpage("ACHIEVER_Buy_This_Plan");

    }

    //Scenario: 6 #Verifying Buy This Plan CTA of Champion Plan
    @When("User clicks on Buy This Plan CTA of Champion Plan")
    public void user_clicks_on_buy_this_plan_cta_of_champion_plan() {

        //Scrolling till CHAMPION_Buy_This_Plan CTA is in view and clicking on it
        _search_throughout_webpage("CHAMPION_Buy_This_Plan");

    }

    //Scenario: 12 #Verifying 'Join our community on Facebook' banner on the Dashboard

    @When("User clicks on Facebook banner card")
    public void user_clicks_on_facebook_banner_card() throws InterruptedException {

        //Storing Current Window Handle in Static String Variable 'Parent_Window'
        Parent_Window = driver.getWindowHandle();

        //Waiting for the page to load and navigating to the Book_Free_Trial CTA
        Thread.sleep(1500);
        _search_throughout_webpage("Facebook_Banner");

    }

    //Scenario: 13 #Verifying ‘Our Students love us’ section on the Demo Dashboard

    @When("User clicks on Right Arrow")
    public void user_clicks_on_right_arrow() {

        _Scrolling_throughout_the_WebPage("Next_Button");

        int currntTime = _get_current_time_in_sec();

        while(_is_displayed(valueForTheGivenKey("Next_Button")))
        {
            _click(valueForTheGivenKey("Next_Button"));

            if(currntTime+20 < _get_current_time_in_sec())
                break;

        }

    }

    @Then("Testimonial Cards should Scroll to Left")
    public void testimonial_cards_should_scroll_to_left() {

        Assert.assertFalse(_is_displayed(valueForTheGivenKey("Next_Button")));

    }

    @When("User clicks on Lefy Arrow")
    public void user_clicks_on_lefy_arrow() {

        int currntTime = _get_current_time_in_sec();

        while(_is_displayed(valueForTheGivenKey("Previous_Button")))
        {
            _click(valueForTheGivenKey("Previous_Button"));

            if(currntTime+20 < _get_current_time_in_sec())
                break;

        }

    }

    @Then("Testimonial Cards should Scroll to Right")
    public void testimonial_cards_should_scroll_to_right() {

        Assert.assertFalse(_is_displayed(valueForTheGivenKey("Previous_Button")));

    }


    //Scenario: 14 #Verifying 'Code-O-Fiesta' banner on the Dashboard

    @When("User clicks on Contest Now CTA of Code-O-Fiesta banner")
    public void userClicksOnCodeOFiestaBannerCard() throws InterruptedException {

        //Scrolling till Code-O-Fiesta banner card is in view and clicking on Contest Now CTA
        Thread.sleep(1500);
        _search_throughout_webpage("Contest_Now_CTA");

    }

    @Then("Verify user is redirected to the Code-O-Fiesta WebPage")
    public void verifyUserIsRedirectedToTheCodeOFiestaWebPage() {

        //Waiting to for Code-O-Fiesta WebPage to load
        wait.until(ExpectedConditions.urlContains("book.brightchamps.com"));

        //Asseting Code-O-Fiesta WebPage
        Assert.assertTrue(driver.getCurrentUrl().contains("book.brightchamps.com"));

    }
}
