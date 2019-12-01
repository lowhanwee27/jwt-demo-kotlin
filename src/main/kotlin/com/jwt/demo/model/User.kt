package com.jwt.demo.model

import org.hibernate.annotations.NaturalId
import java.util.*
import javax.persistence.*
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

/**
 * Created by rajeevkumarsingh on 01/08/17.
 */
@Entity
@Table(name = "users", uniqueConstraints = [UniqueConstraint(columnNames = ["username"]), UniqueConstraint(columnNames = ["email"])])
class User : DateAudit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    var keyId: Long? = 0
    var name: @NotBlank @Size(max = 40) String? = null
    var username: @NotBlank @Size(max = 15) String? = null
    @NaturalId
    var email: @NotBlank @Size(max = 40) @Email String? = null
    var password: @NotBlank @Size(max = 100) String? = null

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_roles", joinColumns = [JoinColumn(name = "user_id")], inverseJoinColumns = [JoinColumn(name = "role_id")])
    var roles: Set<Role> = HashSet<Role>()

    constructor() {}
    constructor(name: String?, username: String?, email: String?, password: String?, keyId: Long?) {
        this.name = name
        this.username = username
        this.email = email
        this.password = password
        this.keyId = keyId
    }

}