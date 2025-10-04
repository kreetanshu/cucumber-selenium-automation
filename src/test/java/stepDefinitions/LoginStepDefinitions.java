package stepDefinitions;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;

import base.BaseFactory;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;

import pages.LoginPage;
import io.cucumber.java.en.Then;

public class LoginStepDefinitions {

	WebDriver driver;
	Properties prop;
	LoginPage loginPage;
	private final String expectedTitle = "Swag Labs";

	@Before
	public void driverInitialization() {
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--incognito");
		options.addArguments("--disable-gpu");
		options.addArguments("--remote-allow-origins=*");
		options.addArguments("--user-data-dir=/tmp/chrome-user-data-" + System.currentTimeMillis());
		driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		loginPage = new LoginPage(driver);
	}

	@Before
	public void loadProfiles() {
		try {
			prop = new Properties();
			prop.load(new FileInputStream("./profiles/dev.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Given("I am able to view the login page")
	public void i_am_able_to_view_the_login_page() {
		driver.get(prop.getProperty("url"));
	}

	@Given("I have entered valid username and password")
	public void i_have_entered_valid_username_and_password() {
		loginPage.enterCredentials(prop.getProperty("username"), prop.getProperty("password"));

	}

	@When("I click on login button")
	public void i_click_on_login_button() {
		loginPage.appLogin();
	}

	@Then("I am able to login")
	public void i_am_able_to_login() throws IOException {
		Assert.assertEquals(loginPage.getTitle(), expectedTitle);
	}

	@Given("I have entered invalid {string} and {string}")
	public void i_have_entered_invalid_and(String username, String password) {
		loginPage.enterCredentials(username, password);
	}

	@Then("I should see error message {string}")
	public void i_should_see_error_message(String expectedError) {
		String actualError = loginPage.getErrorMessage();
		Assert.assertEquals(actualError, expectedError);
	}

	@After
	public void tearDown() {
		if (driver != null) {
			driver.quit();
		}
	}

}
