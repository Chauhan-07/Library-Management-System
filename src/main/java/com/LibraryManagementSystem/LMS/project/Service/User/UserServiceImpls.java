package com.LibraryManagementSystem.LMS.project.Service.User;

import com.LibraryManagementSystem.LMS.project.DAO.UserRepo;
import com.LibraryManagementSystem.LMS.project.Entity.Book;
import com.LibraryManagementSystem.LMS.project.Entity.User;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;


import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserServiceImpls implements UserService{

    @Autowired
    private UserRepo userRepo;
    private JavaMailSender mailSender;

    private User user;

    @Autowired
    public UserServiceImpls(UserRepo userRepo)
    {
        this.userRepo=userRepo;
    }

    @Override
    public User saveUser(User user){
        return userRepo.save(user);
    }

    @Override
    public List<User> findAllUser() {
        return userRepo.findAll();
    }

    @Override
    public Optional<User> findById(int id) {
        return userRepo.findById(id);
    }

    @Override
    public User updateUser(String email, User updatedUser) {
        User existingUser = userRepo.findByEmail(email);
                   //.orElseThrow(() -> new EntityNotFoundException("User not found with email: " + email));

        // Update the existing User with the new data
       // existingUser.setName(updatedUser.getName());
        existingUser.setEmail(updatedUser.getEmail());
        existingUser.setPassword(updatedUser.getPassword());

        // Save the updated User
        return userRepo.save(existingUser);
        
    }

    @Override
    public void deleteUser(int id) {
        if (userRepo.existsById(id)) {
            // Delete the customer
            userRepo.deleteById(id);
        } else {
            // Handle case where the customer with the given ID does not exist
            throw new EntityNotFoundException("Customer with id " + id + " not found");
        }
    }

    public void notifyUser(Book book) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(user.getEmail());
        message.setSubject("Book Availability Notification");
        message.setText("The book " + book.getTitle() + " is now available for borrowing.");
        mailSender.send(message);
    }


    @Override
    public User getUserByEmail(String email) {
        return userRepo.findByEmail(email);
    }
}
