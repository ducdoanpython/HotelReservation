package AdminMenu;

import AdminMenu.RoomMenu.Room;
import Service.CustomerMenu.Customer;
import Service.CustomerMenu.CustomerService;
import Service.ReservationMenu.Reservation;
import Service.ReservationMenu.ReservationService;

import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Map;
import java.util.HashMap;

public class Admin {
    private static Map<Integer, Room> rooms = new HashMap<Integer, Room>();
    private Map<Integer, Room> reservedRooms = new HashMap<Integer, Room>();
    public static Map<Integer, Room> getRooms(){
        return rooms;
    }
    public Map<Integer,Room> getReservedRooms(){
        return reservedRooms;
    }
    public void setReservedRooms(Room addedRooms){
        reservedRooms.put(addedRooms.getRoomNumber(), addedRooms);
    };
    private static int adminChoice(){
        boolean running = true;
        Scanner scanner = new Scanner(System.in);
        int finalChoice = 0;
        while (running) {
            try {
                System.out.println("Enter a Number from 1 to 6: ");
                int userInput = Integer.parseInt(scanner.nextLine());
                if (userInput >= 1 && userInput <= 6){
                    finalChoice = userInput;
                    running = false;
                }
                else{
                    System.out.println("Sorry but we only have options from 1 to 5!");
                }
            } catch (Exception ex) {
                System.out.println("Invalid input please try again!");
            }
        }
        return finalChoice;
    };
    private static void createRoom(){
        Room newRoom = new Room(0, 0, null);
        newRoom.setRoomNumber(newRoom.createRoomNumber());
        newRoom.setRoomType(newRoom.createRoomType());
        newRoom.setCost(newRoom.createRoomCost());
        rooms.put(newRoom.getRoomNumber(), newRoom);
        System.out.println("");
        System.out.println("Room information: ");
        System.out.println("Room number: " + newRoom.getRoomNumber());
        System.out.println("Room Type: " + newRoom.getRoomType());
        System.out.println("Cost per night: " + newRoom.getCost() + "$");
        System.out.println(rooms.size());
    };
    public static void getAllRoom(Map<Integer, Room> roomCollector){
        List<Room> roomList = new ArrayList<Room>(roomCollector.values());
        System.out.println("Here is the list of available rooms: ");
        System.out.println("");
        for (Room room : roomList){
            System.out.println("Room Number: " + room.getRoomNumber());
            System.out.println("Room Type: " + room.getRoomType());
            System.out.println("Room Cost Per Night: " + room.getCost());
            System.out.println("");
        }
    }
    public static void main(String[] args){
        boolean runningAdmin  = true;
        int choice = 0;
        while(runningAdmin){
            System.out.println("Admin Menu");
            System.out.println("1. See all customer");
            System.out.println("2. See all rooms");
            System.out.println("3. See all reservations");
            System.out.println("4. Add a room");
            System.out.println("5. Add Test Data");
            System.out.println("6. Back to Main Menu");
            choice = adminChoice();
            if (choice == 1){
                List<Customer> allCustomers = new ArrayList<Customer>(CustomerService.getAllCustomers());
                for (Customer customer : allCustomers){
                    System.out.println("Customer name: " + customer.getName() + "\t" +"Email " + customer.getEmail());
                    System.out.println("");
                }
                System.out.println("");
            }
            else if (choice == 4){
                createRoom();
                System.out.println("");
            }
            else if (choice == 2){
                getAllRoom(rooms);
                System.out.println("");
            }
            else if (choice == 3){
                ReservationService.printAllReservation();
                System.out.println("");
            }
            else if (choice == 6){
                runningAdmin = false;
                System.out.println("");
            }
        }

    }
}
