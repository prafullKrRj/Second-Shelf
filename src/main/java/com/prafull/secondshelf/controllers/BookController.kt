package com.prafull.secondshelf.controllers

import com.prafull.secondshelf.dto.BookDto
import com.prafull.secondshelf.dto.TransactionDto
import com.prafull.secondshelf.services.BookService
import org.springframework.http.ResponseEntity
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.*

// This is a controller class for Book entity which will handle all the requests related to Book entity.

@RestController
@RequestMapping("/api/books")
class BookController(
    private val bookService: BookService
) {
    @GetMapping("/search")
    fun searchBooks(@RequestParam("query") query: String): ResponseEntity<Any> {
        return try {
            val auth = SecurityContextHolder.getContext().authentication
            val books = bookService.searchBooks(query, auth.name)
            ResponseEntity.ok(books)
        } catch (e: Exception) {
            ResponseEntity.badRequest().body(null)
        }
    }

    @PostMapping("/add")
    fun addBook(@RequestBody bookDto: BookDto): ResponseEntity<Any> {
        return try {
            val auth = SecurityContextHolder.getContext().authentication
            val book = bookService.addBook(auth.name, bookDto)
            ResponseEntity.ok(book)
        } catch (e: Exception) {
            ResponseEntity.badRequest().body(e)
        }
    }

    @PostMapping("/addMultiple")
    fun addMultipleBooks(@RequestBody booksDto: List<BookDto>): ResponseEntity<Any> {
        return try {
            val auth = SecurityContextHolder.getContext().authentication
            val books = bookService.addMultipleBooks(auth.name, booksDto)
            ResponseEntity.ok(books)
        } catch (e: Exception) {
            ResponseEntity.badRequest().body(e)
        }
    }

    @PostMapping("/sell-book")
    fun soldBook(@RequestBody transaction: TransactionDto): ResponseEntity<Any> {
        try {
            val auth = SecurityContextHolder.getContext().authentication
            bookService.sellBook(
                auth.name, transaction.copy(
                    sellerUserName = auth.name
                )
            )
            return ResponseEntity.ok(GeneralResponse(success = true, "Book sold successfully"))
        } catch (e: Exception) {
            return ResponseEntity.badRequest().body(e.message)
        }
    }

    @GetMapping("/getRandomBooks")
    fun getRandomBooks(): ResponseEntity<Any> {
        return try {
            val auth = SecurityContextHolder.getContext().authentication
            val books = bookService.getRandomBooks(auth.name)
            ResponseEntity.ok(books)
        } catch (e: Exception) {
            ResponseEntity.badRequest().body(e.message)
        }
    }
}