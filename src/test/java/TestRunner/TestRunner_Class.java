package TestRunner;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;


@RunWith(Cucumber.class)
@CucumberOptions(plugin = {"pretty","html:target/html/testReport.html", "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:","json:target/cucumber-reports/Cucumber.json","rerun:target/Failed_Scenarios.txt"}, features = {"src/test/java/Features/"},
       monochrome = false, glue={"StepDefinition"}, dryRun = false, tags = "@Smoke Test")

public class TestRunner_Class {
//@Regression and not @smt

}