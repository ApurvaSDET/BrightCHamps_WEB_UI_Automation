package StepDefinition;

import Base.BaseUtil;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import java.util.ArrayList;
import java.util.List;

public class Multiple_Paid_Users extends BaseUtil {

    ArrayList<String> List_of_account_Name;
    ArrayList<String> alObject;
    List<WebElement> account_names;

    //Scenario: 2 #Verifying User lands on the same a/c which is selected at Select Your Account Screen

    @When("User clicks on each paid account")
    public void user_clicks_on_each_paid_account() {

        //Creating ArrayList Object to store the A/C names from dashboard screen
        List_of_account_Name = new ArrayList<>();

        //Creating another ArrayList Object to store the A/C names from Account Select Page
        ArrayList<String> al = new ArrayList<>();

        //Fetching no. of accounts on Account Select Page
        List<WebElement> account_names = driver.findElements(By.xpath(valueForTheGivenKey("Account_Names_On_Select_Page_Paid")));

        //Using enhanced for loop to store the a/c names in ArrayList al

        for (WebElement ele : account_names)

        {
            al.add(ele.getText());
        }

        //Using for loop to click on each accounts
        for(int i =0; i<account_names.size(); i++) {

            try {
                driver.findElement(By.xpath("//p[text()='"+al.get(i)+"']/parent::div/div/div")).click();
            } catch (StaleElementReferenceException e) {
                e.getMessage();
            }

            //waiting for Paid User Home Page to load
            _wait(valueForTheGivenKey("Profile_button"));
            DocumentInReadyState();

            //Storing the a/c name from Home Page in another ArrayList
            List_of_account_Name.add(_get_text(valueForTheGivenKey("Account_Name")));

            //Navigating back to the Account Select Page
            driver.navigate().back();

            //Validating the Account Select Page
            Multiple_Demo_Users MD = new Multiple_Demo_Users(); //Creating an object to call method available in Multiple_demo_Users class
            MD.User_is_at_the_Select_Your_Account_Screen();

        }

    }


    @Then("User should be redirected to their selected paid account only")
    public void user_should_be_redirected_to_their_selected_paid_account_only() {

        //Fetching no. of accounts on Account Select Page
        List<WebElement> account_names = driver.findElements(By.xpath(valueForTheGivenKey("Account_Names_On_Select_Page_Paid")));

        //Using enhanced for loop to get the elements
        int count = 0;
        for (WebElement ele : account_names)

        {
            //Validating if the user is redirected to the same a/c which is opened from Account Select Page
            Assert.assertTrue(ele.getText().contains(List_of_account_Name.get(count)));
            count++;
        }


    }


    //Scenario: 3 #Verifying User lands on the same a/c when selected from dropdown on HomePage

    @Then("User lands on Home Page of Paid User")
    public void user_lands_on_home_page() {

        _wait(valueForTheGivenKey("Profile_button"));
        Assert.assertTrue(_is_displayed(valueForTheGivenKey("Profile_button")));

    }

    @When("User selects each account from dropdown on Profile Page")
    public void user_select_each_account_from_dropdown_profile_page() {

        //Creating ArrayList Object to store the A/C names from greeting text on Home Page
        List_of_account_Name = new ArrayList<>();

        //Creating another ArrayList Object to store the A/C names from drop down menu
        alObject = new ArrayList<>();

        //Navigating to the Profile Page
        _wait(valueForTheGivenKey("Profile_button"));

        //Logic to get the names from Profile Page
        _click(valueForTheGivenKey("Profile_button"));


        //Clicking dropdown button to expand a/c users
        _click(valueForTheGivenKey("Account_Dropdown"));

        //Waiting for dropdown menu to appear
        _wait_forAllElements(valueForTheGivenKey("Dropdown_Items"));

        //Storing A/C names from dropdown into List<WebElements>
        account_names = driver.findElements(By.xpath(valueForTheGivenKey("Dropdown_Items")));

        //Using enhanced for loop to store the a/c names in ArrayList alObject

        for (WebElement ele : account_names) {
            alObject.add(ele.getText());
        }

        //Using for loop to click on each account from dropdown menu
        for (int i = 0; i < account_names.size(); i++) {

            try {
                driver.findElement(By.xpath("//ul[@id='menu-list-grow']/li/div/div/p[text()='"+alObject.get(i)+"']")).click();
            } catch (StaleElementReferenceException e) {
                e.getMessage();
            }

            //Wait for the Name element to load up
            _wait(valueForTheGivenKey("Student_Name_On_Profile"));

            //Getting Full Name of Student from Profile Table
            String ProfileName = driver.findElement(By.xpath(valueForTheGivenKey("Student_Name_On_Profile"))).getAttribute("value");

            //Storing the Title_Heading from Home Page in another ArrayList 'List_of_account_Name'
            List_of_account_Name.add(ProfileName);

            //Waiting for DropDown button to load
            _wait(valueForTheGivenKey("Account_Dropdown"));

            //Clicking dropdown button to expand a/c users
            _click(valueForTheGivenKey("Account_Dropdown"));

        }


    }

    @Then("Verify selected account Profile Page should be opened")
    public void verify_selected_account_profile_page_should_be_opened() {

        //Logic for validating if the same a/c got opened which user actually opened
        for(int i = 0; i < account_names.size(); i++) {

            if(List_of_account_Name.get(i).equals(alObject.get(i)))
                Assert.assertTrue(true);
            else
                Assert.fail();
        }

    }

}
