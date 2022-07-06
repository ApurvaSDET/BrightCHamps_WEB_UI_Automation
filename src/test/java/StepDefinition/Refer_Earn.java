package StepDefinition;

import Base.BaseUtil;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.commons.lang.StringUtils;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
import java.util.concurrent.TimeUnit;

public class Refer_Earn extends BaseUtil {

    public static String Referral_URL_from_CTA;
    public static String Referral_URL_from_Paste;
    public static StudentTitlePage STP;

    //Background Steps:

    @When("User Clicks on Refer & Earn tab")
    public void userClicksOnReferEarnTab() {

        //waiting for profile button
        _wait(valueForTheGivenKey("Refer&Earn"));

        //clicking on profile button
        _click(valueForTheGivenKey("Refer&Earn"));
    }


    @Then("User is at the Refer & Earn Screen")
    public void userIsAtTheReferEarnScreen() {

        //waiting for document to be in ready state
        DocumentInReadyState();

        //waiting for Profile page to load
        _wait(valueForTheGivenKey("COPY_LINK_CTA"));

        //Asserting the Profile pag
        Assert.assertTrue(_is_displayed(valueForTheGivenKey("COPY_LINK_CTA")));

    }

    //Scenario: 1 #Verifying referral modal on Home Page of paid user

    @When("User clicks on Book Free Trial CTA of referral card")
    public void user_clicks_on_book_free_trial_cta_of_referral_card() {

        //Making page to stop loading referral webpage and throw exception, since it's loading infinitely
        driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);

        //Storing Current Window Handle in Static String Variable 'Parent_Window'
        Parent_Window = driver.getWindowHandle();

        //Waiting for the page to load and navigating to the Book_Free_Trial CTA
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

        //Creating StudentTitlePage Class Object
        STP = new StudentTitlePage();

        //*************Copying the Copied text on Email field and getting the copied text****************

        //Reusing methods to navigate to Email field and pasting the copied link on Email text input
        STP.Clicks_on_Profile_button();
        STP.user_clicks_logout_button();
        STP.user_is_at_student_portal();
        STP.user_clicks_on_login_with_password_cta();
        STP.user_is_at_login_with_password_screen();

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
        Assert.assertTrue(Referral_URL_from_CTA.contains(StringUtils.substringBefore(Referral_URL_from_Paste, "-link")));

    }

    //Scenario: 2 #Verifying 'Know More' CTA on referral modal

    @When("User clicks on T&C link on Refer & Earn Page")
    public void userClicksOnTCLinkOnReferEarnPage() throws InterruptedException {

        //Storing Current Window Handle in Static String Variable 'Parent_Window'
        Parent_Window = driver.getWindowHandle();

        //Waiting for the page to load and navigating to the Book_Free_Trial CTA
        Thread.sleep(2500);
        _search_throughout_webpage("T&C_Link");
    }

    @Then("User should be navigated to the new policy tab")
    public void User_should_be_navigated_to_the_new_policy_tab() {

        //Switching driver focus to next Window
        Switch_to_next_tab(Parent_Window);

        //Validating if new tab is opened with Policy URL
        Assert.assertTrue(driver.getCurrentUrl().contains("policy.brightchamps.com"));

    }

    //Scenario: 3 #Verifying 'Facebook' link on referral modal

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


    //Scenario: 4 #Verifying 'WhatsApp' link  on referral modal

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

    //Scenario: 5 #Verifying Leaderboard section on referral modal

    @When("User scrolls down till Leaderboard section of referral page")
    public void User_scrolls_down_till_Leaderboard_section_of_referral_page() throws InterruptedException {

        //Waiting for the page to load and navigating to the Book_Free_Trial CTA
        DocumentInReadyState();
        Thread.sleep(2000);
        _search_throughout_webpage("Last_Rank");

    }


    @Then("Verify all the data on Leaderboard section")
    public void verifyAllTheDataOnLeaderboardSection() {

        //Validating if Correct number of Rows and Coloumns appear on Leaderboard
        Assert.assertEquals(4, _get_WebElements_size(valueForTheGivenKey("LeaderBoard_Coloumn_Count")));
        Assert.assertEquals(10, _get_WebElements_size(valueForTheGivenKey("LeaderBoard_Rows_Count")));

    }



}
