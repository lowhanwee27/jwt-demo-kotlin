package com.jwt.demo.repository

import com.jwt.demo.model.Role
import com.jwt.demo.model.RoleName
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

/**
 * Created by rajeevkumarsingh on 02/08/17.
 */
@Repository
interface RoleRepository : JpaRepository<Role?, Long?> {
    fun findByName(roleName: RoleName?): Role?
}