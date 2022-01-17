package StepDefinition;

import Base.BaseUtil;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.util.ArrayList;
import java.util.Set;

public class Completed_Class_Section extends BaseUtil {

    int ElementsCount;

    //Scenario: 1 #Verifying SESSION BOOKLET links on Completed Class Section

    @When("User clicks on SESSION BOOKLET link of all the completed class cards")
    public void user_clicks_on_session_booklet_link_of_all_the_completed_class_cards() {

        //Waiting for Elements to be present
        try {
            wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(valueForTheGivenKey("Session_booklet_link"))));
        }
        catch (TimeoutException e)
        {
            Assert.fail();
        }
        //Using method to click on each link one by one
        _selecting_one_by_one_from_dropdown(valueForTheGivenKey("Session_booklet_link"));

        //Getting size of Session Booklet
        ElementsCount = driver.findElements(By.xpath(valueForTheGivenKey("Session_booklet_link"))).size();

    }

    @Then("Link should get opened in the new tab")
    public void link_should_get_opened_in_the_new_tab() {

        //Storing Window Handle into Set of String
        Set<String> multipleTabs = driver.getWindowHandles();

        //Converting this Set into ArrayList
        ArrayList<String> tab = new ArrayList<>(multipleTabs);

        //Switching to each opened tab and validating the opened link

        for(int i = 1 ; i<=ElementsCount ; i++)
        {
            driver.switchTo().window(tab.get(i));
            Assert.assertTrue(driver.getCurrentUrl().contains("docs.google.com"));
        }


    }

    //Scenario: 2 #Verifying ASSIGNMENT links on Completed Class Section

    @When("User clicks on ASSIGNMENT link of all the completed class cards")
    public void user_clicks_on_assignment_link_of_all_the_completed_class_cards() {

        //Waiting for Elements to be present
        try {
            wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(valueForTheGivenKey("Assignment_link"))));
        }
        catch (TimeoutException e)
        {
            Assert.fail();
        }
        //Using method to click on each link one by one
        _selecting_one_by_one_from_dropdown(valueForTheGivenKey("Assignment_link"));

        //Getting size of Assignment
        ElementsCount = driver.findElements(By.xpath(valueForTheGivenKey("Assignment_link"))).size();

    }

}
