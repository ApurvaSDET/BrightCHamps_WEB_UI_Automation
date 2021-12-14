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


}
