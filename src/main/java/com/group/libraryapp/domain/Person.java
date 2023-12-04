package com.group.libraryapp.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @OneToOne
    private Address address;

    @Builder
    public Person(String name, Address address) {
        this.name = name;
        this.address = address;
    }

    // 연관관계 편의 메서드 (양방향 연관관계일 때 사용)
    public void setAddress(Address address) {
        this.address = address;
        this.address.setPerson(this);
    }
}
