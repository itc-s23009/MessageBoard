package jp.ac.it_college.std.s23009.messageboard.presentation.form

data class UserRegisterRequest(
    val viewName: String,
    val email: String,
    val password: String
)

data class UserLoginRequest(
    val email: String,
    val password: String
)
