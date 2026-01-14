package com.logicule.books.mapper;

import com.logicule.books.dto.CreateBookRequest;
import com.logicule.books.dto.UpdateBookRequest;
import com.logicule.books.entity.Book;

public class BookMapper {

    public static Book toEntity(CreateBookRequest req, long id) {
        Book book = new Book();
        book.setId(id);
        book.setTitle(req.getTitle());
        book.setAuthor(req.getAuthor());
        book.setCategory(req.getCategory());
        book.setRating(req.getRating());
        return book;
    }

    public static void updateEntity(Book book, UpdateBookRequest req) {
        if (req.getTitle() != null)
            book.setTitle(req.getTitle());

        if (req.getAuthor() != null)
            book.setAuthor(req.getAuthor());

        if (req.getCategory() != null)
            book.setCategory(req.getCategory());

        if (req.getRating() != null)
            book.setRating(req.getRating());
    }
}
