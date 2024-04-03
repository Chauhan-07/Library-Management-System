package com.LibraryManagementSystem.LMS.project.DAO;


import com.LibraryManagementSystem.LMS.project.Entity.payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<payment,Integer> {
}
