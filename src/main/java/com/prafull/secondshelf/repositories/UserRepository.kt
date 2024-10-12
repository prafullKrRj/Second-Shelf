package com.prafull.secondshelf.repositories

import com.prafull.secondshelf.model.UserEntity
import org.springframework.data.jpa.repository.JpaRepository

import org.springframework.stereotype.Repository

@Repository
interface UserRepository : JpaRepository<UserEntity, Long> {

}

/**
 * This interface is used to handle CRUD operations for UserEntity.
 *
 * JpaRepository extends PagingAndSortingRepository which in turn extends CrudRepository.
 * JpaRepository provides JPA related methods such as flushing the persistence context and deleting records in a batch.
 * CrudRepository mainly provides basic CRUD operations.
 */