package com.ironhack.IronLibrary.repository;
import com.ironhack.IronLibrary.model.Author;
import com.ironhack.IronLibrary.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, String> {


    Optional<Book> findByTitle(String title);

    List<Book> findByCategory(String category);

    List<Book> findByAuthor(Author author);
    // Relacion con autor. Book findBy (String name);

}
