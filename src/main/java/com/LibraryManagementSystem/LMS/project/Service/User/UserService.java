package com.LibraryManagementSystem.LMS.project.Service.User;

import com.LibraryManagementSystem.LMS.project.Entity.Customer;
import com.LibraryManagementSystem.LMS.project.Entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    User saveUser(User user);

    List<User> findAllUser();

    Optional<User> findById(int id);

    User updateUser(int id, User user);

    void deleteUser(int id);

    User getUserByEmail(String email);
}
