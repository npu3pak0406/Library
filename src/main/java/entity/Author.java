package entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ostash on 15.09.2016.
 */
@Entity
public class Author {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer authorId;
    @Column
    private String firstName;
    @Column
    private String lastName;

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "authors", cascade = CascadeType.ALL)
    private List<Book> books = new ArrayList<Book>();

    public Author() {
    }

    public Integer getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Integer authorId) {
        this.authorId = authorId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    @Override
    public String toString() {
        return "Author{" +
                "id=" + authorId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
