package pages;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.BaseFactory;

import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class LoginPage {
	
	WebDriver driver;
	WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//input[@id='user-name']")
	WebElement usernameInput;

	@FindBy(xpath = "//input[@id='password']")
	WebElement passwordInput;

	@FindBy(xpath = "//input[@id='login-button']")
	WebElement loginButton;

	@FindBy(xpath = "//h3[@data-test='error']")
	WebElement errorHeader;

	public void enterCredentials(String username, String password) {
		usernameInput.sendKeys(username);
		passwordInput.sendKeys(password);

	}

	public void appLogin() {
		loginButton.click();
	}

	public String getTitle() throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		File dest = new File(System.getProperty("user.dir") + File.separator + "Screenshots" + File.separator
				+ System.currentTimeMillis() + ".png");
		FileUtils.copyFile(src, dest);
		return driver.getTitle();
	}

	public String getErrorMessage() {
	 wait.until(ExpectedConditions.visibilityOf(errorHeader));
     return errorHeader.getText();
	}
}
