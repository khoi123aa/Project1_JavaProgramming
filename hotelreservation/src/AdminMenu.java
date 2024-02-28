
import java.util.Scanner;

/**
 *
 * @author KhoiTHA
 */
public class AdminMenu {

    public static void adminMenu() {
        try {
            boolean isAdminRun = true;
            Scanner scanner = new Scanner(System.in);
            displayAdminMenu();
            while (isAdminRun) {
                try {
                    int selection = Integer.parseInt(scanner.nextLine());
                    isAdminRun = exeAdminSelect(scanner, selection);
                } catch (Exception ex) {
                    System.out.println("Please enter a number between 1 and 6");
                }
            }
        } catch (Exception ex) {
            System.out.println("Error - Exiting program");
        }
    }

    public static void displayAdminMenu() {
        System.out.println("Admin Menu");
        System.out.println();
        System.out.println("==========================================================");
        System.out.println("1. See all Customers");
        System.out.println("2. See all Rooms");
        System.out.println("3. See all Reservations");
        System.out.println("4. Add a Room");
        System.out.println("5. Add Test Data");
        System.out.println("6. Back to main menu");
        System.out.println("==========================================================");
        System.out.println("Please select a number for the menu option");
    }

    public static boolean exeAdminSelect(Scanner scanner, Integer selection) {
        boolean isAdminRun = true;
        switch (selection) {
            case 1:
                displayAllCustomers();
                break;
            case 2:
                displayAllRooms();
                break;
            case 3:
                displayAllReservations();
                break;
            case 4:
                addRoom(scanner);
                break;
            case 5:
                addTestData();
                break;
            case 6:
                MainMenu.displayMenu();
                break;
            default:
                System.out.println("Please enter a number between 1 and 6\n");
        }

        return isAdminRun;
    }

    private static void displayAllCustomers() {
    }

    private static void displayAllRooms() {
    }

    private static void displayAllReservations() {
    }

    private static void addRoom(Scanner scanner) {
    }

    private static void addTestData() {
    }

}
