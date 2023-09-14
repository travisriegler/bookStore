package com.weCode.bookStore.model;

import jakarta.persistence.*;
import org.springframework.lang.NonNull;

import java.util.UUID;

@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "UUID")
    private UUID id;

    @Column
    @NonNull
    private String title;

    @Column
    @NonNull
    private String description;

    @Column
    @NonNull
    private int releaseYear;


}
