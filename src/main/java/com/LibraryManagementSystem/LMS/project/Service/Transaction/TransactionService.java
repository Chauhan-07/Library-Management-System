package com.LibraryManagementSystem.LMS.project.Service.Transaction;

import com.LibraryManagementSystem.LMS.project.DAO.TransactionRepository;
import com.LibraryManagementSystem.LMS.project.Entity.transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;

    @Autowired
    public TransactionService(TransactionRepository transactionRepository)
    {
        this.transactionRepository = transactionRepository;
    }

    //get all the transactions in list

    public List<transaction> getAllTransactions()
    {
        return transactionRepository.findAll();
    }

    // get transaction individually by id

    public Optional<transaction> getTransactionsById(int id){
        return transactionRepository.findById(id);
    }

    public transaction saveTransaction(transaction transaction) {
        return (transaction) transactionRepository.save(transaction);
    }

    //    //update the transaction
    public transaction updateTransaction(int id, transaction updatedTransaction)
    {
        Optional<transaction> exixtingTransaction  = transactionRepository.findById(id);
        if(exixtingTransaction.isPresent())
        {
            transaction transaction = exixtingTransaction.get();
            transaction.setcard_id(updatedTransaction.getcard_id());
            transaction.setTransaction_date(updatedTransaction.getTransaction_date());
            return transactionRepository.save(transaction);
        }
        else {
            throw new RuntimeException("Transaction not found");
        }
    }

    // delete the transaction by  id

    public void deleteTransaction(int id)
    {
        transactionRepository.deleteById(id);
    }
}
