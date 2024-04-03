package com.LibraryManagementSystem.LMS.project.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name="return_book")
public class ReturnBook {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne
    @JoinColumn(name = "transactionBook_id")
    private transaction_book transactionBook_id;

    @Column(name = "return_date")
    private LocalDate return_date;

    public ReturnBook(transaction_book transactionBook_id) {
        this.transactionBook_id = transactionBook_id;
    }

    public transaction_book getTransactionBook_id() {
        return transactionBook_id;
    }

    public void setTransactionBook_id(transaction_book transactionBook_id) {
        this.transactionBook_id = transactionBook_id;
    }
}
