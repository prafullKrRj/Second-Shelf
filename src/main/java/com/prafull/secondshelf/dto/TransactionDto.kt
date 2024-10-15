package com.prafull.secondshelf.dto

import lombok.Builder

@Builder
data class TransactionDto(
    val bookId: Long,
    val transactionId: Long = 0,
    val amount: Double,
    val transactionDate: Long = System.currentTimeMillis(),
    val sellerUserName: String? = null,
    val buyerUserName: String? = null
)