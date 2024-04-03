package com.LibraryManagementSystem.LMS.project.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name="customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name="name",nullable = false)
    private String name;

    @Column(name="email",nullable = false)
    private String email;

    @Column(name="contact_no",nullable = false)
    private String contact_no;

    @Column(name="address",nullable = false)
    private String address;

    @JsonIgnore
    @OneToOne(mappedBy = "customer", cascade = CascadeType.ALL)
    //@JoinColumn(name="customerId")
    private Card card;

    @OneToOne
    @JoinColumn(name = "user_id",unique = true) // Reference to User entity
    private User user;



}
