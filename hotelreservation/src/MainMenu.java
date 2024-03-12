
import api.HotelResource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import model.Customer;
import model.IRoom;
import model.Room;
import model.RoomType;
import model.Reservation;
import service.CustomerService;
import service.ReservationService;

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
                    System.out.println("Please enter a number between 1 and 6");
                }
            }
        } catch (Exception ex) {
            System.out.println("Error - Exiting program");
        }
    }

    public static void displayMenu() {
        System.out.println("Welcome to the Hotel Reservation Application");
        System.out.println("==========================================================");
        System.out.println("1. Find and reserve a room");
        System.out.println("2. See my reservations");
        System.out.println("3. Create an Account");
        System.out.println("4. Admin");
        System.out.println("5. Add Test Data");
        System.out.println("6. Exit");
        System.out.println("==========================================================");
        System.out.println("Please select a number for the menu option");
    }

    public static boolean exeSelect(Scanner scanner, Integer selection) {
        boolean isRun = true;
        switch (selection) {
            case 1:
                findAndReserveRoom(scanner);
                displayMenu();
                break;
            case 2:
                seeMyReservations(scanner);
                displayMenu();
                break;
            case 3:
                createAccount(scanner);
                displayMenu();
                break;
            case 4:
                AdminMenu.adminMenu();
                break;
            case 5:
                addTestData();
                displayMenu();
                break;
            case 6:
                isRun = false;
                break;
            default:
                System.out.println("Please enter a number between 1 and 6\n");
        }

        return isRun;
    }

    public static void addTestData() {
        Customer customer1 = new Customer("Test1", "Khoi", "test1@gmail.com");
        Customer customer2 = new Customer("Test2", "Khoi", "test2@gmail.com");

        CustomerService.addCustomer("test1@gmail.com", "Test1", "Khoi");
        CustomerService.addCustomer("test2@gmail.com", "Test2", "Khoi");

        Room singleRoom1 = new Room("100", 500.0, RoomType.SINGLE);
        Room singleRoom2 = new Room("101", 500.0, RoomType.SINGLE);
        Room doubleRoom1 = new Room("201", 800.0, RoomType.DOUBLE);
        Room doubleRoom2 = new Room("202", 800.0, RoomType.DOUBLE);

        ReservationService.addRoom(singleRoom1);
        ReservationService.addRoom(singleRoom2);
        ReservationService.addRoom(doubleRoom1);
        ReservationService.addRoom(doubleRoom2);

        Date checkInDate1 = validDate("03/11/2024");
        Date checkOutDate1 = validDate("03/12/2024");
        Date checkInDate2 = validDate("03/18/2024");
        Date checkOutDate2 = validDate("03/19/2024");

        ReservationService.reserveARoom(customer1, singleRoom1, checkInDate1, checkOutDate1);
        ReservationService.reserveARoom(customer2, doubleRoom1, checkInDate1, checkOutDate1);

        ReservationService.reserveARoom(customer1, singleRoom1, checkInDate2, checkOutDate2);
        ReservationService.reserveARoom(customer2, doubleRoom1, checkInDate2, checkOutDate2);

        Date check1 = validDate("03/11/2024");
        Date check2 = validDate("03/12/2024");

        HotelResource.findARoom(check1, check2);
        HotelResource.findARoom(check1, check2);
    }

    private static void findAndReserveRoom(Scanner scanner) {
        Date checkIn = getInputDate(scanner, "Enter Check-In Date mm/dd/yyyy example 02/01/2020");
        Date checkOut = getInputDate(scanner, "Enter Check-Out Date mm/dd/yyyy example 02/21/2020");
        Collection<IRoom> availableRooms = HotelResource.findARoom(checkIn, checkOut);

        if (availableRooms.isEmpty()) {
            Date newCheckInDate = getRecommendedDate(checkIn);
            Date newCheckOutDate = getRecommendedDate(checkOut);
            availableRooms = HotelResource.findARoom(newCheckInDate, newCheckOutDate);

            if (availableRooms.isEmpty()) {
                System.out.println("There are no available rooms on the date you booked");
            } else {
                System.out.println("There are no available rooms for those dates. Rooms available for alternative dates:"
                        + "\nCheck-In Date: " + newCheckInDate
                        + "\nCheck-Out Date: " + newCheckOutDate);
                reserveRoom(scanner, availableRooms, newCheckInDate, newCheckOutDate);
            }
        } else {
            System.out.println("Available rooms for check-in on " + checkIn + " and check-out on " + checkOut);
            reserveRoom(scanner, availableRooms, checkIn, checkOut);
        }
    }

    private static void seeMyReservations(Scanner scanner) {
        String emailPattern = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        Pattern pattern = Pattern.compile(emailPattern);
        boolean validEmail = false;
        String email = null;
        while (!validEmail) {
            System.out.println("Enter email format number: name@domain.com");
            email = scanner.nextLine();
            Matcher matcher = pattern.matcher(email);
            if (matcher.matches()) {
                validEmail = true;
            } else {
                System.out.println("Email format not correct");
            }
        }

        Customer customer = HotelResource.getCustomer(email);
        if (customer == null) {
            System.out.println("Account not exists for that email");
        } else {
            Collection<Reservation> reservations = HotelResource.getCustomersReservations(email);

            for (Reservation reservation : reservations) {
                System.out.println(reservation.toString());
            }
        }
    }

    private static Customer createAccount(Scanner scanner) {
        String emailPattern = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        Pattern pattern = Pattern.compile(emailPattern);
        boolean validEmail = false;
        String email = null;
        while (!validEmail) {
            System.out.println("Enter email format number: name@domain.com");
            email = scanner.nextLine();
            Matcher matcher = pattern.matcher(email);
            if (matcher.matches()) {
                validEmail = true;
            } else {
                System.out.println("Email format not correct");
            }
        }
        System.out.println("First Name");
        String firstName = scanner.nextLine();
        System.out.println("Last Name");
        String lastName = scanner.nextLine();

        HotelResource.createACustomer(email, firstName, lastName);
        Customer customer = HotelResource.getCustomer(email);
        return customer;
    }

    private static Date getInputDate(final Scanner scanner, String messages) {
        Date inputDate = null;
        while (inputDate == null) {
            System.out.println(messages);
            inputDate = validDate(scanner.nextLine());
        }
        return inputDate;
    }

    public static Date validDate(String dateStr) {
        SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
        try {
            return format.parse(dateStr);
        } catch (ParseException e) {
            System.out.println("Invalid date format, please use dd/mm/yyyy");
            return null;
        }
    }

    private static Date getRecommendedDate(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DATE, 7);
        return c.getTime();
    }

    private static void reserveRoom(Scanner scanner, Collection<IRoom> availableRooms, Date checkIn, Date checkOut) {
        for (IRoom room : availableRooms) {
            System.out.println(room.toString());
        }
        System.out.println("Would you like to book a room? y/n");
        String choice = scanner.nextLine();

        while (!choice.equalsIgnoreCase("y") && !choice.equalsIgnoreCase("n")) {
            System.out.println("Please Enter y/Y (Yes) or n/N (No)");
            choice = scanner.nextLine();
        }

        if (choice.equalsIgnoreCase("y")) {
            System.out.println("Do you have an account with us? y/n");
            String isAccount = scanner.nextLine();
            if (isAccount.equalsIgnoreCase("y")) {
                System.out.println("Enter Email format: name@domain.com");
                String customerEmail = scanner.nextLine();
                if (HotelResource.getCustomer(customerEmail) == null) {
                    System.out.println("Customer email not found.\nYou may need to create a new account.");
                    Customer createdCustomer = createAccount(scanner);
                    System.out.println("What room number would you like to reserve?");
                    String roomNumber = scanner.nextLine();

                    while (!isCorrectRoom(availableRooms, roomNumber)) {
                        System.out.println("Room number not available.\nPlease enter a valid room number.");
                        System.out.println("What room number would you like to reserve?");
                        roomNumber = scanner.nextLine();
                    }

                    IRoom room = HotelResource.getRoom(roomNumber);
                    Reservation reservation = HotelResource.bookARoom(createdCustomer.getEmail(), room, checkIn, checkOut);
                    System.out.println("Reservation created successfully!");
                    System.out.println(reservation);
                } else {
                    System.out.println("What room number would you like to reserve?");
                    String roomNumber = scanner.nextLine();

                    while (!isCorrectRoom(availableRooms, roomNumber)) {
                        System.out.println("Room number not available.\nPlease enter a valid room number.");
                        System.out.println("What room number would you like to reserve?");
                        roomNumber = scanner.nextLine();
                    }

                    IRoom room = HotelResource.getRoom(roomNumber);
                    Reservation reservation = HotelResource.bookARoom(customerEmail, room, checkIn, checkOut);
                    System.out.println("Reservation created successfully!");
                    System.out.println(reservation);
                }
            } else {
                Customer createdCustomer = createAccount(scanner);

                System.out.println("What room number would you like to reserve?");
                String roomNumber = scanner.nextLine();

                while (!isCorrectRoom(availableRooms, roomNumber)) {
                    System.out.println("Room number not available.\nPlease enter a valid room number.");
                    System.out.println("What room number would you like to reserve?");
                    roomNumber = scanner.nextLine();
                }

                IRoom room = HotelResource.getRoom(roomNumber);
                Reservation reservation = HotelResource.bookARoom(createdCustomer.getEmail(), room, checkIn, checkOut);
                System.out.println("Reservation created successfully!");
                System.out.println(reservation);
            }
        }
    }

    private static boolean isCorrectRoom(Collection<IRoom> availableRooms, String roomNumber) {
        return availableRooms.stream().anyMatch(room -> room.getRoomNumber().equals(roomNumber));
    }
}
