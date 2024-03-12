
import api.AdminResource;
import api.HotelResource;
import java.util.ArrayList;
import java.util.Scanner;
import model.Customer;
import model.IRoom;
import model.Room;
import model.RoomType;

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
        System.out.println("5. Back to main menu");
        System.out.println("==========================================================");
        System.out.println("Please select a number for the menu option");
    }

    public static boolean exeAdminSelect(Scanner scanner, Integer selection) {
        boolean isAdminRun = true;
        switch (selection) {
            case 1:
                displayAllCustomers();
                displayAdminMenu();
                break;
            case 2:
                displayAllRooms();
                displayAdminMenu();
                break;
            case 3:
                displayAllReservations();
                displayAdminMenu();
                break;
            case 4:
                addRoom(scanner);
                displayAdminMenu();
                break;
            case 5:
                isAdminRun = false;
                MainMenu.displayMenu();
                break;
            default:
                System.out.println("Please enter a number between 1 and 5\n");
        }

        return isAdminRun;
    }

    private static void displayAllCustomers() {
        ArrayList<Customer> customers = new ArrayList<>();
        customers = (ArrayList<Customer>) AdminResource.getAllCustomers();
        for (Customer customer : customers) {
            System.out.println(customer.toString());
        }
    }

    private static void displayAllRooms() {
        ArrayList<IRoom> rooms = new ArrayList<>();
        rooms = (ArrayList<IRoom>) AdminResource.getAllRooms();
        for (IRoom room : rooms) {
            System.out.println(room.toString());
        }
    }

    private static void displayAllReservations() {
        AdminResource.displayAllReservations();
    }

    private static void addRoom(Scanner scanner) {
        System.out.print("Enter room number: ");
        String roomNumber = scanner.nextLine();
        boolean validRoomNumber = false;
        while (!validRoomNumber) {
            IRoom isExists = HotelResource.getRoom(roomNumber);
            if (isExists == null) {
                validRoomNumber = true;
            } else {
                System.out.println("Room number is existed");
                System.out.print("Please enter another room number: ");
                roomNumber = scanner.nextLine();
            }
        }

        System.out.print("Enter price per night: ");
        double price = Double.parseDouble(scanner.nextLine());
        boolean validPrice = false;
        while (!validPrice) {
            if (price < 0) {
                System.out.println("The price must be greater or equal than 0");
                System.out.print("Please enter price per night: ");
                price = Double.parseDouble(scanner.nextLine());
            } else {
                validPrice = true;
            }
        }

        System.out.println("Enter room type : 1 for single bed, 2 for double bed");
        RoomType roomType = null;
        int options = Integer.parseInt(scanner.nextLine());
        boolean validRoomType = false;

        while (!validRoomType) {
            switch (options) {
                case 1:
                    roomType = RoomType.SINGLE;
                    validRoomType = true;
                    break;
                case 2:
                    roomType = RoomType.DOUBLE;
                    validRoomType = true;
                    break;
                default:
                    System.out.println("Room type must be between 1 and 2");
                    System.out.println("Please enter room type (1 for single bed, 2 for double bed): ");
                    break;
            }
        }
        ArrayList<IRoom> rooms = new ArrayList<>();
        Room newRoom = new Room(roomNumber, price, roomType);
        rooms.add(newRoom);
        AdminResource.addRoom(newRoom);
        
        System.out.println("Would you like to add another room y/n");
        String choice = scanner.nextLine();

        while (!choice.equalsIgnoreCase("y") && !choice.equalsIgnoreCase("n")) {
            System.out.println("Please Enter y/Y (Yes) or n/N (No)");
            choice = scanner.nextLine();
        }

        if (choice.equalsIgnoreCase("y")) {
            addRoom(scanner);
        }
    }
}
