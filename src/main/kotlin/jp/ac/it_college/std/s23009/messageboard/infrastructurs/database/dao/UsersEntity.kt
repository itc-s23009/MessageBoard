package jp.ac.it_college.std.s23009.messageboard.infrastructurs.database.dao

import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class UsersEntity(id: EntityID<Long>) : LongEntity(id) {
    companion object : LongEntityClass<UsersEntity>(UsersTable)

    val viewName by UsersTable.viewName
    val email by UsersTable.email
    val password by UsersTable.password
}