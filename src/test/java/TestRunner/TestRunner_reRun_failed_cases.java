package TestRunner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;


@RunWith(Cucumber.class)
@CucumberOptions(plugin = {"pretty","html:target/html_failed/testReport.html", "json:target/cucumber-reports_failed/Cucumber.json"}, features = {"@target/Failed_Scenarios.txt"},
        monochrome = false, glue={"StepDefinition"}, dryRun = false, tags = "")


public class TestRunner_reRun_failed_cases {
}
