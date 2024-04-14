package com.LibraryManagementSystem.LMS.project.DTO;

import com.LibraryManagementSystem.LMS.project.Entity.Book;
import com.LibraryManagementSystem.LMS.project.Entity.transaction;
import lombok.Data;

@Data
public class TransactionDetailsDTO {
    public String user_name;
    public transaction transaction_object;
    public Book book_object;

    public TransactionDetailsDTO(String user_name, transaction transaction_object, Book book_object) {
        this.user_name = user_name;
        this.transaction_object = transaction_object;
        this.book_object = book_object;
    }

    public TransactionDetailsDTO() {

    }
}
