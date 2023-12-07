package com.group.libraryapp.controller.book;

import com.group.libraryapp.dto.book.BookCreateRequest;
import com.group.libraryapp.service.book.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/book")
public class BookController {

    private final BookService bookService;

    /*
    책 정보 저장 테스트 입니다.
     */
    @PostMapping
    public ResponseEntity<Void> saveBook(@RequestBody BookCreateRequest bookCreateRequest) {
        bookService.saveBook(bookCreateRequest.getName());
        return ResponseEntity.ok().build();
    }
}
