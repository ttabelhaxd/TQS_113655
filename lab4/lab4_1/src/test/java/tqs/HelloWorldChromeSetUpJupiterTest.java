package tqs;

import io.github.bonigarcia.seljup.SeleniumJupiter;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;

import static java.lang.invoke.MethodHandles.lookup;
import static org.assertj.core.api.Assertions.assertThat;
import static org.slf4j.LoggerFactory.getLogger;

@ExtendWith(SeleniumJupiter.class)
class HelloWorldChromeSetUpJupiterTest {

    static final Logger log = getLogger(lookup().lookupClass());

    @Test
    void testCalculator(ChromeDriver driver) {
        // Exercise
        String sutUrl = "https://bonigarcia.dev/selenium-webdriver-java/";
        driver.get(sutUrl);
        driver.findElement(By.linkText("Slow calculator")).click();
        String title = driver.getTitle();
        log.debug("The title of {} is {}", sutUrl, title);

        String currentURL = driver.getCurrentUrl();

        // Verify
        assertThat(currentURL).isEqualTo("https://bonigarcia.dev/selenium-webdriver-java/slow-calculator.html");
    }

    @Test
    void test(ChromeDriver driver) {
        // Exercise
        String sutUrl = "https://bonigarcia.dev/selenium-webdriver-java/";
        driver.get(sutUrl);
        String title = driver.getTitle();
        log.debug("The title of {} is {}", sutUrl, title);

        // Verify
        assertThat(title).isEqualTo("Hands-On Selenium WebDriver with Java");
    }
}
