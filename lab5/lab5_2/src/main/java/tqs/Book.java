package tqs;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;


@Getter
@Setter
@ToString
public class Book {
    private final String title;
    private final String author;
    private final Date published;

    public Book(String title, String author, Date published) {
        this.title = title;
        this.author = author;
        this.published = published;
    }
}