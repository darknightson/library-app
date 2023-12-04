package com.group.libraryapp.service.book.impl;

import com.group.libraryapp.domain.book.BookEntity;
import com.group.libraryapp.domain.book.BookRepository;
import com.group.libraryapp.service.book.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Primary
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    @Override
    @Transactional
    public void saveBook(String name) {
        bookRepository.save(BookEntity.builder().name(name).build());
    }
}
