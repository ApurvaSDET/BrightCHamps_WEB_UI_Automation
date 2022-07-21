package StepDefinition;

import Base.BaseUtil;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.sl.In;
import org.junit.Assert;

import java.io.File;
import java.util.Arrays;
import java.util.Objects;

public class Certificates extends BaseUtil {

    //Scenario: 1 #Verifying Unlocked Certificate

    @When("User Scrolls down till Goodies section")
    public void user_scrolls_down_till_goodies_section() throws InterruptedException {

        //Scrolling till locked certificate is visible on the screen
        Thread.sleep(1500);

        //Scrolling till Download_Certificate_Link is visible on the screen
        _Scrolling_throughout_the_WebPage("View_Certificate_button");

    }

    @And("User Click on View Certificate button")
    public void User_Click_on_View_Certificate_button() {

        //Clicking on View Certificate button
        _click(valueForTheGivenKey("View_Certificate_button"));

    }

    @Then("Verify Certificate Should be enlarged")
    public void verify_certificate_should_be_enlarged() throws InterruptedException {

        //Waiting for the Modal to Open
        _wait(valueForTheGivenKey("Close_Certificate_Modal"));
        Thread.sleep(1500);

        //Validating Unlocked Demo Certificate Title and Download Link
        Assert.assertTrue(_is_displayed(valueForTheGivenKey("Close_Certificate_Modal")));

    }


    @When("User clicks on X button")
    public void userClicksOnXButton() {

        //Clicking on X button
        _click(valueForTheGivenKey("Close_Certificate_Modal"));

    }

    @Then("Certificate Modal should be closed")
    public void certificateModalShouldBeClosed() {

        //Waiting for the Modal to close
        _WaitAbsence(valueForTheGivenKey("Download_Certificate_button"));

        //Validating Modal Closure
        Assert.assertFalse(_is_displayed(valueForTheGivenKey("Download_Certificate_button")));
    }


    //Scenario: 2 #Verifying Unlocked Certificate can be downloaded

    @Then("Verify Certificate Should be able to Download")
    public void verify_certificate_should_be_able_to_download() {

        //Clicking on Download Certificate Link
        _click(valueForTheGivenKey("Download_Certificate_button"));

        //Validating if the certificate was actually downloaded
        Assert.assertTrue(filepresent(System.getProperty("user.dir")+valueForTheGivenKey("Downloaded_File_Path")));

    }

    //Scenario: 3 #Verifying Certificates on the Home Page

    @When("User clicks on paid account")
    public void user_clicks_on_paid_account() {

        _click(valueForTheGivenKey("Account_Select_Page"));

    }

    @Then("Verify Certificate shown on the Dashboard")
    public void verify_certificate_shown_on_the_dashboard() throws InterruptedException {

        //Waiting for Certificate to load
        _wait(valueForTheGivenKey("Certificate_Dashboard"));
        Thread.sleep(1500);

        //Asserting the Certificate Section
        Assert.assertTrue(_is_displayed(valueForTheGivenKey("Certificate_Dashboard")));

    }

    @When("User clicks on Certifcates")
    public void user_clicks_on_certifcates() {

        _click(valueForTheGivenKey("Certificate_Screen_right_arrow"));

    }

    @Then("User should be redirected to Certificates screen")
    public void user_should_be_redirected_to_certificates_screen() {

        _wait(valueForTheGivenKey("Certificate_Screen"));
        Assert.assertTrue(_is_displayed(valueForTheGivenKey("Certificate_Screen")));

    }



    //Scenario: 4 #Verifying Unlocked Certificates on Certificate Screen

    @Given("User is at Certificates Screen")
    public void user_is_at_certificates_screen() throws InterruptedException {

        //Resuing above methods to Navigate to Certificate Screen
        verify_certificate_shown_on_the_dashboard();
        user_clicks_on_certifcates();
        user_should_be_redirected_to_certificates_screen();

    }

    @Then("Verify Unlocked Certifiates should be available under Certificates Earned Section")
    public void verify_unlocked_certifiates_should_be_available_under_certificates_earned_section() {

        //Waiting for all the certificates to load
        _wait_forAllElements(valueForTheGivenKey("Certificate_image"));

        //Asserting if Certificates are downloadable
        Assert.assertTrue(_get_WebElements_size(valueForTheGivenKey("Certificate_image")) >= 2);


    }

    @When("User Click on Certificate image")
    public void userClickOnCertificateImage() {

        _click(valueForTheGivenKey("Certificate_image"));

    }

    @Then("Pop-up Should Open")
    public void popUpShouldOpen() {

        //Waiting for the Modal
        _wait(valueForTheGivenKey("Download_App_Now_CTA"));

        //Validating the Pop-up Modal
        Assert.assertTrue(_is_displayed(valueForTheGivenKey("Download_App_Now_CTA")));

    }

    @When("User Click on Download on APP CTA on Modal")
    public void userClickOnDownloadOnAPPCTAOnModal() {

        _click(valueForTheGivenKey("Download_App_Now_CTA"));

    }

    @Then("User should be redirected to APP Promotion link")
    public void userShouldBeRedirectedToAPPPromotionLink() {

        _wait(valueForTheGivenKey("App_Promotion_Page"));

        Assert.assertTrue(_is_displayed(valueForTheGivenKey("App_Promotion_Page")));

    }

    @Then("Unlocked Certificate can be downloaded")
    public void unlocked_certificate_can_be_downloaded() throws InterruptedException {

        //Clicking on download icons of all the Certificates
        _selecting_one_by_one_from_dropdown(valueForTheGivenKey("Download_icons"));

        //Waiting for all the Certificates to download
        Thread.sleep(3000);

        //Deleting all the files downloaded
        File folder = new File(System.getProperty("user.dir"));
        Arrays.stream(Objects.requireNonNull(folder.listFiles()))
                .filter(f -> f.getName().endsWith(".pdf"))
                .forEach(File::delete);

    }

    //Scenario: 5 #Verifying Locked Certificates on Certificate Screen

    @Then("Verify Locked Certificates should be available Under Upgrade Now Section")
    public void verify_locked_certifcates_should_be_available_under_upgrade_now_section() {

        //Scrolling till locked Certificates Section
        _Scrolling_throughout_the_WebPage("Upgrade_Plan_CTA");

        //Waiting for all the locked Certificates to load
        _wait_forAllElements(valueForTheGivenKey("Lock_Icon_Certificate"));

    }

    @Then("Verify lock icon on all the locked Certificates")
    public void verify_lock_icon_on_all_the_locked_certificates() {

        //Asserting if Certificates are downloadable
        Assert.assertTrue(_get_WebElements_size(valueForTheGivenKey("Lock_Icon_Certificate")) >= 3);

    }

    //Scenario: 6 #Verifying Upgrade Now on Certificate Screen

    @When("User Clicks on Update this Plan CTA")
    public void user_clicks_on_update_this_plan_cta() {

        _search_throughout_webpage("Upgrade_Plan_CTA");

    }

}
