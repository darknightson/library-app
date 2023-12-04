package com.group.libraryapp.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String street;

    private String city;

    @OneToOne(mappedBy = "address")
    private Person person;

    @Builder
    private Address(String street, String city, Person person) {
        this.street = street;
        this.city = city;
        this.person = person;
    }

    // 연관관계 편의 메서드 (양방향 연관관계일 때 사용)
    public void setPerson(Person person) {
        this.person = person;
    }
}
