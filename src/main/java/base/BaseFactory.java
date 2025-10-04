package base;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.cucumber.java.Before;

public class BaseFactory {
    
    public static WebDriver driver;
    public static Properties prop;
    
    @Before
    public void driverInitialization(){
        ChromeOptions options = new ChromeOptions();
         options.addArguments("--incognito");
        //options.addArguments("--headless=new"); // or "--headless" for older Chrome
        //options.addArguments("--no-sandbox");
        //options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-gpu");
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--user-data-dir=/tmp/chrome-user-data-" + System.currentTimeMillis());
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        //return driver;
    }

    @Before
    public void loadProfiles(){
      try {
        prop = new Properties();
        prop.load(new FileInputStream("./profiles/dev.properties"));
      } catch (IOException e) {
        e.printStackTrace();
      }
      
    }

}
