package jp.ac.it_college.std.s23009.messageboard.infrastructurs.database.dao

import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class ThreadsEntity(id: EntityID<Long> ) : LongEntity(id) {
    companion object : LongEntityClass<ThreadsEntity>(ThreadsTable)

    val title by ThreadsTable.title
    val user by ThreadsTable.user
    val createdAt by ThreadsTable.createdAt
    val updatedAt by ThreadsTable.updatedAt
    val deleted by ThreadsTable.deleted
}