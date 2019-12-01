package com.jwt.demo.payload

/**
 * Created by rajeevkumarsingh on 19/08/17.
 */
class JwtAuthenticationResponse(var accessToken: String) {
    var tokenType = "Bearer"

}