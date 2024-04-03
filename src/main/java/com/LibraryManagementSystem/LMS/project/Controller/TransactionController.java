package com.LibraryManagementSystem.LMS.project.Controller;

import com.LibraryManagementSystem.LMS.project.Entity.transaction;
import com.LibraryManagementSystem.LMS.project.Service.Transaction.TransactionService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/transaction")
public class TransactionController {

    private final TransactionService transactionService;

    @Autowired
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
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


}

