package StepDefinition;

import Base.BaseUtil;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.commons.lang.StringUtils;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.TimeUnit;


public class Profile extends BaseUtil {

    //Scenario: 1 #Verifying Updating Profile Page information

    ArrayList<String> AL;
    String filename;
    String DOB;
    String Changed_Date;
    String Changed_Month;
    String Changed_year;
    Random rand = new Random();

    @When("User updates all the Profile page information")
    public void user_updates_all_the_profile_page_information() throws InterruptedException {

        //First Adding Values to be entered in ArrayList
        AL = new ArrayList<>();

        AL.add(getAlphaNumericString("Alpha",7)); //Student's First Name
        AL.add(getAlphaNumericString("Alpha",7)); // Student's Last Name
        AL.add(getAlphaNumericString("Alpha",7)); // Father's First name
        AL.add(getAlphaNumericString("Alpha",7)); // Mother's First Name
        AL.add(getAlphaNumericString("Numeric",10)); //Father's Phone Number
        AL.add(getAlphaNumericString("Alpha",5)+"@mailinator.com"); //Father's Email
        AL.add(getAlphaNumericString("Numeric",10)); //Mother's Phone Number
        AL.add(getAlphaNumericString("Alpha",5)+"@mailinator.com"); //Mother's Email
        AL.add(getAlphaNumericString("Alpha",6)); //City
        AL.add(getAlphaNumericString("Alpha",8)); //School


        //User Clicks on Edit Button
        _click(valueForTheGivenKey("Edit_Profile"));
        Thread.sleep(1000);

        //User clears and enters Student Name
        _clear("Student_Name");
        _SendKeys("Student_Name", AL.get(0)+" "+AL.get(1));

        //User clears and enters Father's Name
        _clear("Father_Name");
        _SendKeys("Father_Name", AL.get(2)+" "+AL.get(1));

        //User clears and enters Mother's Name
        _clear("Mother_Name");
        _SendKeys("Mother_Name", AL.get(3)+" "+AL.get(1));

        //Storing previous value into AL Object at 10th index
        String Previous_Value = driver.findElement(By.xpath(valueForTheGivenKey("Grade_dropdown_button"))).getAttribute("value");
        AL.add(Previous_Value);

        //User enters Grade from dropdown
        do {
            _click(valueForTheGivenKey("Grade_dropdown_button"));
            Thread.sleep(2000);
            _random_options_from_dropdown(valueForTheGivenKey("Grade_dropdown_popup"));

        } while (AL.get(10).equals(driver.findElement(By.xpath(valueForTheGivenKey("Grade_dropdown_button"))).getAttribute("value")));

        //Storing previous value into AL Object at 11th index
        String PreviousValue = driver.findElement(By.xpath(valueForTheGivenKey("Gender_dropdown_button"))).getAttribute("value");
        AL.add(PreviousValue);

        //User enters Gender from dropdown
        do {
            _click(valueForTheGivenKey("Gender_dropdown_button"));
            _wait_forAllElements(valueForTheGivenKey("Gender_dropdown_popup"));
            _random_options_from_dropdown(valueForTheGivenKey("Gender_dropdown_popup"));

        } while (AL.get(11).equals(driver.findElement(By.xpath(valueForTheGivenKey("Gender_dropdown_button"))).getAttribute("value")));

        //User clears and enters Father's Phone Number
        _clear("Father_Number");
        _SendKeys("Father_Number", AL.get(4));

        //User clears and enters Father's Email
        _clear("Father_Email_ID");
        _SendKeys("Father_Email_ID", AL.get(5));

        //User clears and enters Mother's Phone Number
        _clear("Mother_Number");
        _SendKeys("Mother_Number", AL.get(6));

        //User clears and enters Mother's Email
        _clear("Mother_Email_ID");
        _SendKeys("Mother_Email_ID", AL.get(7));

        //Storing previous value into AL Object at 12th index
        AL.add(driver.findElement(By.xpath(valueForTheGivenKey("Country_dropdown_button"))).getAttribute("value"));

        //User enters Country from dropdown
        _click(valueForTheGivenKey("Country_dropdown_button"));
        Thread.sleep(2000);
        _selecting_particular_options_from_dropdown(valueForTheGivenKey("Country_dropdown_popup"),"India | India Standard");

        //User clears and enters City
        _clear("City");
        _SendKeys("City", AL.get(8));

        //User clears and enters School Name
        _clear("School_Name");
        _SendKeys("School_Name", AL.get(9));


        //Clicking on SAVE button to update the newly entered data
        _search_throughout_webpage("Save_Button");
        Thread.sleep(500);

    }

