package com.LibraryManagementSystem.LMS.project.DAO;

import com.LibraryManagementSystem.LMS.project.Entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorDao extends JpaRepository<Author,Integer> {
}
