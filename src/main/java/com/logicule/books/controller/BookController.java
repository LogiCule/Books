package com.logicule.books.controller;

import com.logicule.books.entity.Book;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/api")
public class BookController {
    List<Book> bookList = new ArrayList<>(List.of(
            new Book("1984", "George Orwell", "Fiction"),
            new Book("To Kill a Mockingbird", "Harper Lee", "Fiction"),
            new Book("The Alchemist", "Paulo Coelho", "Fiction"),
            new Book("Sapiens", "Yuval Noah Harari", "Non-Fiction"),
            new Book("Atomic Habits", "James Clear", "Non-Fiction"),
            new Book("The Psychology of Money", "Morgan Housel", "Non-Fiction"),
            new Book("The Hobbit", "J.R.R. Tolkien", "Fiction"),
            new Book("Educated", "Tara Westover", "Non-Fiction"),
            new Book("The Catcher in the Rye", "J.D. Salinger", "Fiction"),
            new Book("Deep Work", "Cal Newport", "Non-Fiction")
    ));


    @GetMapping("/books")
    public List<Book> getBooks(){
        return bookList;
    }

}
