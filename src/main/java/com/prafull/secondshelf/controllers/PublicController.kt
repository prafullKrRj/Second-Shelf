package com.prafull.secondshelf.controllers

import com.prafull.secondshelf.services.UserService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/public")
class PublicController(
    private val userService: UserService
) {

    @GetMapping("/health-check")
    fun healthCheck(): String {
        return "I am healthy"
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