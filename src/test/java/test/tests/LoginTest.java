package test.tests;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import test.pages.LoginPage;
import test.utils.BaseTest;
import java.time.Duration;

public class LoginTest extends BaseTest {

    @Test(priority = 1, description = "Search for Selenium on Google")
    public void testGoogleSearch() {
        LoginPage googlePage = new LoginPage(driver);
        googlePage.enterSearchText("Selenium WebDriver");

        // Wait properly for the title to update
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.titleContains("Selenium"));

        String title = driver.getTitle();
        System.out.println("Page title is: " + title);

        Assert.assertTrue(title.contains("Selenium"),
            "Title should contain Selenium. Actual title: " + title);
    }
}
