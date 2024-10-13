package com.prafull.secondshelf.repositories

import com.prafull.secondshelf.model.Transaction
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface TransactionRepository : CrudRepository<Transaction, Long> {
}