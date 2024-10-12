package com.prafull.secondshelf.controllers

import com.prafull.secondshelf.dto.BookDto
import com.prafull.secondshelf.dto.UserDto
import com.prafull.secondshelf.services.UserService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

/*
 *     Created by Prafull on 25/08/2021.
 *     Description:
 *          This is a controller class for UserEntity related operations.
 * */
@RestController
@RequestMapping("/api/user")
class UserController(
    private val userService: UserService
) {

    @PostMapping("/add")
    fun addUser(@RequestBody userDto: UserDto): ResponseEntity<Any> {
        try {
            userService.saveNewUser(userDto)
            return ResponseEntity.ok("User added successfully")
        } catch (e: Exception) {
            return ResponseEntity.badRequest().body(e.message)
        }
    }

    @GetMapping("/books/{username}")
    fun getListedBooks(@PathVariable username: String): ResponseEntity<Any> {
        try {
            val books = userService.getAllBooksToSellByUsername(username)
            return ResponseEntity.ok(books)
        } catch (e: Exception) {
            return ResponseEntity.badRequest().body(e.message)
        }
    }

    @PostMapping("/books/{username}")
    fun addBook(@PathVariable username: String, @RequestBody book: BookDto): ResponseEntity<Any> {
        try {
            userService.addBookToSell(username, book)
            return ResponseEntity.ok("Book added successfully")
        } catch (e: Exception) {
            return ResponseEntity.badRequest().body(e.message)
        }
    }
}