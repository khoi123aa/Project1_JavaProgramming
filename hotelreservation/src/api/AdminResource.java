package api;

import model.Customer;
import model.IRoom;
import service.CustomerService;
import java.util.Collection;
import service.ReservationService;

/**
 *
 * @author KhoiTHA
 */
public class AdminResource {

    public static Customer getCustomer(String email) {
        return CustomerService.getCustomer(email);
    }

    public static void addRoom(IRoom room) {
        ReservationService.addRoom(room);
    }

    public static Collection<IRoom> getAllRooms() {
        return ReservationService.getAllRooms();
    }

    public static Collection<Customer> getAllCustomers() {
        return CustomerService.getAllCustomers();
    }

    public static void displayAllReservations() {
        ReservationService.printAllReservation();
    }
}
