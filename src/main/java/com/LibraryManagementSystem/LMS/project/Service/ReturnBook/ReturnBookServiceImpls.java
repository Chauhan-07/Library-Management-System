package com.LibraryManagementSystem.LMS.project.Service.ReturnBook;

import com.LibraryManagementSystem.LMS.project.DAO.ReturnBookRepo;
import com.LibraryManagementSystem.LMS.project.Entity.ReturnBook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.WeakHashMap;

@Service
public class ReturnBookServiceImpls implements ReturnBookService{

    private final ReturnBookRepo returnBookRepo;

    @Autowired
    public ReturnBookServiceImpls(ReturnBookRepo returnBookRepo) {
        this.returnBookRepo = returnBookRepo;
    }

    @Override
    public ReturnBook save(ReturnBook returnBook) {
        return returnBookRepo.save(returnBook);
    }
}
