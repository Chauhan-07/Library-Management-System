package com.LibraryManagementSystem.LMS.project.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "payment")
public class payment {

    @Id
    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int payment_id;

    @OneToOne
    @JoinColumn(name = "transaction_id")
    private transaction transaction_id;

    @Column
    private int amount;

    @Column
    private LocalDate payment_date;

    public payment() {
    }

    public payment(transaction transaction_id, int amount, LocalDate payment_date) {
        this.transaction_id = transaction_id;
        this.amount = amount;
        this.payment_date = payment_date;
    }



    public int getPayment_id() {
        return payment_id;
    }

    public void setPayment_id(int payment_id) {
        this.payment_id = payment_id;
    }

    public transaction getTransaction_id() {
        return transaction_id;
    }

    public void setTransaction_id(transaction transaction_id) {
        this.transaction_id = transaction_id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public LocalDate getPayment_date() {
        return payment_date;
    }

    public void setPayment_date(LocalDate payment_date) {
        this.payment_date = payment_date;
    }
}

