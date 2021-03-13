package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.entity.Customer;
import java.util.List;

public interface CustomerService {

    Customer saveCustomer(Customer customer);
    Customer getCustomer(long customerId);
    List<Customer> getAllCustomers();
    Customer getCustomerByPetId(long petId);

}
