package com.LibraryManagementSystem.LMS.project.Service.Payment;

import com.LibraryManagementSystem.LMS.project.DAO.PaymentRepository;
import com.LibraryManagementSystem.LMS.project.DAO.ReturnBookRepo;
import com.LibraryManagementSystem.LMS.project.Entity.ReturnBook;
import com.LibraryManagementSystem.LMS.project.Entity.payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.util.Date;
import java.util.List;
import java.util.Optional;


@Service
public class PaymentService {

    private  PaymentRepository paymentRepository;

    private  ReturnBook returnBook;
    private  ReturnBookRepo returnBookRepo;
    @Autowired
    public PaymentService(PaymentRepository paymentRepository, ReturnBook returnBook, ReturnBook returnBook1, ReturnBookRepo returnBookRepo)
    {
        this.paymentRepository = paymentRepository;
        this.returnBook = returnBook1;
        this.returnBookRepo = returnBookRepo;
    }


//    @Autowired
//    public PaymentService(ReturnBook returnBook) {
//        this.returnBook = returnBook;
//    }

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


    public double calculateFine(int id)
    {
        int finePerDay= 5;
        Optional<ReturnBook> rbook=returnBookRepo.findById(id);
        LocalDate return_date = rbook.get().getReturn_date();
        Date due_date = rbook.get().getTransactionBook_id().getTransaction_id().getDue_date();
        LocalDate dueDate = due_date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        int diffInDays = (int) ChronoUnit.DAYS.between(return_date, dueDate);
        double fine = Math.abs(diffInDays*finePerDay);
        return fine;

    }
}
