package com.LibraryManagementSystem.LMS.project.Controller;

import com.LibraryManagementSystem.LMS.project.DAO.CustomerRepo;
import com.LibraryManagementSystem.LMS.project.Entity.Customer;
import com.LibraryManagementSystem.LMS.project.Service.Customer.CustomerService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private CustomerRepo customerRepository;
    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) {
        Customer savedCustomer = customerService.saveCustomer(customer);
        return new ResponseEntity<>(savedCustomer, HttpStatus.CREATED);
    }


    @GetMapping
    public List<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    @GetMapping("/{customerId}")
    public Customer getCustomerById(@PathVariable int customerId) {
        Optional<Customer> customerOptional = customerService.getCustomerById(customerId);
        return customerOptional.orElse(null); // Return null if customer not found
    }

    @GetMapping("/byCard/{cardId}")
    public Customer getCustomerByCardId(@PathVariable int cardId) {
        Optional<Customer> customerOptional = customerService.getCustomerByCardId(cardId);
        return customerOptional.orElse(null); // Return null if customer not found for the given card
    }

    @PutMapping("/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable int id, @RequestBody Customer updatedCustomer) {
        Customer updated = customerService.updateCustomer(id, updatedCustomer);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable int id) {
        // Invoke the delete method from the service layer
        customerService.deleteCustomer(id);
        return ResponseEntity.noContent().build();
    }
}

