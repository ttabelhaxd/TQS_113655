package tqs;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.*;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class Library {
    private List<Book> store = new ArrayList<>();

    public List<Book> findBooksByAuthor(String name) {
        return store.stream().filter(book -> book.getAuthor().equals(name)).collect(Collectors.toList());
    }

    public void addBook(Book book) {
        store.add(book);
    }

    public List<Book> findBooks(Date from, Date to) {
        Calendar end = Calendar.getInstance();
        end.setTime(to);
        end.roll(Calendar.YEAR, 1);

        return store.stream().filter(book -> from.before(book.getPublished()) && end.getTime().after(book.getPublished())).sorted(Comparator.comparing(Book::getPublished).reversed()).collect(Collectors.toList());
    }
}
