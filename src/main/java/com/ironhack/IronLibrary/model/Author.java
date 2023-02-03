package com.ironhack.IronLibrary.model;

import jakarta.persistence.*;

@Entity
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer authorId;
    private String name;
    private String email;
    @OneToOne
    private Book authorBook;

    public Author() {
    }

    public Author(String name, String email, Book authorBook) {
        this.name = name;
        this.email = email;
        this.authorBook = authorBook;
    }

    public Author(Integer authorId, String name, String email, Book authorBook) {
        this.authorId = authorId;
        this.name = name;
        this.email = email;
        this.authorBook = authorBook;
    }
}
