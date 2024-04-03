package com.LibraryManagementSystem.LMS.project.DAO;


import com.LibraryManagementSystem.LMS.project.Entity.transaction;
import jakarta.transaction.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface TransactionRepository  extends JpaRepository <transaction,Integer>{
}
