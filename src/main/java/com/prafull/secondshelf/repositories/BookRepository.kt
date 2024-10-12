package com.prafull.secondshelf.repositories

import com.prafull.secondshelf.model.Book
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface BookRepository : CrudRepository<Book, Long> {

}