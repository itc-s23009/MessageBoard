package jp.ac.it_college.std.s23009.messageboard.infrastructurs.database.dao

import jp.ac.it_college.std.s23009.messageboard.domain.model.Threads
import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class ThreadsEntity(id: EntityID<Long> ) : LongEntity(id) {
    companion object : LongEntityClass<ThreadsEntity>(ThreadsTable)

    var title by ThreadsTable.title
    var userId by UsersEntity referencedOn ThreadsTable.userId
    var createdAt by ThreadsTable.createdAt
    var updatedAt by ThreadsTable.updatedAt
    var deleted by ThreadsTable.deleted

    fun toThread(): Threads{
        return Threads(
            id.value,
            title,
            userId.id.value,
            createdAt,
            updatedAt,
            deleted
        )
    }
}