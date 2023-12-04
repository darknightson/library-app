package com.group.libraryapp.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PersonService {

    private final PersonRepository personRepository;
    private final AddressRepository addressRepository;

    @Autowired
    public PersonService(PersonRepository personRepository, AddressRepository addressRepository) {
        this.personRepository = personRepository;
        this.addressRepository = addressRepository;
    }

    @Transactional
    public Person savePerson() {
        Person person = personRepository.save(Person.builder().name("test").build());
        Address address = addressRepository.save(Address.builder().city("test").street("test").build());
        person.setAddress(address);
        System.out.println("address.getPerson() = " + address.getPerson().getName());
        return person;
    }
}


