package jp.ac.it_college.std.s23009.messageboard.presentation.form

data class UserRegisterRequest(
    val email: String,
    val password: String,
    val viewName: String
)

data class UserLoginRequest(
    val email: String,
    val password: String
)
