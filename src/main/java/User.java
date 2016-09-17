import controller.BookController;
import controller.CommandController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

public class User {

    private CommandController commandController;

    private String[] commands = {
            BookController.ADD_BOOK,
            BookController.EDIT_BOOK,
            BookController.REMOVE_BOOK,
            BookController.SHOW_BOOKS,
            BookController.EXIT
    };

    public User() {
        commandController = new BookController();
    }

    public void run() {

        do {
            Scanner scanner = new Scanner(System.in);
            greeting();
            final int mainMenuSelection = askUserForNumberInput(scanner, "1 - add;\n2 - remove;\n3 - edit;\n4 - show all books;\n5 - exit;", 5);
            String command = commands[mainMenuSelection - 1];
            commandController.execute(command);
        } while (true);
    }

    private static int askUserForNumberInput(Scanner scanner, String prompt, int maxValue) {
        System.out.println(prompt);
        int value = scanner.nextInt();
        while (value < 1 || value > maxValue) {
            System.out.println("invalid menu item, please try again");
            value = scanner.nextInt();
        }
        return value;
    }

    private void greeting() {
        System.out.println("\nWelcome to our Library!");
        System.out.println("In our library you can add, remove or update book also you can see all books.");
        System.out.println("Please select menu item:\n");
    }
}
