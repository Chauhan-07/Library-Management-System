package com.LibraryManagementSystem.LMS.project.Controller;

import com.LibraryManagementSystem.LMS.project.Entity.Author;
import com.LibraryManagementSystem.LMS.project.Service.Author.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@RestController
@CrossOrigin
@RequestMapping("/author")

public class AuthorController {
    @Autowired
    private AuthorService authorService;

    @Autowired
    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @PostMapping
    public ResponseEntity<Author>  create(@RequestBody Author author)
    {
        Author a= authorService.create(author);

        return new ResponseEntity<>(a,HttpStatus.CREATED );
    }

    @GetMapping
    public ResponseEntity<List<Author>>  getAllAuthors()
    {
        List<Author> l=authorService.getAllAuthors();
        return new ResponseEntity<>(l,HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public Author getAuthorById(@PathVariable int id)
    {
        Optional<Author> a= authorService.getAuthorById(id);
        return a.orElse(null);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Author> updateAuthor(@PathVariable int id,@RequestBody Author authorDetails)
    {
        Author updated=authorService.updateAuthor(id,authorDetails);
        return new ResponseEntity<>(updated,HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAuthor(@PathVariable int id)
    {
        authorService.delete(id);
        return ResponseEntity.noContent().build();
    }


}
