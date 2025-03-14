package tqs;

import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.Keys;
import java.time.Duration;
import static org.junit.jupiter.api.Assertions.*;

public class SearchSteps {
    private WebDriver driver;
    private WebDriverWait wait;

    @Given("I am on the online library homepage")
    public void i_am_on_the_online_library_homepage() {
        FirefoxOptions options = new FirefoxOptions();
        options.setBinary("/usr/bin/firefox");
        driver = new FirefoxDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get("https://cover-bookstore.onrender.com/");
    }

    @When("I search for the book {string}")
    public void i_search_for_the_book(String bookTitle) {
        WebElement searchBox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[data-testid=search-input]")));
        searchBox.sendKeys(bookTitle + Keys.ENTER);
    }

    @Then("I should see the book {string} by {string}")
    public void i_should_see_the_book_by(String expectedTitle, String expectedAuthor) {
        String actualTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[data-testid=book-title]"))).getText();
        String actualAuthor = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[data-testid=book-author]"))).getText();

        assertEquals(expectedTitle, actualTitle);
        assertEquals(expectedAuthor, actualAuthor);
    }

    @When("I click on the book {string}")
    public void i_click_on_the_book(String bookTitle) {
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[data-testid=book-title]"))).click();
    }

    @Then("I should see the book details for {string}")
    public void i_should_see_the_book_details_for(String expectedTitle) {
        String actualTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[data-testid=book-detail-title]"))).getText();
        assertEquals(expectedTitle, actualTitle);
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
