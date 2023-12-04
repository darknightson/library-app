package com.group.libraryapp.service.loan;

import com.group.libraryapp.dto.loan.BookLoanReturnRequest;

public interface LoanService {

    void saveLoan(String userName, String bookName);

    void returnBook(String userName, String bookName);
}
