package com.jwt.demo.repository


import com.jwt.demo.model.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

/**
 * Created by rajeevkumarsingh on 02/08/17.
 */
@Repository
interface UserRepository : JpaRepository<User?, Long?> {
    fun findByEmail(email: String?): Optional<User?>?
    fun findByUsernameOrEmail(username: String?, email: String?): User?
    fun findByKeyId(keyId: Long) : User?
    fun findByIdIn(userIds: List<Long?>?): List<User?>?
    fun findByUsername(username: String?): Optional<User?>?
    fun existsByUsername(username: String?): Boolean?
    fun existsByEmail(email: String?): Boolean?
}