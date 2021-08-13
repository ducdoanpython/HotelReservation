package AdminMenu.RoomMenu;

import AdminMenu.Admin;

import java.util.Map;
import java.util.Scanner;

public class Room {
    private int cost;
    private int roomNumber;
    private String roomType;
    public Room(int cost, int roomNumber, String roomType) {
        this.cost = cost;
        this.roomNumber = roomNumber;
        this.roomType = roomType;
    }

    public int getCost() {
        return this.cost;
    }
    public String getRoomType() {
        return this.roomType;
    }
    public int getRoomNumber() {
        return this.roomNumber;
    }
    public void setCost(int newCost) {
         this.cost = newCost;
    }
    public void setRoomNumber(int newRoomNumber) {
        this.roomNumber = newRoomNumber;
    }
    public void setRoomType(String newRoomType) {
        this.roomType = newRoomType;
    }
    public int createRoomNumber(){
        Admin checkRooms = new Admin();
        Map<Integer, Room> rooms = checkRooms.getRooms();
        boolean running = true;
        Scanner scanner = new Scanner(System.in);
        int finalChoice = 0;
        while (running) {
            try {
                System.out.println("Enter a Room number ");
                int userInput = Integer.parseInt(scanner.nextLine());
                if (!rooms.containsKey(userInput)){
                    finalChoice = userInput;
                    running = false;
                }
                else{
                    System.out.println("The room is already available cannot create the same room twice!!!");
                }
            } catch (Exception ex) {
                System.out.println("Invalid input please try again!");
            }
        }
        return finalChoice;
    }
    public int createRoomCost(){
        boolean running = true;
        Scanner scanner = new Scanner(System.in);
        int finalChoice = 0;
        while (running) {
            try {
                System.out.println("Cost per night: ");
                int userInput = Integer.parseInt(scanner.nextLine());
                finalChoice = userInput;
                running = false;
            } catch (Exception ex) {
                System.out.println("Invalid input please try again!");
            }
        }
        return finalChoice;
    }
    public String createRoomType(){
        boolean running = true;
        Scanner scanner = new Scanner(System.in);
        int finalChoice = 0;
        while (running) {
            try {
                System.out.println("Enter a room type (1 for single bed, 2 for double bed) ");
                int userInput = Integer.parseInt(scanner.nextLine());
                if (userInput >= 1 && userInput <= 2){
                    finalChoice = userInput;
                    running = false;
                }
                else{
                    System.out.println("Sorry but we only have 2 options");
                }
            } catch (Exception ex) {
                System.out.println("Invalid input please try again!");
            }
        }
        if (finalChoice == 1){
            return "single";
        }
        else{
            return "double";
        }
    }
}
