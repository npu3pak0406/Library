package controller;

import dao.BookDAO;
import dao.BookDAOImpl;
import entity.Author;
import entity.Book;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.UserUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;


public class BookController implements CommandController {

    public static Logger LOGGER = LoggerFactory.getLogger(BookController.class);

    private BookDAO bookDAO = new BookDAOImpl();

    private List<Author> authors = new ArrayList<>();
    private List<Book> books = new ArrayList<>();
    private String authorsStr = new String();
    private String name = new String();

    public static final String ADD_BOOK = "ADD_BOOK";
    public static final String REMOVE_BOOK = "REMOVE_BOOK";
    public static final String EDIT_BOOK = "EDIT_BOOK";
    public static final String SHOW_BOOKS = "SHOW_BOOKS";
    public static final String EXIT = "EXIT";

    public BookController() {
    }

    @Override
    public void execute(String command) {
        Scanner scanner = new Scanner(System.in);
        switch (command) {
            case ADD_BOOK:
                Book newBook = new Book();
                bookDAO.save(setBookParam(scanner, newBook));
                LOGGER.info("Book ({}) has been successfully added to library", newBook.toString());
                break;

            case REMOVE_BOOK:
                System.out.println("Enter book's name which you want to remove: ");
                name = scanner.nextLine();
                books = bookDAO.findByName(name);
                if (books.isEmpty()) {
                    LOGGER.info("Library doesn't have book with name " + "\" " + name + "\"");
                } else if (books.size() > 1) {
                    System.out.println("Library has " + books.size() + " books with name \"" + name + "\"" + ", which exactly you want to choose: ");
                    final Integer inputNumber = UserUtil.askUserForNumberInput(scanner, books, books.size());
                    bookDAO.delete(books.get(inputNumber - 1).getBookId());
                    LOGGER.info("Book ({}) has been successfully removed from library", books.get(0).toString());
                } else {
                    bookDAO.delete(books.get(0).getBookId());
                    LOGGER.info("Book ({}) has been successfully removed from library", books.get(0).toString());
                }
                break;
            case EDIT_BOOK:
                System.out.println("Enter book's name which you want to edit: ");
                name = scanner.nextLine();
                books = bookDAO.findByName(name);
                if (books.isEmpty()) {
                    LOGGER.info("Library doesn't have book with name " + "\" " + name + "\"");
                } else if (books.size() > 1) {
                    System.out.println("Library has " + books.size() + " books with name \"" + name + "\"" + ", which exactly you want to choose: ");
                    final Integer inputNumber = UserUtil.askUserForNumberInput(scanner, books, books.size());
                    bookDAO.update(setBookParam(scanner, books.get(inputNumber - 1)));
                    LOGGER.info("Book ({}) has been successfully updated", books.get(0).toString());
                } else {
                    Book editBook = setBookParam(scanner, books.get(0));
                    bookDAO.update(editBook);
                    LOGGER.info("Book ({}) has been successfully updated", books.get(0).toString());
                }
                break;
            case SHOW_BOOKS:
                List<Book> books = bookDAO.findAll();
                System.out.println("List of books:");
                for (Book book : books) {
                    authors = book.getAuthors();
                    authorsStr = "";
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

    private Book setBookParam(Scanner scanner, Book book) {
        System.out.println("Enter book name: ");
        book.setName(scanner.nextLine());

        System.out.println("Enter book's authors (format: FirstName1 LastName1, SecondName2 SecondName2,...):");
        authorsStr = scanner.nextLine();
        authors = new ArrayList<>();
        List<String> auts = Arrays.asList(authorsStr.split(","));
        for (String author : auts) {
            Author newAuthor = new Author();
            List<String> names = Arrays.asList(author.trim().split(" "));
            newAuthor.setFirstName(names.get(0));
            newAuthor.setLastName(names.get(1));
            authors.add(newAuthor);
        }
        book.setAuthors(authors);
        return book;
    }

}
