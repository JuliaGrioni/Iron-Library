package com.ironhack.IronLibrary.repository;
import com.ironhack.IronLibrary.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, String> {
    Book findByTitle (String title);
    Book findByCategory (String category);

    // Relacion con autor. Book findBy (String name);

}
