package test.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import test.pages.LoginPage;
import test.utils.BaseTest;

public class LoginTest extends BaseTest {

    @Test(priority = 1, description = "Search for Selenium on Google")
    public void testGoogleSearch() {
        LoginPage googlePage = new LoginPage(driver);
        googlePage.enterSearchText("Selenium WebDriver");

        // Wait for results page to load
        try { Thread.sleep(3000); } catch (Exception e) {}

        String title = googlePage.getTitle();
        System.out.println("Page title is: " + title);

        Assert.assertTrue(title.contains("Google"),
            "Title should contain Google. Actual title: " + title);
    }
}