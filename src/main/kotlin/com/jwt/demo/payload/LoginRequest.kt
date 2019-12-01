package com.jwt.demo.payload

import javax.validation.constraints.NotBlank

/**
 * Created by rajeevkumarsingh on 02/08/17.
 */
class LoginRequest {
    var usernameOrEmail: @NotBlank String? = null
    var password: @NotBlank String? = null

}