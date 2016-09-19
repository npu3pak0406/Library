package entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
public class Book {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer bookId;
    @Column
    private String name;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "booksAuthor", joinColumns = {@JoinColumn(name = "bookId")}, inverseJoinColumns = {
            @JoinColumn(name = "authorId")})
    private List<Author> authors = new ArrayList<>();

    public Book() {
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

    @Override
    public String toString() {
        StringBuilder autorStr = new StringBuilder();
        for (Author author : authors) {
            autorStr.append(author.toString()).append(", ");
        }
        return autorStr.toString() + "\"" + name + "\"";
    }
}
