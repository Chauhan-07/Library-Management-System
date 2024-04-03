package com.LibraryManagementSystem.LMS.project.Controller;

import com.LibraryManagementSystem.LMS.project.Entity.Genre;
import com.LibraryManagementSystem.LMS.project.Service.Genre.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/genre")
public class GenreController {

    @Autowired
    private GenreService genreService;
    @GetMapping
    public ResponseEntity<List<Genre>> getAllGenre()
    {
        List<Genre> l= genreService.getAllGenres();
        return new ResponseEntity<>(l, HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<Genre> createGenre(@RequestBody Genre genre)
    {
        Genre g= genreService.create(genre);
        return new ResponseEntity<>(g,HttpStatus.CREATED);
    }
    @GetMapping("/{id}")
    public Genre getGenreById(@PathVariable int id)
    {
        Optional<Genre> genre = genreService.getGenreById(id);
        return genre.orElse(null);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Genre> updateGenre(@PathVariable int id,@RequestBody Genre genreDetails)
    {
        Genre updated=genreService.updateGenre(id,genreDetails);
        return new ResponseEntity<>(updated,HttpStatus.OK);

    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGenre(@PathVariable int id)
    {
        genreService.delete(id);
        return ResponseEntity.noContent().build();
    }



}

