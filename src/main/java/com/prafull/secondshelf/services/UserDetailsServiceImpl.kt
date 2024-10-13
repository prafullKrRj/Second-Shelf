package com.prafull.secondshelf.services

import com.prafull.secondshelf.model.UserEntity
import com.prafull.secondshelf.repositories.UserRepository
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class UserDetailsServiceImpl(
    private val userRepository: UserRepository
) : UserDetailsService {

    override fun loadUserByUsername(username: String?): UserDetails {
        val user: UserEntity =
            userRepository.findByUsername(username) ?: throw IllegalArgumentException("User not found")
        val dto = user.toDto();
        return User.builder().username(dto.username).password(dto.password).roles(dto.role).build()
    }
}