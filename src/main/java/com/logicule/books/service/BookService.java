package com.logicule.books.service;

import com.logicule.books.dto.CreateBookRequest;
import com.logicule.books.dto.UpdateBookRequest;
import com.logicule.books.entity.Book;
import com.logicule.books.mapper.BookMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class BookService {

    private final List<Book> bookList = new ArrayList<>(List.of(
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

    public List<Book> getBooks(String category) {
        if (category == null) return bookList;

        return bookList.stream()
                .filter(book -> book.getCategory().equalsIgnoreCase(category))
                .toList();
    }

    public Book getBookById(long id) {
        return bookList.stream()
                .filter(book -> book.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public List<Book> searchByTitle(String title) {
        return bookList.stream()
                .filter(book -> book.getTitle().toLowerCase().contains(title.toLowerCase()))
                .toList();
    }

    public Book addBook(CreateBookRequest request) {
        Book book = BookMapper.toEntity(request, idGenerator.getAndIncrement());
        bookList.add(book);
        return book;
    }

    public Book updateBook(long id, UpdateBookRequest request) {
        for (Book book : bookList) {
            if (book.getId() == id) {
                BookMapper.updateEntity(book, request);
                return book;
            }
        }
        return null;
    }

    public boolean deleteBook(long id) {
        return bookList.removeIf(book -> book.getId() == id);
    }
}
