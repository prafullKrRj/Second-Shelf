package com.prafull.secondshelf.repositories


import com.prafull.secondshelf.model.Book
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface BookRepository : CrudRepository<Book, Long> {

    @Query(
        "SELECT b FROM Book b JOIN FETCH b.seller " +
                "WHERE (LOWER(b.title) LIKE LOWER(CONCAT('%', :query, '%')) " +
                "OR LOWER(b.description) LIKE LOWER(CONCAT('%', :query, '%')) " +
                "OR LOWER(b.genre) LIKE LOWER(CONCAT('%', :query, '%')) " +
                "OR LOWER(b.author) LIKE LOWER(CONCAT('%', :query, '%'))) " +
                "AND b.seller.id = :sellerId"
    )
    fun searchBooks(@Param("query") query: String?, @Param("sellerId") sellerId: Long?): List<Book?>?
}