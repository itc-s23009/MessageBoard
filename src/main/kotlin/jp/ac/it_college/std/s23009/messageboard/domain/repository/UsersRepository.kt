package jp.ac.it_college.std.s23009.messageboard.domain.repository

import jp.ac.it_college.std.s23009.messageboard.domain.model.Users

interface UsersRepository {
    fun findByEmail(email: String): Users?

    fun findById(id: Long): Users?
}