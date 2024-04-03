package com.LibraryManagementSystem.LMS.project.Service.Customer;

import com.LibraryManagementSystem.LMS.project.Entity.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerService {
    Customer saveCustomer(Customer customer);

    List<Customer> getAllCustomers();

    Optional<Customer> getCustomerById(int customerId);

    Optional<Customer> getCustomerByCardId(int cardId);

    Customer updateCustomer(int id, Customer customer);

    void deleteCustomer(int customerId);
}
