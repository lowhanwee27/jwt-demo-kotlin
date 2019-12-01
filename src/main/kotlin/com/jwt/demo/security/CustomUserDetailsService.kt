package com.jwt.demo.security

import com.jwt.demo.exception.ResourceNotFoundException
import com.jwt.demo.model.User
import com.jwt.demo.repository.UserRepository
import com.jwt.demo.security.UserPrincipal
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

/**
 * Created by rajeevkumarsingh on 02/08/17.
 */
@Service
class CustomUserDetailsService : UserDetailsService {
    @Autowired
    var userRepository: UserRepository? = null

    @Transactional
    @Throws(UsernameNotFoundException::class)
    override fun loadUserByUsername(usernameOrEmail: String): UserDetails { // Let people login with either username or email
        val user: User = userRepository?.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail) ?: throw UsernameNotFoundException("User not found with username or email : $usernameOrEmail")

            return UserPrincipal.create(user)
    }

    @Transactional
    fun loadUserById(keyId: Long): UserDetails {
        val user: User = userRepository?.findByKeyId(keyId) ?: throw ResourceNotFoundException("User", "keyId", keyId)

        return UserPrincipal.create(user)
    }
}