package com.example.graphqlspringtest.service;

import com.example.graphqlspringtest.model.Author;
import com.example.graphqlspringtest.model.Book;
import com.example.graphqlspringtest.repository.AuthorRepository;
import com.example.graphqlspringtest.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthorService {
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    @Autowired
    public AuthorService(AuthorRepository authorRepository, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
//        addStarterData();
    }

    private void addStarterData(){
        Author author1 = authorRepository.save(Author.builder()
                .firstName("Joanne")
                .lastName("Rowling")
                .build());
        bookRepository.save(Book.builder()
                .name("Harry Potter and the Philosopher's Stone")
                .authorId(author1.getId())
                .pageCount(223)
                .build());

        Author author2 = authorRepository.save(Author.builder()
                .firstName("Herman")
                .lastName("Melville")
                .build());
        bookRepository.save(Book.builder()
                .name("Moby Dick")
                .authorId(author2.getId())
                .pageCount(635)
                .build());

        Author author3 = authorRepository.save(Author.builder()
                .firstName("Anne")
                .lastName("Rice")
                .build());
        bookRepository.save(Book.builder()
                .name("Interview with the vampire")
                .authorId(author3.getId())
                .pageCount(371)
                .build());
    }

    public Optional<Author> getById(Long authorId) {
        return authorRepository.findById(authorId);
    }
}
