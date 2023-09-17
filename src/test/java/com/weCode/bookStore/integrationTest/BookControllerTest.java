package com.weCode.bookStore.integrationTest;

import com.weCode.bookStore.BookStoreApplication;
import com.weCode.bookStore.dto.BookDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.jdbc.Sql;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(classes = BookStoreApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BookControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    @Sql(scripts = {"classpath:InsertInitialBookRecordForTest.sql"})
    public void shouldReturnBooksWhenBookApiCalled() {
        String url = "http://localhost:" + port + "/api/v1/books";

        ResponseEntity<BookDto[]> response = restTemplate.getForEntity(url, BookDto[].class);

        BookDto[] bookArray = response.getBody();

        assertNotNull(bookArray, "Books should not be null");

        assertEquals(1, bookArray.length, "Should contain 1 element in the array");
    }
}
