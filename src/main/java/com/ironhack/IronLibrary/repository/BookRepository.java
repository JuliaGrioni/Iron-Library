package com.ironhack.IronLibrary.repository;
import com.ironhack.IronLibrary.model.Author;
import com.ironhack.IronLibrary.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, String> {

    Optional<Book> findBookByTitle(String title);

    List<Book> findBookByCategory(String category);


//    @Query("SELECT b FROM Book b WHERE b.author.name = :authorName")
    List<Book> findBookByAuthor(Author author);
}
