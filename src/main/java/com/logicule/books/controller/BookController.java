package com.logicule.books.controller;

import com.logicule.books.dto.CreateBookRequest;
import com.logicule.books.dto.UpdateBookRequest;
import com.logicule.books.entity.Book;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;


@RestController
@RequestMapping("/api/books")
public class BookController {
    List<Book> bookList = new ArrayList<Book>(List.of(
            new Book(1,"1984", "George Orwell", "Fiction",5),
            new Book(2,"To Kill a Mockingbird", "Harper Lee", "Fiction",4),
            new Book(3,"The Alchemist", "Paulo Coelho", "Fiction",3),
            new Book(4,"Sapiens", "Yuval Noah Harari", "Non-Fiction",2),
            new Book(5,"Atomic Habits", "James Clear", "Non-Fiction",1),
            new Book(6,"The Psychology of Money", "Morgan Housel", "Non-Fiction",5),
            new Book(7,"The Hobbit", "J.R.R. Tolkien", "Fiction",3),
            new Book(8,"Educated", "Tara Westover", "Non-Fiction",4),
            new Book(9,"Deep Work", "Cal Newport", "Non-Fiction",2),
            new Book(10,"The Catcher in the Rye", "J.D. Salinger", "Fiction",1)
    ));

    private final AtomicLong idGenerator = new AtomicLong(11);
    @GetMapping
    public List<Book> getBooks(@RequestParam(required = false) String category){
        if(category==null)
            return bookList;
        return bookList.stream().filter(book->book.getCategory().equalsIgnoreCase(category)).toList();
    }

    @GetMapping("/{id}")
    public Book getBookById(@PathVariable long id){
        return bookList.stream()
                .filter(book -> book.getId() == id)
                .findFirst()
                .orElse(null);
    }

    @GetMapping("/search/{title}")
    public List<Book> getBookByTitle(@PathVariable String title){
        return  bookList.stream()
                .filter(book -> book.getTitle().toLowerCase().contains(title.toLowerCase())).toList();
    }

    @PostMapping
    public Book addBook(@RequestBody CreateBookRequest book){
        Book newBook = new Book(idGenerator.getAndIncrement(), book.getTitle(), book.getAuthor(), book.getCategory(), book.getRating());
        bookList.add(newBook);
        return newBook;
    }

    @PutMapping("/{id}")
    public Book updateBook(@PathVariable int id, @RequestBody UpdateBookRequest updatedBook){
        for (Book book : bookList) {
            if (book.getId() == id) {
                book.setTitle(updatedBook.getTitle());
                book.setAuthor(updatedBook.getAuthor());
                book.setCategory(updatedBook.getCategory());
                book.setRating(updatedBook.getRating());
                return book;
            }
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public String deleteBook(@PathVariable long id){
        for (int i = 0; i < bookList.size(); i++) {
            if(bookList.get(i).getId() == id){
                bookList.remove(i);
                return "Book with id "+id+" deleted successfully.";
            }
        }
        return "Book with id "+id+" not found.";
    }
}
