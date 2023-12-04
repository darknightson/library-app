package com.group.libraryapp.service.loan.impl;

import com.group.libraryapp.domain.book.BookEntity;
import com.group.libraryapp.domain.book.BookRepository;
import com.group.libraryapp.domain.loan.LoanRepository;
import com.group.libraryapp.domain.user.UserEntity;
import com.group.libraryapp.domain.user.UserRepository;
import com.group.libraryapp.service.loan.LoanService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class LoanServiceImpl implements LoanService {

    private final LoanRepository userLoanRepository;
    private final BookRepository bookRepository;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public void saveLoan(String userName, String bookName) {
        // 책이 이미 대출되었는지 확인, 대출되었다면 예외 발생
        BookEntity bookEntity = bookRepository.findByName(bookName)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 책입니다."));

        if (userLoanRepository.existsByBookNameAndIsReturned(bookEntity.getName(), false)) {
            throw new IllegalArgumentException("대출 중인 책 입니다.");
        }

        // 사용자 정보 확인
        UserEntity userEntity = userRepository.findByName(userName)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자입니다."));

        // 대출 정보 저장
        // UserEntity 에  CascadeType.ALL 설정으로 인해 LoanEntity 도 같이 저장됨 ( 부모가 저장 될때 자식도 같이 저장 )
         userEntity.loanBook(bookName);

//        userLoanRepository.save(LoanEntity.builder()
//                        .bookName(bookEntity.getName())
//                        .userEntity(userEntity)
//                .build());
    }

    @Override
    @Transactional
    public void returnBook(String userName, String bookName) {
        UserEntity userEntity = userRepository.findByName(userName).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 유저 입니다."));

        userEntity.returnBook(bookName);

//        userLoanRepository.findByBookNameAndIsReturned(bookName, false)
//                .ifPresentOrElse(
//                        LoanEntity::doReturn,
//                        () -> {
//                            throw new IllegalArgumentException("대출 중인 책이 아닙니다.");
//                        });

    }
}
