package utils;

import controller.BookController;
import controller.CommandController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserUtil {

    private CommandController commandController = new BookController();
    private final static List<String> USER_COMMANDS = new ArrayList<>();

    public UserUtil() {
        fillCommands();
    }

    public void run() {
        System.out.println("Welcome to our Library!");
        System.out.println("In our library you can add, remove or update book also you can see all books.");

        do {
            System.out.println("\nPlease select menu item:\n");
            Scanner scanner = new Scanner(System.in);
            final int mainMenuSelection = askUserForNumberInput(scanner, USER_COMMANDS);
            String command = USER_COMMANDS.get(mainMenuSelection - 1);
            commandController.execute(command);
        } while (true);
    }

    public static int askUserForNumberInput(Scanner scanner, List<?> prompt) {
        for (int i = 0; i < prompt.size(); i++) {
            System.out.println(i + 1 + ". " + prompt.get(i));
        }
        int value = scanner.nextInt();
        while (value < 1 || value > prompt.size()) {
            System.out.println("invalid menu item, please try again");
            value = scanner.nextInt();
        }
        return value;
    }

    private void fillCommands() {
        USER_COMMANDS.add("ADD_BOOK");
        USER_COMMANDS.add("REMOVE_BOOK");
        USER_COMMANDS.add("EDIT_BOOK");
        USER_COMMANDS.add("SHOW_BOOKS");
        USER_COMMANDS.add("EXIT");
    }
}
