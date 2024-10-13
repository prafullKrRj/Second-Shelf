package com.prafull.secondshelf.controllers

import com.prafull.secondshelf.dto.BookDto
import com.prafull.secondshelf.services.BookService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

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

    @PostMapping("/add/{username}")
    fun addBook(@PathVariable username: String, @RequestBody bookDto: BookDto): ResponseEntity<Any> {
        return try {
            val book = bookService.addBook(username, bookDto)
            ResponseEntity.ok(book)
        } catch (e: Exception) {
            ResponseEntity.badRequest().body(e)
        }
    }

}