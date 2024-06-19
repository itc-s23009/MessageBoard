package jp.ac.it_college.std.s23009.messageboard.domain.model

import kotlinx.datetime.LocalDateTime

data class Messages(
    val id: Long,
    val thread: Long,
    val userId: Long,
    val message: String,
    val postedAt: LocalDateTime,
    val updateAt: LocalDateTime,
    val deleted: Boolean
)