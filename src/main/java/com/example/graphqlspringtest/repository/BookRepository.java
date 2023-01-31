package com.example.graphqlspringtest.repository;

import com.example.graphqlspringtest.model.Book;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    @Query("SELECT b FROM Book b ORDER BY b.id DESC")
    List<Book> getAllBooksWithLimit(Pageable pageable);
}
