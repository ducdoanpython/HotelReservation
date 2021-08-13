package Service.CustomerMenu;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CustomerService {
    private static List<Customer> allCustomers = new ArrayList<Customer>();
    public static void addCustomer(Customer customer){
        allCustomers.add(customer);
    }
    public static Customer getCustomer(String customerEmail){
//        Iterator<Customer> it = allCustomers.iterator();
        for (Customer customer : allCustomers){
            if (customer.getEmail().equals(customerEmail)){
                return customer;
            }
        }
        System.out.println("Cannot found this email in db");
        return null;
    }
    public static List<Customer> getAllCustomers(){
        return allCustomers;
    }
    public static String getNameByEmail (String email){
        for (Customer customer : allCustomers){
            if (customer.getEmail().equals(email)){
                return customer.getName();
            }
        }
        return null;
    }
}
