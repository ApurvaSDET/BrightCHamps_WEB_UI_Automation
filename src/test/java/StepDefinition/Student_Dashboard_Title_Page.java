package StepDefinition;

import Base.BaseUtil;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import java.util.*;
import java.util.List;

public class Student_Dashboard_Title_Page extends BaseUtil {

    public static List<Map<String,String>> data;

    //Scenario: 1 #Verifying login via OTP using Email

    @When("User clicks on Email button")
    public void user_clicks_on_email_button() {

        _click(valueForTheGivenKey("Email"));

    }

    @And("User enters valid Email address")
    public void user_enters_valid_email_address(DataTable dataTable) {

        //getting values from Datatable as a Key: Value pair in feature file
        data = dataTable.asMaps(String.class, String.class);

        driver.findElement(By.xpath(valueForTheGivenKey("Email_field"))).sendKeys(data.get(0).get("Email"));

    }

    @When("User clicks on Login CTA")
    public void user_clicks_on_login_cta() {

    _click(valueForTheGivenKey("Login_CTA"));

    }

    @Then("OTP sent Successfully message should appear")
    public void otp_sent_successfully_message_should_appear() {

        _wait(valueForTheGivenKey("OTP_Sent_Alert"));
        Assert.assertEquals("OTP has been sent to "+data.get(0).get("Email"),_get_text(valueForTheGivenKey("OTP_Sent_Alert")));

        //Waiting for the Alert to get disappear
        _WaitAbsence(valueForTheGivenKey("OTP_Sent_Alert"));

    }

    @And("User lands on Enter Verification Code Screen")
    public void user_lands_on_enter_verification_code_screen() {

        Assert.assertTrue(_is_displayed(valueForTheGivenKey("Verify_OTP")));

    }

    @When("User enters OTP")
    public void user_enters_otp(){

        //Getting current window handleID
        String Parent_Window = driver.getWindowHandle();

        //Opening a new tab in Chrome Window
        openNewTab();

        //Using method to switch driver to newly opened tab
        Switch_to_next_tab(Parent_Window);

        //Navigating to mailinator.com and fetching OTP from their inbox
        driver.get("https://www.mailinator.com/");
        _wait(valueForTheGivenKey("Mailinator_search_box"));
        driver.findElement(By.xpath(valueForTheGivenKey("Mailinator_search_box"))).sendKeys(data.get(0).get("Email"),Keys.ENTER);

        _wait(valueForTheGivenKey("Inbox_mailinator"));
        _click(valueForTheGivenKey("Inbox_mailinator"));

        //Switching to message body iFrame and getting OTP
        driver.switchTo().frame(valueForTheGivenKey("Inbox_frame"));
        _wait(valueForTheGivenKey("OTP_Text"));

        //Storing OTP in ArrayList as a separate text
        String OTP = _get_text(valueForTheGivenKey("OTP_Text"));

        //Creating ArrayList and storing values in it
        ArrayList<String> al = new ArrayList();

        al.add((String.valueOf(OTP.charAt(0))));
        al.add((String.valueOf(OTP.charAt(1))));
        al.add((String.valueOf(OTP.charAt(2))));
        al.add((String.valueOf(OTP.charAt(3))));



        //Switching back to BrightChamps Login via OTP page
        driver.switchTo().window(Parent_Window);

        //Fetching list of WebElements to enter OTP in Separate text box
        List<WebElement> dropdown_menu = driver.findElements(By.xpath(valueForTheGivenKey("OTP_Text_Input")));

        //Using enhanced for loop to enter OTP in separate text box
        int index = 0; //to get iteration count
        for (WebElement ele : dropdown_menu)

        {

            ele.sendKeys(al.get(index));
            index++;

        }

    }

    @And("Clicks on Verify OTP CTA")
    public void clicks_on_verify_otp_cta(){

        _click(valueForTheGivenKey("Verify_OTP"));

    }

