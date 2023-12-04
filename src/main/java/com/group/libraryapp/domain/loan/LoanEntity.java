package com.group.libraryapp.domain.loan;

import com.group.libraryapp.domain.user.UserEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "user_loan_history")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LoanEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    boolean isReturned;

    private String bookName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity userEntity;

    @Builder
    private LoanEntity(boolean isReturned, String bookName, UserEntity userEntity) {
        this.isReturned = isReturned;
        this.bookName = bookName;
        this.userEntity = userEntity;
    }

    public void doReturn() {
        this.isReturned = true;

    }
}
