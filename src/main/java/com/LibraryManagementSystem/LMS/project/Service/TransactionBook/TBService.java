package com.LibraryManagementSystem.LMS.project.Service.TransactionBook;

import com.LibraryManagementSystem.LMS.project.DAO.BookDao;
import com.LibraryManagementSystem.LMS.project.DAO.ReturnBookRepo;
import com.LibraryManagementSystem.LMS.project.DAO.TBRepository;
import com.LibraryManagementSystem.LMS.project.DAO.TransactionRepository;
import com.LibraryManagementSystem.LMS.project.DTO.TransactionDetailsDTO;
import com.LibraryManagementSystem.LMS.project.Entity.Book;
import com.LibraryManagementSystem.LMS.project.Entity.ReturnBook;
import com.LibraryManagementSystem.LMS.project.Entity.transaction;
import com.LibraryManagementSystem.LMS.project.Entity.transaction_book;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@Service
public class TBService {
    private final TBRepository tbRepository;
    private final BookDao bookdao;

    private final TransactionRepository transactionRepository;

    private final ReturnBookRepo returnBookRepo;

    @Autowired
    public TBService(TBRepository tbRepository, BookDao bookdao, TransactionRepository transactionRepository, ReturnBookRepo returnBookRepo) {
        this.tbRepository = tbRepository;
        this.bookdao = bookdao;
        this.transactionRepository = transactionRepository;
        this.returnBookRepo = returnBookRepo;
    }

    public transaction_book saveTransaction_Book(transaction_book transaction_book) {
        return tbRepository.save(transaction_book);
    }

    public List<transaction_book> getAllTransaction_Book() {
        return tbRepository.findAll();
    }

    public Optional<transaction_book> getTransaction_BookById(int id) {
        return tbRepository.findById(id);
    }


//    public transaction_book updateTransactionBook(int id, transaction_book transactionBook) {
//        Optional<transaction_book> existingTransactionBookOptional = tbRepository.findById(id);
//
//        if (existingTransactionBookOptional.isPresent()) {
//            transaction_book existingTransactionBook = existingTransactionBookOptional.get();
//            existingTransactionBook.setBook_id(transactionBook.getBook_id());
//            existingTransactionBook.setTransaction_id(transactionBook.getTransaction_id());
//            existingTransactionBook.setReturn_date(transactionBook.getReturn_date());
//
//            return tbRepository.save(existingTransactionBook);
//        } else {
//            throw new RuntimeException("Transaction Book Not Found");
//        }
//    }


    public void deleteTransactionBook(int id) {

        tbRepository.deleteById(id);
    }

    public void issueBook(int bookId) {
        Book book = bookdao.findById(bookId).orElseThrow(() -> new EntityNotFoundException("Book not found with id: " + bookId));
        book.issueBook(); // Call the non-static method on the book instance
        bookdao.save(book);
    }
//    public void returnBook(int bookId) {
//
//        Book book = bookdao.findById(bookId).orElseThrow(() -> new EntityNotFoundException("Book not found with id: " + bookId));
//        book.returnBook();
//        bookdao.save(book);
//    }

    public void returnBook(int transactionBookId) {
        transaction_book transactionBook = tbRepository.findById(transactionBookId)
                .orElseThrow(() -> new EntityNotFoundException("TransactionBook not found with id: " + transactionBookId));

        // Assuming you have a reference to the Book entity within TransactionBook
        Book book = transactionBook.getBook_id();
        book.returnBook(); // Increment the quantity

        bookdao.save(book); // Save the updated book
    }

    public int getCountOfTransactionBookByCard(int cardId) {
        return tbRepository.countTransactionsBooksByCard_id(cardId);
    }

    public List<Map<String,Object>> getBookIdByTransactionId(List<Integer> transactionId) {
        return tbRepository.getBookIdByTransactionId(transactionId);
    }
    //public List<TransactionDetailsDTO> getTransactionDetails()
}


