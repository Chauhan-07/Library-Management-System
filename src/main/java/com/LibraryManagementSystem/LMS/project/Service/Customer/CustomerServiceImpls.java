package com.LibraryManagementSystem.LMS.project.Service.Customer;

import com.LibraryManagementSystem.LMS.project.DAO.CardRepo;
import com.LibraryManagementSystem.LMS.project.DAO.CustomerRepo;
import com.LibraryManagementSystem.LMS.project.Entity.Card;
import com.LibraryManagementSystem.LMS.project.Entity.Customer;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CustomerServiceImpls implements CustomerService {

    @Autowired
    private CustomerRepo customerRepository;


    @Autowired
    private CardRepo cardRepository;

    @Autowired
    public CustomerServiceImpls(CustomerRepo customerRepository, CardRepo cardRepository) {
        this.customerRepository = customerRepository;
        this.cardRepository = cardRepository;
    }

//    public Customer saveCustomer(Customer customer) {
//        return customerRepository.save(customer);
//    }
public Customer saveCustomer(Customer customer) {
//    Card card = new Card();
//    card.setCustomer(customer);
//    //card.setExpiredDate(LocalDateTime.of(2024, 3, 25, 16, 8, 38, 206000));
//    card.setStatus(true);
//
//    customer.setCard(card); // Set the card for the customer

    return customerRepository.save(customer);
}

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Optional<Customer> getCustomerById(int customerId) {

        return customerRepository.findById(customerId);
    }

    public Optional<Customer> getCustomerByCardId(int cardId) {
        Optional<Card> cardOptional = cardRepository.findById(cardId);
        return cardOptional.map(Card::getCustomer);
    }

    public Customer updateCustomer(int id, Customer updatedCustomer) {
        // Check if the customer exists
        Customer existingCustomer = customerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Customer not found with id: " + id));

        // Update the existing customer with the new data
        existingCustomer.setName(updatedCustomer.getName());
        existingCustomer.setEmail(updatedCustomer.getEmail());
        existingCustomer.setContact_no(updatedCustomer.getContact_no());
        existingCustomer.setAddress(updatedCustomer.getAddress());

        // Save the updated customer
        return customerRepository.save(existingCustomer);
    }
    @Override
    public void deleteCustomer(int customerId) {
        // Check if the customer exists
        if (customerRepository.existsById(customerId)) {
            // Delete the customer
            customerRepository.deleteById(customerId);
        } else {
            // Handle case where the customer with the given ID does not exist
            throw new EntityNotFoundException("Customer with id " + customerId + " not found");
        }
    }
}
