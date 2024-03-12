package model;

/**
 *
 * @author KhoiTHA
 */
public class FreeRoom extends Room {

    public FreeRoom(String roomNumber, RoomType enumeration) {
        super(roomNumber, (double) 0, enumeration);
    }

    @Override
    public String toString() {
        return "Room Number: " + this.getRoomNumber() + " " + this.getRoomType() + " bed Room Price: $" + this.getRoomPrice();
    }
}
