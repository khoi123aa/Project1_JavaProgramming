package service;

import java.util.ArrayList;
import java.util.Collection;
import model.Customer;

/**
 *
 * @author KhoiTHA
 */
public class CustomerService {

    private static ArrayList<Customer> customers = new ArrayList<>();

    public static void addCustomer(String email, String firstName, String lastName) {
        Customer newCustomer = new Customer(firstName, lastName, email);
        customers.add(newCustomer);
    }

    public static Customer getCustomer(String customerEmail) {
        for (Customer customer : customers) {
            if (customer.getEmail().equals(customerEmail)) {
                return customer;
            }
        }
        return null;
    }

    public static Collection<Customer> getAllCustomers() {
        return customers;
    }
}
