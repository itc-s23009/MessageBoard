package jp.ac.it_college.std.s23009.messageboard.infrastructurs.database.repository

import jp.ac.it_college.std.s23009.messageboard.domain.model.Users
import jp.ac.it_college.std.s23009.messageboard.domain.repository.UsersRepository
import jp.ac.it_college.std.s23009.messageboard.infrastructurs.database.dao.UsersEntity
import jp.ac.it_college.std.s23009.messageboard.infrastructurs.database.dao.UsersTable
import org.jetbrains.exposed.sql.transactions.transaction
import org.springframework.stereotype.Repository

@Repository
class UsersRepositoryImpl : UsersRepository {
    override fun findByEmail(email: String): Users? {
        return transaction {
            UsersEntity.find {
                UsersTable.email eq email
            }.singleOrNull()?.toUser()
        }
    }

    override fun findById(id: Long): Users? {
        return transaction {
            UsersEntity.findById(id)?.toUser()
        }
    }

    override fun createUser(user: Users): Users {
        return transaction {
            val newUsersEntity = UsersEntity.new {
                this.viewName = user.viewName
                this.email = user.email
                this.password = user.password
            }
            newUsersEntity.toUser()
        }
    }

    override fun updateUser(user: Users): Users {
        return transaction {
            val usersEntity = UsersEntity.findById(user.id)
                ?: throw IllegalArgumentException("User not found with Id")

            usersEntity.apply {
                viewName = user.viewName
                email = user.email
            }
            usersEntity.toUser()
        }
    }

    override fun deleteUser(id: Long) {
        transaction {
            UsersEntity.findById(id)?.delete()
        }
    }
}
