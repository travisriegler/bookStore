package com.weCode.bookStore.controller;

import com.weCode.bookStore.dto.BookDto;
import com.weCode.bookStore.service.BookService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class BookControllerTest {

    @InjectMocks
    private BookController bookController;

    @Mock
    private BookService bookService;

    @Mock
    private ModelMapper mapper;

    @Test
    void shouldReturnBookDtoListWhenGetBooksCalled() {
        List<BookDto> bookDtos = new ArrayList<>();
        bookDtos.add(getBookDto());

        when(bookService.getBooks()).thenReturn(bookDtos);
        ResponseEntity<List<BookDto>> books = bookController.getBooks();
        assertThat(books.getBody()).isNotNull();
        assertThat(books.getBody().size()).isEqualTo(1);
    }

    @Test
    void shouldReturnBookDtoListWhenGetBooksByTitleCalled() {
        List<BookDto> bookDtos = new ArrayList<>();
        bookDtos.add(getBookDto());

        when(bookService.getBooksByTitle(anyString())).thenReturn(bookDtos);
        ResponseEntity<List<BookDto>> books = bookController.getBooksByTitle("test title");
        assertThat(books.getBody()).isNotNull();
        assertThat(books.getBody().size()).isEqualTo(1);
    }


    private BookDto getBookDto() {
        return BookDto.builder()
                .title("test title")
                .description("test description")
                .id(UUID.randomUUID())
                .releaseYear(2020)
                .build();
    }

}
