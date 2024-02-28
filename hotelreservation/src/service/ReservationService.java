package service;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import model.Customer;
import model.IRoom;
import model.Reservation;

/**
 *
 * @author KhoiTHA
 */
public class ReservationService {
    
    private static final Map<String, IRoom> roomMap = new HashMap<String, IRoom>();
    private static final Map<String, Collection<Reservation>> reservationMap = new HashMap<String, Collection<Reservation>>();

    public void addRoom(IRoom room) {
    }

    public IRoom getARoom(String roomId) {
        return null;
    }

    public Reservation reserveARoom(Customer customer, IRoom room, Date checkInDate, Date checkOutDate) {
        return null;
    }

    public Collection<IRoom> findRooms(Date checkInDate, Date checkOutDate) {
        return null;
    }

    public Collection<Reservation> getCustomersReservation(Customer customer) {
        return null;
    }

    public void printAllReservation() {

    }
}
