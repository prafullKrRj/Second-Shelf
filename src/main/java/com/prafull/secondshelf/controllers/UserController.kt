package com.prafull.secondshelf.controllers

import com.prafull.secondshelf.dto.UserDto
import com.prafull.secondshelf.services.UserService
import org.springframework.http.ResponseEntity
import org.springframework.security.core.context.SecurityContextHolder
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
    @GetMapping("/books")
    fun getListedBooks(): ResponseEntity<Any> {
        try {
            val auth = SecurityContextHolder.getContext().authentication
            println(auth.name)
            val books = userService.getListedBooks(auth.name)
            return ResponseEntity.ok(books)
        } catch (e: Exception) {
            return ResponseEntity.badRequest().body(e.message)
        }
    }

    @PutMapping("/update")
    fun updateUser(@RequestBody userDto: UserDto): ResponseEntity<Any> {
        try {
            val auth = SecurityContextHolder.getContext().authentication
            userService.updateUser(auth.name, userDto)
            return ResponseEntity.ok(userDto)
        } catch (e: Exception) {
            return ResponseEntity.badRequest().body(e.message)
        }
    }

    @GetMapping("/sold-books")
    fun getSoldBooks(): ResponseEntity<Any> {
        try {
            val auth = SecurityContextHolder.getContext().authentication
            val books = userService.getSoldBooks(auth.name)
            return ResponseEntity.ok(books)
        } catch (e: Exception) {
            return ResponseEntity.badRequest().body(e.message)
        }
    }

    @GetMapping("/bought-books")
    fun getBoughtBooks(): ResponseEntity<Any> {
        try {
            val auth = SecurityContextHolder.getContext().authentication
            val books = userService.getBoughtBooks(auth.name)
            return ResponseEntity.ok(books)
        } catch (e: Exception) {
            return ResponseEntity.badRequest().body(e.message)
        }
    }

    @GetMapping
    fun getUser(): ResponseEntity<Any> {
        try {
            val auth = SecurityContextHolder.getContext().authentication
            val user = userService.getUser(auth.name)
            return ResponseEntity.ok(user.toDto())
        } catch (e: Exception) {
            return ResponseEntity.badRequest().body(e.message)
        }
    }
}