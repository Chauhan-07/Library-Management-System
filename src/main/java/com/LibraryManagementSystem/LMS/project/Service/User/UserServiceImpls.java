package com.LibraryManagementSystem.LMS.project.Service.User;

import com.LibraryManagementSystem.LMS.project.DAO.ReservationRepository;
import com.LibraryManagementSystem.LMS.project.DAO.UserRepo;
import com.LibraryManagementSystem.LMS.project.Entity.Book;
import com.LibraryManagementSystem.LMS.project.Entity.Reservation;
import com.LibraryManagementSystem.LMS.project.Entity.User;
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

    private ReservationRepository reservationRepository;
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
    public User updateUser(int id, User updatedUser) {
        User existingUser = userRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + id));

        // Update the existing User with the new data
        existingUser.setName(updatedUser.getName());
        existingUser.setEmail(updatedUser.getEmail());
        existingUser.setPassword(updatedUser.getPassword());


        // Save the updated User
        return userRepo.save(existingUser);
        
    }
    public void reserveBook(User user,Book book) {
        if (book.getQuantity() > 0) {
            book.setQuantity(book.getQuantity() - 1);
        } else {
            book.setReservation(book.getReservation() + 1);
            Reservation reservation = new Reservation(user, book);
            reservationRepository.save(reservation);
        }
    }
    public void returnBook(Book book) {
        if (book.getReservation() > 0) {
            book.setReservation(book.getReservation() - 1);
            if (book.getReservation() == 0) {
                book.setQuantity(book.getQuantity() + 1);
            }
            Optional<Reservation> reservation = reservationRepository.findByUserAndBook(this, book);
            reservation.ifPresent(reservationRepository::delete);
        }
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
