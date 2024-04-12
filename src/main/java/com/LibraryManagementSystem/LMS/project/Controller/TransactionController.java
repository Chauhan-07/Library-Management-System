package com.LibraryManagementSystem.LMS.project.Controller;

import com.LibraryManagementSystem.LMS.project.Entity.Book;
import com.LibraryManagementSystem.LMS.project.Entity.transaction;
import com.LibraryManagementSystem.LMS.project.Service.Transaction.TransactionService;
import com.LibraryManagementSystem.LMS.project.Service.TransactionBook.TBService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/transaction")
public class TransactionController {

    private final TransactionService transactionService;

    private final TBService tbService;

    @Autowired
    public TransactionController(TransactionService transactionService, TBService tbService) {
        this.transactionService = transactionService;
        this.tbService = tbService;
    }

    // save the transaction
    @PostMapping
    public ResponseEntity<transaction> saveTransaction(@RequestBody transaction transaction) {
        transaction newTransaction = transactionService.saveTransaction(transaction);
        return ResponseEntity.ok(newTransaction);
    }

    // get all the transactions
    @GetMapping
    public List<transaction> getAllTransactions() {
        return transactionService.getAllTransactions();
    }

    // get single transaction using id
    @GetMapping("/{id}")
    public ResponseEntity<transaction> getTransactionById(@PathVariable int id)
    {
        Optional<transaction> transaction = transactionService.getTransactionsById(id);
        return transaction.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // update the transaction by id

    @Transactional
    @PutMapping("/{id}")
    public  ResponseEntity <transaction> updateTransaction(@PathVariable int id, @RequestBody transaction updatedTransaction)
    {
        transaction transaction = transactionService.updateTransaction(id, updatedTransaction);
        if (transaction != null) {
            return ResponseEntity.ok(transaction);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // delete transaction using id


    @DeleteMapping("/{id}")
    public  void deleteTransaction(@PathVariable("id") int id)
    {
        transactionService.deleteTransaction(id);
    }

    @GetMapping("/TransactionByCardCount/{cardId}")
    public ResponseEntity<Integer> getCountOfTransactionByCard(@PathVariable int cardId) {
        int borrowedBooksCount = transactionService.getCountOfTransactionByCard(cardId);
        return ResponseEntity.ok(borrowedBooksCount);
    }
    @GetMapping("/TransactionByCardId/{cardId}")
    public ResponseEntity< List<Map<String,Object>>> getTransactionByCardId(@PathVariable int cardId)
    {
        List<Integer> transaction = transactionService.getTransactionByCardId(cardId);
        List<Map<String,Object>>  transactionBook = tbService.getBookIdByTransactionId(transaction);

        return ResponseEntity.ok(transactionBook);
    }

}

