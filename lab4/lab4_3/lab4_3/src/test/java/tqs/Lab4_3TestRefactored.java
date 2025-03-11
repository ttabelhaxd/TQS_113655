package tqs;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.Keys;
import java.time.Duration;

public class Lab4_3TestRefactored {
    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeEach
    public void setUp() {
        driver = new FirefoxDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Wait explícito de 10 segundos
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void lab43() {
        // 1. Abrir o site
        driver.get("https://cover-bookstore.onrender.com/");
        driver.manage().window().setSize(new Dimension(1850, 1053));

        // 2. Procurar por "Harry Potter"
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                        By.cssSelector("[data-testid=search-input]")).sendKeys("Harry Potter" + Keys.ENTER));

        // 3. Verificar se o livro correto aparece nos resultados
        String bookTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("[data-testid=book-title]"))).getText(); // Usando data-testid
        String bookAuthor = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("[data-testid=book-author]"))).getText(); // Usando data-testid

        assertEquals("Harry Potter and the Sorcerer's Stone", bookTitle); // Verifica o título
        assertEquals("J. K. Rowling", bookAuthor); // Verifica o autor

        // 4. Clicar no livro para ver detalhes
        wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector("[data-testid=book-title]"))).click();

        // 5. Verificar se a página de detalhes foi carregada
        String pageTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("[data-testid=book-detail-title]"))).getText();
        assertEquals("Harry Potter and the Sorcerer's Stone", pageTitle);
    }
}