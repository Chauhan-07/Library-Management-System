package com.LibraryManagementSystem.LMS.project.DAO;

import com.LibraryManagementSystem.LMS.project.Entity.Book;
import com.LibraryManagementSystem.LMS.project.Entity.Reservation;
import com.LibraryManagementSystem.LMS.project.Service.User.UserServiceImpls;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ReservationRepository extends JpaRepository<Reservation, Integer> {
    Optional<Reservation> findByUserAndBook(UserServiceImpls user, Book book);
}

