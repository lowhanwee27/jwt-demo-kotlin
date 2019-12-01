package com.jwt.demo.model

import org.hibernate.annotations.NaturalId
import javax.persistence.*

/**
 * Created by rajeevkumarsingh on 01/08/17.
 */
@Entity
@Table(name = "roles")
class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
    @Enumerated(EnumType.STRING)
    @NaturalId
    @Column(length = 60)
    var name: RoleName? = null

    constructor() {}
    constructor(name: RoleName?) {
        this.name = name
    }

}