package service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import model.Customer;
import model.IRoom;
import model.Reservation;

/**
 *
 * @author KhoiTHA
 */
public class ReservationService {

    private static final ArrayList<IRoom> rooms = new ArrayList<>();
    private static final ArrayList<Reservation> reservations = new ArrayList<>();

    public static void addRoom(IRoom room) {
        rooms.add(room);
    }

    public static IRoom getARoom(String roomId) {
        for (IRoom room : rooms) {
            if (room.getRoomNumber().equals(roomId)) {
                return room;
            }
        }
        return null;
    }

    public static Collection<IRoom> getAllRooms() {
        return rooms;
    }

    public static Reservation reserveARoom(Customer customer, IRoom room, Date checkInDate, Date checkOutDate) {
        Reservation reservation = new Reservation(customer, room, checkInDate, checkOutDate);
        reservations.add(reservation);
        return reservation;
    }

    public static Collection<Reservation> findRoomsAreBooked(Date checkInDate, Date checkOutDate) {
        ArrayList<Reservation> bookedRooms = new ArrayList<>();
        for (Reservation reservation : reservations) {
            if (reservation.getCheckInDate().getTime() >= checkInDate.getTime()
                    && reservation.getCheckOutDate().getTime() <= checkOutDate.getTime()) {
                bookedRooms.add(reservation);
            }
        }
        return bookedRooms;
    }

    public static Collection<IRoom> findRooms(Date checkInDate, Date checkOutDate) {
        ArrayList<Reservation> bookedRooms = new ArrayList<>(findRoomsAreBooked(checkInDate, checkOutDate));
        ArrayList<IRoom> iRooms = new ArrayList<>();
        for (Reservation reservation : bookedRooms) {
            iRooms.add(reservation.getRoom());
        }
        ArrayList<IRoom> roomsAvailable = new ArrayList<>();
        for (IRoom room : rooms) {
            boolean isBooked = false;
            for (IRoom bookedRoom : iRooms) {
                if (room.equals(bookedRoom)) {
                    isBooked = true;
                    break;
                }
            }
            if (!isBooked) {
                roomsAvailable.add(room);
            }
        }
        return roomsAvailable;
    }

    public static Collection<Reservation> getCustomersReservation(Customer customer) {
        ArrayList<Reservation> matchingReservations = new ArrayList<>();

        for (Reservation reservation : reservations) {
            if (reservation.getCustomer().getEmail().equals(customer.getEmail())) {
                matchingReservations.add(reservation);
            }
        }
        return matchingReservations;
    }

    public static void printAllReservation() {
        for (Reservation reservation : reservations) {
            System.out.println(reservation.toString());
        }
    }
}
