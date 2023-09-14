package com.weCode.bookStore.repository;

import com.weCode.bookStore.model.Book;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface BookRepository extends CrudRepository<Book, UUID> {

}
