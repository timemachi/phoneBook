package contacts;

import java.util.Scanner;

public class UserInterface {
    private final Actions actions;
    private final Scanner scanner;

    public UserInterface() {
        actions = new Actions();
        scanner = new Scanner(System.in);
    }
    public void startMenu() {
        while (true) {
            System.out.print("\n[menu] Enter action (add, list, search, count, exit): ");
            String action = scanner.nextLine();
            switch (action) {
                case "add" -> actions.add();
                case "list" -> actions.info();
                case "search" -> actions.search();
                case "count" -> actions.count();
                case "exit" -> {
                    scanner.close();
                    actions.closeScanner();
                    actions.exit();
                    return;
                }
                default -> System.out.println("Incorrect action.");
            }
        }
    }
}
