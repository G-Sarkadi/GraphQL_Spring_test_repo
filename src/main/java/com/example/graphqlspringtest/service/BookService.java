package com.example.graphqlspringtest.service;

import com.example.graphqlspringtest.model.Book;
import com.example.graphqlspringtest.model.Author;
import com.example.graphqlspringtest.repository.AuthorRepository;
import com.example.graphqlspringtest.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    @Autowired
    public BookService(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    public Optional<Book> getById(Long id) {
        return bookRepository.findById(id);
    }

    public List<Book> getAllBooks(Integer limit) {
        return bookRepository.getAllBooksWithLimit(PageRequest.of(0,limit));
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Book saveNewBook(String name, int pageCount, Author author) {
        Optional<Author> optionalAuthor = authorRepository.findByName(author.getFirstName(), author.getLastName());
        Long authorId;
        if (optionalAuthor.isPresent()) {
            authorId = optionalAuthor.get().getId();
        } else {
            authorId = authorRepository.save(author).getId();
        }
        Book newBook = Book.builder()
                .name(name)
                .pageCount(pageCount)
                .authorId(authorId)
                .build();

        return bookRepository.save(newBook);
    }
}
