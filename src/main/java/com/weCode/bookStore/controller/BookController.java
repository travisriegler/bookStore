package com.weCode.bookStore.controller;

import com.weCode.bookStore.dto.BookDto;
import com.weCode.bookStore.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/books")
public class BookController {

    @Autowired
    private BookService bookService;


    @GetMapping
    public ResponseEntity<List<BookDto>> getBooks() {
        List<BookDto> books = bookService.getBooks();
        return ResponseEntity.ok(books);
    }

    @GetMapping("/{title}")
    public ResponseEntity<List<BookDto>> getBooksByTitle(@PathVariable("title") String title) {
        List<BookDto> books = bookService.getBooksByTitle(title);
        return ResponseEntity.ok(books);
    }


}
