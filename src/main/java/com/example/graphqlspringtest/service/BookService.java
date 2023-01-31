package com.example.graphqlspringtest.service;

import com.example.graphqlspringtest.model.Book;
import com.example.graphqlspringtest.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class BookService {
    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Optional<Book> getById(UUID id) {
        return bookRepository.findById(id);
    }

    public List<Book> getAllBooks(Integer limit) {
        return bookRepository.getAllBooksWithLimit(PageRequest.of(0,limit));
    }
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

}
