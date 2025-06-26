package hooks;

import org.openqa.selenium.WebDriver;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.Status;
import pages.PageObject;

public class Hooks extends PageObject {
	
//	protected WebDriver driver;
	
	@Before
	public void setup() {
		
	}
	
	@After
	public void tearDown(Scenario scenario) {
		String scenarioName = scenario.getName();
		String scenarioStatus = scenario.getStatus().name();
		
		scenario.log("Status of the " + scenarioName + " is: " + scenarioStatus );
		
		driver.quit();
	}

}
