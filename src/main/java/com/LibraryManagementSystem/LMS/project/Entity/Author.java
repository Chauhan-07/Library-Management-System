package com.LibraryManagementSystem.LMS.project.Entity;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name="author")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;
    @Column(name="name")
    private String name;

    @OneToMany(mappedBy = "author",cascade = CascadeType.ALL)
    @JsonManagedReference(value="author")
    private List<Book> bookList=new ArrayList<>();

    public Author(int id) {
        this.id = id;
    }
}
