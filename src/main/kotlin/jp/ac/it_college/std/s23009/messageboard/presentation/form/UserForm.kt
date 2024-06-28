package jp.ac.it_college.std.nakasone.message.board.presentation.form

import kotlinx.serialization.Serializable

@Serializable
data class PostUserRegisterRequest(
    val viewName: String,
    val email: String,
    val password: String
)

@Serializable
data class GetUserInfoResponse(
    val id: Long,
    val viewName: String,
)

@Serializable
data class UserLoginRequest(
    val email: String,
    val password: String
)
