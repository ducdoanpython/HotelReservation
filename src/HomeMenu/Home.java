package HomeMenu;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import AdminMenu.Admin;
import AdminMenu.RoomMenu.Room;
import Service.CustomerMenu.Customer;
import Service.CustomerMenu.CustomerService;
import Service.ReservationMenu.Reservation;
import Service.ReservationMenu.ReservationService;


public class Home {
    private static String userName;
    private static int userChoice() {
        boolean running = true;
        Scanner scanner = new Scanner(System.in);
        int finalChoice = 0;
        while (running) {
            try {
                System.out.println("Enter a Number from 1 to 5: ");
                int userInput = Integer.parseInt(scanner.nextLine());
                if (userInput >= 1 && userInput <= 5) {
                    finalChoice = userInput;
                    running = false;
                } else {
                    System.out.println("Sorry but we only have options from 1 to 5!");
                }
            } catch (Exception ex) {
                System.out.println("Invalid input please try again!");
            }
        }
        return finalChoice;
    }

    public static void main(String[] args) throws ParseException {
        while (true) {
            System.out.println("Welcome to the Hotel Reservation.");
            System.out.println("Please choose an option:");
            System.out.println("1. Find and reserve a room");
            System.out.println("2. See my reservations");
            System.out.println("3. Create an account");
            System.out.println("4. Admin");
            System.out.println("5. Exit");
            int result = userChoice();
            if (result == 1) {
                ReservationService.makeAReservation();
            } else if (result == 2) {
                Scanner emailScanner = new Scanner(System.in);
                System.out.println("Please enter your email for verification: ");
                String userEmail = emailScanner.nextLine();
                String userName = CustomerService.getNameByEmail(userEmail);
                System.out.println("");
                if ( userName == null) {
                    System.out.println("Cannot find user. Please make sure that you had an account and logged in!");
                }
                else{
                    ReservationService currentReservationService = new ReservationService();
                    Reservation currentReservation = currentReservationService.getAllReservation().get(userName);
                    if ( currentReservation == null){
                        System.out.println("An error occurs. It seems like you haven't booked any room!");
                    }
                    else{
                        System.out.println("You reserved room: " + currentReservation.getRoom().getRoomNumber());
                        System.out.println("Your move in date is: " + currentReservation.getCheckInDate());
                        System.out.println("Your check out date is: " + currentReservation.getCheckOutDate());
                    }
                }
                System.out.println("");
            } else if (result == 3) {
                Customer newCustomer = new Customer(null, null);
                newCustomer.createAnAccount();
                CustomerService.addCustomer(newCustomer);
                System.out.println("");
            } else if (result == 4) {
                Admin admin = new Admin();
                admin.main(null);
                System.out.println("");
            } else {
                break;
            }
        }
    }
}

