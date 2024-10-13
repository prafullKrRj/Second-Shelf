package com.prafull.secondshelf.controllers

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

    @GetMapping("/{username}/books")
    fun getListedBooks(@PathVariable username: String): ResponseEntity<Any> {
        try {
            val books = userService.getListedBooks(username)
            return ResponseEntity.ok(books)
        } catch (e: Exception) {
            return ResponseEntity.badRequest().body(e.message)
        }
    }

    @PutMapping("/{username}")
    fun updateUser(@PathVariable username: String, @RequestBody userDto: UserDto): ResponseEntity<Any> {
        try {
            userService.updateUser(username, userDto)
            return ResponseEntity.ok("User updated successfully")
        } catch (e: Exception) {
            return ResponseEntity.badRequest().body(e.message)
        }
    }
}