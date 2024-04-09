package com.LibraryManagementSystem.LMS.project.Entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name="book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="title")
    private String title;

    @Column(name="stock")
    private Integer stock;

    @ManyToOne

    @JoinColumn(name="genre_id")
    private Genre genre;

    @ManyToOne

    @JoinColumn(name="author_id")
    @JsonBackReference(value="author_manage")
    private Author author;

    @Column(name="quantity")
    private int quantity;

    @Column(name="reservation")
    private Integer reservation;




    @OneToMany(mappedBy = "book_id")

    //@JsonIgnore
    private List<transaction_book> transactionBook;

    @ManyToMany
    @JoinTable(
            name = "book_reservation",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<User> reservedUsers;

   // @OneToMany(mappedBy ="book_id")
   // private List<transaction_book> transactionBookList;
   public void issueBook() {
       if (quantity > 0) {
           quantity--;
       }
      else {
              reservation++;

       }

   }

    public void returnBook() {
       if(reservation<=0){
        quantity++;}
       else{
           reservation--;}

        if (!reservedUsers.isEmpty()) {
            // Notify the first user in the list that the book is available
            User user = reservedUsers.get(0);
            // Code to notify the user
            // Remove the user from the reserved list
            reservedUsers.remove(user);
        }
    }


    public Book(int id) {
        this.id = id;
    }
}
