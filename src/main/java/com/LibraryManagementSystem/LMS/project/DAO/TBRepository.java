package com.LibraryManagementSystem.LMS.project.DAO;


import com.LibraryManagementSystem.LMS.project.Entity.Book;
import com.LibraryManagementSystem.LMS.project.Entity.transaction_book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TBRepository extends JpaRepository<transaction_book,Integer> {

}
