package com.prafull.secondshelf.services;

import com.prafull.secondshelf.dto.BookDto;
import com.prafull.secondshelf.dto.TransactionDto;
import com.prafull.secondshelf.dto.UserDto;
import com.prafull.secondshelf.model.Book;
import com.prafull.secondshelf.model.UserEntity;
import com.prafull.secondshelf.repositories.UserRepository;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service
public class UserService {

    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    private static final Logger logger = Logger.getLogger(UserService.class.getName());
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void saveNewUser(UserDto user) throws Exception {
        if (userRepository.findByUsername(user.getUsername()) != null) {
            throw new Exception("User already exists");
        }
        userRepository.save(new UserEntity(
                new UserDto(user.getUsername(), encoder.encode(user.getPassword()), user.getFullName(), user.getMobileNumber(), user.getRole())
        ));
    }


    public UserEntity getUser(String username) throws Exception {
        UserEntity user = userRepository.findByUsername(username);
        if (user == null) {
            throw new Exception("User Not found");
        }
        return user;
    }


    @NotNull
    public List<String> getAllUsers() {
        return userRepository.getAllUsers();
    }

    public void saveUser(UserEntity user) {
        userRepository.save(user);
    }

    @Nullable
    public List<BookDto> getListedBooks(@NotNull String username) {
        List<Book> books = userRepository.getBooksFromUser(username);
        return books.stream().map(Book::toBookDto).collect(Collectors.toList());
    }

    public void updateUser(@NotNull String username, @NotNull UserDto userDto) {
        UserEntity user = userRepository.findByUsername(username);
        if (user == null) {
            logger.warning("User not found");
            return;
        }
        user.setUsername(userDto.getUsername());
        user.setPassword(userDto.getPassword());
        user.setFullName(userDto.getFullName());
        user.setMobileNumber(userDto.getMobileNumber());
        userRepository.save(user);
    }

    public void soldBook(@NotNull String username, @NotNull TransactionDto transaction) throws Exception {
    }

    public UserEntity getUserFromId(long buyerId) throws Exception {
        return userRepository.findById(buyerId).orElseThrow(() -> new Exception("User not found"));
    }
}