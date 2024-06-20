package jp.ac.it_college.std.s23009.messageboard.infrastructurs.database.repository

import jp.ac.it_college.std.s23009.messageboard.domain.model.Threads
import jp.ac.it_college.std.s23009.messageboard.domain.repository.ThreadsRepository
import jp.ac.it_college.std.s23009.messageboard.infrastructurs.database.dao.ThreadsEntity
import jp.ac.it_college.std.s23009.messageboard.infrastructurs.database.dao.UsersEntity
import org.jetbrains.exposed.sql.transactions.transaction
import org.springframework.stereotype.Repository

@Repository
class ThreadsRepositoryImpl : ThreadsRepository {
    override fun createThread(thread: Threads): Threads {
        return transaction {
            val newThread = ThreadsEntity.new {
                title = thread.title
                userId = UsersEntity[thread.userId]
                createdAt = thread.createdAt
                updatedAt = thread.updatedAt
                deleted = thread.delete
            }
            newThread.toThread()
        }
    }

    override fun getThreadById(id: Long): Threads? {
        return transaction {
            val threadEntity = ThreadsEntity.findById(id)
            threadEntity?.toThread()
        }
    }

    override fun getAllThreads(): List<Threads> {
        return transaction {
            ThreadsEntity.all().map { it.toThread() }
        }
    }

    override fun updateThread(thread: Threads): Threads {
        return transaction {
            val threadEntity = ThreadsEntity.findById(thread.id)
                ?: throw IllegalArgumentException("Threads not found with id: ${thread.id}")

            threadEntity.apply {
                title = thread.title
                userId = UsersEntity[thread.userId]
                createdAt = thread.createdAt
                updatedAt = thread.updatedAt
                deleted = thread.delete
            }
            threadEntity.toThread()
        }
    }

    override fun deleteThread(id: Long) {
        transaction {
            ThreadsEntity.findById(id)?.delete()
        }
    }
}
