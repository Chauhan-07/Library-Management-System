package com.LibraryManagementSystem.LMS.project.Entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Calendar;
import java.util.Date;

@Entity
@Table(name = "transaction")
@Access(AccessType.FIELD)
public class transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn(name="card_id")
    @JsonBackReference(value = "card_id")
    private Card card_id;

    @Column(nullable = false)
    private Date transaction_date;

    @Column(nullable = false)
    private Date due_date;

    @OneToOne(mappedBy = "transaction_id")
    @JsonIgnore
    private payment Payment;

    public transaction(payment payment) {
        Payment = payment;
    }

    public payment getPayment() {
        return Payment;
    }

    public void setPayment(payment payment) {
        Payment = payment;
    }

    //    @OneToMany(mappedBy = "transaction_id", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<transaction_book> transactionBooks = new ArrayList<>();

    public transaction(Card card_id, Date transaction_date, Date due_date) {
        this.card_id = card_id;
        this.transaction_date = transaction_date;
        this.due_date = due_date;
    }

    //Passing this in default constructor to get current date as transaction_date
    //And then giving the transaction_date to calculate the due_date
    public transaction() {
        this.transaction_date = Calendar.getInstance().getTime();
        this.due_date = calculateDueDate(this.transaction_date);
    }
    public transaction(int id) {
        this.id = id;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Card getcard_id() {
        return card_id;
    }

    public void setcard_id(Card card_id) {
        this.card_id = card_id;
    }

    public Date getTransaction_date() {
        return transaction_date;
    }

    public void setTransaction_date(Date transaction_date) {
        this.transaction_date = transaction_date;
        this.due_date = calculateDueDate(transaction_date);
    }

    public Date getDue_date() {
        return due_date;
    }

    public void setDue_date(Date due_date) {
        this.due_date = due_date;
    }

    private Date calculateDueDate(Date transactionDate) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(transactionDate);
        calendar.add(Calendar.DAY_OF_MONTH, 15);
        return calendar.getTime();
    }


//    @Override
//    public String toString() {
//        return "transaction{" +
//                "id=" + id +
//                ", card_id=" + card_id +
//                ", transaction_date=" + transaction_date +
//                '}';
//  }

}

