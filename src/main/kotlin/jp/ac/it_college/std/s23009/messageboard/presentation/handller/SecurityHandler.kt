package jp.ac.it_college.std.s23009.messageboard.presentation.handler
import org.springframework.security.core.Authentication


import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.AuthenticationEntryPoint
import org.springframework.security.web.authentication.AuthenticationFailureHandler
import org.springframework.security.web.authentication.AuthenticationSuccessHandler
import org.springframework.stereotype.Component

@Component
class SecurityHandler : AuthenticationEntryPoint, AuthenticationSuccessHandler, AuthenticationFailureHandler {
    override fun commence(request: HttpServletRequest, response: HttpServletResponse, authException: AuthenticationException) {
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, authException.message)
    }

    override fun onAuthenticationSuccess(request: HttpServletRequest, response: HttpServletResponse, authentication: Authentication) {
        response.writer.write("Authentication successful")
        response.writer.flush()
    }

    override fun onAuthenticationFailure(request: HttpServletRequest, response: HttpServletResponse, exception: AuthenticationException) {
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Authentication failed")
    }
}
