package com.prafull.secondshelf.services;

import com.prafull.secondshelf.dto.BookDto;
import com.prafull.secondshelf.dto.UserDto;
import com.prafull.secondshelf.model.Book;
import com.prafull.secondshelf.model.UserEntity;
import com.prafull.secondshelf.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final BookService bookService;

    public UserService(UserRepository userRepository, BookService bookService) {
        this.userRepository = userRepository;
        this.bookService = bookService;
    }

    public void saveNewUser(UserDto user) throws Exception {
        if (userRepository.findByUsername(user.getUsername()) != null) {
            throw new Exception("User already exists");
        }
        userRepository.save(new UserEntity(user));
    }

    public List<BookDto> getAllBooksToSellByUsername(String username) throws Exception {
        UserEntity userEntity = userRepository.findByUsername(username);
        if (userEntity != null) {
            return userRepository.getBooksFromUser(username).stream().map(Book::toBookDto).collect(Collectors.toList());
        } else {
            throw new Exception("User not found");
        }
    }

    @Transactional
    public void addBookToSell(String username, BookDto book) throws Exception {
        UserEntity savedUser = userRepository.findByUsername(username);
        if (savedUser == null) {
            throw new Exception("User not found");
        }
        Book book1 = new Book(book);
        book1.setSeller(savedUser);
        savedUser.getListedBooks().add(book1);
        userRepository.save(savedUser);
        bookService.addBook(book, savedUser);
    }
}