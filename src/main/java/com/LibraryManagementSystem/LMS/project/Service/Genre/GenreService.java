package com.LibraryManagementSystem.LMS.project.Service.Genre;

import com.LibraryManagementSystem.LMS.project.Entity.Genre;

import java.util.List;
import java.util.Optional;

public interface GenreService {

    Genre create(Genre genre);
    List<Genre> getAllGenres();

    Optional<Genre> getGenreById(int id);

    Genre updateGenre(int id,Genre genredetails);

    void delete(int id);






}

