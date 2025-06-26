package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import utils.LocatorConfigReader;

public class PageObject {
	
	protected static WebDriver driver = new ChromeDriver();
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
	
	public void navigateToUrl(String url) {
		WebDriverManager.chromedriver().setup();
		driver.get(url);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
	}
	
	public void enterbyXpath(String xpath, String value) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys(value);
	}
	
	public void enterbyId(String id, String value) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(id))).sendKeys(value);
	}
	
	public void clickByXpath(String xpath) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
	}
	
	public void clickById(String id) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(id))).click();
	}
	
	public void loginWithUsernameAndPassword(String username, String password) {
		enterbyXpath(LocatorConfigReader.get("login-username"), username);
		clickById(LocatorConfigReader.get("login-button"));
		clickByXpath(LocatorConfigReader.get("login-withPasswordLink"));
		enterbyId(LocatorConfigReader.get("login-password"), password);
		clickById(LocatorConfigReader.get("login-button"));
		
	}
	
	public void tearDown() {
		driver.quit();
	}
	
	public static String readJson(Response response, String key) {
		String res = response.asString();
		JsonPath js = new JsonPath(res);
		return js.get(key).toString();
	}

}
