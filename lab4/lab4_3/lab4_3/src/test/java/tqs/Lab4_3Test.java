package tqs;

// Importações do JUnit 5
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import java.util.*;

public class Lab4_3Test {
  private WebDriver driver;
  private Map<String, Object> vars;
  JavascriptExecutor js;

  @BeforeEach
  public void setUp() {
    driver = new FirefoxDriver();
    js = (JavascriptExecutor) driver;
    vars = new HashMap<String, Object>();
  }

  @AfterEach
  public void tearDown() {
    driver.quit();
  }

  public String waitForWindow(int timeout) {
    try {
      Thread.sleep(timeout);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    Set<String> whNow = driver.getWindowHandles();
    Set<String> whThen = (Set<String>) vars.get("window_handles");
    if (whNow.size() > whThen.size()) {
      whNow.removeAll(whThen);
    }
    return whNow.iterator().next();
  }

  @Test
  public void lab43() {
    // Test name: Lab4_3
    // Step # | name | target | value
    // 1 | open | / |
    driver.get("https://cover-bookstore.onrender.com/");
    // 2 | setWindowSize | 1850x1053 |
    driver.manage().window().setSize(new Dimension(1850, 1053));
    // 3 | click | css=.Navbar_searchBarContainer__3UbnF .Navbar_searchBarInput__w8FwI |
    driver.findElement(By.cssSelector(".Navbar_searchBarContainer__3UbnF .Navbar_searchBarInput__w8FwI")).click();
    // 4 | type | css=.Navbar_searchBarContainer__3UbnF .Navbar_searchBarInput__w8FwI | Harry Potter
    driver.findElement(By.cssSelector(".Navbar_searchBarContainer__3UbnF .Navbar_searchBarInput__w8FwI")).sendKeys("Harry Potter");
    // 5 | sendKeys | css=.Navbar_searchBarContainer__3UbnF .Navbar_searchBarInput__w8FwI | ${KEY_ENTER}
    driver.findElement(By.cssSelector(".Navbar_searchBarContainer__3UbnF .Navbar_searchBarInput__w8FwI")).sendKeys(Keys.ENTER);
    // 6 | click | css=.SearchList_bookTitle__1wo4a |
    driver.findElement(By.cssSelector(".SearchList_bookTitle__1wo4a")).click();
    // 7 | click | css=.rippleBtnBorder > .content |
    driver.findElement(By.cssSelector(".rippleBtnBorder > .content")).click();
    // 8 | click | css=.LoginPage_googleLoginBtn__3wLhn |
    vars.put("window_handles", driver.getWindowHandles());
    // 9 | storeWindowHandle | root |
    driver.findElement(By.cssSelector(".LoginPage_googleLoginBtn__3wLhn")).click();
    // 10 | selectWindow | handle=${win1315} |
    vars.put("win1315", waitForWindow(2000));
    // 11 | close |  |
    vars.put("root", driver.getWindowHandle());
    // 12 | selectWindow | handle=${root} |
    driver.switchTo().window(vars.get("win1315").toString());
    // 13 | click | css=.rippleBtn > .content |
    driver.close();
    driver.switchTo().window(vars.get("root").toString());
    driver.findElement(By.cssSelector(".rippleBtn > .content")).click();
  }
}