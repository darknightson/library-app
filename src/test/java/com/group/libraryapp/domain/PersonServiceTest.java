package com.group.libraryapp.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PersonServiceTest {


    @Autowired
    private PersonService personService;

    @DisplayName("연관관계 편의 메서드 테스트 입니다.")
    @Test
    void savePerson() {

        // given & when
        Person person = personService.savePerson();
        // then
        Assertions.assertThat(person.getAddress()).isNotNull();

    }


}