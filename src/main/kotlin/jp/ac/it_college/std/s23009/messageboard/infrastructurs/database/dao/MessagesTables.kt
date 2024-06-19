package jp.ac.it_college.std.s23009.messageboard.infrastructurs.database.dao


import org.jetbrains.exposed.dao.id.LongIdTable
import org.jetbrains.exposed.sql.kotlin.datetime.datetime

object MessagesTables : LongIdTable("message") {
    val thread = reference("thread_id", ThreadsTable)
    val user = reference("user_id", UsersTable)
    val message = text("message")
    val postedAt = datetime("posted_at")
    val updatedAt = datetime("updated_at")
    val deleted = bool("deleted").default(false)
}
