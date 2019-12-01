package com.jwt.demo.controller

import com.jwt.demo.exception.AppException
import com.jwt.demo.model.Role
import com.jwt.demo.model.RoleName
import com.jwt.demo.model.User
import com.jwt.demo.payload.ApiResponse
import com.jwt.demo.payload.JwtAuthenticationResponse
import com.jwt.demo.payload.LoginRequest
import com.jwt.demo.payload.SignUpRequest
import com.jwt.demo.repository.RoleRepository
import com.jwt.demo.repository.UserRepository
import com.jwt.demo.security.JwtTokenProvider
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import java.util.function.Supplier
import javax.validation.Valid

/**
 * Created by rajeevkumarsingh on 02/08/17.
 */
@RestController
@RequestMapping("/api/auth")
class AuthController {
    @Autowired
    var authenticationManager: AuthenticationManager? = null
    @Autowired
    var userRepository: UserRepository? = null
    @Autowired
    var roleRepository: RoleRepository? = null
    @Autowired
    var passwordEncoder: PasswordEncoder? = null
    @Autowired
    var tokenProvider: JwtTokenProvider? = null

    @PostMapping("/signin")
    fun authenticateUser(@RequestBody loginRequest: @Valid LoginRequest?): ResponseEntity<*> {
        val authentication = authenticationManager!!.authenticate(
                UsernamePasswordAuthenticationToken(
                        loginRequest!!.usernameOrEmail,
                        loginRequest!!.password
                )
        )
        SecurityContextHolder.getContext().authentication = authentication
        val jwt = tokenProvider!!.generateToken(authentication)
        return ResponseEntity.ok<Any>(JwtAuthenticationResponse(jwt))
    }

    @PostMapping("/signup")
    fun registerUser(@RequestBody signUpRequest: @Valid SignUpRequest?): ResponseEntity<*> {
        if (userRepository!!.existsByUsername(signUpRequest!!.username)!!) {
            return ResponseEntity<Any?>(ApiResponse(false, "Username is already taken!"),
                    HttpStatus.BAD_REQUEST)
        }
        if (userRepository!!.existsByEmail(signUpRequest!!.email)!!) {
            return ResponseEntity<Any?>(ApiResponse(false, "Email Address already in use!"),
                    HttpStatus.BAD_REQUEST)
        }

        var aNumber = 0
        aNumber = (Math.random() * 9000000 + 1000000).toInt()
        // Creating user's account
        val user = User(signUpRequest.name, signUpRequest.username,
                signUpRequest.email, signUpRequest.password,aNumber.toLong())
        user.password = passwordEncoder!!.encode(user.password);

        val userRole: Role = roleRepository!!.findByName(RoleName.ROLE_USER) ?: throw AppException("User Role not set.")

        user.roles = setOf(userRole)
        val result: User = userRepository!!.save(user)
        val location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/users/{username}")
                .buildAndExpand(result.username).toUri()
        return ResponseEntity.created(location).body<Any>(ApiResponse(true, "User registered successfully"))
    }
}