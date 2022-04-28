package StepDefinition;

import Base.BaseUtil;
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

    @When("User Scrolls down till Certificate section")
    public void user_scrolls_down_till_certificate_section() {

        //Scrolling till Download_Certificate_Link is visible on the screen
        _Scrolling_throughout_the_WebPage("Download_Certificate_Link");

    }

    @Then("Verify Certificate Should be unlocked")
    public void verify_certificate_should_be_unlocked() {

        //Validating Unlocked Demo Certificate Title and Download Link
        Assert.assertTrue(_is_displayed(valueForTheGivenKey("Unlocked_Demo_Certificate")));
        Assert.assertTrue(_is_displayed(valueForTheGivenKey("Download_Certificate_Link")));

    }


    //Scenario: 2 #Verifying Unlocked Certificate can be downloaded

    @Then("Verify Certificate Should be able to Download")
    public void verify_certificate_should_be_able_to_download() {

        //Clicking on Download Certificate Link
        _click(valueForTheGivenKey("Download_Certificate_Link"));

        //Validating if the certificate was actually downloaded
        Assert.assertTrue(filepresent(System.getProperty("user.dir")+valueForTheGivenKey("Downloaded_File_Path")));

    }

    //Scenario: 3 #Verifying Certificates on the Home Page

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
        _wait_forAllElements(valueForTheGivenKey("Download_icons"));

        //Asserting if Certificates are downloadable
        Assert.assertTrue(_get_WebElements_size(valueForTheGivenKey("Download_icons")) >= 2);


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
