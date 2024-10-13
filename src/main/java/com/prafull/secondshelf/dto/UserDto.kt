package com.prafull.secondshelf.dto

import lombok.Builder

@Builder
data class UserDto(
    val username: String,
    val password: String,
    val fullName: String? = null,
    val mobileNumber: String? = null,
    val role: String? = "user"
)
