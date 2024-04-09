package com.LibraryManagementSystem.LMS.project.Entity;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="reservation")
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name="book_id")
    private Book book;

    public Reservation(User user, Book book) {
        this.user = user;
        this.book = book;
    }

    // getters and setters...


    public Reservation(int id, User user, Book book) {
        this.id = id;
        this.user = user;
        this.book = book;
    }

    public Reservation() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }
}