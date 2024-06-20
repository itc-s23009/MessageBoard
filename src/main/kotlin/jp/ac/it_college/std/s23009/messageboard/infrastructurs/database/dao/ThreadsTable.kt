package jp.ac.it_college.std.s23009.messageboard.infrastructurs.database.dao


import org.jetbrains.exposed.dao.id.LongIdTable
import org.jetbrains.exposed.sql.kotlin.datetime.datetime

object ThreadsTable : LongIdTable("thread") {
    val title = varchar("title", 256)
    val userId = reference("user_id", UsersTable)
    val createdAt = datetime("created_at")
    val updatedAt = datetime("updated_at")
    val deleted = bool("deleted").default(false)
}
