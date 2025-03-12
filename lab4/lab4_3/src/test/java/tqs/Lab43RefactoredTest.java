package tqs;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.Keys;
import java.time.Duration;

public class Lab43RefactoredTest {
    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeEach
    public void setUp() {
        FirefoxOptions options = new FirefoxOptions();
        options.setBinary("/usr/bin/firefox");
        driver = new FirefoxDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void testSearchHarryPotter() {
        driver.get("https://cover-bookstore.onrender.com/");
        driver.manage().window().setSize(new Dimension(1850, 1053));

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[data-testid=search-input]"))).sendKeys("Harry Potter" + Keys.ENTER);

        String bookTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[data-testid=book-title]"))).getText();
        String bookAuthor = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[data-testid=book-author]"))).getText();

        assertEquals("Harry Potter and the Sorcerer's Stone", bookTitle);
        assertEquals("J. K. Rowling", bookAuthor);

        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[data-testid=book-title]"))).click();

        String pageTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[data-testid=book-detail-title]"))).getText();
        assertEquals("Harry Potter and the Sorcerer's Stone", pageTitle);
    }
}