package tqs;

import io.github.bonigarcia.seljup.SeleniumJupiter;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SeleniumJupiter.class)
public class LoginTest {
    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        driver = new FirefoxDriver(new FirefoxOptions());
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }


    @Test
    public void testLogin(WebDriver driver) {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("admin", "password");

        assertTrue(loginPage.isLoginSuccessful());

        driver.quit();
    }
}