package api;

import model.Customer;
import model.IRoom;
import service.CustomerService;

import java.util.Collection;
import java.util.List;

/**
 *
 * @author KhoiTHA
 */
public class AdminResource {

    public static Customer getCustomer(String email) {
        return CustomerService.getCustomer(email);
    }

    public static void addRoom(List<IRoom> room) {
    }

    public static Collection<IRoom> getAllRooms() {
        return null;
    }

    public static Collection<Customer> getAllCustomers() {
        return CustomerService.getAllCustomers();
    }

    public void displayAllReservations() {
    }
}
