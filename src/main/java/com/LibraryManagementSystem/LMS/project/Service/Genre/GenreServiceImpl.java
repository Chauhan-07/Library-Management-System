package com.LibraryManagementSystem.LMS.project.Service.Genre;

import com.LibraryManagementSystem.LMS.project.DAO.GenreDao;
import com.LibraryManagementSystem.LMS.project.Entity.Genre;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class GenreServiceImpl implements GenreService{

    @Autowired
    private GenreDao genreDao;

    @Autowired
    public GenreServiceImpl(GenreDao genreDao) {
        this.genreDao = genreDao;
    }

    @Override
    public Genre create(Genre genre) {
        return genreDao.save(genre);

    }

    @Override
    public List<Genre> getAllGenres() {
        return genreDao.findAll();
    }

    @Override
    public Optional<Genre> getGenreById(int id) {
        return genreDao.findById(id);
    }
    @Override
    public Genre updateGenre(int id, Genre genredetails) {
        Genre genre = genreDao.findById(id)
                .orElseThrow(() -> new RuntimeException("Genre not found with id: " + id));
        genre.setName(genredetails.getName());
        return genreDao.save(genre);

    }

    @Override
    public void delete(int id) {
        if(genreDao.existsById(id))
        {
            genreDao.deleteById(id);
        }
        else {
            throw new EntityNotFoundException("Genre with id "+id +" not found" );
        }
    }

}
