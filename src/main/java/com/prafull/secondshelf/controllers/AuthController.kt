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
            return ResponseEntity.ok(GeneralResponse(true, "User logged in successfully"))
        } catch (e: Exception) {
            return ResponseEntity.badRequest().body(e.message)
        }
    }

    @GetMapping("/login")
    fun login(): ResponseEntity<Any> {
        try {
            val userInfo = userService.getUser(SecurityContextHolder.getContext().authentication.name)
            return ResponseEntity.ok(userInfo.toDto())
        } catch (e: Exception) {
            return ResponseEntity.badRequest().body(e.message)
        }
    }
}

data class GeneralResponse(
    val success: Boolean,
    val message: String
)