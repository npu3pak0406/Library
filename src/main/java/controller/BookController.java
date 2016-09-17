package controller;

import dao.BookDAO;
import dao.BookDAOImpl;
import entity.Author;
import entity.Book;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class BookController implements CommandController {

    public static Logger LOGGER = LoggerFactory.getLogger(BookController.class);

    private BookDAO bookDAO = new BookDAOImpl();
    private Book newBook;
    private List<Author> authors = new ArrayList<>();
    private List<Book> books = new ArrayList<>();
    private String authorsStr = new String();

    public static final String ADD_BOOK = "ADD BOOK";
    public static final String REMOVE_BOOK = "REMOVE BOOK";
    public static final String EDIT_BOOK = "EDIT BOOK";
    public static final String SHOW_BOOKS = "SHOW_BOOKS";
    public static final String EXIT = "EXIT";

    public BookController() {
    }

    @Override
    public void execute(String command) {
        Scanner scanner = new Scanner(System.in);
            switch (command) {
                case ADD_BOOK:
                    System.out.println("Enter book name: ");
                    newBook = new Book();
                    newBook.setName(scanner.nextLine());

                    System.out.println("Enter book's authors (format: FirstName1 LastName1, SecondName2 SecondName2,...):");
                    authorsStr = scanner.nextLine();
                    List<String> auts = Arrays.asList(authorsStr.split(","));
                    for (String author : auts) {
                        Author newAuthor = new Author();
                        List<String> names = Arrays.asList(author.trim().split(" "));
                        newAuthor.setFirstName(names.get(0));
                        newAuthor.setLastName(names.get(1));
                        authors.add(newAuthor);
                    }
                    newBook.setAuthors(authors);
                    bookDAO.save(newBook);
                    LOGGER.info("Book {} has been successfully added to library", newBook.getName());
                    break;

                case REMOVE_BOOK:
                    System.out.println("Enter book's name which you want to remove: ");
                    String name = scanner.nextLine();
                    books = bookDAO.findByName(name);
                    System.out.println(books);
                    //bookDAO.delete(new Book());
                    break;
                case EDIT_BOOK:
                    bookDAO.update(new Book());
                    break;
                case SHOW_BOOKS:
                    List<Book> books = bookDAO.findAll();
                    System.out.println("List of books:");
                    for (Book book : books) {
                        authors = book.getAuthors();
                        for (Author author : authors) {
                            authorsStr += author.getFirstName() + " " + author.getLastName() + ", ";
                        }
                        System.out.println("\t - " + authorsStr + " " + "\"" + book.getName() + "\"");
                    }

                    break;
                case EXIT:
                    System.exit(1);
                    break;
            }

    }
}
