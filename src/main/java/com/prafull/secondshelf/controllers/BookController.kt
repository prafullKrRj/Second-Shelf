package com.prafull.secondshelf.controllers

import com.prafull.secondshelf.dto.BookDto
import com.prafull.secondshelf.services.BookService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

// This is a controller class for Book entity which will handle all the requests related to Book entity.

@RestController
@RequestMapping("/api/books")
class BookController(
    private val bookService: BookService
) {

    @GetMapping("/all")
    fun getAllBooks(): ResponseEntity<List<BookDto>> {
        return try {
            val books = bookService.getAllBooks()
            ResponseEntity.ok(books)
        } catch (e: Exception) {
            ResponseEntity.badRequest().body(null)
        }
    }
}