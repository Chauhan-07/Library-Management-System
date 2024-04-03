package com.LibraryManagementSystem.LMS.project.DAO;

import com.LibraryManagementSystem.LMS.project.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User,Integer> {
    User findByEmail(String email);
}
