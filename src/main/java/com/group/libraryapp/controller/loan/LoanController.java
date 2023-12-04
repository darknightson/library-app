package com.group.libraryapp.controller.loan;

import com.group.libraryapp.dto.loan.BookLoanRequest;
import com.group.libraryapp.dto.loan.BookLoanReturnRequest;
import com.group.libraryapp.service.loan.LoanService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/book")
public class LoanController {

    private final LoanService loanService;

    @PostMapping("/loan")
    public ResponseEntity<Void> loanBook(@RequestBody BookLoanRequest BookLoanRequest) {
        loanService.saveLoan(BookLoanRequest.getUserName(), BookLoanRequest.getBookName());
        return ResponseEntity.ok().build();
    }

    @PutMapping("/return")
    public ResponseEntity<Void> returnBook(@RequestBody BookLoanReturnRequest request) {
        loanService.returnBook(request.getUserName(), request.getBookName());
        return ResponseEntity.ok().build();
    }
}
