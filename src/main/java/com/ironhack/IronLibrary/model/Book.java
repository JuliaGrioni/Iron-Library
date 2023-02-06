package com.ironhack.IronLibrary.model;

import jakarta.persistence.*;

@Entity
public class Book {
    @Id
    private String isbn;
    private String category;
    private String title;
    private Integer quantity;

    @ManyToOne
    private Author author;

    public Book() {
    }

    public Book(String isbn, String category, String title, Integer quantity, Author author) {
        this.isbn = isbn;
        this.category = category;
        this.title = title;
        this.quantity = quantity;
        this.author = author;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "Book{" +
                "isbn='" + isbn + '\'' +
                ", category='" + category + '\'' +
                ", title='" + title + '\'' +
                ", quantity=" + quantity +
                '}';
    }
}
