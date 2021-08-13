package Service.ReservationMenu;

import AdminMenu.Admin;
import AdminMenu.RoomMenu.Room;
import Service.CustomerMenu.Customer;
import Service.CustomerMenu.CustomerService;

import java.text.SimpleDateFormat;
import java.util.*;

public class ReservationService {
    private static Map<String, Reservation> allReservation = new HashMap<String, Reservation>();
    public Map<String, Reservation> getAllReservation(){
       return allReservation;
    }
    //call this after checking the room is available and the checkOutDate
    public static Reservation reserveARoom(Customer customer, Room room, Date checkInDate, Date checkOutDate){
        Reservation roomForReservation = new Reservation(customer, room, checkInDate, checkOutDate);
        allReservation.put(customer.getName(), roomForReservation);
        return roomForReservation;
    };
    public static void printAllReservation(){
        System.out.println("Here is the list of reserved rooms: ");
        System.out.println("");
        for (Reservation reservation : allReservation.values()){
            System.out.println("Name of customer: " +  reservation.getCustomer().getName());
            System.out.println("Reserved Room: " + reservation.getRoom().getRoomNumber());
            System.out.println("Check in date: " + reservation.getCheckInDate());
            System.out.println("Check out date: " + reservation.getCheckOutDate());
        }
    };
    public static Date duplicateRoomReservationCheckOutDate(int roomNumber){
        List<Reservation> reservationList = new ArrayList<Reservation>(allReservation.values());
        for (Reservation reservation : reservationList){
            if (reservation.getRoom().getRoomNumber() == roomNumber){
                return reservation.getCheckOutDate();
            }
        }
        return null;
    }
    public static boolean dayInDayOutCheck(Date upcomingDayIn, Date previousDayOut){
        if (previousDayOut.before(upcomingDayIn)){
            return true;
        }
        return false;
    }
    public static Date findCheckOutDateByRoomNumber(int roomNumber){
        for (Reservation reservation : allReservation.values()){
            if (reservation.getRoom().getRoomNumber() == roomNumber){
                return reservation.getCheckOutDate();
            }
        }
        return null;
    }
    public static void makeAReservation(){
        Admin admin = new Admin();
        Map<Integer, Room> availableRooms = admin.getRooms();
        admin.getAllRoom(availableRooms);
        printAllReservation();
        System.out.println("");
        boolean reservationProcess = true;
        Reservation bookRoom = new Reservation(null, null, null, null);
        while (reservationProcess) {
            Scanner emailScanner = new Scanner(System.in);
            System.out.println("Please enter your email: ");
            String userEmail = emailScanner.nextLine();
            Customer registeredCustomer = CustomerService.getCustomer(userEmail);
            if (registeredCustomer == null) {
                System.out.println("Cannot find your email! Please create your own account or use an existing email");
                break;
            }
            //Check the format (try catch the wrong Date format)
            boolean askingUserPreference = true;
            while(askingUserPreference){
                Date moveInDate;
                Date checkOutDate;
                while (true){
                    try{
                        Scanner dateInScanner = new Scanner(System.in);
                        System.out.println("When do you want to move in: ");
                        moveInDate = new SimpleDateFormat("MM/dd/yyyy").parse(dateInScanner.nextLine());
                        Scanner dateOutScanner = new Scanner(System.in);
                        System.out.println("When do you want to check out: ");
                        checkOutDate = new SimpleDateFormat("MM/dd/yyyy").parse(dateOutScanner.nextLine());
                        if (moveInDate.after(checkOutDate)){
                            System.out.println("Your checkout date should be after the move in date");
                        }
                        else{
                            break;
                        }
                    }catch(Exception e){
                        System.out.println("Please fill in a correct order!");
                    }
                }
                Scanner roomScanner = new Scanner(System.in);
                System.out.println("Select a room: ");
                int toBeReservedRoom = Integer.parseInt(roomScanner.nextLine());
                if (
                        duplicateRoomReservationCheckOutDate(toBeReservedRoom) != null &&
                                dayInDayOutCheck(moveInDate, duplicateRoomReservationCheckOutDate(toBeReservedRoom))
                ){
                    reserveARoom(registeredCustomer, Admin.getRooms().get(toBeReservedRoom), moveInDate, checkOutDate);
                    askingUserPreference = false;
                }
                else if (
                        duplicateRoomReservationCheckOutDate(toBeReservedRoom) != null &&
                                !dayInDayOutCheck(moveInDate, duplicateRoomReservationCheckOutDate(toBeReservedRoom))
                ){
                    System.out.println("We sorry! The room you chose was already taken by another customer. It's occupied until " + findCheckOutDateByRoomNumber(toBeReservedRoom));
                    System.out.println("");
                }
                else{
                    reserveARoom(registeredCustomer, Admin.getRooms().get(toBeReservedRoom), moveInDate, checkOutDate);
                    askingUserPreference = false;
                }
            };
            reservationProcess = false;
        }
        System.out.println("");
    }
}
