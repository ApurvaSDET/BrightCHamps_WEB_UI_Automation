package StepDefinition;


import Base.BaseUtil;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import java.util.List;
import java.util.Map;

public class StudentTitlePage extends BaseUtil {


    @Given("User is at Student portal")
    public void user_is_at_student_portal() {

        //Navigating to the Home Page of student portal
        driver.get("https://students.brightchamps.com/");
        //waiting for home page to load
        WaitForTitleToBe(valueForTheGivenKey("Student_Title_Page"));
        //Asserting the Student Home Page
        Assert.assertEquals(valueForTheGivenKey("Student_Title_Page"), driver.getTitle());
    }

    @When("User clicks on 'Login with Password' CTA")
    public void user_clicks_on_login_with_password_cta() {

        _click(valueForTheGivenKey("Login_with_Password_CTA"));

    }

    @Then("User is at 'Login with Password' screen")
    public void user_is_at_login_with_password_screen() {

        _wait(valueForTheGivenKey("Sign_in_CTA"));
        Assert.assertTrue(_is_displayed(valueForTheGivenKey("Sign_in_CTA")));


    }
    @When("User enters valid email and password")
    public void user_enters_valid_email_and_password(DataTable dataTable) {

        //getting values from Datatable as a Key: Value pair in feature file
        List<Map<String,String>> data = dataTable.asMaps(String.class, String.class);

        driver.findElement(By.xpath(valueForTheGivenKey("Email_field"))).sendKeys(data.get(0).get("Email"));
        driver.findElement(By.xpath(valueForTheGivenKey("Password_field"))).sendKeys(data.get(0).get("Password"));
    }

    @And("User clicks on Sign-in CTA")
    public void Sign_in_CTA_click() {

        _click(valueForTheGivenKey("Sign_in_CTA"));
    }

    @Then("User is redirected to the Home Page")
    public void user_is_redirected_to_the_home_page() {

        //waiting for home page to load
        _wait(valueForTheGivenKey("Account_Select_Page"));
        //Asserting the Student Home Page
        Assert.assertTrue(_is_displayed(valueForTheGivenKey("Account_Select_Page")));

    }
}
