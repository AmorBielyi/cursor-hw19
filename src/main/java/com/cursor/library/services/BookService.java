package com.cursor.library.services;

import com.cursor.library.daos.BookDao;
import com.cursor.library.exceptions.BadIdException;
import com.cursor.library.exceptions.BookNameIsNullException;
import com.cursor.library.exceptions.BookNameIsTooLongException;
import com.cursor.library.models.Book;
import com.cursor.library.models.BookDto;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class BookService {

    public final BookDao bookDao;

    public BookService(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    public Book getById(final String bookId) {
        if (bookId == null || bookId.isBlank()) {
            throw new BadIdException();
        }
        return bookDao.getById(bookId);
    }

    public Book createBook(final BookDto bookDto) {
        final Book newBook = new Book(UUID.randomUUID().toString());
        newBook.setName(getValidatedBookName(bookDto.getName()));
        newBook.setDescription(bookDto.getDescription());
        newBook.setAuthors(bookDto.getAuthors());
        newBook.setNumberOfWords(bookDto.getNumberOfWords());
        newBook.setRating(bookDto.getRating());
        newBook.setYearOfPublication(bookDto.getYearOfPublication());

        return bookDao.addBook(newBook);
    }

    public String getValidatedBookName(final String name) {
        if (name == null) {
            throw new BookNameIsNullException();
        }
        if (name.length() > 1000) {
            throw new BookNameIsTooLongException();
        }
        return name.trim();
    }
}
