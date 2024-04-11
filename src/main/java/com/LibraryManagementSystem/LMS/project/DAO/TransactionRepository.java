package com.LibraryManagementSystem.LMS.project.DAO;


import com.LibraryManagementSystem.LMS.project.Entity.transaction;
import jakarta.transaction.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface TransactionRepository  extends JpaRepository <transaction,Integer>{
    @Query("SELECT COUNT(t) FROM transaction t WHERE t.card_id.id = :cardId")
    int countTransactionsByCard_id(@Param("cardId") int cardId);
}
