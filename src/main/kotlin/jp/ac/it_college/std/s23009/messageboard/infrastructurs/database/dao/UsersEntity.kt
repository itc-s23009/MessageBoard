package jp.ac.it_college.std.s23009.messageboard.infrastructurs.database.dao

import jp.ac.it_college.std.s23009.messageboard.domain.model.Users
import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class UsersEntity(id: EntityID<Long>) : LongEntity(id) {
    companion object : LongEntityClass<UsersEntity>(UsersTable)

    var viewName by UsersTable.viewName
    var email by UsersTable.email
    var password by UsersTable.password

    fun toUser(): Users {
        return Users(
            id.value,
            viewName,
            email,
            password
        )
    }
}