package tqs;

import io.cucumber.java.ParameterType;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SearchBooksSteps {
    private Library library = new Library();
    private List<Book> result;

    @ParameterType("\\d{4}-\\d{2}-\\d{2}")
    public Date iso8601Date(String date) throws ParseException {
        return new SimpleDateFormat("yyyy-MM-dd").parse(date);
    }

    @Given("a book with the title {string}, written by {string}, published in {iso8601Date}")
    public void a_book_with_the_title_written_by_published_in(String title, String author, Date publishedDate) {
        Book book = new Book(title, author, publishedDate);
        library.addBook(book);
    }

    @Given("another book with the title {string}, written by {string}, published in {iso8601Date}")
    public void another_book_with_the_title_written_by_published_in(String title, String author, Date publishedDate) {
        Book book = new Book(title, author, publishedDate);
        library.addBook(book);
    }

    @Given("the following books")
    public void the_following_books(io.cucumber.datatable.DataTable dataTable) throws ParseException {
        List<Map<String, String>> books = dataTable.asMaps(String.class, String.class);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        for (Map<String, String> book : books) {
            library.addBook(new Book(book.get("title"), book.get("author"), formatter.parse(book.get("published"))));
        }
    }

    @When("the customer searches for books published between {iso8601Date} and {iso8601Date}")
    public void the_customer_searches_for_books_published_between_and(Date startDate, Date endDate) {
        result = library.findBooks(startDate, endDate);
    }

    @When("the customer searches for books by author {string}")
    public void the_customer_searches_for_books_by_author(String author) {
        result = library.findBooksByAuthor(author);
    }

    @Then("{int} books should have been found")
    public void books_should_have_been_found(int expectedCount) {
        assertEquals(expectedCount, result.size());
    }

    @Then("Book {int} should have the title {string}")
    public void book_should_have_the_title(int index, String title) {
        assertEquals(title, result.get(index - 1).getTitle());
    }

    @Then("Book {int} should have the author {string}")
    public void book_should_have_the_author(int index, String author) {
        assertEquals(author, result.get(index - 1).getAuthor());
    }
}
