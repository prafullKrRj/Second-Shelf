package com.prafull.secondshelf.dto

import lombok.Builder
import java.time.LocalDateTime

@Builder
data class TransactionDto(
    val bookId: Long,
    val amount: Double,
    val transactionDate: LocalDateTime = LocalDateTime.now(),
    val sellerUserName: String,
    val buyerUserName: String
)