package com.prafull.secondshelf.model;

import com.prafull.secondshelf.dto.BookDto;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "books")
@Data
@NoArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String author;

    @Column(name = "year_of_printing")
    private Integer yearOfPrinting;

    @Column(columnDefinition = "TEXT")
    private String description;

    private String genre;

    @Column(name = "cover_image_url")
    private String coverImageUrl;

    @Column(name = "number_of_pages")
    private Integer numberOfPages;

    @Column(nullable = false)
    private Double price;

    @ManyToOne
    @JoinColumn(name = "seller_id", nullable = false)
    private UserEntity seller;

    @Column(name = "listed_at")
    private LocalDateTime listedAt;

    @Column(name = "is_available")
    private boolean isAvailable;

    public Book(BookDto book) {
        this.title = book.getTitle();
        this.author = book.getAuthor();
        this.yearOfPrinting = book.getYearOfPrinting();
        this.description = book.getDescription();
        this.genre = book.getGenre();
        this.coverImageUrl = book.getCoverImageUrl();
        this.numberOfPages = book.getNumberOfPages();
        this.price = book.getPrice();
        this.listedAt = book.getListedAt();
    }

    public BookDto toBookDto() {
        return new BookDto(title, author, yearOfPrinting, description, genre, coverImageUrl, numberOfPages, price, listedAt);
    }
}