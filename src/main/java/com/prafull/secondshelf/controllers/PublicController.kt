package com.prafull.secondshelf.controllers

import com.prafull.secondshelf.dto.BookDto
import com.prafull.secondshelf.dto.UserDto
import com.prafull.secondshelf.services.BookService
import com.prafull.secondshelf.services.UserService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/public")
class PublicController(
    private val userService: UserService,
    private val bookService: BookService
) {

    @GetMapping("/health-check")
    fun healthCheck(): String {
        return "I am healthy"
    }

    @GetMapping("/all")
    fun getAllBooks(): ResponseEntity<List<BookDto>> {
        return try {
            val books = bookService.getAllBooks()
            ResponseEntity.ok(books)
        } catch (e: Exception) {
            ResponseEntity.badRequest().body(null)
        }
    }

    @GetMapping("/getUsers")
    fun getUsers(): ResponseEntity<Any> {
        try {
            val users = userService.allUsers
            return ResponseEntity.ok(users)
        } catch (e: Exception) {
            return ResponseEntity.badRequest().body(e.message)
        }
    }


}