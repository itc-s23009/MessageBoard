package jp.ac.it_college.std.s23009.messageboard.infrastructurs.database.repository

import jp.ac.it_college.std.s23009.messageboard.domain.model.Users
import jp.ac.it_college.std.s23009.messageboard.domain.repository.UsersRepository
import jp.ac.it_college.std.s23009.messageboard.infrastructurs.database.dao.UsersEntity
import jp.ac.it_college.std.s23009.messageboard.infrastructurs.database.dao.UsersTable
import org.jetbrains.exposed.sql.transactions.transaction
import org.springframework.stereotype.Repository

@Repository
class UsersRepositoryImpl : UsersRepository {

    override fun findById(id: Long): Users? {
        return transaction {
            val userEntity = UsersEntity.findById(id)
            userEntity?.toUser()
        }
    }

    override fun findByEmail(email: String): Users? {
        return transaction {
            val userEntity = UsersEntity.find { UsersTable.email eq email }.firstOrNull()
            userEntity?.toUser()
        }
    }

    override fun createUser(user: Users): Users {
        return transaction {
            val newUserEntity = UsersEntity.new {
                this.viewName = user.viewName
                this.email = user.email
                this.password = user.password // 通常はパスワードのハッシュ化が必要
            }
            newUserEntity.toUser()
        }
    }

    override fun updateUser(user: Users): Users {
        return transaction {
            val userEntity = UsersEntity.findById(user.id)
                ?: throw IllegalArgumentException("User not found with id: ${user.id}")

            userEntity.apply {
                viewName = user.viewName
                email = user.email
                // パスワードの更新などがあれば適宜追加する
            }
            userEntity.toUser()
        }
    }

    override fun deleteUser(id: Long) {
        transaction {
            UsersEntity.findById(id)?.delete()
        }
    }
}
