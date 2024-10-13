package com.prafull.secondshelf.dto

import lombok.Builder
import java.time.LocalDateTime

@Builder
data class BookDto(
    val title: String,
    val author: String,
    val yearOfPrinting: Int?,
    val description: String?,
    val genre: String?,
    val coverImageUrl: String?,
    val numberOfPages: Int?,
    val price: Double,
    val listedAt: LocalDateTime = LocalDateTime.now(),
    val id: Long? = 0
)