package com.logicule.books.controller;

import com.logicule.books.dto.CreateBookRequest;
import com.logicule.books.dto.UpdateBookRequest;
import com.logicule.books.entity.Book;
import com.logicule.books.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name="Book REST API endpoints",description = "operations on books in the library")
@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @Operation(summary = "Get all books or filter by category",
               description = "Retrieve a list of all books in the library. Optionally, filter the books by category by providing a category query parameter.")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public List<Book> getBooks(@Parameter(description = "optional query parameter") @RequestParam(required = false) String category) {
        return bookService.getBooks(category);
    }

    @Operation(summary = "Get book by ID",
               description = "Retrieve a specific book by its unique ID.")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public Book getBookById(@Parameter(description = "Id of book to be retrieved") @PathVariable @Min(value=1) long id) {
        return bookService.getBookById(id);
    }

    @Operation(summary = "Search books by title",
               description = "Search for books that contain the specified title string.")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/search/{title}")
    public List<Book> getBookByTitle(@Parameter( description = "search key") @PathVariable String title) {
        return bookService.searchByTitle(title);
    }

    @Operation(summary = "Add a new book",
               description = "Add a new book to the library by providing the book details in the request body.")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Book createBook(@Valid @RequestBody CreateBookRequest book) {
        return bookService.addBook(book);
    }

    @Operation(summary = "Update an existing book",
               description = "Update the details of an existing book by its ID. Provide the updated book details in the request body.")
    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{id}")
    public Book updateBook(@Parameter(description = "Id of book to be updated") @PathVariable @Min(value=1) long id,
                          @Valid @RequestBody UpdateBookRequest updatedBook) {
        return bookService.updateBook(id, updatedBook);
    }

    @Operation(summary = "Delete a book",
               description = "Delete a book from the library by its unique ID.")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteBook(@Parameter(description = "Id of book to be deleted") @PathVariable @Min(value=1) long id) {
         bookService.deleteBook(id);

    }
}
