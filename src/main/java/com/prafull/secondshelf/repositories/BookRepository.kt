package com.prafull.secondshelf.repositories

import com.prafull.secondshelf.model.BookEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface BookRepository : JpaRepository<BookEntity, Long> {
}