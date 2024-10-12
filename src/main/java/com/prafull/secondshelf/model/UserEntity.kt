package com.prafull.secondshelf.model

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import lombok.NoArgsConstructor

/**
 * Represents a user entity in the database.
 *
 * Annotations:
 * - @Entity: Specifies that the class is an entity and is mapped to a database table.
 * - @NoArgsConstructor: Generates a no-argument constructor.
 * - @Id: Specifies the primary key of an entity.
 * - @GeneratedValue: Provides for the specification of generation strategies for the values of primary keys.
 *
 * Fields:
 * - id: The unique identifier for the user, generated automatically.
 */
@Entity
@NoArgsConstructor
data class UserEntity(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Long = 0
)