package com.LibraryManagementSystem.LMS.project.DAO;

import com.LibraryManagementSystem.LMS.project.Entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CardRepo extends JpaRepository<Card,Integer> {

    @Query("SELECT COUNT(c) FROM Card c WHERE c.status = true")
    long countActiveCards();

}
