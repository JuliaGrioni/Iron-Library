package com.ironhack.IronLibrary.model;

import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

@Entity
public class Book {
    @Id
    private String isbn;
    private String category;
    private String title;
    private Integer quantity;

    @ManyToOne (cascade = CascadeType.ALL)
    private Author author;

//    @OneToOne
//    private Issue issue;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(isbn, book.isbn) && Objects.equals(category, book.category) && Objects.equals(title, book.title) && Objects.equals(quantity, book.quantity) && Objects.equals(author.getName(), book.author.getName()) && Objects.equals(author.getEmail(), book.author.getEmail());
    }

    @Override
    public int hashCode() {
        return Objects.hash(isbn, category, title, quantity, author);
    }
}
