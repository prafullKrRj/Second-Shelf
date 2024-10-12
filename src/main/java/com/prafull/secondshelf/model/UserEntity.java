package com.prafull.secondshelf.model;

import com.prafull.secondshelf.dto.UserDto;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.HashSet;
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
    private LocalDateTime createdAt = LocalDateTime.now();


    @OneToMany(mappedBy = "seller", cascade = CascadeType.ALL)
    private Set<Book> listedBooks = new HashSet<>();

    @OneToMany(mappedBy = "buyer", cascade = CascadeType.ALL)
    private Set<Transaction> boughtBooks = new HashSet<>();

    @OneToMany(mappedBy = "seller", cascade = CascadeType.ALL)
    private Set<Transaction> soldBooks = new HashSet<>();

    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "role", nullable = false)
    @Enumerated(EnumType.STRING)
    private Set<Role> roles = new HashSet<>();


    public UserEntity(UserDto userDto) {
        this.username = userDto.getUsername();
        this.password = userDto.getPassword();
        this.fullName = userDto.getFullName();
        this.mobileNumber = userDto.getMobileNumber();
    }

    public UserDto toDto() {
        return new UserDto(
                username,
                password,
                fullName,
                mobileNumber
        );
    }
}
