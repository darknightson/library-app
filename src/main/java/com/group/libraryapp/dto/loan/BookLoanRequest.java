package com.group.libraryapp.dto.loan;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BookLoanRequest {

    private String userName;
    private String bookName;
}
