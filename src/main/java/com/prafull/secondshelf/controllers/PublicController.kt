package com.prafull.secondshelf.controllers

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/public")
class PublicController {

    @GetMapping("/health-check")
    fun healthCheck(): String {
        return "I am healthy"
    }

}