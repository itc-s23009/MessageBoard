package jp.ac.it_college.std.s23009.messageboard.domain.repository

import jp.ac.it_college.std.s23009.messageboard.domain.model.Users

interface UsersRepository {
    fun findById(id: Long): Users?
    fun findByEmail(email: String): Users?
    fun createUser(user: Users): Users
    fun updateUser(user: Users): Users
    fun deleteUser(id: Long)
}
