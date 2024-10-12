package com.prafull.secondshelf.services;

import com.prafull.secondshelf.dto.BookDto;
import com.prafull.secondshelf.model.Book;
import com.prafull.secondshelf.model.UserEntity;
import com.prafull.secondshelf.repositories.BookRepository;
import org.jetbrains.annotations.Nullable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService {

    private final BookRepository booksRepository;

    public BookService(BookRepository booksRepository) {
        this.booksRepository = booksRepository;
    }

    public void addBook(BookDto bookDto, UserEntity userEntity) {
        Book book = new Book(bookDto);
        book.setSeller(userEntity);
        booksRepository.save(book);
    }

    @Nullable
    public List<BookDto> getAllBooks() {
        List<Book> books = new ArrayList<>();
        booksRepository.findAll().forEach(books::add);
        return books.stream().map(Book::toBookDto).collect(Collectors.toList());
    }
}