    @Then("Authenticated successfully message should appear")
    public void Verification_successful_message_should_appear(){

        _wait(valueForTheGivenKey("OTP_Sent_Alert"));
        Assert.assertEquals("Authenticated successfully",_get_text(valueForTheGivenKey("OTP_Sent_Alert")));

    }


    //Scenario: 2 #Verifying login via OTP using Mobile - Negative Test case

    @When("User clicks on Mobile button")
    public void user_clicks_on_mobile_button() {

        _click(valueForTheGivenKey("Mobile"));

    }

    @And("User enters Invalid Mobile no")
    public void user_enters_invalid_mobile_no(DataTable dataTable) {

        //getting values from Datatable as a Key: Value pair in feature file
        data = dataTable.asMaps(String.class, String.class);

        driver.findElement(By.xpath(valueForTheGivenKey("Mobile_text_field"))).sendKeys(data.get(0).get("Mobile"));

        //Clicking on Login CTA
        user_clicks_on_login_cta();

        //Asserting the error message for incorrect number format
        _wait(valueForTheGivenKey("Error_msg"));
        Assert.assertEquals("You have entered an invalid number",_get_text(valueForTheGivenKey("Error_msg")));

        //Entering valid but unregistered number
        driver.navigate().refresh();
        _wait(valueForTheGivenKey("Mobile_text_field"));
        driver.findElement(By.xpath(valueForTheGivenKey("Mobile_text_field"))).sendKeys(data.get(1).get("Mobile"));


    }

    @Then("Error message should appear")
    public void error_message_should_appear() {

        _wait(valueForTheGivenKey("Error_msg"));
        Assert.assertEquals("Phone number is not registered!",_get_text(valueForTheGivenKey("Error_msg")));

    }

    @When("User enters valid Mobile no")
    public void user_enters_valid_mobile_no(DataTable dataTable) {

        //getting values from Datatable as a Key: Value pair in feature file
        data = dataTable.asMaps(String.class, String.class);
        driver.navigate().refresh();
        _wait(valueForTheGivenKey("Mobile_text_field"));
        driver.findElement(By.xpath(valueForTheGivenKey("Mobile_text_field"))).sendKeys(data.get(0).get("Mobile"));

    }

    @Then("OTP sent Successful message should appear")
    public void otp_sent_successful_message_should_appear() {

        _wait(valueForTheGivenKey("OTP_Sent_Alert"));

        if (_get_text(valueForTheGivenKey("OTP_Sent_Alert")).contains("OTP has been sent to"))
            Assert.assertTrue(true);

        else
            Assert.fail();

        _WaitAbsence(valueForTheGivenKey("OTP_Sent_Alert"));
    }

    @When("User Clicks on Send again link")
    public void user_clicks_on_send_again_link() {

        _click(valueForTheGivenKey("Resend_link"));

    }

    @Then("OTP resend message should appear")
    public void otp_resend_message_should_appear() {

        _wait(valueForTheGivenKey("OTP_Sent_Alert"));

        if (_get_text(valueForTheGivenKey("OTP_Sent_Alert")).contains("OTP has been sent to"))
            Assert.assertTrue(true);

        else
            Assert.fail();


        _WaitAbsence(valueForTheGivenKey("OTP_Sent_Alert"));

    }


    @When("User enters Invalid OTP")
    public void user_enters_invalid_otp() {

        //Fetching list of WebElements to enter OTP in Separate text box
        List<WebElement> dropdown_menu = driver.findElements(By.xpath(valueForTheGivenKey("OTP_Text_Input")));

        //Using enhanced for loop to enter OTP in separate text box
        int index = 1; //to get iteration count
        for (WebElement ele : dropdown_menu)

        {

            ele.sendKeys(String.valueOf(index));
            index++;

        }

    }

    @Then("Incorrect OTP message should appear")
    public void incorrect_otp_message_should_appear() {

        _wait(valueForTheGivenKey("OTP_Sent_Alert"));
        Assert.assertEquals("Incorrect OTP",_get_text(valueForTheGivenKey("OTP_Sent_Alert")));


    }


