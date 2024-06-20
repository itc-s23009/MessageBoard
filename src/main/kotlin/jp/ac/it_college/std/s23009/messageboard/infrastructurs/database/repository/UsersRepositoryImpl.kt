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
}
