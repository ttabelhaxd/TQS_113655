package tqs;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
public class Library {
    private List<Book> store = new ArrayList<>();

    public Library() {
    }

    public List<Book> findBooksByAuthor(String name) {

    }

    public void addBook(Book book) {

    }

    public List<Book> findBooks(LocalDateTime from, LocalDateTime to) {

    }
}
