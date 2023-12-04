package com.group.libraryapp.domain.user;

import com.group.libraryapp.domain.loan.LoanEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "user")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;

    private int age;

    @OneToMany(mappedBy = "userEntity", cascade = CascadeType.ALL)
    private List<LoanEntity> userLoanHistories = new ArrayList<>();

    @Builder
    public UserEntity(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public void updateUser(String name, int age) {
        this.name = name;
        this.age = age;
    }

    // 책 대출 정보 저장
    public void loanBook(String bookName) {
        this.userLoanHistories.add(LoanEntity.builder()
                .bookName(bookName)
                .userEntity(this)
                .build());
    }

    // 책 반납 // 아래건 cascade랑 상관없이 lazy 기능임
    public void returnBook(String bookName) {
        LoanEntity targetLoanEntity = this.userLoanHistories
                .stream()
                .filter(loanEntity -> bookName.equals(loanEntity.getBookName()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("대출 중인 책이 아닙니다."));
        targetLoanEntity.doReturn();
    }
}
