package com.LibraryManagementSystem.LMS.project.Service.Payment;

import com.LibraryManagementSystem.LMS.project.DAO.PaymentRepository;
import com.LibraryManagementSystem.LMS.project.Entity.payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;

    @Autowired
    public PaymentService(PaymentRepository paymentRepository)
    {
        this.paymentRepository = paymentRepository;
    }

    public payment savePayment(payment Payment)
    {
        return paymentRepository.save(Payment);
    }

    public List<payment> getAllPayments()
    {
        return paymentRepository.findAll();
    }

    public Optional<payment> getPaymentById(int payment_id)
    {
        return paymentRepository.findById(payment_id);
    }

    public payment updatePayment(int payment_id, payment updatedPayment) {
        Optional<payment> existingPayment = paymentRepository.findById(payment_id);
        if (existingPayment.isPresent()) {
            payment Payment = existingPayment.get();
            Payment.setTransaction_id(updatedPayment.getTransaction_id());
            Payment.setPayment_date(updatedPayment.getPayment_date());
            Payment.setAmount(updatedPayment.getAmount());
            return paymentRepository.save(Payment);
        } else {
            throw new RuntimeException("Payment not found");
        }
    }

    public void deletePayment(int id){
        paymentRepository.deleteById(id);
    }
}
