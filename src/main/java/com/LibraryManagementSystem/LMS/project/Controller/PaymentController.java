package com.LibraryManagementSystem.LMS.project.Controller;

import com.LibraryManagementSystem.LMS.project.DAO.PaymentRepository;
import com.LibraryManagementSystem.LMS.project.DAO.ReturnBookRepo;
import com.LibraryManagementSystem.LMS.project.Entity.ReturnBook;
import com.LibraryManagementSystem.LMS.project.Entity.payment;
import com.LibraryManagementSystem.LMS.project.Entity.transaction;
import com.LibraryManagementSystem.LMS.project.Service.Payment.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/payment")
public class PaymentController {

    private final PaymentService paymentService;

    private final ReturnBook returnBook;
    private final ReturnBookRepo returnBookRepo;

    private PaymentRepository paymentRepository;

    @Autowired
    public PaymentController(PaymentService paymentService, ReturnBook returnBook, ReturnBookRepo returnBookRepo)
    {
        this.paymentService = paymentService;
        this.returnBook = returnBook;
        this.returnBookRepo = returnBookRepo;
    }

    @PostMapping ("/{id}")
    public ResponseEntity<payment> savePayment(@PathVariable int id,@RequestBody payment Payment)
    {
        int amount =(int)paymentService.calculateFine(id);
        Optional<ReturnBook> rbook = returnBookRepo.findById(id);
        transaction transactionId=rbook.get().getTransactionBook_id().getTransaction_id();
        payment newPayment = new payment();
        newPayment.setAmount(amount);
        newPayment.setTransaction_id(transactionId);
         newPayment.setPayment_date(Payment.getPayment_date());
        paymentService.savePayment(newPayment);
//        paymentService.savePayment(Payment);


        return ResponseEntity.ok(newPayment);
    }

    @GetMapping
    public List<payment> getAllPayments()
    {

        return paymentService.getAllPayments();
    }

    @GetMapping("/{payment_id}")
    public ResponseEntity<payment> getPaymentById(@PathVariable int payment_id){
        Optional<payment> Payment = paymentService.getPaymentById(payment_id);
        return Payment.map(ResponseEntity::ok).orElseGet(()->ResponseEntity.notFound().build());
    }

    @PutMapping("/{payment_id}")
    public ResponseEntity<payment> updatePayment(@PathVariable int payment_id,@RequestBody payment Payment){
        payment updatedPayment = paymentService.updatePayment(payment_id,Payment);

        return ResponseEntity.ok(updatedPayment);
    }

    @DeleteMapping("/{payment_id}")
    public ResponseEntity<String>deletePayment(@PathVariable int payment_id){
        paymentService.deletePayment(payment_id);
        return ResponseEntity.ok("Payment deleted successfully!");
    }
    @GetMapping("/totalFine")
    public int getTotalFine() {
        return paymentService.getTotalFine();
    }


    @GetMapping("/totalFineForUser/{user_id}")
    public Integer getTotalFineForUser(@PathVariable int user_id) {
        return paymentService.getTotalFineForUser(user_id);
    }
}


