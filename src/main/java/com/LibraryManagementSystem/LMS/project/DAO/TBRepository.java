package com.LibraryManagementSystem.LMS.project.DAO;


import com.LibraryManagementSystem.LMS.project.Entity.Book;
import com.LibraryManagementSystem.LMS.project.Entity.transaction_book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface TBRepository extends JpaRepository<transaction_book,Integer> {
    @Query("SELECT COUNT(t) FROM transaction_book t WHERE t.transaction_id.card_id.id = :cardId")
    int countTransactionsBooksByCard_id(@Param("cardId") int cardId);


    @Query("SELECT t.book_id as book, count(*) as number FROM transaction_book t WHERE t.transaction_id.id IN (?1) group by t.book_id")
    List<Map<String,Object>> getBookIdByTransactionId(List<Integer> transactionId);


}
