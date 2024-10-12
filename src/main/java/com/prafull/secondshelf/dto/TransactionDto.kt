package com.prafull.secondshelf.dto

import lombok.Builder

@Builder
data class TransactionDto(
    val bookId: Long,
    val amount: String
)