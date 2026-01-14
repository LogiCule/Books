package com.logicule.books.controller;

import com.logicule.books.dto.CreateBookRequest;
import com.logicule.books.dto.UpdateBookRequest;
import com.logicule.books.entity.Book;
import com.logicule.books.service.BookService;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public List<Book> getBooks(@RequestParam(required = false) String category) {
        return bookService.getBooks(category);
    }

    @GetMapping("/{id}")
    public Book getBookById(@PathVariable long id) {
        return bookService.getBookById(id);
    }

    @GetMapping("/search/{title}")
    public List<Book> getBookByTitle(@PathVariable String title) {
        return bookService.searchByTitle(title);
    }

    @PostMapping
    public Book addBook(@RequestBody CreateBookRequest book) {
        return bookService.addBook(book);
    }

    @PutMapping("/{id}")
    public Book updateBook(@PathVariable long id,
                           @RequestBody UpdateBookRequest updatedBook) {
        return bookService.updateBook(id, updatedBook);
    }

    @DeleteMapping("/{id}")
    public String deleteBook(@PathVariable long id) {
        boolean deleted = bookService.deleteBook(id);
        return deleted
                ? "Book with id " + id + " deleted successfully."
                : "Book with id " + id + " not found.";
    }
}
