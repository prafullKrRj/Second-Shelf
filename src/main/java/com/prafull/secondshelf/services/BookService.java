package com.prafull.secondshelf.services;

import com.prafull.secondshelf.dto.BookDto;
import com.prafull.secondshelf.dto.TransactionDto;
import com.prafull.secondshelf.model.Book;
import com.prafull.secondshelf.model.Transaction;
import com.prafull.secondshelf.model.UserEntity;
import com.prafull.secondshelf.repositories.BookRepository;
import jakarta.transaction.Transactional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class BookService {

    private final BookRepository booksRepository;
    private final UserService userService;
    private final TransactionService transactionService;

    public BookService(BookRepository booksRepository, UserService us, TransactionService ts) {
        this.booksRepository = booksRepository;
        this.userService = us;
        this.transactionService = ts;
    }

    public List<BookDto> getAllBooksToSellByUsername(String username) throws Exception {
        UserEntity user = userService.getUser(username);
        return user.getListedBooks().stream().map(Book::toBookDto).collect(Collectors.toList());
    }

    @Transactional
    public void deleteListing(String username, Long bookId) throws Exception {
        UserEntity user = userService.getUser(username);
        Optional<Book> book = getBookById(bookId);
        if (book.isEmpty()) throw new Exception("Wrong Book Id");
        user.getListedBooks().removeIf(book1 -> Objects.equals(book1.getId(), bookId));
        userService.saveUser(user);
        deleteBook(bookId);
    }


    @Transactional // for proper synchronization
    public void deleteAllListing(@NotNull String username) throws Exception {
        UserEntity user = userService.getUser(username);
        deleteBooks(user.getListedBooks().stream().map(Book::getId).collect(Collectors.toList()));
        user.setListedBooks(Collections.emptySet());
    }

    /*
     * The issue is that the book is being added to both the
     * `listedBooks` set of the `UserEntity` and the `booksRepository`.
     *  This can cause the book to be saved twice. You should only add the book to the
     *  `booksRepository` and let the persistence context handle the relationship.
     */
    @Transactional
    public void addBook(String username, BookDto bookDto) throws Exception {
        UserEntity user = userService.getUser(username);
        Book book = new Book(bookDto);
        book.setSeller(user);
        booksRepository.save(book);
        user.getListedBooks().add(book);
        userService.saveUser(user);
    }

    @Nullable
    public List<BookDto> getAllBooks() {
        List<Book> books = new ArrayList<>();
        booksRepository.findAll().forEach(books::add);
        return books.stream().map(Book::toBookDto).collect(Collectors.toList());
    }

    public void deleteBook(Long bookId) {
        booksRepository.deleteById(bookId);
    }

    public Optional<Book> getBookById(Long bookId) {
        return booksRepository.findById(bookId);
    }

    public void deleteBooks(List<Long> bookIds) throws Exception {
        for (Long bookId : bookIds) {
            deleteBook(bookId);
        }
    }


    @Transactional
    public void soldBook(@NotNull String username, @NotNull TransactionDto transaction) throws Exception {
        UserEntity seller = userService.getUser(username);
        UserEntity buyer = userService.getUser(transaction.getBuyerUserName());
        Book book = getBookById(transaction.getBookId()).orElseThrow(() -> new Exception("Book not found"));
        book.setAvailable(false);
        Transaction transaction1 = transactionService.saveTransaction(transaction, seller, buyer, book);
        seller.getSoldBooks().add(transaction1);
        buyer.getBoughtBooks().add(transaction1);
        userService.saveUser(seller);
        userService.saveUser(buyer);
        booksRepository.save(book);
    }
}