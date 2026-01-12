package com.logicule.books.controller;

import com.logicule.books.entity.Book;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/api")
public class BookController {
    List<Book> bookList = new ArrayList<>(List.of(
            new Book(1,"1984", "George Orwell", "Fiction"),
            new Book(2,"To Kill a Mockingbird", "Harper Lee", "Fiction"),
            new Book(3,"The Alchemist", "Paulo Coelho", "Fiction"),
            new Book(4,"Sapiens", "Yuval Noah Harari", "Non-Fiction"),
            new Book(5,"Atomic Habits", "James Clear", "Non-Fiction"),
            new Book(6,"The Psychology of Money", "Morgan Housel", "Non-Fiction"),
            new Book(7,"The Hobbit", "J.R.R. Tolkien", "Fiction"),
            new Book(8,"Educated", "Tara Westover", "Non-Fiction"),
            new Book(9,"Deep Work", "Cal Newport", "Non-Fiction"),
            new Book(10,"The Catcher in the Rye", "J.D. Salinger", "Fiction")
    ));


    @GetMapping("/books")
    public List<Book> getBooks(@RequestParam(required = false) String category){
        if(category==null)
            return bookList;
        return bookList.stream().filter(book->book.getCategory().equalsIgnoreCase(category)).toList();
    }

    @GetMapping("/books/{id}")
    public Book getBookById(@PathVariable int id){
        return bookList.stream()
                .filter(book -> book.getId() == id)
                .findFirst()
                .orElse(null);
    }

    @GetMapping("/books/search/{title}")
    public Book getBookByTitle(@PathVariable String title){
        return bookList.stream()
                .filter(book -> book.getTitle().toLowerCase().contains(title.toLowerCase()))
                .findFirst()
                .orElse(null);
    }

    @PostMapping("/books")
    public Book addBook(@RequestBody Book book){
        if(bookList.contains(book))
            return null;
        bookList.add(book);
        return book;
    }

    @PutMapping("/books/{id}")
    public Book updateBook(@PathVariable int id, @RequestBody Book updatedBook){
        for (int i = 0; i < bookList.size(); i++) {
            if(bookList.get(i).getId() == id){
                bookList.set(i, updatedBook);
                return updatedBook;
            }
        }
        return null;
    }
}
