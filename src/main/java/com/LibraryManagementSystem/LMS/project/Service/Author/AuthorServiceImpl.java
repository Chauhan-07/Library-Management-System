package com.LibraryManagementSystem.LMS.project.Service.Author;

import com.LibraryManagementSystem.LMS.project.DAO.AuthorDao;
import com.LibraryManagementSystem.LMS.project.Entity.Author;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class AuthorServiceImpl implements AuthorService{
    @Autowired
    private AuthorDao authorDao;

    @Autowired
    public AuthorServiceImpl(AuthorDao authorDao) {
        this.authorDao = authorDao;
    }

    @Override
    public Author create(Author author) {
        return authorDao.save(author);
    }

    @Override
    public List<Author> getAllAuthors() {
        return authorDao.findAll();
    }

    @Override
    public Optional<Author> getAuthorById(int id) {
        return authorDao.findById(id);
    }

    @Override
    public Author updateAuthor(int id, Author authordetails) {
        Author a= authorDao.findById(id)
                .orElseThrow(()-> new RuntimeException("Author not found with id: "+ id));
        a.setName(authordetails.getName());
        return authorDao.save(a);
    }

    @Override
    public void delete(int id) {
        if(authorDao.existsById(id))
        {
            authorDao.deleteById(id);
        }
        else {
            throw new EntityNotFoundException("Author not found wth id : "+id);
        }
    }
}
