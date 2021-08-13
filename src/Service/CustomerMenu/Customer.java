package Service.CustomerMenu;

import java.util.Scanner;
import java.util.regex.Pattern;

public class Customer {
    private String name;
    private String email;
    private final String regex = "^(.+)@(.+).com";
    private final Pattern emailPattern  = Pattern.compile(regex);
    public Customer(String name, String email){
        this.name = name;
        this.email = email;
    }
    public String getName() {
        return this.name;
    };
    public void setName(String name) {
        this.name = name;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String RegExEmailChecker(String email){
        if (emailPattern.matcher(email).matches()){
            return email;
        }
        else{
            System.out.println("Invalid email. Please try again!");
            return null;
        }
    }
    public String getEmail() {
        return this.email;
    }
    public void createAnAccount(){
        //name
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter your name: ");
        String nameInput = scanner.nextLine();
//        newCustomer.setName(nameInput);
        //email
        String emailInput;
        while (true){
            Scanner scanner2 = new Scanner(System.in);
            System.out.println("Please enter your email by following this form (abc@def.com)");
            emailInput = scanner2.nextLine();
            if (RegExEmailChecker(emailInput) != null){
                break;
            }
        };
        //set new user after verifying:
        this.name = nameInput;
        this.email = emailInput;
        System.out.println("");
        System.out.println("Successfully create an account");
    }
}

