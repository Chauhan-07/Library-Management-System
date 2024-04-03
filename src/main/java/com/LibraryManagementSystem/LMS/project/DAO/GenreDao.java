package com.LibraryManagementSystem.LMS.project.DAO;

import com.LibraryManagementSystem.LMS.project.Entity.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenreDao extends JpaRepository<Genre,Integer> {

}
