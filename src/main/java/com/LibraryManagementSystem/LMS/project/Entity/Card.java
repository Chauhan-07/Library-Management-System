package com.LibraryManagementSystem.LMS.project.Entity;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name="card")
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @OneToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;


    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "issued_date",nullable = false)
    private Date issuedDate;


    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "expired_date",nullable = false)
    private Date expiredDate; ;

    @Column(name = "status", columnDefinition = "boolean default true")
    private boolean status;

    @OneToMany(mappedBy = "card_id", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference(value="card_id")
    private List<transaction> transactions = new ArrayList<>();

    @PrePersist
    protected void onCreate() {
        issuedDate = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(issuedDate);
        calendar.add(Calendar.YEAR, 1); // Adding 1 year to the issued date
        expiredDate = calendar.getTime();
    }
    public Customer getCustomer() {
        return customer;
    }

    public void setExpiredDate(LocalDateTime expiredDateTime) {
        this.expiredDate = Date.from(expiredDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

//    public ResponseEntity<Card> updateStatus() {
//        Date currentDate = new Date();
//        if (currentDate.after(expiredDate)) {
//            this.status = false;
//        }
//        return null;
//    }

    public Card(int id) {
        this.id = id;
    }
}
