package com.prafull.secondshelf.repositories

import com.prafull.secondshelf.model.Book
import com.prafull.secondshelf.model.UserEntity
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : CrudRepository<UserEntity, Long> {

    fun findByUsername(username: String): UserEntity?

    @Query("select username from UserEntity ")
    fun getAllUsers(): List<String>

    @Query("SELECT u.listedBooks FROM UserEntity u WHERE u.username = :username")
    fun getBooksFromUser(@Param("username") username: String): List<Book?>


}

/**
 * This interface is used to handle CRUD operations for UserEntity.
 *
 * JpaRepository extends PagingAndSortingRepository which in turn extends CrudRepository.
 * JpaRepository provides JPA related methods such as flushing the persistence context and deleting records in a batch.
 * CrudRepository mainly provides basic CRUD operations.
 */