package model;

/**
 *
 * @author KhoiTHA
 */
public class Room implements IRoom {

    private String roomNumber;
    private Double price;
    private RoomType enumeration;

    public Room(String roomNumber, Double price, RoomType enumeration) {
        this.roomNumber = roomNumber;
        this.price = price;
        this.enumeration = enumeration;
    }

    @Override
    public String getRoomNumber() {
        return this.roomNumber;
    }

    @Override
    public Double getRoomPrice() {
        return this.price;
    }

    @Override
    public RoomType getRoomType() {
        return this.enumeration;
    }

    @Override
    public Boolean isFree() {
        return this.price == 0 ? true : false;
    }

    @Override
    public String toString() {
        return "Room Number: " + this.roomNumber + " " + this.enumeration + " bed Room Price: $" + this.price;
    }
}
