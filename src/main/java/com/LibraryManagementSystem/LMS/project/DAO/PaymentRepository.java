package com.LibraryManagementSystem.LMS.project.DAO;


import com.LibraryManagementSystem.LMS.project.Entity.payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PaymentRepository extends JpaRepository<payment,Integer> {
   // @Query("SELECT SUM(p.amount) FROM payment AS p JOIN transaction AS t ON p.transaction_id = t.id JOIN card AS c ON t.card_id = c.id JOIN customer AS cust ON c.customer_id = cust.id JOIN user AS u ON cust.user_id = u.id WHERE u.id = :user_id")
    @Query("SELECT SUM(p.amount) FROM payment p WHERE p.transaction_id.card_id.customer.user.id = :user_id")
    Integer getTotalFineForUser(@Param("user_id") int user_id);
}
