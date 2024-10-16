package com.prafull.secondshelf.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.prafull.secondshelf.dto.UserDto;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;


    @Column(name = "full_name")
    private String fullName;

    @Column(name = "mobile_number")
    private String mobileNumber;

    @Column(name = "created_at")
    private Long createdAt = System.currentTimeMillis();


    @OneToMany(mappedBy = "seller", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<Book> listedBooks = new HashSet<>();

    @OneToMany(mappedBy = "buyer", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<Transaction> boughtBooks = new HashSet<>();

    @OneToMany(mappedBy = "seller", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonManagedReference
    private Set<Transaction> soldBooks = new HashSet<>();

    @Column(name = "role")
    private String role;


    public UserEntity(UserDto userDto) {
        this.username = userDto.getUsername();
        this.password = userDto.getPassword();
        this.fullName = userDto.getFullName();
        this.mobileNumber = userDto.getMobileNumber();
        this.role = userDto.getRole();
    }

    public void addBook(Book book) {
        listedBooks.add(book);
        book.setSeller(this);
    }

    public UserDto toDto() {
        return new UserDto(
                username,
                password,
                fullName,
                mobileNumber,
                role
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        UserEntity user = (UserEntity) obj;
        return Objects.equals(id, user.id) &&
                Objects.equals(username, user.username);
    }
}
