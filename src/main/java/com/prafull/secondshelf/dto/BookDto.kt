package com.prafull.secondshelf.dto

import lombok.Builder

@Builder
data class BookDto(
    val title: String?,
    val author: String?,
    val yearOfPrinting: Int?,
    val description: String?,
    val genre: String?,
    val coverImageUrl: String?,
    val numberOfPages: Int?,
    val price: Double,
    val listedAt: Long = System.currentTimeMillis(),
    val id: Long? = 0,
    val sellerUserName: String? = null,
    val sellerNumber: String? = null,
    val sellerFullName: String? = null
)