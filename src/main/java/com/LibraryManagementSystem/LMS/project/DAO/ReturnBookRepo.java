package com.LibraryManagementSystem.LMS.project.DAO;

import com.LibraryManagementSystem.LMS.project.Entity.ReturnBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReturnBookRepo extends JpaRepository<ReturnBook,Integer> {

}
