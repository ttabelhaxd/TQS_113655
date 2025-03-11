package tqs;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import java.util.*;

public class Lab4_2test {
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

  @Test
  public void untitled() {
    // Test name: Untitled
    // Step # | name | target | value
    // 1 | open | / | 
    driver.get("https://blazedemo.com/");
    // 2 | setWindowSize | 1850x1053 | 
    driver.manage().window().setSize(new Dimension(1850, 1053));
    // 3 | click | css=.btn-primary | 
    driver.findElement(By.cssSelector(".btn-primary")).click();
    // 4 | click | css=tr:nth-child(2) .btn | 
    driver.findElement(By.cssSelector("tr:nth-child(2) .btn")).click();
    // 5 | click | id=inputName | 
    driver.findElement(By.id("inputName")).click();
    // 6 | type | id=inputName | abel
    driver.findElement(By.id("inputName")).sendKeys("abel");
    // 7 | click | id=address | 
    driver.findElement(By.id("address")).click();
    // 8 | type | id=address | rua
    driver.findElement(By.id("address")).sendKeys("rua");
    // 9 | click | css=body | 
    driver.findElement(By.cssSelector("body")).click();
    // 10 | click | id=city | 
    driver.findElement(By.id("city")).click();
    // 11 | type | id=city | aveiro
    driver.findElement(By.id("city")).sendKeys("aveiro");
    // 12 | click | id=state | 
    driver.findElement(By.id("state")).click();
    // 13 | type | id=state | aveiro
    driver.findElement(By.id("state")).sendKeys("aveiro");
    // 14 | click | css=body | 
    driver.findElement(By.cssSelector("body")).click();
    // 15 | click | id=zipCode | 
    driver.findElement(By.id("zipCode")).click();
    // 16 | type | id=zipCode | 12345678
    driver.findElement(By.id("zipCode")).sendKeys("12345678");
    // 17 | click | id=creditCardNumber | 
    driver.findElement(By.id("creditCardNumber")).click();
    // 18 | type | id=creditCardNumber | 123456789
    driver.findElement(By.id("creditCardNumber")).sendKeys("123456789");
    // 19 | click | id=nameOnCard | 
    driver.findElement(By.id("nameOnCard")).click();
    // 20 | type | id=nameOnCard | john doe
    driver.findElement(By.id("nameOnCard")).sendKeys("john doe");
    // 21 | click | css=body | 
    driver.findElement(By.cssSelector("body")).click();
    // 22 | click | css=.checkbox | 
    driver.findElement(By.cssSelector(".checkbox")).click();
    // 23 | click | css=.btn-primary | 
    driver.findElement(By.cssSelector(".btn-primary")).click();
  }
}