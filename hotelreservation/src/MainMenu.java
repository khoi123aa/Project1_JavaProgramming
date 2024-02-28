
import api.HotelResource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Scanner;
import model.IRoom;
import model.Reservation;

/**
 *
 * @author KhoiTHA
 */
public class MainMenu {
    public static void mainMenu() {
        try (Scanner scanner = new Scanner(System.in)) {
            boolean isRun = true;
            displayMenu();
            while (isRun) {
                try {
                    int selection = Integer.parseInt(scanner.nextLine());
                    isRun = exeSelect(scanner, selection);
                } catch (Exception ex) {
                    System.out.println("Please enter a number between 1 and 5");
                }
            }
        } catch (Exception ex) {
            System.out.println("Error - Exiting program");
        }
    }

    public static void displayMenu() {
        System.out.println("Welcome to the Hotel Reservation Application");
        System.out.println();
        System.out.println("==========================================================");
        System.out.println("1. Find and reserve a room");
        System.out.println("2. See my reservations");
        System.out.println("3. Create an Account");
        System.out.println("4. Admin");
        System.out.println("5. Exit");
        System.out.println("==========================================================");
        System.out.println("Please select a number for the menu option");
    }

    public static boolean exeSelect(Scanner scanner, Integer selection) {
        boolean isRun = true;
        switch (selection) {
            case 1:
                findAndReserveRoom(scanner);
                break;
            case 2:
                seeMyReservations(scanner);
                break;
            case 3:
                createAccount(scanner);
                break;
            case 4:
                AdminMenu.adminMenu();
                break;
            case 5:
                isRun = false;
                break;
            default:
                System.out.println("Please enter a number between 1 and 5\n");
        }

        return isRun;
    }

    private static void findAndReserveRoom(Scanner scanner) {
        System.out.println("Enter Check-In Date mm/dd/yyyy example 02/01/2020");
        Date checkIn = getInputDate(scanner);

        System.out.println("Enter Check-Out Date mm/dd/yyyy example 02/21/2020");
        Date checkOut = getInputDate(scanner);

        boolean isBook = false;

        if (checkIn != null && checkOut != null) {
            Collection<IRoom> availableRooms = HotelResource.findARoom(checkIn, checkOut);

            if (availableRooms.isEmpty()) {
                Date newCheckInDate = getRecommendedDate(checkIn);
                Date newCheckOutDate = getRecommendedDate(checkOut);
                availableRooms = HotelResource.findARoom(newCheckInDate, newCheckOutDate);

                if (availableRooms.isEmpty()) {
                    System.out.println("No rooms found.");
                } else {
                    System.out.println("There are no available rooms for those dates. Rooms available for alternative dates:"
                            + "\nCheck-In Date: " + newCheckInDate
                            + "\nCheck-Out Date: " + newCheckOutDate);
                    isBook = reserveRoom(scanner, availableRooms, newCheckInDate, newCheckOutDate);

                }
            } else {
                System.out.println("Available rooms for check-in date " + checkIn + " and check-out date " + checkOut);
                isBook = reserveRoom(scanner, availableRooms,checkIn, checkOut);
            }
        }

    }

    private static void seeMyReservations(Scanner scanner) {
    }

    private static void createAccount(Scanner scanner) {
    }

    private static Date getInputDate(final Scanner scanner) {
        try {
            return new SimpleDateFormat("MM/dd/yyyy").parse(scanner.nextLine());
        } catch (ParseException ex) {
            System.out.println("Invalid date format, format date must be dd/mm/yyyy");
        }
        return null;
    }

    private static Date getRecommendedDate(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DATE, 7);
        return c.getTime();
    }

    private static boolean reserveRoom(Scanner scanner, Collection<IRoom> availableRooms, Date checkIn, Date checkOut) {
        for (IRoom room : availableRooms) {
            System.out.println(room.toString());
        }
        System.out.println();
        System.out.println("Would you like to book a room? y/n");
        String choice = scanner.nextLine();
        if (choice.equalsIgnoreCase("y") || choice.equalsIgnoreCase("yes")) {
            System.out.println("Do you have an account with us? y/n");
            String isAccount = scanner.nextLine();
            if (isAccount.equalsIgnoreCase("y") || isAccount.equalsIgnoreCase("yes")) {
                System.out.println("Enter Email format: name@domain.com");
                String customerEmail = scanner.nextLine();
                if (HotelResource.getCustomer(customerEmail) == null) {
                    System.out.println("Customer email not found.\nYou may need to create a new account.");
                } else {
                    System.out.println("What room number would you like to reserve?");
                    String roomNumber = scanner.nextLine();

                    if (availableRooms.stream().anyMatch(room -> room.getRoomNumber().equals(roomNumber))) {
                        IRoom room = HotelResource.getRoom(roomNumber);

                        Reservation reservation = HotelResource.bookARoom(customerEmail, room, checkIn, checkOut);
                        System.out.println("Reservation created successfully!");
                        System.out.println(reservation);
                    } else {
                        System.out.println("Error: room number not available.\nStart reservation again.");
                    }
                }
            }
        }
        return false;
    }
}
