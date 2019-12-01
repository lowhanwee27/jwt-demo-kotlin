package com.jwt.demo.payload

import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

/**
 * Created by rajeevkumarsingh on 02/08/17.
 */
class SignUpRequest {
    var name: @NotBlank @Size(min = 4, max = 40) String? = null
    var username: @NotBlank @Size(min = 3, max = 15) String? = null
    var email: @NotBlank @Size(max = 40) @Email String? = null
    var password: @NotBlank @Size(min = 6, max = 20) String? = null

}