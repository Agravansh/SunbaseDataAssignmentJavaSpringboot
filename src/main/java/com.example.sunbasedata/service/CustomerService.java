package com.example.sunbasedata.service;

import com.example.sunbasedata.model.Customer;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CustomerService {
    private final Map<String, Customer> customers = new HashMap<>();

    public Customer createCustomer(Customer customer) {
        // Check if first_name and last_name are present
        if (customer.getFirstName() == null || customer.getLastName() == null) {
            return null;
        }

        // Generate UUID for the new customer
        String uuid = generateUUID();

        // Set UUID for the customer and save it in the map
        customer.setUuid(uuid);
        customers.put(uuid, customer);

        return customer;
    }

    public List<Customer> getAllCustomers() {
        return new ArrayList<>(customers.values());
    }

    public boolean deleteCustomer(String uuid) {
        return customers.remove(uuid) != null;
    }

    public Customer updateCustomer(String uuid, Customer updatedCustomer) {
        if (customers.containsKey(uuid)) {
            // Check if first_name and last_name are present
            if (updatedCustomer.getFirstName() == null || updatedCustomer.getLastName() == null) {
                return null;
            }

            // Update the customer in the map
            updatedCustomer.setUuid(uuid);
            customers.put(uuid, updatedCustomer);
            return updatedCustomer;
        }
        return null; // Customer not found
    }

    private String generateUUID() {
        return UUID.randomUUID().toString();
    }
}
