package cucumberOptions;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/main/resources/features/Playlist.feature", plugin = { "pretty",
		"json:target/jsonReports/cucumber-report.json",
		"html:target/cucumber/cucumber.html" }, glue = { "stepDefinitions" })

public class TestRunner {

}
