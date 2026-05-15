package test.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {

    private WebDriver driver;
    private WebDriverWait wait;
    private JavascriptExecutor js;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        this.js = (JavascriptExecutor) driver;
    }

    public void enterSearchText(String text) {
        try {
            // Wait for page to fully load
            Thread.sleep(3000);

            // Try to close any popup first
            try {
                WebElement rejectBtn = driver.findElement(
                    By.xpath("//button[contains(., 'Reject') or contains(., 'Hylkää') or contains(., 'Accept') or contains(., 'Hyväksy')]")
                );
                rejectBtn.click();
                Thread.sleep(1000);
            } catch (Exception e) {
                // No popup found - continue
            }

            // Use JavaScript to type directly
            WebElement searchBox = driver.findElement(By.name("q"));
            js.executeScript("arguments[0].value=arguments[1];", searchBox, text);
            searchBox.sendKeys(Keys.RETURN);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void clickSearch() {
        // search triggered by RETURN key above
    }

    public String getTitle() {
        return driver.getTitle();
    }
}