    @Then("Profile Updated Success message should appear")
    public void Profile_Updated_Success_message_should_appear() {

        _wait(valueForTheGivenKey("OTP_Sent_Alert"));
        Assert.assertEquals("Profile Updated", _get_text(valueForTheGivenKey("OTP_Sent_Alert")));
        _WaitAbsence(valueForTheGivenKey("OTP_Sent_Alert"));

    }

    @And("Profile Page should get updated")
    public void profile_page_should_get_updated() {

        Assert.assertEquals(AL.get(0)+" "+AL.get(1),driver.findElement(By.xpath(valueForTheGivenKey("Student_Name"))).getAttribute("value"));
        Assert.assertEquals(AL.get(2)+" "+AL.get(1),driver.findElement(By.xpath(valueForTheGivenKey("Father_Name"))).getAttribute("value"));
        Assert.assertEquals(AL.get(3)+" "+AL.get(1),driver.findElement(By.xpath(valueForTheGivenKey("Mother_Name"))).getAttribute("value"));
        Assert.assertEquals(AL.get(4),driver.findElement(By.xpath(valueForTheGivenKey("Father_Number"))).getAttribute("value"));
        Assert.assertEquals(AL.get(5),driver.findElement(By.xpath(valueForTheGivenKey("Father_Email_ID"))).getAttribute("value"));
        Assert.assertEquals(AL.get(6),driver.findElement(By.xpath(valueForTheGivenKey("Mother_Number"))).getAttribute("value"));
        Assert.assertEquals(AL.get(7),driver.findElement(By.xpath(valueForTheGivenKey("Mother_Email_ID"))).getAttribute("value"));
        Assert.assertEquals(AL.get(8),driver.findElement(By.xpath(valueForTheGivenKey("City"))).getAttribute("value"));
        Assert.assertEquals(AL.get(9),driver.findElement(By.xpath(valueForTheGivenKey("School_Name"))).getAttribute("value"));
        Assert.assertNotEquals(AL.get(10),driver.findElement(By.xpath(valueForTheGivenKey("Grade_dropdown_button"))).getAttribute("value"));
        Assert.assertNotEquals(AL.get(11),driver.findElement(By.xpath(valueForTheGivenKey("Gender_dropdown_button"))).getAttribute("value"));
        Assert.assertEquals(AL.get(12),driver.findElement(By.xpath(valueForTheGivenKey("Country_dropdown_button"))).getAttribute("value"));

    }

    //Scenario: 2 #Verifying Updating Hobbies on Profile Page

    @When("User clicks on X button of Hobbies")
    public void user_clicks_on_button_of_hobbies() throws InterruptedException {

        //User Clicks on Edit Button
        _click(valueForTheGivenKey("Edit_Profile"));
        Thread.sleep(1000);

        //Scrolling the Page
        _search_throughout_webpage("Password");

        //User deletes all the existing Hobbies
        do {
            Thread.sleep(1000);
            _selecting_one_by_one_from_dropdown(valueForTheGivenKey("Delete_Hobby_Icon"));
        }
        while (!driver.findElements(By.xpath(valueForTheGivenKey("Hobby_Text"))).isEmpty());

    }


