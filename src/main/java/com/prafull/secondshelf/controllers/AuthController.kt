package com.prafull.secondshelf.controllers

import com.prafull.secondshelf.dto.UserDto
import com.prafull.secondshelf.services.UserService
import org.springframework.http.ResponseEntity
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.*

// This controller will handle authentication-related requests in the future
@RestController
@RequestMapping("/auth")
class AuthController(
    private val userService: UserService
) {
    @PostMapping("/register")
    fun addUser(@RequestBody userDto: UserDto): ResponseEntity<Any> {
        try {
            userService.saveNewUser(userDto)
            return ResponseEntity.ok(RegisterResponse(true, "User registered successfully"))
        } catch (e: Exception) {
            return ResponseEntity.badRequest().body(e.message)
        }
    }

    @GetMapping("/login")
    fun login(): ResponseEntity<Any> {
        try {
            SecurityContextHolder.getContext().authentication
            return ResponseEntity.ok(LoginResponse(true, "User logged in successfully"))
        } catch (e: Exception) {
            return ResponseEntity.badRequest().body(e.message)
        }
    }
}

data class RegisterResponse(
    val success: Boolean,
    val message: String
)

data class LoginResponse(
    val success: Boolean,
    val message: String
)