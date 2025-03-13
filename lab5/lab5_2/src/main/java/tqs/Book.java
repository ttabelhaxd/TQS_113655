package tqs;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class Book {
    private final String title;
    private final String author;
    private final LocalDateTime published;

    public Book(String title, String author, LocalDateTime published) {
        this.title = title;
        this.author = author;
        this.published = published;
    }
}