package com.example.graphqlspringtest.repository;

import com.example.graphqlspringtest.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
    Optional<Author> findById(Long authorId);

    @Query(nativeQuery = true, value = "SELECT * FROM Author WHERE first_name = :first_name AND last_name = :last_name")
    Optional<Author> findByName(@Param("first_name") String firstName, @Param("last_name") String lastName);

}
