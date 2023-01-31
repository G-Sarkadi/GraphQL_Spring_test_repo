package com.example.graphqlspringtest.controller;

import com.example.graphqlspringtest.model.Author;
import com.example.graphqlspringtest.model.Book;
import com.example.graphqlspringtest.service.AuthorService;
import com.example.graphqlspringtest.service.BookService;
import lombok.AllArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Optional;

@Controller
@AllArgsConstructor
public class BookController {
    private final BookService bookService;
    private final AuthorService authorService;

    @QueryMapping
    public Book bookById(@Argument String id) {
        Optional<Book> optionalBook = bookService.getById(Long.valueOf(id));
        return optionalBook.orElseGet(Book::new);
    }

    @QueryMapping
    public List<Book> allBooks(@Argument Integer limit){
        if (limit == null) {
            return bookService.getAllBooks();
        }
        return bookService.getAllBooks(limit);
    }

    @SchemaMapping
    public Author author(Book book) {
        Optional<Author> optionalAuthor = authorService.getById(book.getAuthorId());
        return optionalAuthor.orElseGet(Author::new);
    }
}
