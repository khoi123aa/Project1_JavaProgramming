package api;

import java.util.Collection;
import java.util.Date;
import model.Customer;
import model.IRoom;
import model.Reservation;
import service.CustomerService;

/**
 *
 * @author KhoiTHA
 */
public class HotelResource {

    public static Customer getCustomer(String email) {
        return CustomerService.getCustomer(email);
    }

    public static void createACustomer(String email, String firstName, String lastName) {
        CustomerService.addCustomer(email, firstName, lastName);
    }

    public static IRoom getRoom(String roomNumber) {
        return null;
    }

    public static Reservation bookARoom(String customerEmail, IRoom room, Date checkInDate, Date CheckOutDate) {
        return null;
    }

    public static Collection<Reservation> getCustomerReservations(String customerEmail) {
        return null;
    }

    public static Collection<IRoom> findARoom(Date checkIn, Date checkOut) {
        return null;
    }
}
