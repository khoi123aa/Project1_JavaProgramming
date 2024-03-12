package model;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author KhoiTHA
 */
public class Reservation {

    private Customer customer;
    private IRoom room;
    private Date checkInDate;
    private Date checkOutDate;

    public Reservation(Customer customer, IRoom room, Date checkInDate, Date checkOutDate) {
        this.customer = customer;
        this.room = room;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public IRoom getRoom() {
        return room;
    }

    public void setRoom(IRoom room) {
        this.room = room;
    }

    public Date getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(Date checkInDate) {
        this.checkInDate = checkInDate;
    }

    public Date getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(Date checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    @Override
    public String toString() {
        return "\nReservation\n" 
                + this.customer 
                + "\nRoom: " + this.room.getRoomPrice() + " - " + this.room.getRoomType() + " bed"
                + "\nCheckin Date: " + new SimpleDateFormat("E MMM dd yyyy").format(this.checkInDate) 
                + "\nCheckout Date: " + new SimpleDateFormat("E MMM dd yyyy").format(this.checkOutDate) ;
    }
}
