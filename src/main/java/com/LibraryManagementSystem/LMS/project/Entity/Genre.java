package com.LibraryManagementSystem.LMS.project.Entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name="genre")
public class Genre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;
    @Column(name="name")
    private String name;

    @OneToMany(mappedBy = "genre",cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Book> bookList=new ArrayList<>();

    public Genre(int id) {
        this.id = id;
    }
}
