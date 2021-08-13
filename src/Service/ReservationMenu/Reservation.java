package Service.ReservationMenu;

import AdminMenu.RoomMenu.Room;
import Service.CustomerMenu.Customer;

import java.util.Date;

public class Reservation {
    private Customer customer;
    private Room room;
    private Date checkInDate;
    private Date checkOutDate;

    public Customer getCustomer() {
        return this.customer;
    }
    public Date getCheckInDate() {
        return this.checkInDate;
    }
    public Date getCheckOutDate() {
        return this.checkOutDate;
    }
    public Room getRoom() {
        return this.room;
    }

    public Reservation(Customer customer, Room room, Date checkInDate, Date checkOutDate){
        this.customer = customer;
        this.room = room;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
    }
}