    //Scenario: 3 #Verifying login via OTP using Email - Negative Test case


    @And("User enters Invalid Email address")
    public void User_enters_Invalid_Email_address(DataTable dataTable) {

        //getting values from Datatable as a Key: Value pair in feature file
        data = dataTable.asMaps(String.class, String.class);

        driver.findElement(By.xpath(valueForTheGivenKey("Email_field"))).sendKeys(data.get(0).get("Email"));

        //Clicking on Login CTA
        user_clicks_on_login_cta();

        //Asserting the error message for incorrect number format
        _wait(valueForTheGivenKey("Error_msg_Email"));
        Assert.assertEquals(valueForTheGivenKey("Email_Error_Message"),_get_text(valueForTheGivenKey("Error_msg_Email")));

        //Entering valid but unregistered number
        driver.navigate().refresh();
        _wait(valueForTheGivenKey("Email"));
        user_clicks_on_email_button();
        driver.findElement(By.xpath(valueForTheGivenKey("Email_field"))).sendKeys(data.get(1).get("Email"));


    }


    @Then("Error message should be shown")
    public void error_message_should_be_shown() {

        _wait(valueForTheGivenKey("Error_msg_Email"));
        Assert.assertEquals(valueForTheGivenKey("Email_Error_Message"),_get_text(valueForTheGivenKey("Error_msg_Email")));

        //Removing older written data
        driver.navigate().refresh();
        _wait(valueForTheGivenKey("Email"));
        user_clicks_on_email_button();

    }


    //Scenario: 4 #Verifying older OTP can’t be used after resending new one


    //Scenario: 5 #Verifying login via Resent OTP using Email


    //Scenario: 6 #Verifying login with E-mail/Passwords - All negative test cases



    @When("User enters {string} and {string} combination")
    public void User_enters_Invalid_email_and_password_combination(String InvalidEmail, String Password) {

        driver.findElement(By.xpath(valueForTheGivenKey("Email_field"))).sendKeys(InvalidEmail);
        driver.findElement(By.xpath(valueForTheGivenKey("Password_field"))).sendKeys(Password);

    }

    @And("User clicks on SIGN IN CTA")
    public void Sign_in_CTA_click() {

        _click(valueForTheGivenKey("Sign_in_CTA"));

    }

    @Then("Proper {string} should appear")
    public void Proper_validation_message_should_appear(String Validation_Message) {

        _wait(valueForTheGivenKey("OTP_Sent_Alert"));
        Assert.assertEquals(Validation_Message,_get_text(valueForTheGivenKey("OTP_Sent_Alert")));

    }


    //Scenario: 7 #Verifying Forgot Password feature

    @When("User clicks on forgot password link")
    public void user_clicks_on_forgot_password_link() {

        _wait(valueForTheGivenKey("Forgot_Password"));
        _click(valueForTheGivenKey("Forgot_Password"));

    }

    @Then("User is redirected to forgot password screen")
    public void user_is_redirected_to_forgot_password_screen() {

        _wait(valueForTheGivenKey("Reset_Password"));

    }

    @And("Click on RESET PASSWORD CTA")
    public void click_on_reset_password_cta() {

        _click(valueForTheGivenKey("Reset_Password"));

    }

    @Then("Success alert should appear")
    public void success_alert_should_appear() {

        _wait(valueForTheGivenKey("OTP_Sent_Alert"));
        Assert.assertEquals("An email has been sent to "+data.get(0).get("Email")+". Please follow the instructions to reset your password.",_get_text(valueForTheGivenKey("OTP_Sent_Alert")));

    }

    @And("User gets redirected to the title page")
    public void User_redirected_to_title_page() {

        //waiting for redirection to the Login page
        _wait(valueForTheGivenKey("Email"));
        //Asserting the Student Home Page
        Assert.assertTrue(_is_displayed(valueForTheGivenKey("Email")));

    }


}
