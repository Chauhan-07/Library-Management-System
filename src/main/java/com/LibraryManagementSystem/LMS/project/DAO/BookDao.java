package com.LibraryManagementSystem.LMS.project.DAO;


import com.LibraryManagementSystem.LMS.project.Entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookDao extends JpaRepository<Book,Integer> {

    Book findByAuthorIdAndGenreIdAndTitle(int authorId, int genreId, String title);

}
