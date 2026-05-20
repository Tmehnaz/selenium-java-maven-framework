package test.utils;

import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest{
    protected WebDriver driver;
    protected Properties config;

@BeforeClass
public void setUp() throws Exception {
    config = new Properties();
    FileInputStream fis = new FileInputStream("src/test/resources/config.properties");
    config.load(fis);

    String browser = config.getProperty("browser");
    boolean headless = Boolean.parseBoolean(config.getProperty("headless"));

    if (browser.equalsIgnoreCase("chrome")){
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        if(headless) {
    options.addArguments("--headless");
    options.addArguments("--no-sandbox");           // required on Linux CI
    options.addArguments("--disable-dev-shm-usage"); // prevents Chrome crashes on CI
    options.addArguments("--window-size=1920,1080"); // replaces --start-maximized in headless
} else {
    options.addArguments("--start-maximized");
}
options.addArguments("--disable-notifications");
        driver = new ChromeDriver(options);

    }else if(browser.equalsIgnoreCase("firefox")){
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
    }

    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(
         Integer.parseInt(config.getProperty("implicitWait"))
        )
        );
        driver.manage().window().maximize();
        driver.get(config.getProperty("baseUrl"));
}
@AfterClass
public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }


}
