package com.LibraryManagementSystem.LMS.project.Controller;

import com.LibraryManagementSystem.LMS.project.Entity.payment;
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

    @Autowired
    public PaymentController(PaymentService paymentService)
    {
        this.paymentService = paymentService;
    }

    @PostMapping
    public ResponseEntity<payment> savePayment(@RequestBody payment Payment)
    {
        payment newPayment = paymentService.savePayment(Payment);
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

}


