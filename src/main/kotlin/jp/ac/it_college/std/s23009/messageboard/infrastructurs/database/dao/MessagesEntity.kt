package jp.ac.it_college.std.s23009.messageboard.infrastructurs.database.dao

import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class MessagesEntity(id: EntityID<Long> ) : LongEntity(id) {
    companion object : LongEntityClass<MessagesEntity>(MessagesTables)

    val threadId by MessagesTables.thread
    val userId by MessagesTables.user
    val message by MessagesTables.message
    val postedAt by MessagesTables.postedAt
    val updatedAt by MessagesTables.updatedAt
    val deleted by MessagesTables.deleted
}