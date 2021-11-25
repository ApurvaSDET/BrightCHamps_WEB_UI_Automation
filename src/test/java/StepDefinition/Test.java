package StepDefinition;


import Base.BaseUtil;
import io.cucumber.java.en.Then;
import org.junit.Assert;

public class Test extends BaseUtil {


    @Then("User is at Student portal")
    public void user_is_at_student_portal() {

        driver.get("https://students.brightchamps.com/");
        System.out.println(driver.getTitle());
        Assert.assertEquals("Student | BrightChamps", driver.getTitle());
    }
}
