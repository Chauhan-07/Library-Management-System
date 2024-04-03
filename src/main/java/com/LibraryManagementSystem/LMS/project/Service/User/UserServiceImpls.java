package com.LibraryManagementSystem.LMS.project.Service.User;

import com.LibraryManagementSystem.LMS.project.DAO.UserRepo;
import com.LibraryManagementSystem.LMS.project.Entity.User;
import com.LibraryManagementSystem.LMS.project.Entity.User;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserServiceImpls implements UserService{

    @Autowired
    private UserRepo userRepo;

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

    @Override
    public User getUserByEmail(String email) {
        return userRepo.findByEmail(email);
    }
}