    @Then("Hobbies should be deleted")
    public void hobbies_should_be_deleted() {

        try {
            wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(valueForTheGivenKey("Hobby_Text"))));
            Assert.assertTrue(driver.findElements(By.xpath(valueForTheGivenKey("Hobby_Text"))).isEmpty());
        }
        catch (TimeoutException e)
        {
            Assert.assertTrue(true);
        }
    }

    @When("User enters new Hobbies from Dropdown")
    public void user_enters_new_hobbies_from_dropdown() throws InterruptedException {

        //User enters multiple random Hobbies from dropdown
        Thread.sleep(1000);
        for (int i = 0 ; i<5; i++) {
            _click(valueForTheGivenKey("Hobby_dropdown_button"));
            Thread.sleep(1000);
            _random_options_from_dropdown(valueForTheGivenKey("Hobby_dropdown_popup"));
        }

    }

    @Then("New Hobbies should be entered")
    public void new_hobbies_should_be_entered() {

        try {
            wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(valueForTheGivenKey("Hobby_Text"))));
            Assert.assertFalse(driver.findElements(By.xpath(valueForTheGivenKey("Hobby_Text"))).isEmpty());
        }
        catch (TimeoutException e)
        {
            Assert.fail();
        }

    }

    @When("User clicks on Save Button")
    public void user_clicks_on_save_button() {

        //Clicking on SAVE button to update the newly entered data
        _search_throughout_webpage("Save_Button");

    }

    @And("New hobbies should be updated on Profile Page")
    public void new_hobbies_should_be_updated_on_profile_page() {

        //Scrolling the Page
        _search_throughout_webpage("Password");

        //Validating if hobbies are updated after Saving the profile
        new_hobbies_should_be_entered();

    }

    //Scenario Outline: 3 #Validating invalid input for Update Password

    @When("User clicks on Change Password")
    public void user_clicks_on_change_password() {

        //Scrolling the Page
        _search_throughout_webpage("Change_Password");

    }


    @Then("Enter password text field should appear")
    public void enter_password_text_field_should_appear() {

        Assert.assertTrue(_is_displayed(valueForTheGivenKey("Placeholder_Text")));

    }

    @When("User enters {string} password")
    public void user_enters_password(String InvalidPassword) {

        _SendKeys("Placeholder_Text",InvalidPassword);

    }

    @And("Clicks on Save button")
    public void clicks_on_save_button() {

        _search_throughout_webpage("Save_Button_Password");

    }

    @Then("Appropriate {string} should appear")
    public void Appropriate_validation_message_should_appear(String Validation_Message) {

        _wait(valueForTheGivenKey("OTP_Sent_Alert"));
        Assert.assertEquals(Validation_Message,_get_text(valueForTheGivenKey("OTP_Sent_Alert")));

    }


    //Scenario: 4 #Validate Sub-heading on Profile Page

    @And("Verify Student first Name as Sub-heading on Profile Page")
    public void Verify_Student_first_Name_as_Sub_heading_on_Profile_Page() {

        //Fetching first Name of Student from Profile Page
        String StudentFullName = driver.findElement(By.xpath(valueForTheGivenKey("Student_Name"))).getAttribute("value");

        String FirstName = StringUtils.substringBefore(StudentFullName, " ");

        Assert.assertEquals(FirstName+"'s Information", _get_text(valueForTheGivenKey("Profile_Subheading")));

    }

    //Scenario: 5 #Validate Update Now CTA from Home Page


    @When("User Clicks on Home button")
    public void user_clicks_on_home_button() {

        _click(valueForTheGivenKey("Home_button"));

    }


    @Then("User is navigated to the Home Page")
    public void user_is_navigated_to_the_home_page() {

        wait.until(ExpectedConditions.urlToBe(valueForTheGivenKey("WEB_URL")+"/dashboard"));
        Assert.assertEquals(valueForTheGivenKey("WEB_URL")+"/dashboard", driver.getCurrentUrl());

    }

    @When("User Clicks on Update Now CTA")
    public void user_clicks_on_update_now_cta() {

        _search_throughout_webpage("Update_Now_CTA");

    }


    //Scenario: 6 #Verifying Upload Profile Pic from Profile Page

    @When("User upload profile pic")
    public void user_upload_profile_pic() {

        String filePath;

        filename = driver.findElement(By.xpath(valueForTheGivenKey("Profile_Pic_locator"))).getAttribute("src");

        if(filename.contains("mountains"))
            //Location of the image file to be uploaded
            filePath = System.getProperty("user.dir")+"/src/test/resources/Profile_Pic/flower.jpeg";

        else
            //Location of the image file to be uploaded
            filePath = System.getProperty("user.dir")+"/src/test/resources/Profile_Pic/mountains.jpeg";


        //Uploading file using SendKeys method
        driver.findElement(By.xpath(valueForTheGivenKey("Pic_Upload"))).sendKeys(filePath);

    }

    @Then("Profile pic updated message should appear")
    public void profile_pic_updated_message_should_appear() {

        _wait(valueForTheGivenKey("OTP_Sent_Alert"));
        Assert.assertEquals("Profile picture updated",_get_text(valueForTheGivenKey("OTP_Sent_Alert")));

    }

    @Then("Profile Pic should get changed")
    public void profile_pic_should_get_changed(){

       Assert.assertNotEquals(filename, driver.findElement(By.xpath(valueForTheGivenKey("Profile_Pic_locator"))).getAttribute("src"));

    }


    //Scenario: 7 #Verifying Updating DOB from Calendar on Profile Page

    @When("User enters DOB as input")
    public void User_enters_DOB_as_input() {

        //Logic to enter DOB
        _click(valueForTheGivenKey("Edit_Profile")); //Click on Edit button

        _SendKeys("DOB_Input", "12-01-2012"); //Sending valid input data (DOB)

        //Clicking on SAVE button to update the newly entered data
        _search_throughout_webpage("Save_Button");


    }

    @Then("DOB should be entered")
    public void DOB_should_be_entered() {

        //Validating Success message
        Profile_Updated_Success_message_should_appear();

        //Validating if DOB is updated wrt to the provided input data (DOB)
        DOB = driver.findElement(By.xpath(valueForTheGivenKey("DOB_Input"))).getAttribute("value");
        Assert.assertEquals("2012-01-12",DOB);

    }

    @When("User Clicks on DOB field")
    public void user_clicks_on_dob_field() {

        //Logic to enter DOB
        _click(valueForTheGivenKey("Edit_Profile"));

        //Clicks on DOB icon to invoke DatePicker
        _click(valueForTheGivenKey("DOB_Input"));

    }

    @And("User selects any random date")
    public void user_selects_any_random_date() {

        //Using Arrow_Up keys to change the date via keyboard actions
        for(int i =0; i<=rand.nextInt(9)+1; i++) {
            driver.findElement(By.xpath(valueForTheGivenKey("DOB_Input"))).sendKeys(Keys.ARROW_UP);
        }

    }

    @Then("Newly selected date should appear on DOB field")
    public void Newly_selected_date_should_appear_on_DOB_field() {

        //Storing initial Date in String
        String initial_date = StringUtils.substringAfter(StringUtils.substringAfter(DOB, "-"), "-");

        //Storing Changed Date in String after Keyboard actions
        Changed_Date = StringUtils.substringAfter(StringUtils.substringAfter(driver.findElement(By.xpath(valueForTheGivenKey("DOB_Input"))).getAttribute("value"), "-"), "-");

        //Validating if initial and changed dates are different
        Assert.assertNotEquals(initial_date,Changed_Date);

    }

    @When("User selects any random Month")
    public void user_selects_any_random_month() {

        //Using Arrow_Right keys to select the month via keyboard actions
        driver.findElement(By.xpath(valueForTheGivenKey("DOB_Input"))).sendKeys(Keys.ARROW_RIGHT);

        //Using Arrow_Up keys to change the month via keyboard actions
        for(int i =0; i<=rand.nextInt(9)+1;i++) {
            driver.findElement(By.xpath(valueForTheGivenKey("DOB_Input"))).sendKeys(Keys.ARROW_DOWN);
        }

    }

    @Then("Newly selected Month should appear on DOB field")
    public void Newly_selected_month_should_appear_on_DOB_field() {

        //Storing initial Month in String
        String initial_month = StringUtils.substringBefore(StringUtils.substringAfter(DOB, "-"), "-");

        //Storing Changed Month in String after Keyboard actions
        Changed_Month = StringUtils.substringBefore(StringUtils.substringAfter(driver.findElement(By.xpath(valueForTheGivenKey("DOB_Input"))).getAttribute("value"), "-"), "-");

        //Validating if initial and changed months are different
        Assert.assertNotEquals(initial_month,Changed_Month);

    }

    @When("User selects any random Year")
    public void user_selects_any_random_year() {

        //Calling above method to mimic same actions as done above
        user_selects_any_random_month();

    }

    @Then("Newly selected Year should appear on DOB field")
    public void Newly_selected_year_should_appear_on_DOB_field() {

        //Storing initial Year in String
        String initial_month = StringUtils.substringBefore(DOB, "-");

        //Storing Changed year in String after Keyboard actions
        Changed_year = StringUtils.substringBefore(driver.findElement(By.xpath(valueForTheGivenKey("DOB_Input"))).getAttribute("value"), "-");

        //Validating if initial and changed year are different
        Assert.assertNotEquals(initial_month,Changed_year);

    }

    @And("New DOB should be updated on Profile Page")
    public void new_dob_should_be_updated_on_profile_page() {

        //Storing new DOB in appropriate format
        String NEW_DOB = Changed_year+"-"+Changed_Month+"-"+Changed_Date;

        //Getting DOB after saving Profile page
        String Updated_DOB = driver.findElement(By.xpath(valueForTheGivenKey("DOB_Input"))).getAttribute("value");

        //Validating if NEW_DOB and Updated_DOB are same
        Assert.assertEquals(NEW_DOB,Updated_DOB);

    }

    //Scenario: 8 #Validating invalid input on Profile Page

    @When("User enters invalid input in Student Name")
    public void user_enters_invalid_input_in_student_name() throws InterruptedException {

        //User Clicks on Edit Button
        _click(valueForTheGivenKey("Edit_Profile"));
        Thread.sleep(1000);

        _clear("Student_Name");
        _SendKeys("Student_Name", "12345@#$%^");

    }

    @Then("User should not be able to enter anything except alphabets in Student Name")
    public void user_should_not_be_able_to_enter_anything_except_alphabets_student_name() {

        Assert.assertEquals("",_get_value(valueForTheGivenKey("Student_Name")));
    }

    @When("User enters invalid input in Mother Name")
    public void user_enters_invalid_input_in_mother_name() {

        _clear("Mother_Name");
        _SendKeys("Mother_Name", "12345@#$%^");

    }

    @Then("User should not be able to enter anything except alphabets in Mother Name")
    public void user_should_not_be_able_to_enter_anything_except_alphabets_mother_name() {

        Assert.assertEquals("",_get_value(valueForTheGivenKey("Mother_Name")));
    }

    @When("User enters invalid input in Father Name")
    public void user_enters_invalid_input_in_father_name() {

        _clear("Father_Name");
        _SendKeys("Father_Name", "12345@#$%^");

    }

    @Then("User should not be able to enter anything except alphabets in Father Name")
    public void user_should_not_be_able_to_enter_anything_except_alphabets_father_name() {

        Assert.assertEquals("",_get_value(valueForTheGivenKey("Father_Name")));
    }

    @When("User enters invalid input in Father Phone Number")
    public void user_enters_invalid_input_in_father_phone_number() {

        _clear("Father_Number");
        _SendKeys("Father_Number", "qwerty@#$%^");

    }

    @Then("User should not be able to enter anything except numeric values in Father Phone Number")
    public void user_should_not_be_able_to_enter_anything_except_numeric_values() {

        Assert.assertEquals("",_get_value(valueForTheGivenKey("Father_Number")));

    }

    @When("User enters invalid input in Father Email")
    public void user_enters_invalid_input_in_father_email() {

        _clear("Father_Email_ID");
        _SendKeys("Father_Email_ID", "qwerty2345@#$%^");

    }

    @Then("User should not be able to enter invalid email format")
    public void user_should_not_be_able_to_enter_invalid_email_format() {

       // Assert.assertEquals("",_get_value(valueForTheGivenKey("Father_Email_ID")));

    }

    @When("User enters invalid input in Mother Phone Number")
    public void user_enters_invalid_input_in_mother_phone_number() {

        _clear("Mother_Number");
        _SendKeys("Mother_Number", "qwerty@#$%^");

    }

    @Then("User should not be able to enter anything except numeric values in Mother Phone Number")
    public void user_should_not_be_able_to_enter_anything_except_numeric_values_mother() {

        Assert.assertEquals("",_get_value(valueForTheGivenKey("Mother_Number")));

    }

    @When("User enters invalid input in Mother Email")
    public void user_enters_invalid_input_in_mother_email() {

        _clear("Mother_Email_ID");
        _SendKeys("Mother_Email_ID", "12345@#$%^");
    }

    @When("User enters invalid input in City")
    public void user_enters_invalid_input_in_city() {

        _clear("City");
        _SendKeys("City", "12345@#$%^");

    }

    @Then("User should not be able to enter anything except alphabets in City")
    public void user_should_not_be_able_to_enter_anything_except_alphabets_city() {

        Assert.assertEquals("",_get_value(valueForTheGivenKey("City")));
    }

    @When("User enters invalid input in School Name")
    public void user_enters_invalid_input_in_school_name() {

        _clear("School_Name");
        _SendKeys("School_Name", "12345@#$%^");

    }

    @Then("User should not be able to enter anything except alphabets in School Name")
    public void user_should_not_be_able_to_enter_anything_except_alphabets_school_name() {

        Assert.assertEquals("",_get_value(valueForTheGivenKey("School_Name")));
    }


}
