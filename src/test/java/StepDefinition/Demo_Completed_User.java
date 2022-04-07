package StepDefinition;

import Base.BaseUtil;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;

public class Demo_Completed_User extends BaseUtil {


    //Background Conditions

    @Then("User is at the Home Page of Demo Completed account")
    public void user_is_at_the_home_page_of_demo_completed_account() {

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


    }

    @Then("User should be redirected to Payment Gateway")
    public void user_should_be_redirected_to_payment_gateway() {


    }

    //Scenario: 5 #Verifying Buy This Plan CTA of Achiever Plan
    @When("User clicks on Buy This Plan CTA of Achiever Plan")
    public void user_clicks_on_buy_this_plan_cta_of_achiever_plan() {


    }

    //Scenario: 6 #Verifying Buy This Plan CTA of Champion Plan
    @When("User clicks on Buy This Plan CTA of Champion Plan")
    public void user_clicks_on_buy_this_plan_cta_of_champion_plan() {


    }

    //Scenario: 12 #Verifying 'Join our community on Facebook' banner on the Dashboard

    @When("User clicks on Facebook banner card")
    public void user_clicks_on_facebook_banner_card() {


    }

    //Scenario: 13 #Verifying ‘Our Students love us’ section on the Demo Dashboard

    @When("User clicks on Right Arrow")
    public void user_clicks_on_right_arrow() {


    }

    @Then("Testimonial Cards should Scroll to Left")
    public void testimonial_cards_should_scroll_to_left() {


    }

    @When("User clicks on Lefy Arrow")
    public void user_clicks_on_lefy_arrow() {


    }

    @Then("Testimonial Cards should Scroll to Right")
    public void testimonial_cards_should_scroll_to_right() {


    }

    //Scenario: 14 #Verifying Unlocked Certificate

    @When("User Scrolls down till Certificate section")
    public void user_scrolls_down_till_certificate_section() {


    }

    @Then("Verify Certificate Should be unlocked")
    public void verify_certificate_should_be_unlocked() {


    }


    //Scenario: 15 #Verifying Unlocked Certificate can be downloaded

    @Then("Verify Certificate Should be able to Download")
    public void verify_certificate_should_be_able_to_download() {


    }

}
