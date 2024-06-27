package jp.ac.it_college.std.s23009.messageboard.presentation.controller

import jp.ac.it_college.std.s23009.messageboard.application.service.UserService
import jp.ac.it_college.std.s23009.messageboard.presentation.form.UserRegisterRequest
import jp.ac.it_college.std.s23009.messageboard.presentation.form.UserLoginRequest
import org.springframework.http.HttpStatus
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/users")
class UserController(
    private val userService: UserService,
    private val authenticationManager: AuthenticationManager
) {

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    fun register(@RequestBody request: UserRegisterRequest) {
        println("Registering user: ${request.email}")
        userService.register(request.viewName, request.email, request.password)
    }

    @PostMapping("/login")
    fun login(@RequestBody request: UserLoginRequest): Authentication {
        println("Attempting to authenticate user: ${request.email}")
        val authentication = authenticationManager.authenticate(
            UsernamePasswordAuthenticationToken(request.email, request.password)
        )
        SecurityContextHolder.getContext().authentication = authentication
        println("User authenticated: ${request.email}")
        return authentication
    }

    @DeleteMapping("/delete/{users_id}")
    fun userDelete(@PathVariable users_id: Long) {
        userService.delete(users_id)
    }
}
